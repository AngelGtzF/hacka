package mx.itesm.testing.otherExamples.hackaton;
import mx.itesm.testing.util.drivers.EnhancedWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.random;

public class InaiCrawler {

    public static void SearchTerm(EnhancedWebDriver driver, String searchTerm) throws InterruptedException {
        //ingresa al inai
        driver.get("https://consultapublicamx.inai.org.mx/vut-web/faces/view/consultaPublica.xhtml#sujetosObligados");
        WebElement state = driver.findDynamicElement(By.className("dropdown-toggle"));
        changeState(driver,state,searchTerm);

        state = driver.findDynamicElement(By.className("dropdown-toggle"));
        Thread.sleep(4000);


    }
    //espera que se desloque la pantalla
    private static void changeState(EnhancedWebDriver driver, WebElement state, String searchTerm) throws InterruptedException {
        String[] pregunta = {"¿Sabes cuantos ingresos declarado que tuvo el partido de morena en este año? \n"
                +", No tuvo ingreso \n"+", 3 millones \n"+ ", 500 mil \n",
                "¿Sabes cuánto es el informe de ingresos del DIF (agosto)? \n"  + ", $200,000 \n" + ", No lo sé \n" + ", No presenta informe",
                "¿Cuántas convocatorias crees que se expidieron en el presente año para la mujer en el sector público? \n"+
                ", De 1 a 10 \n" + ", De 11 a 20 \n" + ", De 21 a 30 \n" + ", Más de 30"};
        if (state != null) {
            state.click();
            WebElement temp = driver.switchTo().activeElement();
            for (int i = 0; i < 18; i++) temp.sendKeys(Keys.ARROW_DOWN);
            temp.sendKeys(Keys.ENTER);
            driver.findDynamicElement(By.className("capaBloqueaPantalla"));
            driver.waitForElementToDisappear(By.className("capaBloqueaPantalla"), 30, 500);
            Integer numero = (int) (random() * 14);

            //elegir de un abc
            WebElement listVal = driver.findDynamicElement(By.id("formListaSujetosAZ:listaAZSujetos"));
            listVal.click();
            temp = driver.switchTo().activeElement();
            if (listVal != null) {
                for (int i = 0; i < numero; i++) temp.sendKeys(Keys.ARROW_DOWN);
                temp.sendKeys(Keys.ENTER);
                temp.click();
                driver.findDynamicElement(By.className("capaBloqueaPantalla"));
                driver.waitForElementToDisappear(By.className("capaBloqueaPantalla"), 30, 500);
            }

            numero = (int) (random() * 43);

            List<WebElement> cards = driver.findDynamicElements(By.className("tituloTarjetaObligacion"));
            if (cards != null) {
                System.out.println(cards.size());

                driver.clickLink(cards.get(numero));
            }

            Thread.sleep(1000);
            FileWriter flwriter = null;
            try {
                flwriter = new FileWriter("D:\\UNI\\7 semestre\\Hack\\preguntas.txt");
                BufferedWriter bfwriter = new BufferedWriter(flwriter);

                numero = (int) (random() * 3);
                bfwriter.write(pregunta[numero]+"\n");
                
                //cierra el buffer intermedio
                bfwriter.close();
                System.out.println("Archivo creado satisfactoriamente..");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (flwriter != null) {
                    try {//cierra el flujo principal
                        flwriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }

    }
}

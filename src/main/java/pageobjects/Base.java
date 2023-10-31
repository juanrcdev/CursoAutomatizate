package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;


/*
ESTA CLASE SERVIRÍA PARA ENCAPSULAR LOS METODOS QUE UTILIZA SELENIUM PARA INTERACTURAR CON LOS ELEMENTOS WEB.
DE ESTA FORMA EN CASO DE QUE CAMBIE ALGO EN LA API DE SELENIUM NO TENDRAIAMOS QUE MODIFICAR TODOS LOS PAGEOBJECTS.
LOS PAGE OBJECTS HEREDARAN DE ESTA CLASE.
*/

public class Base {
    WebDriver driver;
    WebDriverWait wait;

    public Base(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //Para encapsular el metodo click de Selenium añadiendole esperas explicitas
    public void hacerClick(WebElement elemento){
        wait.until(ExpectedConditions.visibilityOf(elemento));
        wait.until(ExpectedConditions.elementToBeClickable(elemento)).click();
    }

    /*
    Para encapsular el metodo sendKeys de Selenium añadiendole espera explicitas.
    Le pasamos por parametros el webelement en el que queremos introducir el texto asi como el texto
     */
    public void introducirTexto(WebElement elemento, String texto){
        wait.until(ExpectedConditions.visibilityOf(elemento)).sendKeys(texto);
    }

    /*
    Para encapsular el metodo findElementBy añadiendo a la vez una espera explicita
    pasandole por parametros el localizador.
     */
    public WebElement buscarElemento(By by){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    /*
    Para encapsular uno de los metodos que usa selenium para capturar una lista de elementos,
    añadiendo a su vez una espera explicita
     */
    public List<WebElement> capturarListaElementos(By localizador){
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(localizador));
    }

    //Para encapsular el metodo que usa Selenium para comprobar si un checkbox esta marcado o no
    public Boolean comprobarCheckBoxActivado(WebElement element){
        return element.isSelected();
    }

}

package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Googlepage extends Base {

    public Googlepage(WebDriver driver){
        super(driver);
    }

    //Localizador del searchbox de Google
    By buscadorLocator = By.name("q");
    //Localizador del boton de aceptar las cookies
    By cookiesLocator = By.id("L2AGLb");
    //Localizador de los productos de amazon que aparecen en el carrusel de productos al hacer la busqueda
    By enlaceAmazonLocator = By.xpath("//div[@data-dtld='amazon.es']");

    public void aceptarCookies(){
        WebElement elemento = buscarElemento(cookiesLocator);
        hacerClick(elemento);
    }

    public void realizarBusqueda(String busqueda){
        WebElement element = buscarElemento(buscadorLocator);
        introducirTexto(element,busqueda);
        element.submit();
    }

    public void accederProductosAmazon(){
        WebElement element = buscarElemento(enlaceAmazonLocator);
        hacerClick(element);
    }
}

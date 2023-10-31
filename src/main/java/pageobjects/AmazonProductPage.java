package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AmazonProductPage extends Base{

    public AmazonProductPage(WebDriver driver){
        super(driver);
    }

    //Localizadores
    By precioLocator = By.xpath("//*[@id='corePrice_feature_div']//span[@class='a-offscreen']//parent::span[contains(@class,'a-price')]");
    By fechaLocator = By.xpath("//div[@id='deliveryBlockMessage']//a//parent::span//span");
    By acceptCookiesLocator = By.xpath("//input[@name='accept']");
    By busquedaLocator = By.id("twotabsearchtextbox");

    public void imprimirPrecioProducto(){
        WebElement element = buscarElemento(precioLocator);
        String texto = element.getText().replace("\n", ",");
        System.out.println("Precio del producto:" + texto);
    }

    public void imprimirFechaEntrega(){
        WebElement element = buscarElemento(fechaLocator);
        System.out.println("Fecha de entrega: "+ element.getText());
    }

    public void aceptarCookies(){
        WebElement element = buscarElemento(acceptCookiesLocator);
        hacerClick(element);
    }

    public void buscarProducto(String texto){
        WebElement element = buscarElemento(busquedaLocator);
        introducirTexto(element,texto);
        element.submit();
    }
}

package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class AmazonMainPage extends Base {

    public AmazonMainPage(WebDriver driver) {
        super(driver);
    }

    //Localizadores
    By resultadoBusquedaNombreLocator = By.xpath("//div[@data-component-type='s-search-result']//h2");
    By resultadoBusquedaPrecioLocator = By.xpath("//div[@data-component-type='s-search-result']//span[@class='a-price-whole']");
    By acceptCookiesLocator = By.xpath("//input[@name='accept']");
    By primeCheckBoxLocator = By.xpath("//li[@Aria-label='Envío gratis por Amazon']");
    By desplegableOrdenacionLocator = By.id("s-result-sort-select");

    //Aceptar las cookies de la pagina principal de Amazon
    public void aceptarCookies(){
        WebElement element = buscarElemento(acceptCookiesLocator);
        hacerClick(element);
    }

    //Activar los envios Gratis por Amazon. Primero capturo el elemento y luego si el mismo no esta activado ya, lo activo.
    public void activarEnvioPrime(){
        WebElement element = buscarElemento(primeCheckBoxLocator);
        if (!comprobarCheckBoxActivado(element)){
            hacerClick(element);
        }
    }

    /*
    Este punto de la tarea no lo supe hacer funcionar, me daba una "Stale Element Reference Exception " al intentar
    capturar el elemento "Select", o sea el desplegable.
    */
    public void ordenarPorPrecioMasBajo(){
        WebElement element = buscarElemento(desplegableOrdenacionLocator);
        hacerClick(element);
        Select select = new Select(element);
        select.selectByValue("price-asc-rank");
    }

    /*
    Capturo todos los webelements que contienen el nombre del producto y el precio. Luego recorro la lista de nombres
    y le añado el precio. (He acortado el nombre del producto para que no sea demasiado largo)
    */
    public void listarProductosNombrePrecio(){
        List<WebElement> listaNombres = capturarListaElementos(resultadoBusquedaNombreLocator);
        List<WebElement> listaPrecios = capturarListaElementos(resultadoBusquedaPrecioLocator);

        for (int i = 0 ; i < listaNombres.size();i++){
            String nombre = listaNombres.get(i).getText();
            String precio = listaPrecios.get(i).getText() + " €";

            if (nombre.length() > 50){
                nombre = nombre.substring(0,50);
            }

            System.out.println( nombre + " --> " + precio);
        }
    }
}

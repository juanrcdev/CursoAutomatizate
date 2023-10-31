import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobjects.AmazonMainPage;
import pageobjects.AmazonProductPage;
import pageobjects.Googlepage;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class Maintest {

    String URL = "https://www.google.es/";
    String PRODUCTO = "Compresor eléctrico";
    WebDriver driver;

    @Before
    public void setUP(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);
    }

    @Test
    public void test() {
        /*
        En este bloque se inicializa el PageObject de la pagina de Google, se aceptan las cookies, se realiza la busqueda
        del producto y se accede al primer producto del carrusel que se venda en Amazon
         */
        Googlepage googlepage = new Googlepage(driver);
        googlepage.aceptarCookies();
        googlepage.realizarBusqueda(PRODUCTO);
        googlepage.accederProductosAmazon();

        /*
        AQUI TUVE QUE BUSCAR COMO CAMBIAR DE PESTAÑA YA QUE AL HACER CLICK SOBRE EL PRODUCTO DE AMAZON, ESTE SE ABRE
        EN UNA NUEVA PESTAÑA, Y SI NO CAMBIABA A LA NUEVA PESTAÑA NO PODIA INTERACTUAR CON LOS WEBLEMENTS
         */
        ArrayList<String> tabs = new ArrayList<> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        /*
        Se Inicializa el PageObject de la pagina de amazon que se muestra al acceder a un producto concreto. Se aceptan
        las cookies de Amazon, se imprime el precio del producto y la fecha de entrega del mismo. Por ultimo se
        hace la busqueda del producto en el buscador propio de Amazon
         */
        AmazonProductPage amazonProductPage = new AmazonProductPage(driver);
        amazonProductPage.aceptarCookies();
        amazonProductPage.imprimirPrecioProducto();
        amazonProductPage.imprimirFechaEntrega();
        amazonProductPage.buscarProducto(PRODUCTO);

        /*
        Se inicializa el PageObject correspondiente a la pagina principal de Amazon, que en este punto muestra la
        lista de productos resultado de la busqueda anterior en el buscador propio de Amazon. Se activa el Prime
         */
        AmazonMainPage amazonMainPage = new AmazonMainPage(driver);
        amazonMainPage.activarEnvioPrime();
        /*
           EL SIGUIENTE PUNTO ES EL UNICO QUE NO PUDE LOGRAR DE LA TAREA (Ordenar precio ascendente)
           ME LANZABA UNA EXCEPCION DEL TIPO "Stale Element Reference Exception" al tratar de capturar
           el elemento "Select", o sea, el dropdown list.
        */
        //amazonMainPage.ordenarPorPrecioMasBajo();

        //Por ultimo se lista el nombre y precio de cada producto
        amazonMainPage.listarProductosNombrePrecio();

        /*
        Esto solo lo puse por si en caso de ejecutar el test quisieras ver el estado final de la navegación y que no
        se cierre el navegador demasiado rapido. Se puede borrar, no afecta para nada al ejercicio.
         */
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @After
    public void shutDown(){
        if(driver != null){
            driver.quit();
        }
    }
}

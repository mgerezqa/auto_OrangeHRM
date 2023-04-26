package pruebas;

import org.testng.annotations.Test;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import paginas.PaginaInicio;

public class MainTest {

	String url = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
	WebDriver driver;
	
	@BeforeSuite //rutina para abrir el navegador
	public void abrirNavegador() { //se suele llamar setUp()
		// 1) Configurando el navegador Chrome
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		// 2) Abrir el navegador en la página donde haremos la prueba
		driver.get(url);
//		3)  maximizar el navegador
		driver.manage().window().maximize();
	}
	
	@Test
	public void completarCampos() {
		PaginaInicio newSession = new PaginaInicio(driver);
		
		WebDriverWait myWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		myWait.until(ExpectedConditions.elementToBeClickable(By.name("username")));
		
		newSession.ingresarCredenciales("Admin","admin123");

		
	}
	
	@Test
	public void iniciarSesion() {
		PaginaInicio newSession = new PaginaInicio(driver);
		
//		Agrego demora para empezar a enviar la información a los campos

		WebDriverWait myWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		myWait.until(ExpectedConditions.elementToBeClickable(By.name("username")));

		newSession.ingresarCredenciales("Admin","admin123");

		newSession.hacerClickEnLogin();
		
//		Assert.assertNotNull(url); // Comprueba que el valor no sea nulo

	}
	
 @AfterSuite
	public void cerrarNavegador() throws InterruptedException { //se suele llamar tearDown()
     Thread.sleep(10*1000);//espero 10 segundos antes de cerrar la pagina	
	 driver.close();
		
	}
}



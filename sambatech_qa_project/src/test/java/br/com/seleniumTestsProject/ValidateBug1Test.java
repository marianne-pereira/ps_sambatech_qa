package br.com.seleniumTestsProject;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//Issue 1: Miniatura dos conteúdos não são exibidos
public class ValidateBug1Test {

	private WebDriver driver;
	private SambaVideosPage sambavideospage;
	private WebDriverWait wait;
	
	//tempo máximo de espera nos "wait.until" (segundos)
	private int WAIT_TIME = 5;
	
	@Before
	public void inicializa() {
		//System.setProperty("webdriver.gecko.driver", "D:/Git/geckodriver.exe");
		System.setProperty("webdriver.gecko.driver", "resources/firefoxGeckoDriver/v0.20.0/windows64/geckodriver.exe");
		this.driver = new FirefoxDriver();
		sambavideospage = new SambaVideosPage(driver);
		wait = new WebDriverWait(driver, WAIT_TIME);
	}
	
	@Test
	public void validacaoIssue1() {

		sambavideospage.abrirPagina();
		sambavideospage.login(wait);
		
		//boolean tem = driver.getPageSource().contains("marmota");
		//assertTrue(("Geladeira", 123, "Paulo Henrique", true));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("icon-14-pagination-next-black")));
		
		//Clica na imagem do Koala! Talvez haja uma forma melhor de pegar esse elemento
		driver.findElement(By.partialLinkText("Koala.jpg")).click();

		//Aqui deveria ocorrer a verificação e comparação da imagem padrão de miniatura com a miniatura real!
		
	}
	
	@After
	public void finalizaTeste() {
		driver.close();
	}
}

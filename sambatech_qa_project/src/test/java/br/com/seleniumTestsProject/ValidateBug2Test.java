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

//Issue #3 (porque o 2 é melhoria)
public class ValidateBug2Test {

	private WebDriver driver;
	private SambaVideosPage sambavideospage;
	private WebDriverWait wait;
	
	//tempo máximo de espera nos "wait.until" (segundos)
	private int WAIT_TIME = 15;
	
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

		//aguarda carregar um elemento da tela
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("icon-14-pagination-next-black")));
		
		//Clica no menú conteúdo
		driver.findElement(By.id("mn-content")).click();
		driver.findElement(By.className("downloadRaw")).click();
		
		//verifica se exibe o texto de erro de download
		String bodyText = driver.findElement(By.tagName("body")).getText();
		assertTrue("Download feito com sucesso, não exibiu a mensagem de erro!", bodyText.contains("This XML file does not appear to have any style information associated with it. The document tree is shown below."));
	}
	
	@After
	public void finalizaTeste() {
		driver.close();
	}
}

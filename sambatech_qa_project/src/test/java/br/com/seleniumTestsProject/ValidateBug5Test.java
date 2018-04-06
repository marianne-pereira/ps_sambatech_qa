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

//Issue 7: Critar/Editar conteúdo - Contador do campo [Descrição Longa] inconsistente
public class ValidateBug5Test {

	private WebDriver driver;
	private SambaVideosPage sambavideospage;
	private WebDriverWait wait;
	
	//tempo máximo de espera nos "wait.until" (segundos)
	private int WAIT_TIME = 5;
	private String textoDescricao;
	
	@Before
	public void inicializa() {
		//System.setProperty("webdriver.gecko.driver", "D:/Git/geckodriver.exe");
		System.setProperty("webdriver.gecko.driver", "resources/firefoxGeckoDriver/v0.20.0/windows64/geckodriver.exe");
		this.driver = new FirefoxDriver();
		sambavideospage = new SambaVideosPage(driver);
		wait = new WebDriverWait(driver, WAIT_TIME);
		textoDescricao = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
				+ "Etiam sollicitudin iaculis turpis. Phasellus nunc turpis, mollis id orci nec, porttitor sodales risus. Proin ultrices, quam sit amet posuere euismod, erat risus tincidunt ex, et porta justo risus quis diam. "
				+ "Donec diam mauris, volutpat et felis ut, maximus bibendum libero. Etiam id volutpat erat. In fermentum, libero non sodales consequat, est metus bibendum massa, "
				+ "eget egestas nisl quam eget lectus. Maecenas non iaculis erat. "
				+ "Sed laoreet porttitor libero ac eleifend. Nunc quis vulputate sapien. Aliquam erat volutpat. Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
				+ "Fusce dignissim ipsum sit amet ligula vestibulum viverra. Praesent porta pellentesque ultrices. Aliquam in sem nec nulla hendrerit vulputate.Nunc finibus massa est, at tristique erat sagittis nec. "
				+ "Sed ultrices pellentesque ex, non tincidunt urna lobortis sed. Cras leo ex, rhoncus non dignissim volutpat, condimentum quis elit. Integer blandit eget orci sed amet.";
	}
	
	@Test
	public void validacaoIssue1() {

		sambavideospage.abrirPagina();
		sambavideospage.login(wait);
		
		//boolean tem = driver.getPageSource().contains("marmota");
		//assertTrue(("Geladeira", 123, "Paulo Henrique", true));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("icon-14-pagination-next-black")));
		
		driver.findElement(By.id("mn-content")).click();
		
		//Clica na imagem do Koala! Talvez haja uma forma melhor de pegar esse elemento
		driver.findElement(By.partialLinkText("Koala.jpg")).click();

		WebElement descricaolongatext = driver.findElement(By.id("description"));
		descricaolongatext.sendKeys(textoDescricao);
		
		WebElement contador = driver.findElement(By.className("character-count muted label label-important"));
		assertTrue("Contador está diferente de 1000, numero de caracteresdo texto digitado", (contador.getText() != "1000"));
		
	}
	
	@After
	public void finalizaTeste() {
		driver.close();
	}
}

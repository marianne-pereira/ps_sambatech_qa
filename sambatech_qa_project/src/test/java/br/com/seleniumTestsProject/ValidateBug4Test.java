package br.com.seleniumTestsProject;

import java.sql.Date;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//Issue 6: 

/*Criar/editar campanha - Estoura erro na tela ao tentar modificar [hora fim] para antes do horário atual
 * 
 * */
public class ValidateBug4Test {

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
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("icon-14-pagination-next-black")));
		
				
		//Acessa o menu monetização.
		driver.findElement(By.id("mn-monetization")).click();
			
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("create-campaign")));
		driver.findElement(By.id("create-campaign")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("create-campaign")));
		
		//mapeamento de campos obrigatórios
		WebElement nomeCampanha = driver.findElement(By.name("name"));
		WebElement url = driver.findElement(By.name("urlClickThrough"));
		WebElement meta = driver.findElement(By.name("target"));
		WebElement cpm = driver.findElement(By.name("grossValue"));
		WebElement dtinicio = driver.findElement(By.name("start"));
		WebElement dtfim = driver.findElement(By.name("end"));
		WebElement hrinicio = driver.findElement(By.name("startTime"));
		WebElement hrfim = driver.findElement(By.name("endTime"));
		/*	WebElement campoEmail = driver.findElement(By.name(""));
		WebElement campoEmail = driver.findElement(By.name(""));
		WebElement campoEmail = driver.findElement(By.name(""));
		WebElement campoEmail = driver.findElement(By.name(""));
		
		WebElement campo =*/
		GregorianCalendar c = new GregorianCalendar();
		//preenchimento dos campos obrigatórios
		nomeCampanha.sendKeys("Campanha de teste");
		meta.sendKeys("50");
		url.sendKeys("www.google.com.br");
		cpm.sendKeys("50");
		dtinicio.sendKeys("01/04/2018");
		dtfim.sendKeys(""+c.get(GregorianCalendar.DAY_OF_MONTH) +"/" + c.get(GregorianCalendar.MONTH) + "/" + c.get(GregorianCalendar.YEAR));
	
		//como fazer seleçao em campo select?
		
		//hrfim.S
		
		//Falta selecionar corretamente sempre 00:00 e clicarem salvar para verificar amsg de erro
		//Aqui deveria ocorrer a verificação e comparação da imagem padrão de miniatura com a miniatura real!
		
	}
	
	@After
	public void finalizaTeste() {
		driver.close();
	}
}

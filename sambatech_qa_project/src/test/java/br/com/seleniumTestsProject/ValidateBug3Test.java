package br.com.seleniumTestsProject;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


//Issue 5: Criar/Editar conteúdo - Mensagem de validação com texto ambíguo

/*Ao preencher o campo [descrição longa] com tamanho além do permitido e clicar em salvar, sistema exibe a mensagem "A descrição informada é longa demais. Reduza o texto e tente novamente." Porém, o termo 'descrição' deixa ambígua a mensagem pois existem dois campos que podem estar sendo referidos: [descrição curta] e [descrição longa].

Modificar a mensagem para que seja definido qual campo está sendo validado.
 * 
 * */
public class ValidateBug3Test {

	private WebDriver driver;
	private SambaVideosPage sambavideospage;
	private WebDriverWait wait;
	
	//tempo máximo de espera nos "wait.until" (segundos)
	private int WAIT_TIME = 5;
	
	@Before
	public void inicializa() {
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
		driver.findElement(By.id("mn-content")).click();
		
		
		//para simular esse cenário seria necessário enviar arquivo.. encontrei esse código porém serianecessário adaptá-lo.
		/*
		WebElement arquivo1 = driver.findElement(By.id("fcArquivo"));
	    File file = new File("");
	    arquivo1.sendKeys(file.getAbsolutePath());
	    
		/*
		 campoEmail.sendKeys("aXXX");
	        campoSenha.sendKeys("123456789");
	        campoEmail.submit();
	      */  
		
	}
	
	@After
	public void finalizaTeste() {
		driver.close();
	}
}

package br.com.seleniumTestsProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class SambaVideosPage {

    private WebDriver driver;

    public SambaVideosPage(WebDriver driver) {
        this.driver = driver;
    }

    public void abrirPagina() {
        driver.get("http://web1.qa.sambatech.com:10000/");
    }
    
    public void login(WebDriverWait wait) {
    	//aguarda exibição do campo e-mail.
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("plax-cloud-front")));
		
		WebElement campoEmail = driver.findElement(By.id("inputEmail"));
        WebElement campoSenha = driver.findElement(By.id("inputPassword"));
        
        campoEmail.sendKeys("avaliacao_qa_samba@sambatech.com.br");
        campoSenha.sendKeys("123456789");
        campoEmail.submit();
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mn-dashboard")));
    }
}

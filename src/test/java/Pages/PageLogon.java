package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import Helpers.Helpers;

public class PageLogon {
	private WebDriver driver;
	private By titleText;

	public PageLogon(WebDriver driver) {
		this.driver = driver;

		this.driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[2]/td/table/tbody/tr/td[1]")).click(); //SIGN-ON

		Helpers limpiar = new Helpers();
		limpiar.clear(driver);

		titleText = By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[3]/td/p/font/b"); //Welcome back to
	}

	public void assertLogonPage() {

		//String validar = driver.findElement(titleText).getText();
		Assert.assertTrue(driver.findElement(titleText).getText().contains("Welcome back to"));
	}
}

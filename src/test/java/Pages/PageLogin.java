package Pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
//import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class PageLogin {
	private WebDriver driver;
	private By userField;
	private By passwordField;
	private By loginButton;
	private By fields;

	public PageLogin(WebDriver driver) {
		this.driver = driver;
		userField = By.name("userName");
		passwordField = By.name("password");
		loginButton = By.name("submit");
		fields = By.tagName("input");

	}

	public void Login(String user, String pass) {
		this.driver.findElement(userField).sendKeys(user);
		this.driver.findElement(passwordField).sendKeys(pass);
		this.driver.findElement(loginButton).click();
		
		/*File myScreenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(myScreenshot, new File("LOGIN "+System.currentTimeMillis()+".png"));
		}catch (IOException e) {
			e.printStackTrace();
		}*/
		
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		//Helpers helper = new Helpers();
		//helper.sleepSeconds(4);
	}
	
	public void fields_login(String user, String pass) {
		List<WebElement> loginFields = driver.findElements(fields);
		loginFields.get(1).sendKeys(user);
		loginFields.get(2).sendKeys(pass);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	public void verifyFields() {
		List<WebElement> loginFields = driver.findElements(fields);
		System.out.println(loginFields.size());
		Assert.assertTrue(loginFields.size()==4);
	}

}

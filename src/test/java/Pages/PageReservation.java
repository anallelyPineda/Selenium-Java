package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Helpers.Helpers;

public class PageReservation {
	private WebDriver driver;
	private By titleText;
	private By passengerDrop;
	private By fromDrop;
	private By toDrop;

	public PageReservation(WebDriver driver) {
		this.driver = driver;
		this.driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[1]/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]")).click();

		//Helpers limpiar = new Helpers();
		//limpiar.clear(driver);

		titleText = By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[3]/td/font");
		passengerDrop = By.name("passCount");
		fromDrop = By.name("fromPort");
		toDrop = By.name("toPort");

	}

	public void assertPage() {
		
		Assert.assertTrue(driver.findElement(titleText).getText().contains("Use our Flight Finder to search for the lowest fare on participating airlines. Once you've booked your flight, don't forget to visit the Mercury Tours Hotel Finder to reserve lodging in your destination city."));
	}

	public void selectPassenger(int cant) {
		//WebDriverWait wait = new WebDriverWait(driver, 10);
		//WebElement cantidadPasajeros = wait.until(ExpectedConditions.presenceOfElementLocated(passengerDrop));
		//Select selectPasajeros = new Select(cantidadPasajeros);
		Select selectPasajeros = new Select(driver.findElement(passengerDrop));
		selectPasajeros.selectByVisibleText(Integer.toString(cant));

	}

	public void selectFromPort(int index) {
		Select selectFrom = new Select(driver.findElement(fromDrop));
		selectFrom.selectByIndex(index);
	}

	public void selectToPort(String city) {
		Select selectTo = new Select(driver.findElement(toDrop));
		selectTo.selectByValue(city);
	}

}

package Helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Helpers {
	private WebDriver driver;

	public void sleepSeconds(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void clear(WebDriver driver) {
		this.driver = driver;
		this.sleepSeconds(2);

		WebElement frame1 = this.driver.findElement(By.id("google_ads_iframe_/24132379/INTERSTITIAL_DemoGuru99_0"));
		this.driver.switchTo().frame(frame1);
		WebElement frame2 = this.driver.findElement(By.id("ad_iframe"));
		this.driver.switchTo().frame(frame2);
		
		WebElement validar = driver.findElement(By.xpath("//*[@id=\"dismiss-button\"]")); 
		if (validar != null) {
			this.driver.findElement(By.xpath("//*[@id=\"dismiss-button\"]")).click();
			this.driver.switchTo().defaultContent();
		}
	}

}

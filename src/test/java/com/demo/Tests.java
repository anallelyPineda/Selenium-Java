package com.demo;

import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import Helpers.Helpers;
import Pages.PageLogin;
import Pages.PageLogon;
import Pages.PageReservation;

import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.maven.shared.utils.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Tests {
	private WebDriver driver;
	ArrayList<String> tabs;
	

	@BeforeMethod
	public void setUp() {
		DesiredCapabilities caps = new DesiredCapabilities();
		System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		//driver.manage().window().maximize();
		//driver.manage().window().fullscreen();
		//driver.manage().window().setSize(new Dimension(800,600));
		//for(int i=0;i<=800;i++)
		//driver.manage().window().setPosition(new Point(i,i));
		driver.navigate().to("https://demo.guru99.com/test/newtours/");
		JavascriptExecutor javaScriptExecutor = (JavascriptExecutor)driver;
		String googleWindow = "window.open('http://ww.google.com')";
		javaScriptExecutor.executeScript(googleWindow);
		
		tabs = new ArrayList<String> (driver.getWindowHandles());
		/* driver.navigate().to("https://demo.guru99.com/selenium/newtours/"); */

		Helpers helper = new Helpers();
		helper.sleepSeconds(5);
	}

	@Test
	public void loginIncorrecto() {
		driver.switchTo().window(tabs.get(1)).navigate().to("https://www.youtube.com");
		driver.switchTo().window(tabs.get(0));
		PageLogin pageLogin = new PageLogin(driver);
		PageLogon pageLogon = new PageLogon(driver);		
		pageLogin.Login("Ana", "test");
		pageLogon.assertLogonPage();
	}

	@Test
	public void login() {
		PageLogin pageLogin = new PageLogin(driver);
		pageLogin.Login("mercury", "mercury");
		PageReservation pageReservation = new PageReservation(driver);
		pageReservation.assertPage();
	}

	@Test
	public void reservation() {
		//WebDriver driverV2 = new ChromeDriver();
		PageLogin pageLogin = new PageLogin(driver);
		pageLogin.Login("mercury", "mercury");
		PageReservation pageReservation = new PageReservation(driver);
		pageReservation.selectPassenger(1);
		pageReservation.selectFromPort(3);
		pageReservation.selectToPort("London");
	}

	@Test
	public void pruebaCantidadDeCampos() {
		PageLogin pageLogin = new PageLogin(driver);
		pageLogin.verifyFields();
	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		if (!result.isSuccess()) {
			File myScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(myScreenshot, new File("ERROR "+ System.currentTimeMillis()+".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		driver.switchTo().window(tabs.get(0)).close();
		driver.switchTo().window(tabs.get(1)).close();
		//driver.close();
	}
}

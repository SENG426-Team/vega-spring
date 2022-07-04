package com.uvic.venus;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class VenusApplicationTests2 {

	WebDriver driver;

	@BeforeAll
    void setUp() {

		String path = System.getProperty("user.dir")+"/src/test/java/com/uvic/venus/chromedriver";

		System.setProperty("webdriver.chrome.driver", path); // TODO: Probably need to use relative path and move chromedriver into project folder

		driver = new ChromeDriver();
		driver.get("http://localhost:3000"); // TODO: Figure out if url needs to be changed for production
	}

	public void logout() {

		driver.get("http://localhost:3000/account");		

		WebElement submitButton = driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[2]/div/div/button"));
		submitButton.click();
	}

	public void adminLogin() throws InterruptedException {

		driver.get("http://localhost:3000/login");

		WebElement usernameInputBox = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div/div/form/div[1]/input"));
		usernameInputBox.sendKeys("admin@venus.com");

		WebElement passwordInputBox = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div/div/form/div[2]/input"));
		passwordInputBox.sendKeys("pass");

		WebElement submitButton = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div/div/form/button"));
		submitButton.click();

		// Wait for page load
		Thread.sleep(1000);
	}

	public void userLogin() throws InterruptedException {

		driver.get("http://localhost:3000/login");

		WebElement usernameInputBox = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div/div/form/div[1]/input"));
		usernameInputBox.sendKeys("testuser@venus.com");

		WebElement passwordInputBox = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div/div/form/div[2]/input"));
		passwordInputBox.sendKeys("pass");

		WebElement submitButton = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div/div/form/button"));
		submitButton.click();

		// Wait for page load
		Thread.sleep(1000);
	}

    @Test
	@Order(1)
	void AdminCanChangeRoletoStaff() throws InterruptedException {

		adminLogin();

		driver.get("http://localhost:3000/platform");

		// wait for page load
		Thread.sleep(5000);

		logout();
	}


	@AfterAll
	void tearDown() throws InterruptedException{
		if(driver!=null){
			driver.close();
			driver.quit();
		}
	}

}

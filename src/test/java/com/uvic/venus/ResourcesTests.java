package com.uvic.venus;
import java.net.*;  

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.Arrays;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

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


// This file includes test cases related to the Admin change role feature

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class ResourcesTests {

	WebDriver driver;

	@BeforeAll
    void setUp() {
		System.setProperty("webdriver.chrome.driver",
		"/Users/isabellarojas/Downloads/chromedriver"); // TODO: Probably need to use relative path and move chromedriver into project folder?
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


	/* Scenario: User can upload a resources */
	@Test
	@Order(1)
	void UploadResource() throws InterruptedException {

		adminLogin();

		driver.get("http://localhost:3000");

		// wait for page load
		Thread.sleep(1000);

		WebElement resourcesPage = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[1]/nav[2]/div/div/a[4]"));
		resourcesPage.click();


        WebElement uploadElement = driver.findElement(By.id("formFile"));
        uploadElement.sendKeys(""); //HERE YOU NEED TO ADD THE ABSOLUTE PATH OF THE FILE

        WebElement submitFile = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[2]/div[1]/div/button"));
		submitFile.click();

		//wait for submit button
		Thread.sleep(1000);
		
		driver.navigate().refresh();

		//wait for refreshing page
		Thread.sleep(2000);

		logout();

	}

	/* Scenario: User cannot Upload a Corrupted Resource */
	@Test
	@Order(2)
	void UploadCorruptedResource() throws InterruptedException {

		adminLogin();

		driver.get("http://localhost:3000");

		// wait for page load
		Thread.sleep(1000);

		WebElement resourcesPage = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[1]/nav[2]/div/div/a[4]"));
		resourcesPage.click();
        
        WebElement uploadElement = driver.findElement(By.id("formFile"));
        uploadElement.sendKeys("/Users/isabellarojas/desktop/cor.pdf");  //HERE YOU NEED TO ADD THE ABSOLUTE PATH OF THE FILE

        WebElement submitFile = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[2]/div[1]/div/button"));
		submitFile.click();

		//wait for submit button
		Thread.sleep(1000);
		
		driver.navigate().refresh();

		//wait for refreshing page
		Thread.sleep(2000);

		logout();

	}

	/* Scenario:  User cannot Upload a Resource of an Unaccepted format */
	@Test
	@Order(3)
	void UnacceptedFormat() throws InterruptedException {
		adminLogin();
	
		driver.get("http://localhost:3000");
	
		// wait for page load
		Thread.sleep(1000);
	
		WebElement resourcesPage = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[1]/nav[2]/div/div/a[4]"));
		resourcesPage.click();
			
		WebElement uploadElement = driver.findElement(By.id("formFile"));
		uploadElement.sendKeys("/Users/isabellarojas/downloads/chromedriver"); //HERE YOU NEED TO ADD THE ABSOLUTE PATH OF THE FILE
	
		WebElement submitFile = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[2]/div[1]/div/button"));
		submitFile.click();
	
		//wait for submit button
		Thread.sleep(1000);
			
		driver.navigate().refresh();
	
		//wait for refreshing page
		Thread.sleep(2000);
	
		logout();
	
	}

	/* 	Scenario: User can View a List of Uploaded Resources*/
	@Test
	@Order(4)
	void ViewListOfResources() throws InterruptedException {
		adminLogin();
	
		driver.get("http://localhost:3000");
	
		// wait for page load
		Thread.sleep(1000);
	
		WebElement resourcesPage = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[1]/nav[2]/div/div/a[4]"));
		resourcesPage.click();
	
		//wait for refreshing page
		Thread.sleep(2000);
	
		logout();
	
	}

	/* 	Scenario: User cannot Access Resources without Sufficient Permissions*/
	@Test
	@Order(5)
	void AccessPermissions() throws InterruptedException {
		userLogin();
	
		driver.get("http://localhost:3000");
	
		//wait for refreshing page
		Thread.sleep(2000);
	
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

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


// This file includes test cases related to the Admin change role feature

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class VenusApplicationTests {

	WebDriver driver;

	@BeforeAll
    void setUp() {
		System.setProperty("webdriver.chrome.driver",
		"//Users/owner/Downloads/chromedriver"); // TODO: Probably need to use relative path and move chromedriver into project folder?
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

	public void staffLogin() throws InterruptedException {

		driver.get("http://localhost:3000/login");

		WebElement usernameInputBox = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div/div/form/div[1]/input"));
		usernameInputBox.sendKeys("jonoliver@venus.com");

		WebElement passwordInputBox = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div/div/form/div[2]/input"));
		passwordInputBox.sendKeys("pass");

		WebElement submitButton = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div/div/form/button"));
		submitButton.click();

		// Wait for page load
		Thread.sleep(1000);
	}

	/* Scenario: Admin wants to change a registered user of type User to type Staff */
	@Test
	@Order(1)
	void AdminCanChangeRoletoStaff() throws InterruptedException {

		adminLogin();

		driver.get("http://localhost:3000/adminpanel");

		// wait for page load
		Thread.sleep(1000);

		WebElement dropDownList = driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[2]/table/tbody/tr[2]/td[5]/select"));
		dropDownList.click();

		WebElement staffListItem = driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[2]/table/tbody/tr[2]/td[5]/select/option[2]"));
		staffListItem.click();

		logout();
	}

	/* Scenario: Admin wants to change a registered user of type Staff to type User */
	@Test
	@Order(2)
	void AdminCanChangeRoletoUser() throws InterruptedException {

		adminLogin();

		driver.get("http://localhost:3000/adminpanel");
		
		Thread.sleep(1000);

		WebElement dropDownList = driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[2]/table/tbody/tr[2]/td[5]/select"));
		dropDownList.click();

		WebElement userListItem = driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[2]/table/tbody/tr[2]/td[5]/select/option[3]"));
		userListItem.click();

		logout();
	}

	/* Scenario: User wants to change another users role */
	@Test
	@Order(3)
	void UserCannotChangeRole() throws InterruptedException {

		userLogin();

		WebElement navBar = driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[1]/nav[2]/div/div"));

		ArrayList<String> navElements = new ArrayList<String>(Arrays.asList(navBar.getText().split("\n")));

		assertFalse(navElements.contains("Admin"));

		logout();
	}

	/* Scenario: Staff wants to change another users role */
	@Test
	@Order(4)
	void StaffCannotChangeRole() throws InterruptedException {

		staffLogin();

		WebElement navBar = driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[1]/nav[2]/div/div"));

		ArrayList<String> navElements = new ArrayList<String>(Arrays.asList(navBar.getText().split("\n")));

		assertFalse(navElements.contains("Admin"));

		logout();
	}

	/* Scenario:  User wants to access resource upload */
	@Test
	@Order(5)
	void UserCannotAccessResourceUpload() throws InterruptedException {

		userLogin();

		WebElement navBar = driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[1]/nav[2]/div/div"));

		ArrayList<String> navElements = new ArrayList<String>(Arrays.asList(navBar.getText().split("\n")));

		assertFalse(navElements.contains("Resources"));

		logout();
	}

		/* Scenario:  User wants to create a secret */
		@Test
		@Order(6)
		void vegaVaultSecretCreation() throws InterruptedException {
	
			userLogin();

			driver.get("http://localhost:3000/vegavault");

			WebElement createScecretInputBox = driver.findElement(By.xpath("//*[@id='formSecret']"));
			createScecretInputBox.sendKeys("secret 1");

			WebElement createSecretButton = driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[2]/div[1]/div/button"));
			createSecretButton.click();

			Thread.sleep(1000);

			WebElement secret = driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[2]/div[2]/table/tbody/tr/td[2]"));			

			assertTrue(secret.getText().toString().equals("secret 1"));
	
			logout();
		}

		@Test
		@Order(7)
		void vegaVaultSecretDeletion() throws InterruptedException {

			userLogin();

			driver.get("http://localhost:3000/vegavault");

			WebElement createScecretInputBox = driver.findElement(By.xpath("//*[@id='formSecret']"));
			createScecretInputBox.sendKeys("secret 2");

			WebElement createSecretButton = driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[2]/div[1]/div/button"));
			createSecretButton.click();

			Thread.sleep(1000);

			WebElement deleteButton = driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[2]/div[2]/table/tbody/tr/td[4]/button"));			
			deleteButton.click();

			Thread.sleep(1000);
	
			assertTrue(driver.findElements(By.xpath("//*[@id='root']/div/div[1]/div[2]/div[2]/table/tbody/tr/td[2]")).isEmpty());

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

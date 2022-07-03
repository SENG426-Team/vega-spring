package com.uvic.venus;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

		WebElement logoutPage = driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[1]/nav[2]/div/div/div/a"));
		logoutPage.click();

		WebElement submitButton = driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[2]/div/div/button"));
		submitButton.click();
	}


	@Test
	@Order(1)
	void AdminCanChangeRoletoStaff() throws InterruptedException {

		WebElement loginPage =  driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[1]/nav[2]/div/div/div/a"));
		loginPage.click();

		WebElement usernameInputBox = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div/div/form/div[1]/input"));
		usernameInputBox.sendKeys("admin@venus.com");

		WebElement passwordInputBox = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div/div/form/div[2]/input"));
		passwordInputBox.sendKeys("pass");

		WebElement submitButton = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div/div/form/button"));
		submitButton.click();

		// Wait for page load
		Thread.sleep(1000);

		WebElement adminPage = driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[1]/nav[2]/div/div/a[7]"));
		adminPage.click();

		WebElement dropDownList = driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[2]/table/tbody/tr[2]/td[5]/select"));
		dropDownList.click();

		WebElement staffListItem = driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[2]/table/tbody/tr[2]/td[5]/select/option[2]"));
		staffListItem.click();

		logout();
	}

	@Test
	@Order(2)
	void AdminCanChangeRoletoUser() throws InterruptedException {

		WebElement loginPage =  driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[1]/nav[2]/div/div/div/a"));
		loginPage.click();

		WebElement usernameInputBox = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div/div/form/div[1]/input"));
		usernameInputBox.sendKeys("admin@venus.com");

		WebElement passwordInputBox = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div/div/form/div[2]/input"));
		passwordInputBox.sendKeys("pass");

		WebElement submitButton = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div/div/form/button"));
		submitButton.click();

		// Wait for page load
		Thread.sleep(1000);

		WebElement adminPage = driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[1]/nav[2]/div/div/a[7]"));
		adminPage.click();

		WebElement dropDownList = driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[2]/table/tbody/tr[2]/td[5]/select"));
		dropDownList.click();

		WebElement userListItem = driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[2]/table/tbody/tr[2]/td[5]/select/option[3]"));
		userListItem.click();

		logout();
	}

	@Test
	@Order(3)
	void UserCannotChangeRole() throws InterruptedException {

		WebElement loginPage =  driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[1]/nav[2]/div/div/div/a"));
		loginPage.click();

		WebElement usernameInputBox = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div/div/form/div[1]/input"));
		usernameInputBox.sendKeys("testuser@venus.com");

		WebElement passwordInputBox = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div/div/form/div[2]/input"));
		passwordInputBox.sendKeys("pass");

		WebElement submitButton = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div/div/form/button"));
		submitButton.click();

		// Wait for load
		Thread.sleep(1000);

		// Check that admin page for changing user roles is not present
		assertTrue(driver.findElements(By.xpath("//*[@id='root']/div/div[1]/div[1]/nav[2]/div/div/a[7]")).isEmpty());

		logout();
	}

	@Test
	@Order(4)
	void StaffCannotChangeRole() throws InterruptedException {

		WebElement loginPage =  driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[1]/nav[2]/div/div/div/a"));
		loginPage.click();

		WebElement usernameInputBox = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div/div/form/div[1]/input"));
		usernameInputBox.sendKeys("testuser@venus.com");

		WebElement passwordInputBox = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div/div/form/div[2]/input"));
		passwordInputBox.sendKeys("pass");

		WebElement submitButton = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div/div/form/button"));
		submitButton.click();

		// Wait for load
		Thread.sleep(1000);

		// Check that admin page for changing user roles is not present
		assertTrue(driver.findElements(By.id("//*[@id='root']/div/div[1]/div[1]/nav[2]/div/div/a[7]")).isEmpty());

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

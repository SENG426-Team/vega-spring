package com.uvic.venus;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
		// Click logout page
		driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[1]/nav[2]/div/div/div/a")).click();
		// Click submit
		driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[2]/div/div/button")).click();
	}

	@Test
	void AdminCanChangeRole() throws InterruptedException {
		// Click on login page
		driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[1]/nav[2]/div/div/div/a")).click();
		// Enter username
		driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div/div/form/div[1]/input")).sendKeys("admin@venus.com");
		// Enter passwork
		driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div/div/form/div[2]/input")).sendKeys("pass");
		// Click submit
		driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div/div/form/button")).click();
		// Wait for load
		Thread.sleep(1000);
		// Click on admin page
		driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[1]/nav[2]/div/div/a[7]")).click();
		// Click on drop down
		driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[2]/table/tbody/tr[2]/td[5]/select")).click();
		// Select 'STAFF'
		driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[2]/table/tbody/tr[2]/td[5]/select/option[2]")).click();

		// TODO: add select USER

		logout();
	}

	@Test
	void UserCannotChangeRole() throws InterruptedException {
		// Click on login page
		driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[1]/nav[2]/div/div/div/a")).click();
		// Enter username
		driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div/div/form/div[1]/input")).sendKeys("testuser@venus.com");
		// Enter passwork
		driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div/div/form/div[2]/input")).sendKeys("pass");
		// Click submit
		driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div/div/form/button")).click();
		// Wait for load
		Thread.sleep(1000);
		// Check that admin page for changing user roles is not present
		assertTrue(driver.findElements(By.id("//*[@id='root']/div/div[1]/div[1]/nav[2]/div/div/a[7]")).isEmpty());

		logout();
	}

	@Test
	void StaffCannotChangeRole() throws InterruptedException {
		// Click on login page
		driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[1]/nav[2]/div/div/div/a")).click();
		// Enter username
		driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div/div/form/div[1]/input")).sendKeys("jonoliver@venus.com");
		// Enter passwork
		driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div/div/form/div[2]/input")).sendKeys("pass");
		// Click submit
		driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div/div/form/button")).click();
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

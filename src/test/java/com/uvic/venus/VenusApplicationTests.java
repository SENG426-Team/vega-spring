package com.uvic.venus;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
		
		String path = System.getProperty("user.dir")+"/src/test/java/com/uvic/venus/chromedriver";

		System.setProperty("webdriver.chrome.driver", path);

		driver = new ChromeDriver();
		driver.get("http://localhost:3000");
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

	public void user2Login() throws InterruptedException {

		driver.get("http://localhost:3000/login");

		WebElement usernameInputBox = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div/div/form/div[1]/input"));
		usernameInputBox.sendKeys("testuser2@venus.com");

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

	public void createSecret() throws InterruptedException {

		driver.get("http://localhost:3000/vegavault");

		WebElement createScecretInputBox = driver.findElement(By.xpath("//*[@id='formSecret']"));
		createScecretInputBox.sendKeys("secret 1");

		WebElement createSecretButton = driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[2]/div[1]/div/button"));
		createSecretButton.click();

		Thread.sleep(1000);

	}

	public void shareSecret() throws InterruptedException {
		
		WebElement usernameInputBox = driver.findElement(By.xpath("//*[@id='formUsername']"));
		usernameInputBox.sendKeys("testuser2@venus.com");

		WebElement secretID = driver.findElement(By.xpath("//*[@id='formShareID']/option[2]"));
		secretID.click();

		WebElement submitButton = driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[2]/div[2]/div/div/button"));	
		
		Thread.sleep(1000);		
		
		submitButton.click();
	}

	/* Feature: Changing User Roles */

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

	/* Scenario: Admin want to change it's role to user */
	@Test
	@Order(3)
	void AdminCannotChangeAdminRole() throws InterruptedException {

		adminLogin();

		driver.get("http://localhost:3000/adminpanel");
		
		Thread.sleep(1000);

		WebElement dropDownList = driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[2]/table/tbody/tr[1]/td[5]/select"));
		dropDownList.click();

		WebElement userListItem = driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[2]/table/tbody/tr[1]/td[5]/select/option[2]"));
		userListItem.click();

		Thread.sleep(1000);

		driver.switchTo().alert().accept();

		logout();
	}

	/* Scenario: User wants to change another users role */
	@Test
	@Order(4)
	void UserCannotChangeRole() throws InterruptedException {

		userLogin();

		WebElement navBar = driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[1]/nav[2]/div/div"));

		ArrayList<String> navElements = new ArrayList<String>(Arrays.asList(navBar.getText().split("\n")));

		assertFalse(navElements.contains("Admin"));

		logout();
	}

	/* Scenario: Staff wants to change another users role */
	@Test
	@Order(5)
	void StaffCannotChangeRole() throws InterruptedException {

		staffLogin();

		WebElement navBar = driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[1]/nav[2]/div/div"));

		ArrayList<String> navElements = new ArrayList<String>(Arrays.asList(navBar.getText().split("\n")));

		assertFalse(navElements.contains("Admin"));

		logout();
	}

	/* Feature: Vega Vault */

	/* Scenario: User wants to create a secret */
	@Test
	@Order(6)
	void vegaVaultSecretCreation() throws InterruptedException {

		userLogin();

		createSecret();

		WebElement secret = driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[2]/div[2]/table/tbody/tr/td[2]"));			

		assertTrue(secret.getText().toString().equals("secret 1"));

		logout();
	}

	/* Scenario: User wants to delete a secret */
	@Test
	@Order(7)
	void vegaVaultSecretDeletion() throws InterruptedException {

		userLogin();

		createSecret();

		WebElement deleteButton = driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[2]/div[2]/table/tbody/tr/td[4]/button"));			
		deleteButton.click();

		Thread.sleep(1000);

		// check that secret has been deleted
		assertTrue(driver.findElements(By.xpath("//*[@id='root']/div/div[1]/div[2]/div[2]/table/tbody/tr/td[2]")).isEmpty());

		logout();

	}

	/* Scenario: User wants to share a secret with another user */
	@Test
	@Order(8)
	void vegaVaultSecretSharing() throws InterruptedException {

		userLogin();

		createSecret();

		shareSecret();

		logout();

		user2Login();

		driver.get("http://localhost:3000/vegavault");

		Thread.sleep(1000);

		WebElement sharedSecret = driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[2]/div[2]/table/tbody/tr/td[3]"));			

		assertTrue(sharedSecret.getText().toString().equals("secret 1"));

		logout();

	}

	/* Scenario: User wants to delete shared secret */
	@Test
	@Order(9)
	void vegaVaultSecretSharingThenDeleting() throws InterruptedException {

		userLogin();

		createSecret();

		shareSecret();

		logout();

		user2Login();

		driver.get("http://localhost:3000/vegavault");

		Thread.sleep(1000);		

		WebElement sharedSecret = driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[2]/div[2]/table/tbody/tr/td[3]"));			

		assertTrue(sharedSecret.getText().toString().equals("secret 1"));

		logout();

		userLogin();

		driver.get("http://localhost:3000/vegavault");

		Thread.sleep(1000);	

		WebElement deleteButton = driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[2]/div[2]/table/tbody/tr/td[4]/button"));		
		
		deleteButton.click();

		logout();

		user2Login();

		driver.get("http://localhost:3000/vegavault");

		// check that shared secret has been deleted
		assertTrue(driver.findElements(By.xpath("//*[@id='root']/div/div[1]/div[2]/div[2]/table/tbody/tr/td[3]")).isEmpty());

	}

	/* Feature: Resource Upload */

	/* Scenario: Admin want to upload a resource */
	@Test
	@Order(10)
	void UploadResource() throws InterruptedException {

		adminLogin();

		// wait for page load
		Thread.sleep(1000);

		driver.get("http://localhost:3000/resources");

		String path = System.getProperty("user.dir")+"/src/test/java/com/uvic/venus/test_file.txt";

		WebElement uploadElement = driver.findElement(By.id("formFile"));
        uploadElement.sendKeys(path); 

        WebElement submitFile = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[2]/div[1]/div/button"));
		submitFile.click();

		//wait for submit button
		Thread.sleep(1000);

		driver.navigate().refresh();

		//wait for refreshing page
		Thread.sleep(2000);

		logout();

	}

	/* Scenario: User cannot Upload a Resource of an Unaccepted format */
	@Test
	@Order(11)
	void UnacceptedFormat() throws InterruptedException {

		adminLogin();

		// wait for page load
		Thread.sleep(1000);

		driver.get("http://localhost:3000/resources");

		String path = System.getProperty("user.dir")+"/src/test/java/com/uvic/venus/chromedriver";

		WebElement uploadElement = driver.findElement(By.id("formFile"));
		uploadElement.sendKeys(path);

		WebElement submitFile = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[2]/div[1]/div/button"));
		submitFile.click();

		//wait for submit button
		Thread.sleep(1000);

		driver.navigate().refresh();

		//wait for refreshing page
		Thread.sleep(2000);

		logout();

	}

	/* Scenario: staff can view a list of resources uploaded by an admin */
	@Test
	@Order(12)
	void viewListOfResources() throws InterruptedException {

		staffLogin();

		// wait for page load
		Thread.sleep(1000);

		driver.get("http://localhost:3000/resources");

		Thread.sleep(1000);

		logout();

	}

	/* Scenario: User without sufficient permissions wants to access resource upload */
	@Test
	@Order(13)
	void UserCannotAccessResourceUpload() throws InterruptedException {

		userLogin();

		WebElement navBar = driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[1]/nav[2]/div/div"));

		ArrayList<String> navElements = new ArrayList<String>(Arrays.asList(navBar.getText().split("\n")));

		assertFalse(navElements.contains("Resources"));

		logout();
	}

	/* Feature: Account Creation */

   /* Scenario: New user creates an account and once registered their account appears in the admin pannel */
   @Test
   @Order(14)
   void UserCreatesAccount() throws InterruptedException {

   		driver.get("http://localhost:3000/registeraccount");

		WebElement firstNameInputBox = driver.findElement(By.xpath("//*[@id='formfirstname']"));
		firstNameInputBox.sendKeys("John");

		WebElement lastNameInputBox = driver.findElement(By.xpath("//*[@id='formlastname']"));
		lastNameInputBox.sendKeys("Smith");	

		WebElement usernameInputBox = driver.findElement(By.xpath("//*[@id='formUsername']"));
		usernameInputBox.sendKeys("johnsmith@venus.com");	

		WebElement passwordInputBox = driver.findElement(By.xpath("//*[@id='formPassword']"));
		passwordInputBox.sendKeys("pass");	

		WebElement submitButton = driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[2]/div/div/button"));
		submitButton.click();	

		Thread.sleep(1000);

		logout();

		adminLogin();

		driver.get("http://localhost:3000/adminpanel");

		Thread.sleep(1000);

		WebElement addedUser = driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[2]/table/tbody/tr[13]/td[3]"));		
		
		assertTrue(addedUser.getText().toString().equals("johnsmith@venus.com"));

		logout();

   }

   /* User cannot login to the plateform if they have not been enabled by an admin */
   @Test
   @Order(15)
   void UserCannotLoginIfNotEnabled() throws InterruptedException {

	driver.get("http://localhost:3000/registeraccount");

	WebElement firstNameInputBox = driver.findElement(By.xpath("//*[@id='formfirstname']"));
	firstNameInputBox.sendKeys("John");

	WebElement lastNameInputBox = driver.findElement(By.xpath("//*[@id='formlastname']"));
	lastNameInputBox.sendKeys("Smith");	

	WebElement usernameInputBox = driver.findElement(By.xpath("//*[@id='formUsername']"));
	usernameInputBox.sendKeys("johnsmith@venus.com");	

	WebElement passwordInputBox = driver.findElement(By.xpath("//*[@id='formPassword']"));
	passwordInputBox.sendKeys("pass");	

	WebElement submitButton = driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[2]/div/div/button"));
	submitButton.click();	

	Thread.sleep(1000);

	driver.get("http://localhost:3000/login");

	WebElement usernameInputBox1 = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div/div/form/div[1]/input"));
	usernameInputBox1.sendKeys("johnsmith@venus.com");

	WebElement passwordInputBox1 = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div/div/form/div[2]/input"));
	passwordInputBox1.sendKeys("pass");

	WebElement submitButton1 = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div/div/form/button"));
	submitButton1.click();

	WebElement errorMessage = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[2]/p"));			

	assertTrue(errorMessage.getText().toString().equals("The Current User Is Not Enabled"));

   }

	/* User can login to the plateform if they have been enabled by an admin */
	@Test
	@Order(16)
	void UserCanLoginIfEnabled() throws InterruptedException {

		driver.get("http://localhost:3000/registeraccount");

		WebElement firstNameInputBox = driver.findElement(By.xpath("//*[@id='formfirstname']"));
		firstNameInputBox.sendKeys("John");

		WebElement lastNameInputBox = driver.findElement(By.xpath("//*[@id='formlastname']"));
		lastNameInputBox.sendKeys("Smith");	

		WebElement usernameInputBox = driver.findElement(By.xpath("//*[@id='formUsername']"));
		usernameInputBox.sendKeys("johnsmith@venus.com");	

		WebElement passwordInputBox = driver.findElement(By.xpath("//*[@id='formPassword']"));
		passwordInputBox.sendKeys("pass");	

		WebElement submitButton = driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[2]/div/div/button"));
		submitButton.click();	

		Thread.sleep(1000);

		adminLogin();

		driver.get("http://localhost:3000/adminpanel");

		Thread.sleep(1000);

		WebElement enableUser = driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div[2]/table/tbody/tr[13]/td[4]/a"));	

		JavascriptExecutor jse = (JavascriptExecutor)driver;

		jse.executeScript("arguments[0].scrollIntoView()", enableUser); 

		Thread.sleep(1000);
		
		enableUser.click();

		logout();

		driver.get("http://localhost:3000/login");

		WebElement usernameInputBox1 = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div/div/form/div[1]/input"));
		usernameInputBox1.sendKeys("johnsmith@venus.com");

		WebElement passwordInputBox1 = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div/div/form/div[2]/input"));
		passwordInputBox1.sendKeys("pass");

		WebElement submitButton1 = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div/div/form/button"));
		submitButton1.click();

		// Wait for page load
		Thread.sleep(3000);

	}

	/* User cannot login if they have not created an account */
	@Test
	@Order(17)
	void userCannotLoginWithoutRegistration() throws InterruptedException {

		driver.get("http://localhost:3000/login");

		WebElement usernameInputBox = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div/div/form/div[1]/input"));
		usernameInputBox.sendKeys("Not a registered user");

		WebElement passwordInputBox = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div/div/form/div[2]/input"));
		passwordInputBox.sendKeys("pass");

		WebElement submitButton = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div/div/form/button"));
		submitButton.click();

		WebElement errorMessage = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[2]/p"));			

		Thread.sleep(1000);

		assertTrue(errorMessage.getText().toString().equals("Authentication Error: Username and/or Password is Incorrect"));
		
	}


	@AfterAll
	void tearDown() throws InterruptedException{
		if(driver!=null){
			driver.close();
			driver.quit();
		}
	}

}

import os
import time
import unittest
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.chrome.service import Service
from selenium import webdriver


class Milestone3Tests(unittest.TestCase):
    def setUp(self):
        self.driver = webdriver.Chrome(
            service=Service(ChromeDriverManager().install()))

    def logout(self):
        self.driver.get("https://venus-app.azurewebsites.net/account")

        submitButton = self.driver.find_element(
            By.XPATH, "//*[@id='root']/div/div[1]/div[2]/div/div/button")
        submitButton.click()

    def adminLogin(self):
        self.driver.get("https://venus-app.azurewebsites.net/login")

        usernameInputBox = self.driver.find_element(
            By.XPATH, "//*[@id='root']/div/div[2]/div/div/form/div[1]/input")
        usernameInputBox.send_keys("admin@venus.com")

        passwordInputBox = self.driver.find_element(
            By.XPATH, "//*[@id='root']/div/div[2]/div/div/form/div[2]/input")
        passwordInputBox.send_keys("pass")

        submitButton = self.driver.find_element(
            By.XPATH, "//*[@id='root']/div/div[2]/div/div/form/button")
        submitButton.click()

        # Wait for page load
        time.sleep(1)

    def userLogin(self):
        self.driver.get("https://venus-app.azurewebsites.net/login")

        usernameInputBox = self.driver.find_element(
            By.XPATH, "//*[@id='root']/div/div[2]/div/div/form/div[1]/input")
        usernameInputBox.send_keys("testuser@venus.com")

        passwordInputBox = self.driver.find_element(
            By.XPATH, "//*[@id='root']/div/div[2]/div/div/form/div[2]/input")
        passwordInputBox.send_keys("pass")

        submitButton = self.driver.find_element(
            By.XPATH, "//*[@id='root']/div/div[2]/div/div/form/button")
        submitButton.click()

        # Wait for page load
        time.sleep(1)

    def user2Login(self):
        self.driver.get("https://venus-app.azurewebsites.net/login")

        usernameInputBox = self.driver.find_element(
            By.XPATH, "//*[@id='root']/div/div[2]/div/div/form/div[1]/input")
        usernameInputBox.send_keys("testuser2@venus.com")

        passwordInputBox = self.driver.find_element(
            By.XPATH, "//*[@id='root']/div/div[2]/div/div/form/div[2]/input")
        passwordInputBox.send_keys("pass")

        submitButton = self.driver.find_element(
            By.XPATH, "//*[@id='root']/div/div[2]/div/div/form/button")
        submitButton.click()

        # Wait for page load
        time.sleep(1)

    def staffLogin(self):
        self.driver.get("https://venus-app.azurewebsites.net/login")

        usernameInputBox = self.driver.find_element(
            By.XPATH, "//*[@id='root']/div/div[2]/div/div/form/div[1]/input")
        usernameInputBox.send_keys("jonoliver@venus.com")

        passwordInputBox = self.driver.find_element(
            By.XPATH, "//*[@id='root']/div/div[2]/div/div/form/div[2]/input")
        passwordInputBox.send_keys("pass")

        submitButton = self.driver.find_element(
            By.XPATH, "//*[@id='root']/div/div[2]/div/div/form/button")
        submitButton.click()

        # Wait for page load
        time.sleep(1)

    def createSecret(self):
        self.driver.get("https://venus-app.azurewebsites.net/vegavault")

        createScecretInputBox = self.driver.find_element(
            By.XPATH, "//*[@id='formSecret']")
        createScecretInputBox.send_keys("secret 1")

        createSecretButton = self.driver.find_element(
            By.XPATH, "//*[@id='root']/div/div[1]/div[2]/div[1]/div/button")
        createSecretButton.click()

        time.sleep(1)

    def shareSecret(self):
        usernameInputBox = self.driver.find_element(
            By.XPATH, "//*[@id='formUsername']")
        usernameInputBox.send_keys("testuser2@venus.com")

        secretID = self.driver.find_element(
            By.XPATH, "//*[@id='formShareID']/option[2]")
        secretID.click()

        submitButton = self.driver.find_element(
            By.XPATH, "//*[@id='root']/div/div[1]/div[2]/div[2]/div/div/button")

        time.sleep(1)

        submitButton.click()

    def test_dminCanChangeRoletoStaff(self):
        self.adminLogin()

        self.driver.get("https://venus-app.azurewebsites.net/adminpanel")

        # wait for page load
        time.sleep(1)

        dropDownList = self.driver.find_element(
            By.XPATH, "//*[@id='root']/div/div[1]/div[2]/table/tbody/tr[2]/td[5]/select")
        dropDownList.click()

        staffListItem = self.driver.find_element(
            By.XPATH, "//*[@id='root']/div/div[1]/div[2]/table/tbody/tr[2]/td[5]/select/option[2]")
        staffListItem.click()

        self.logout()

    def test_AdminCanChangeRoletoUser(self):
        self.adminLogin()

        self.driver.get("https://venus-app.azurewebsites.net/adminpanel")

        time.sleep(1)

        dropDownList = self.driver.find_element(
            By.XPATH, "//*[@id='root']/div/div[1]/div[2]/table/tbody/tr[2]/td[5]/select")
        dropDownList.click()

        userListItem = self.driver.find_element(
            By.XPATH, "//*[@id='root']/div/div[1]/div[2]/table/tbody/tr[2]/td[5]/select/option[3]")
        userListItem.click()

        self.logout()

    def test_AdminCannotChangeAdminRole(self):
        self.adminLogin()

        self.driver.get("https://venus-app.azurewebsites.net/adminpanel")

        time.sleep(1)

        dropDownList = self.driver.find_element(
            By.XPATH, "//*[@id='root']/div/div[1]/div[2]/table/tbody/tr[1]/td[5]/select")
        dropDownList.click()

        userListItem = self.driver.find_element(
            By.XPATH, "//*[@id='root']/div/div[1]/div[2]/table/tbody/tr[1]/td[5]/select/option[2]")
        userListItem.click()

        time.sleep(1)

        self.driver.switch_to.alert.accept()

        self.logout()

    def test_StaffCannotChangeRole(self):
        self.staffLogin()

        navBar = self.driver.find_element(
            By.XPATH, "//*[@id='root']/div/div[1]/div[1]/nav[2]/div/div")

        navElements = navBar.text.split("\n")

        self.assertFalse("Admin" in navElements)
        self.logout()

    def test_vegaVaultSecretCreation(self):
        self.userLogin()

        self.createSecret()

        secret = self.driver.find_element(
            By.XPATH, "//*[@id='root']/div/div[1]/div[2]/div[2]/table/tbody/tr/td[2]")

        self.assertTrue(secret.text == "secret 1")

        self.logout()

    def test_vegaVaultSecretDeletion(self):

        self.userLogin()

        self.createSecret()

        deleteButton = self.driver.find_element(
            By.XPATH, "//*[@id='root']/div/div[1]/div[2]/div[2]/table/tbody/tr/td[4]/button")
        deleteButton.click()

        time.sleep(1)

        # check that secret has been deleted
        self.assertTrue(len(self.driver.find_elements(By.XPATH,
                                                      "//*[@id='root']/div/div[1]/div[2]/div[2]/table/tbody/tr/td[2]")) == 0)

        self.logout()

    def test_vegaVaultSecretSharing(self):
        self.userLogin()

        self.createSecret()

        self.shareSecret()

        self.logout()

        self.user2Login()

        self.driver.get("https://venus-app.azurewebsites.net/vegavault")

        time.sleep(1)

        sharedSecret = self.driver.find_element(
            By.XPATH, "//*[@id='root']/div/div[1]/div[2]/div[2]/table/tbody/tr/td[3]")

        self.assertTrue(sharedSecret.text == "secret 1")

        self.logout()

    def vegaVaultSecretSharingThenDeleting(self):
        self.userLogin()

        self.createSecret()

        self.shareSecret()

        self.logout()

        self.user2Login()

        self.driver.get("https://venus-app.azurewebsites.net/vegavault")

        time.sleep(1)

        sharedSecret = self.driver.find_element(
            By.XPATH, "//*[@id='root']/div/div[1]/div[2]/div[2]/table/tbody/tr/td[3]")

        self.assertTrue(sharedSecret.text == ("secret 1"))

        self.logout()

        self.userLogin()

        self.driver.get("https://venus-app.azurewebsites.net/vegavault")

        time.sleep(1)

        deleteButton = self.driver.find_element(
            By.XPATH, "//*[@id='root']/div/div[1]/div[2]/div[2]/table/tbody/tr/td[4]/button")

        deleteButton.click()

        self.logout()

        self.user2Login()

        self.driver.get("https://venus-app.azurewebsites.net/vegavault")

        # check that shared secret has been deleted
        self.assertTrue(len(self.driver.find_elements(
            By.XPATH, "//*[@id='root']/div/div[1]/div[2]/div[2]/table/tbody/tr/td[3]")) == 0)

    def test_UploadResource(self):
        self.adminLogin()

        # wait for page load
        time.sleep(1)

        self.driver.get("https://venus-app.azurewebsites.net/resources")

        path = os.getcwd()+"/src/test/java/com/uvic/venus/test_file.txt"

        uploadElement = self.driver.find_element(By.ID, "formFile")
        uploadElement.send_keys(path)

        submitFile = self.driver.find_element(
            By.XPATH, "/html/body/div[1]/div/div[1]/div[2]/div[1]/div/button")
        submitFile.click()

        # wait for submit button
        time.sleep(1)

        self.driver.navigate().refresh()

        # wait for refreshing page
        time.sleep(2)

        self.logout()

    def test_UnacceptedFormat(self):
        self.adminLogin()

        # wait for page load
        time.sleep(1)

        self.driver.get("https://venus-app.azurewebsites.net/resources")

        path = os.getcwd()+"/src/test/java/com/uvic/venus/chromedriver"

        uploadElement = self.driver.find_element(By.ID, "formFile")
        uploadElement.send_keys(path)

        submitFile = self.driver.find_element(
            By.XPATH, "/html/body/div[1]/div/div[1]/div[2]/div[1]/div/button")
        submitFile.click()

        # wait for submit button
        time.sleep(1)

        self.driver.navigate().refresh()

        # wait for refreshing page
        time.sleep(2)

        self.logout()

    def test_viewListOfResources(self):
        self.staffLogin()

        # wait for page load
        time.sleep(1)

        self.driver.get("https://venus-app.azurewebsites.net/resources")

        time.sleep(1)

        self.logout()

    def test_UserCannotAccessResourceUpload(self):
        self.userLogin()

        navBar = self.driver.find_element(
            By.XPATH, "//*[@id='root']/div/div[1]/div[1]/nav[2]/div/div")

        navElements = navBar.text.split("\n")

        self.assertFalse("Resources" in navElements)

        self.logout()

    def test_UserCreatesAccount(self):
        self.driver.get("https://venus-app.azurewebsites.net/registeraccount")

        firstNameInputBox = self.driver.find_element(
            By.XPATH, "//*[@id='formfirstname']")
        firstNameInputBox.send_keys("John")

        lastNameInputBox = self.driver.find_element(
            By.XPATH, "//*[@id='formlastname']")
        lastNameInputBox.send_keys("Smith")

        usernameInputBox = self.driver.find_element(
            By.XPATH, "//*[@id='formUsername']")
        usernameInputBox.send_keys("johnsmith@venus.com")

        passwordInputBox = self.driver.find_element(
            By.XPATH, "//*[@id='formPassword']")
        passwordInputBox.send_keys("pass")

        submitButton = self.driver.find_element(
            By.XPATH, "//*[@id='root']/div/div[1]/div[2]/div/div/button")
        submitButton.click()

        time.sleep(1)

        self.logout()

        self.adminLogin()

        self.driver.get("https://venus-app.azurewebsites.net/adminpanel")

        time.sleep(1)

        addedUser = self.driver.find_element(
            By.XPATH, "//*[@id='root']/div/div[1]/div[2]/table/tbody/tr[13]/td[3]")

        self.assertTrue(addedUser.text == "johnsmith@venus.com")

        self.logout()

    def test_UserCannotLoginIfNotEnabled(self):
        self.driver.get("https://venus-app.azurewebsites.net/registeraccount")

        firstNameInputBox = self.driver.find_element(
            By.XPATH, "//*[@id='formfirstname']")
        firstNameInputBox.send_keys("John")

        lastNameInputBox = self.driver.find_element(
            By.XPATH, "//*[@id='formlastname']")
        lastNameInputBox.send_keys("Smith")

        usernameInputBox = self.driver.find_element(
            By.XPATH, "//*[@id='formUsername']")
        usernameInputBox.send_keys("johnsmith@venus.com")

        passwordInputBox = self.driver.find_element(
            By.XPATH, "//*[@id='formPassword']")
        passwordInputBox.send_keys("pass")

        submitButton = self.driver.find_element(
            By.XPATH, "//*[@id='root']/div/div[1]/div[2]/div/div/button")
        submitButton.click()

        time.sleep(1)

        self.driver.get("https://venus-app.azurewebsites.net/login")

        usernameInputBox1 = self.driver.find_element(
            By.XPATH, "//*[@id='root']/div/div[2]/div/div/form/div[1]/input")
        usernameInputBox1.send_keys("johnsmith@venus.com")

        passwordInputBox1 = self.driver.find_element(
            By.XPATH, "//*[@id='root']/div/div[2]/div/div/form/div[2]/input")
        passwordInputBox1.send_keys("pass")

        submitButton1 = self.driver.find_element(
            By.XPATH, "//*[@id='root']/div/div[2]/div/div/form/button")
        submitButton1.click()

        errorMessage = self.driver.find_element(
            By.XPATH, "//*[@id='root']/div/div[2]/div[2]/p")

        self.assertTrue(errorMessage.text
                        == "The Current User Is Not Enabled")

    def test_UserCanLoginIfEnabled(self):
        self.driver.get("https://venus-app.azurewebsites.net/registeraccount")

        firstNameInputBox = self.driver.find_element(
            By.XPATH, "//*[@id='formfirstname']")
        firstNameInputBox.send_keys("John")

        lastNameInputBox = self.driver.find_element(
            By.XPATH, "//*[@id='formlastname']")
        lastNameInputBox.send_keys("Smith")

        usernameInputBox = self.driver.find_element(
            By.XPATH, "//*[@id='formUsername']")
        usernameInputBox.send_keys("johnsmith@venus.com")

        passwordInputBox = self.driver.find_element(
            By.XPATH, "//*[@id='formPassword']")
        passwordInputBox.send_keys("pass")

        submitButton = self.driver.find_element(
            By.XPATH, "//*[@id='root']/div/div[1]/div[2]/div/div/button")
        submitButton.click()

        time.sleep(1)

        self.adminLogin()

        self.driver.get("https://venus-app.azurewebsites.net/adminpanel")

        time.sleep(1)

        enableUser = self.driver.find_element(
            By.XPATH, "//*[@id='root']/div/div[1]/div[2]/table/tbody/tr[13]/td[4]/a")

        self.driver.execute_script("arguments[0].scrollIntoView()", enableUser)

        time.sleep(1)

        enableUser.click()

        self.logout()

        self.driver.get("https://venus-app.azurewebsites.net/login")

        usernameInputBox1 = self.driver.find_element(
            By.XPATH, "//*[@id='root']/div/div[2]/div/div/form/div[1]/input")
        usernameInputBox1.send_keys("johnsmith@venus.com")

        passwordInputBox1 = self.driver.find_element(
            By.XPATH, "//*[@id='root']/div/div[2]/div/div/form/div[2]/input")
        passwordInputBox1.send_keys("pass")

        submitButton1 = self.driver.find_element(
            By.XPATH, "//*[@id='root']/div/div[2]/div/div/form/button")
        submitButton1.click()

        # Wait for page load
        time.sleep(3)

    def test_userCannotLoginWithoutRegistration(self):
        self.driver.get("https://venus-app.azurewebsites.net/login")

        usernameInputBox = self.driver.find_element(
            By.XPATH, "//*[@id='root']/div/div[2]/div/div/form/div[1]/input")
        usernameInputBox.send_keys("Not a registered user")

        passwordInputBox = self.driver.find_element(
            By.XPATH, "//*[@id='root']/div/div[2]/div/div/form/div[2]/input")
        passwordInputBox.send_keys("pass")

        submitButton = self.driver.find_element(
            By.XPATH, "//*[@id='root']/div/div[2]/div/div/form/button")
        submitButton.click()

        errorMessage = self.driver.find_element(
            By.XPATH, "//*[@id='root']/div/div[2]/div[2]/p")

        time.sleep(1)

        self.assertTrue(errorMessage.text == "Authentication Error: Username and/or Password is Incorrect")

    def tearDown(self):
        self.driver.close()


if __name__ == "__main__":
    unittest.main()

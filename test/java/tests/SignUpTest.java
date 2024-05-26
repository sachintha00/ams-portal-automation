package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.SignUpPage;

public class SignUpTest {
    WebDriver driver;
    SignUpPage signUpPage;

    @BeforeMethod
    public void setup() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Janodya\\git\\optiomax-web-automation\\AutomateOptiomaxWeb\\Resource Files\\Chrome Driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://optiomax.com/");
        driver.switchTo().frame(0);
        
        signUpPage = new SignUpPage(driver);
        signUpPage.clickChooseButton();
        
        Thread.sleep(5000);
    }

    @Test(priority = 1)
    public void verifyAccountSuccessfulCreation() {
        signUpPage.fillSignUpForm("ValidUser", "Validuser123");
        signUpPage.submitForm();
        
        // Verify successful creation, assuming a success message or redirect
        // WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(""))); 
        // Assert.assertTrue(successMessage.isDisplayed(), "Account creation failed.");
    }

    @Test(priority = 3)
    public void verifySuccessfulNavigation() {
        signUpPage.fillSignUpForm("ValidUser2", "ValidPassword123");
        signUpPage.submitForm();
        
        // Verify navigation
        // driver.getPageSource().contains("");
    }

    @Test(priority = 4)
    public void verifyUnsuccessfulSignUpWithInvalidUsername() {
        signUpPage.fillSignUpForm("123", "ValidPassword123");
        signUpPage.submitForm();
        
        // Verify error message
        // WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(""))); 
        // Assert.assertTrue(errorMessage.isDisplayed(), "Invalid username error not displayed.");
    }

    @Test(priority = 5)
    public void verifyUnsuccessfulSignUpWithInvalidPassword() {
        signUpPage.fillSignUpForm("ValidUser3", "short");
        signUpPage.submitForm();
        
        // Verify error message
        // WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(""))); 
        // Assert.assertTrue(errorMessage.isDisplayed(), "Invalid password error not displayed.");
    }

    @Test(priority = 6)
    public void verifyUnsuccessfulSignUpWithEmptyUsername() throws InterruptedException {
        signUpPage.fillSignUpForm("", "ValidPassword123");
        signUpPage.submitForm();
        
        Thread.sleep(5000);
        
        // Verify error message
        Assert.assertTrue(signUpPage.isUsernameErrorDisplayed(), "Username is required");
    }

    @Test(priority = 7)
    public void verifyUnsuccessfulSignUpWithEmptyPassword() throws InterruptedException {
        signUpPage.fillSignUpForm("validUser4", "");
        signUpPage.submitForm();
        
        Thread.sleep(5000);
        
        // Verify error message
        Assert.assertTrue(signUpPage.isPasswordErrorDisplayed(), "Password is required");
    }

    @Test(priority = 8)
    public void verifyUnsuccessfulSignUpWithEmptyUsernameAndPassword() throws InterruptedException {
        signUpPage.fillSignUpForm("", "");
        signUpPage.submitForm();

        Thread.sleep(2000);
        
        // Verify error message
        Assert.assertTrue(signUpPage.isUsernameErrorDisplayed(), "Username is required");
        Assert.assertTrue(signUpPage.isPasswordErrorDisplayed(), "Password is required");
    }

    @Test(priority = 10)
    public void specialCharactersInUsername() {
        signUpPage.fillSignUpForm("user!@#", "ValidPassword123");
        signUpPage.submitForm();
        
        // Verify error message
        // WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(""))); 
        // Assert.assertTrue(errorMessage.isDisplayed(), "Special characters in username error not displayed.");
    }

    @Test(priority = 11)
    public void exceedingCharacterLimitInUsername() {
        signUpPage.fillSignUpForm("VeryLongUsernameExceedingLimitaVeryLongUsernameExceedingLimit", "ValidPassword123");
        signUpPage.submitForm();
        
        // Verify error message
        // WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(""))); 
        // Assert.assertTrue(errorMessage.isDisplayed(), "Exceeding character limit error not displayed.");
    }

    @Test(priority = 12)
    public void verifyUnsuccessfulSignUpWithWeakPassword() {
        signUpPage.fillSignUpForm("validUser5", "weak");
        signUpPage.submitForm();
        
        // Verify error message
        // WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("")));
        // Assert.assertTrue(errorMessage.isDisplayed(), "Weak password error not displayed.");
    }

    @Test(priority = 13)
    public void verifyUnsuccessfulSignUpWithAlreadyTakenUsername() {
        signUpPage.fillSignUpForm("Ruvinya", "ValidPassword123");
        signUpPage.submitForm();
        
        // Verify error message
        // WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("")));
        // Assert.assertTrue(errorMessage.isDisplayed(), "Username already taken error not displayed.");
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.switchTo().defaultContent();
            driver.quit();
        }
    }
}


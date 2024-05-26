package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.SignUpEmailPage;

public class SignUpEmailTest {
    WebDriver driver;
    SignUpEmailPage signUpEmailPage;

    @BeforeMethod
    public void setUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Janodya\\git\\optiomax-web-automation\\AutomateOptiomaxWeb\\Resource Files\\Chrome Driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://optiomax.com/");
        driver.switchTo().frame(0);

        signUpEmailPage = new SignUpEmailPage(driver);

        signUpEmailPage.clickChooseButton();
        Thread.sleep(5000);

        signUpEmailPage.enterUsername("NewUser");
        signUpEmailPage.enterPassword("NewUser1234");
        signUpEmailPage.clickNextButton();
        Thread.sleep(5000);

        signUpEmailPage.enterOwnerDetails("NewUser", "0112254387", "Galle", "ruvinyafernando@gmail.com", "https://www.google.co.uk/", "0712347887");
    }

    @Test(priority = 1)
    public void testValidEmailSignUp() throws InterruptedException {
        signUpEmailPage.enterSignUpEmail("ruvinyafernando34@gmail.com");
        signUpEmailPage.clickSignUpButton();

        Thread.sleep(3000);

        // Add logic to check if verification email is received
        // boolean isEmailReceived = checkEmailReceived("ruvinyafernando34@gmail.com");
        // Assert.assertTrue(isEmailReceived, "Verification email should be received.");
    }

    @Test(priority = 2)
    public void testInvalidEmailSignUp() throws InterruptedException {
        signUpEmailPage.enterSignUpEmail("invalidemail@gmail.com");
        signUpEmailPage.clickSignUpButton();

        Thread.sleep(3000);

        // Add logic to check if verification email is received
        boolean isEmailReceived = checkEmailReceived("invalidemail@gmail.com");
        Assert.assertFalse(isEmailReceived, "Verification email should not be received.");
    }

    @Test(priority = 3)
    public void testEmptyEmailField() throws InterruptedException {
        signUpEmailPage.enterSignUpEmail("");
        signUpEmailPage.clickSignUpButton();

        Thread.sleep(3000);

        Assert.assertTrue(signUpEmailPage.isErrorMessageDisplayed(), "Owner email is required");
    }

    @Test(priority = 4)
    public void testInvalidDomainEmail() throws InterruptedException {
        signUpEmailPage.enterSignUpEmail("invalid@domain");
        signUpEmailPage.clickSignUpButton();

        Thread.sleep(3000);

        // WebElement errorMessage = driver.findElement(By.xpath("")); 
        // Assert.assertTrue(errorMessage.isDisplayed(), "Error message should be displayed.");
    }

    @AfterMethod
    public void tearDown() {
        driver.switchTo().defaultContent();
        driver.quit();
    }

    private boolean checkEmailReceived(String email) {
        // Add your logic to check the email inbox
        return false;
    }
}


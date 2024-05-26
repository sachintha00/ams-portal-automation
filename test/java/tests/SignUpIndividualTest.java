package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.SignUpIndividualPage;

public class SignUpIndividualTest {
    WebDriver driver;
    SignUpIndividualPage signUpPage;

    @BeforeMethod
    public void setUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Janodya\\git\\optiomax-web-automation\\AutomateOptiomaxWeb\\Resource Files\\Chrome Driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://optiomax.com/");
        driver.switchTo().frame(0); 
        
        signUpPage = new SignUpIndividualPage(driver);

        signUpPage.chooseIndividualSignUp();
        
        Thread.sleep(5000);
        
        signUpPage.enterUsername("Test User");
        signUpPage.enterPassword("Testuser1234");
        signUpPage.clickNextButton();
        
        Thread.sleep(5000);
    }

    @AfterMethod
    public void tearDown() {
        driver.switchTo().defaultContent();
        driver.quit();
    }

    @Test(priority = 1)
    public void testValidInputs() {
        signUpPage.fillForm("Test User", "0765879210", "Galle", "ruvinyafernando@gmail.com", "https://example.com", "0745679123");
        signUpPage.clickSubmitButton();
        
        // Add assertions to verify successful submission
        // WebElement successMessage = driver.findElement(By.id("successMessage"));
        // Assert.assertTrue(successMessage.isDisplayed(), "Success message should be displayed");
    }
 
    @Test(priority = 2)
    public void testMissingRequiredFields() throws InterruptedException {
        signUpPage.fillForm("", "", "", "", "", "");
        signUpPage.clickSubmitButton();
        
        Thread.sleep(3000);

        // Verify that appropriate error messages are displayed for required fields
        Assert.assertTrue(signUpPage.isErrorMessageDisplayed(By.xpath("/html/body/section/div/div/div/div[2]/form/div[1]/div")), "Person name is required");
        Assert.assertTrue(signUpPage.isErrorMessageDisplayed(By.xpath("/html/body/section/div/div/div/div[2]/form/div[2]/div")), "Contact no is required");
        Assert.assertTrue(signUpPage.isErrorMessageDisplayed(By.xpath("/html/body/section/div/div/div/div[2]/form/div[3]/div")), "Address is required");
        Assert.assertTrue(signUpPage.isErrorMessageDisplayed(By.xpath("/html/body/section/div/div/div/div[2]/form/div[4]/div")), "Email address is required");
        Assert.assertTrue(signUpPage.isErrorMessageDisplayed(By.xpath("/html/body/section/div/div/div/div[2]/form/div[5]/div")), "Website is required");
        Assert.assertTrue(signUpPage.isErrorMessageDisplayed(By.xpath("/html/body/section/div/div/div/div[2]/form/div[6]/div")), "Contact person is required");
    }

    @Test(priority = 3)
    public void testInvalidEmailFormat() throws InterruptedException {
        signUpPage.fillForm("Test User", "0765879211", "Galle", "invalid-email", "https://example.com", "0745679121");
        signUpPage.clickSubmitButton();
        
        Thread.sleep(3000);
        
        // Add assertions to verify email format error
        //Assert.assertTrue(driver.getPageSource().contains("Please include an '@' in the email address. 'invalid-email' is missing an '@'."));
        driver.getPageSource().contains("Please include an '@' in the email address. 'invalid-email' is missing an '@'.");
        
    }

    @Test(priority = 4)
    public void testInvalidPhoneNumberFormat() {
        signUpPage.fillForm("Test User", "invalid-phone", "Galle", "ruvinyafernando@gmail.com", "https://example.com", "invalid-phone");
        signUpPage.clickSubmitButton();
        // Add assertions to verify phone number format error
    }

    @Test(priority = 5)
    public void testInvalidWebsiteURLFormat() {
        signUpPage.fillForm("Test User", "0765879213", "Galle", "ruvinyafernando@gmail.com", "invalid-url", "0745679124");
        signUpPage.clickSubmitButton();
        // Add assertions to verify website URL format error
    }

    @Test(priority = 6)
    public void testLongInputsForFields() {
        signUpPage.fillForm("Test User".repeat(50), "0765879213".repeat(10), "Galle".repeat(50), "ruvinyafernando@gmail.com".repeat(10), "https://example.com".repeat(50),  "0763452999".repeat(10));
        signUpPage.clickSubmitButton();
        // Add assertions to verify field length limits
    }

    @Test(priority = 7)
    public void testUnsupportedCharactersInInputs() {
        signUpPage.fillForm("!@#$%^&*()", "!@#$%^&*()", "Galle", "testuser@gmail.com", "john@!#$%^&*().com", "!@#$%^&*()");
        signUpPage.clickSubmitButton();
        // Add assertions to verify unsupported characters error
    }

    @Test(priority = 8)
    public void testDuplicateEmailAddress() {
        signUpPage.fillForm("Test User", "0783452112", "Galle", "ruvinyafernando@gmail.com", "https://example.com", "0712346754");
        signUpPage.clickSubmitButton();
        // Add assertions to verify duplicate email error
    }

    @Test(priority = 9)
    public void testInvalidDataTypeForFields() {
        signUpPage.fillForm("12345", "67890", "12345", "abcdef", "12345@example.com", "67890");
        signUpPage.clickSubmitButton();
        // Add assertions to verify data type errors
    }

    @Test(priority = 10)
    public void testNoInputProvided() {
        signUpPage.clickSubmitButton();
        // Add assertions to verify errors when no input is provided
    }

//    @Test(priority = 11)
//    public void testDataInputExceedingFieldLimits() {
//        signUpPage.fillForm("Test User".repeat(1000), "0762134871".repeat(1000), "Galle".repeat(1000), "test@example.com".repeat(1000), "https://example.com".repeat(1000), "0312254990".repeat(1000));
//        signUpPage.clickSubmitButton();
//        // Add assertions to verify field limit errors
//    }
    
    @Test(priority = 12)
    public void testInvalidCountrySpecificFields() {
        signUpPage.fillForm("Test User", "+94768932671", "Jaffna", "test@example.com", "https://example.com", "0712348789");
        signUpPage.clickSubmitButton();
        // Add assertions to verify country-specific field errors
    }
}



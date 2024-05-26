package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.SignUpEnterprisePage;

import org.openqa.selenium.By;

public class SignUpEnterpriseTest {
    WebDriver driver;
    SignUpEnterprisePage signUpPage;

    @BeforeMethod
    public void setUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Janodya\\git\\optiomax-web-automation\\AutomateOptiomaxWeb\\Resource Files\\Chrome Driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        signUpPage = new SignUpEnterprisePage(driver);
        signUpPage.openPage();
        signUpPage.clickChooseButton();
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
        signUpPage.selectEnterpriseOption();
        signUpPage.fillForm("ABC Company", "0115734890", "0745679123", "Kandy", "abccompany@gmail.com", "https://abccompany.com");
        signUpPage.submitForm();
        // Add assertions to verify successful submission
        // WebElement successMessage = driver.findElement(By.id("successMessage"));
        // Assert.assertTrue(successMessage.isDisplayed(), "Success message should be displayed");
    }

    @Test(priority = 2)
    public void testMissingRequiredFields() throws InterruptedException {
        signUpPage.selectEnterpriseOption();
        signUpPage.fillForm("", "", "", "", "", "");
        signUpPage.submitForm();
        Thread.sleep(3000);
        // Verify that appropriate error messages are displayed for required fields
        Assert.assertTrue(signUpPage.isErrorMessageDisplayed(By.xpath("/html/body/section/div/div/div/div[2]/form/div[1]/div")), "Company name is required");
        Assert.assertTrue(signUpPage.isErrorMessageDisplayed(By.xpath("/html/body/section/div/div/div/div[2]/form/div[2]/div")), "Contact no is required");
        Assert.assertTrue(signUpPage.isErrorMessageDisplayed(By.xpath("/html/body/section/div/div/div/div[2]/form/div[3]/div")), "Contact person is required");
        Assert.assertTrue(signUpPage.isErrorMessageDisplayed(By.xpath("/html/body/section/div/div/div/div[2]/form/div[4]/div")), "Address is required");
        Assert.assertTrue(signUpPage.isErrorMessageDisplayed(By.xpath("/html/body/section/div/div/div/div[2]/form/div[5]/div")), "Email address is required");
        Assert.assertTrue(signUpPage.isErrorMessageDisplayed(By.xpath("/html/body/section/div/div/div/div[2]/form/div[6]/div")), "Website is required");
    }

    @Test(priority = 3)
    public void testInvalidEmailFormat() throws InterruptedException {
        signUpPage.selectEnterpriseOption();
        signUpPage.fillForm("Test Company", "0115879211", "0785642319", "Kandy", "invalid-email", "https://example.com");
        signUpPage.submitForm();
        Thread.sleep(3000);
        // Add assertions to verify email format error
        driver.getPageSource().contains("Please include an '@' in the email address. 'invalid-email' is missing an '@'.");
    }

    @Test(priority = 4)
    public void testInvalidPhoneNumberFormat() {
        signUpPage.selectEnterpriseOption();
        signUpPage.fillForm("Test Company", "invalid-phone", "invalid-phone", "Kandy", "testcompany@gmail.com", "https://example.com");
        signUpPage.submitForm();
        // Add assertions to verify phone number format error
    }

    @Test(priority = 5)
    public void testInvalidWebsiteURLFormat() {
        signUpPage.selectEnterpriseOption();
        signUpPage.fillForm("Test Company", "0115879213", "0746575656", "Kandy", "testcompany@gmail.com", "invalid-url");
        signUpPage.submitForm();
        // Add assertions to verify website URL format error
    }

    @Test(priority = 6)
    public void testLongInputsForFields() {
        signUpPage.selectEnterpriseOption();
        signUpPage.fillForm("Test Company".repeat(50), "0115879213".repeat(10), "0783231123".repeat(50), "Kandy".repeat(10), "testcompany@gmail.com".repeat(50), "https://example.com".repeat(10));
        signUpPage.submitForm();
        // Add assertions to verify field length limits
    }

    @Test(priority = 7)
    public void testUnsupportedCharactersInInputs() {
        signUpPage.selectEnterpriseOption();
        signUpPage.fillForm("!@#$%^&*()", "!@#$%^&*()", "!@#$%^&*()", "Kandy", "john@!#$%^&*().com", "!@#$%^&*()");
        signUpPage.submitForm();
        // Add assertions to verify unsupported characters error
    }

    @Test(priority = 8)
    public void testDuplicateEmailAddress() {
        signUpPage.selectEnterpriseOption();
        signUpPage.fillForm("Test Company", "0123452112", "0701233212", "Kandy", "ruvinyafernando@gmail.com", "https://example.com");
        signUpPage.submitForm();
        // Add assertions to verify duplicate email error
    }

    @Test(priority = 9)
    public void testInvalidDataTypeForFields() {
        signUpPage.selectEnterpriseOption();
        signUpPage.fillForm("12345", "67890", "12345", "12378", "12345@example.com", "67890");
        signUpPage.submitForm();
        // Add assertions to verify data type errors
    }

    @Test(priority = 10)
    public void testNoInputProvided() {
        signUpPage.selectEnterpriseOption();
        signUpPage.submitForm();
        // Add assertions to verify errors when no input is provided
    }
    
//    @Test(priority = 11)
//    public void testDataInputExceedingFieldLimits() {
//        signUpPage.selectEnterpriseOption();
//        signUpPage.fillForm("Test User".repeat(1000), "0112134871".repeat(1000), "Kandy".repeat(1000), "test@example.com".repeat(1000), "https://example.com".repeat(1000), "0312254990".repeat(1000));
//        signUpPage.submitForm();
//        // Add assertions to verify field limit errors
//    }

    @Test(priority = 12)
    public void testInvalidCountrySpecificFields() {
        signUpPage.selectEnterpriseOption();
        signUpPage.fillForm("Test Company", "+11768932671", "+94356789011", "Kandy", "testcompany@gmail.com", "https://example.com");
        signUpPage.submitForm();
        // Add assertions to verify country-specific field errors
    }
}


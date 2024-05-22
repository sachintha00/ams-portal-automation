package optiomaxWebTestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SignUpIndividualTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Janodya\\git\\optiomax-web-automation\\AutomateOptiomaxWeb\\Resource Files\\Chrome Driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://optiomax.com/");
       	driver.switchTo().frame(0); 
       	
    	WebElement chooseButton = driver.findElement(By.xpath("/html/body/section/div/div[2]/div[3]/a/button"));
    	chooseButton.click();
    	
    	Thread.sleep(5000);
    	
    	WebElement username = driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/div[1]/div/input"));
    	username.sendKeys("Test User");
    	
    	WebElement password = driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/div[2]/div/input"));
    	password.sendKeys("Testuser1234");
    	
    	WebElement nextButton = driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/button"));
    	nextButton.click();
    	
    	Thread.sleep(5000);
    }

    @AfterMethod
    public void tearDown() {
    	driver.switchTo().defaultContent();
        driver.quit();
    }

    public void fillForm(String name, String address, String website, String contactNo, String email, String contactPerson) {
        driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/div[1]/input")).sendKeys(name);
        driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/div[2]/input")).sendKeys(contactNo);
        driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/div[3]/input")).sendKeys(address);
        driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/div[4]/input")).sendKeys(email);
        driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/div[5]/input")).sendKeys(website);
        driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/div[6]/input")).sendKeys(contactPerson);
    }

    @Test(priority = 1)
    public void testValidInputs() {
        fillForm("Test User", "0765879210", "Galle", "ruvinyafernando@gmail.com", "https://example.com", "0745679123");
        driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/button[2]")).click();
        
        // Add assertions to verify successful submission
//        WebElement successMessage = driver.findElement(By.id("successMessage"));
//        Assert.assertTrue(successMessage.isDisplayed(), "Success message should be displayed");
        
    }
 
    @Test(priority = 2)
    public void testMissingRequiredFields() throws InterruptedException {
        fillForm("", "", "", "", "", "");
        driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/button[2]")).click();
        
        Thread.sleep(3000);
        
        // Verify that appropriate error messages are displayed for required fields
        WebElement errorMessageName = driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/div[1]/div"));
        Assert.assertTrue(errorMessageName.isDisplayed(), "Person name is required");
        
        WebElement errorMessageContactNo = driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/div[2]/div"));
        Assert.assertTrue(errorMessageContactNo.isDisplayed(), "Contact no is required");
        
        WebElement errorMessageAddress = driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/div[3]/div"));
        Assert.assertTrue(errorMessageAddress.isDisplayed(), "Address is required");
        
        WebElement errorMessageEmail = driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/div[4]/div"));
        Assert.assertTrue(errorMessageEmail.isDisplayed(), "Email address is required");
        
        WebElement errorMessageWebsite = driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/div[5]/div"));
        Assert.assertTrue(errorMessageWebsite.isDisplayed(), "Website is required");
        
        WebElement errorMessagePerson = driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/div[6]/div"));
        Assert.assertTrue(errorMessagePerson.isDisplayed(), "Contact person is required");
    }

    @Test(priority = 3)
    public void testInvalidEmailFormat() throws InterruptedException {
        fillForm("Test User", "0765879211", "Galle", "invalid-email", "https://example.com", "0745679121");
        driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/button[2]")).click();
        
        Thread.sleep(3000);
        
        // Add assertions to verify email format error
        driver.getPageSource().contains("Please include an '@' in the email address. 'invalid-email' is missing an '@'.");
        
    }

    @Test(priority = 4)
    public void testInvalidPhoneNumberFormat() {
        fillForm("Test User", "invalid-phone", "Galle", "ruvinyafernando@gmail.com", "https://example.com", "invalid-phone");
        driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/button[2]")).click();
        // Add assertions to verify phone number format error
    }

    @Test(priority = 5)
    public void testInvalidWebsiteURLFormat() {
        fillForm("Test User", "0765879213", "Galle", "ruvinyafernando@gmail.com", "invalid-url", "0745679124");
        driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/button[2]")).click();
        // Add assertions to verify website URL format error
    }

    @Test(priority = 6)
    public void testLongInputsForFields() {
        fillForm("Test User".repeat(50), "0765879213".repeat(10), "Galle".repeat(50), "ruvinyafernando@gmail.com".repeat(10), "https://example.com".repeat(50),  "0763452999".repeat(10));
        driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/button[2]")).click();
        // Add assertions to verify field length limits
    }

    @Test(priority = 7)
    public void testUnsupportedCharactersInInputs() {
        fillForm("!@#$%^&*()", "!@#$%^&*()", "Galle", "testuser@gmail.com", "john@!#$%^&*().com", "!@#$%^&*()");
        driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/button[2]")).click();
        // Add assertions to verify unsupported characters error
    }

    @Test(priority = 8)
    public void testDuplicateEmailAddress() {
        fillForm("Test User", "0783452112", "Galle", "ruvinyafernando@gmail.com", "https://example.com", "0712346754");
        driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/button[2]")).click();
        // Add assertions to verify duplicate email error
    }

    @Test(priority = 9)
    public void testInvalidDataTypeForFields() {
        fillForm("12345", "67890", "12345", "abcdef", "12345@example.com", "67890");
        driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/button[2]")).click();
        // Add assertions to verify data type errors
    }

    @Test(priority = 10)
    public void testNoInputProvided() {
        driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/button[2]")).click();
        // Add assertions to verify errors when no input is provided
    }

//    @Test(priority = 11)
//    public void testDataInputExceedingFieldLimits() {
//        fillForm("Test User".repeat(1000), "0762134871".repeat(1000), "Galle".repeat(1000), "test@example.com".repeat(1000), "https://example.com".repeat(1000), "0312254990".repeat(1000));
//        driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/button[2]")).click();
//        // Add assertions to verify field limit errors
//    }
    
    @Test(priority = 12)
    public void testInvalidCountrySpecificFields() {
        fillForm("Test User", "+94768932671", "Jaffna", "test@example.com", "https://example.com", "0712348789");
        driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/button[2]")).click();
        // Add assertions to verify country-specific field errors
    }
    
}
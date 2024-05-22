package optiomaxWebTestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SignUpEnterpriseTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Janodya\\git\\optiomax-web-automation\\AutomateOptiomaxWeb\\Resource Files\\Chrome Driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://optiomax.com/");
       	driver.switchTo().frame(0); 
       	
    	WebElement chooseButton = driver.findElement(By.xpath("/html/body/section/div/div[2]/div[4]/a/button"));
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

    public void fillForm(String companyName, String contactNo, String contactPerson, String address, String email, String website) {
    	
    	driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/div/div/label[2]")).click();
        driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/div[1]/input")).sendKeys(companyName);
        driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/div[2]/input")).sendKeys(contactNo);
        driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/div[3]/input")).sendKeys(contactPerson);
        driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/div[4]/input")).sendKeys(address);
        driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/div[5]/input")).sendKeys(email);
        driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/div[6]/input")).sendKeys(website);
    }

    @Test(priority = 1)
    public void testValidInputs() {
        fillForm("ABC Company", "0115734890", "0745679123", "Kandy", "abccompany@gmail.com", "https://abccompany.com");
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
        WebElement errorMessageCompanyName = driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/div[1]/div"));
        Assert.assertTrue(errorMessageCompanyName.isDisplayed(), "Company name is required");
        
        WebElement errorMessageContactNo = driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/div[2]/div"));
        Assert.assertTrue(errorMessageContactNo.isDisplayed(), "Contact no is required");
        
        WebElement errorMessageContactPerson = driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/div[3]/div"));
        Assert.assertTrue(errorMessageContactPerson.isDisplayed(), "Contact person is required");
        
        WebElement errorMessageAddress = driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/div[4]/div"));
        Assert.assertTrue(errorMessageAddress.isDisplayed(), "Address is required");
        
        WebElement errorMessageEmail = driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/div[5]/div"));
        Assert.assertTrue(errorMessageEmail.isDisplayed(), "Email address is required");
        
        WebElement errorMessageWebsite = driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/div[6]/div"));
        Assert.assertTrue(errorMessageWebsite.isDisplayed(), "Website is required");
    }

    @Test(priority = 3)
    public void testInvalidEmailFormat() throws InterruptedException {
        fillForm("Test Company", "0115879211", "0785642319", "Kandy", "invalid-email", "https://example.com");
        driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/button[2]")).click();
        
        Thread.sleep(3000);
        
        // Add assertions to verify email format error
        driver.getPageSource().contains("Please include an '@' in the email address. 'invalid-email' is missing an '@'.");
        
    }

    @Test(priority = 4)
    public void testInvalidPhoneNumberFormat() {
        fillForm("Test Company", "invalid-phone", "invalid-phone", "Kandy", "testcompany@gmail.com", "https://example.com");
        driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/button[2]")).click();
        // Add assertions to verify phone number format error
    }

    @Test(priority = 5)
    public void testInvalidWebsiteURLFormat() {
        fillForm("Test Company", "0115879213", "0746575656", "Kandy", "testcompany@gmail.com", "invalid-url");
        driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/button[2]")).click();
        // Add assertions to verify website URL format error
    }

    @Test(priority = 6)
    public void testLongInputsForFields() {
        fillForm("Test Company".repeat(50), "0115879213".repeat(10), "0783231123".repeat(50), "Kandy".repeat(10), "testcompany@gmail.com".repeat(50),  "https://example.com".repeat(10));
        driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/button[2]")).click();
        // Add assertions to verify field length limits
    }

    @Test(priority = 7)
    public void testUnsupportedCharactersInInputs() {
        fillForm("!@#$%^&*()", "!@#$%^&*()", "!@#$%^&*()", "Kandy", "john@!#$%^&*().com", "!@#$%^&*()");
        driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/button[2]")).click();
        // Add assertions to verify unsupported characters error
    }

    @Test(priority = 8)
    public void testDuplicateEmailAddress() {
        fillForm("Test Company", "0123452112", "0701233212", "Kandy", "ruvinyafernando@gmail.com", "https://example.com");
        driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/button[2]")).click();
        // Add assertions to verify duplicate email error
    }

    @Test(priority = 9)
    public void testInvalidDataTypeForFields() {
        fillForm("12345", "67890", "12345", "12378", "12345@example.com", "67890");
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
//        fillForm("Test User".repeat(1000), "0112134871".repeat(1000), "Kandy".repeat(1000), "test@example.com".repeat(1000), "https://example.com".repeat(1000), "0312254990".repeat(1000));
//        driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/button[2]")).click();
//        // Add assertions to verify field limit errors
//    }
    
    @Test(priority = 12)
    public void testInvalidCountrySpecificFields() {
        fillForm("Test Company", "+11768932671", "+94356789011", "Kandy", "testcompany@gmail.com", "https://example.com");
        driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/button[2]")).click();
        // Add assertions to verify country-specific field errors
    }
    
}

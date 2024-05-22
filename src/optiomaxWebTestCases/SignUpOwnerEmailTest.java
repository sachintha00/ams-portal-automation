package optiomaxWebTestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SignUpOwnerEmailTest {
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
    	username.sendKeys("NewUser");
    	
    	WebElement password = driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/div[2]/div/input"));
    	password.sendKeys("NewUser1234");
    	
    	WebElement nextButton = driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/button"));
    	nextButton.click();
    	
    	Thread.sleep(5000);
    	
        driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/div[1]/input")).sendKeys("NewUser");
        driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/div[2]/input")).sendKeys("0112254387");
        driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/div[3]/input")).sendKeys("Galle");
        driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/div[4]/input")).sendKeys("ruvinyafernando@gmail.com");
        driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/div[5]/input")).sendKeys("https://www.google.co.uk/");
        driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/div[6]/input")).sendKeys("0712347887");
        
    	WebElement nextBtn = driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/button[2]"));
    	nextBtn.click();
    }

    @Test(priority = 1)
    public void testValidEmailSignUp() throws InterruptedException {
        WebElement emailField = driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/div[1]/input")); 
        emailField.sendKeys("ruvinyafernando34@gmail.com");

        WebElement signUpButton = driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/div[2]/button[2]")); 
        signUpButton.click();
        
        Thread.sleep(3000);

        // Add logic to check if verification email is received

//        boolean isEmailReceived = checkEmailReceived("ruvinyafernando34@gmail.com");
//        Assert.assertTrue(isEmailReceived, "Verification email should be received.");
    }

    @Test(priority = 2)
    public void testInvalidEmailSignUp() throws InterruptedException {
        WebElement emailField = driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/div[1]/input")); 
        emailField.sendKeys("invalidemail@gmail.com");

        WebElement signUpButton = driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/div[2]/button[2]")); 
        signUpButton.click();
        
        Thread.sleep(3000);

        // Add logic to check if verification email is received
        boolean isEmailReceived = checkEmailReceived("invalidemail@gmail.com");
        Assert.assertFalse(isEmailReceived, "Verification email should not be received.");
    }

    @Test(priority = 3)
    public void testEmptyEmailField() throws InterruptedException {
        WebElement emailField = driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/div[1]/input")); 
        emailField.clear();

        WebElement signUpButton = driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/div[2]/button[2]")); 
        signUpButton.click();
        
        Thread.sleep(3000);

        WebElement errorMessage = driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/div[1]/div")); 
        Assert.assertTrue(errorMessage.isDisplayed(), "Owner email is required");
    }

    @Test(priority = 4)
    public void testInvalidDomainEmail() throws InterruptedException {
        WebElement emailField = driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/div[1]/input")); 
        emailField.sendKeys("invalid@domain");

        WebElement signUpButton = driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/div[2]/button[2]")); 
        signUpButton.click();
        
        Thread.sleep(3000);

//        WebElement errorMessage = driver.findElement(By.xpath("")); 
//        Assert.assertTrue(errorMessage.isDisplayed(), "Error message should be displayed.");
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


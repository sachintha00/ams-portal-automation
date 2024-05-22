package optiomaxWebTestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SignInTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() throws InterruptedException {
        // Set up the ChromeDriver
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Janodya\\git\\optiomax-web-automation\\AutomateOptiomaxWeb\\Resource Files\\Chrome Driver\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
        driver.get("http://optiomax.com/");
       	driver.switchTo().frame(0);
       	
       	WebElement choosePlan = driver.findElement(By.xpath("/html/body/section/div/div[2]/div[2]/a/button"));
       	choosePlan.click();
       	
       	Thread.sleep(3000);
       	
       	JavascriptExecutor js = (JavascriptExecutor) driver;
       	js.executeScript("window.scrollBy(0,250)", "");
       	
       	WebElement signInButton = driver.findElement(By.xpath("/html/body/section/div/div/div/div[3]/p/a"));
       	signInButton.click();
       	
       	Thread.sleep(3000);
       	
    }

    @AfterClass
    public void tearDown() {
    	driver.switchTo().defaultContent();
        driver.quit();
    }

    // Method to locate and return the email input element
    private WebElement getEmailElement() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/section/div/div/div/div[2]/form/div[1]/div/input")));
    }

    // Method to locate and return the password input element
    private WebElement getPasswordElement() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/section/div/div/div/div[2]/form/div[2]/div/input")));
    }

    // Method to locate and return the sign-in button element
    private WebElement getSignInButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/section/div/div/div/div[2]/form/button")));
    }

    // Helper method to perform sign-in
    private void signIn(String email, String password) throws InterruptedException {
    	Thread.sleep(3000);
        getEmailElement().clear();
        Thread.sleep(3000);
        getEmailElement().sendKeys(email);
        getPasswordElement().clear();
        Thread.sleep(3000);
        getPasswordElement().sendKeys(password);
        getSignInButton().click();
        Thread.sleep(3000);
    }

    private boolean isSignInSuccessful() {

        // return driver.getCurrentUrl().contains("Dashboard");
     
        return driver.getPageSource().contains("Dashboard");
    }

    @Test(priority = 1)
    public void testInvalidSignInWithIncorrectEmail() throws InterruptedException {
        signIn("invalid@example.com", "Ruvinya1234");
        Assert.assertFalse(isSignInSuccessful(), "Sign-in should fail with incorrect email.");
    }

    @Test(priority = 2)
    public void testInvalidSignInWithIncorrectPassword() throws InterruptedException {
        signIn("ruvinyafernando@gmail.com", "incorrect123");
        Thread.sleep(6000);
        Assert.assertFalse(isSignInSuccessful(), "Sign-in should fail with incorrect password.");
    }

    @Test(priority = 3)
    public void testInvalidSignInWithEmptyEmail() throws InterruptedException {
        signIn(" ", "Ruvinya1234");
        Assert.assertFalse(isSignInSuccessful(), "Sign-in should fail with empty email.");
    }

    @Test(priority = 4)
    public void testInvalidSignInWithEmptyPassword() throws InterruptedException {
        signIn("ruvinyafernando@gmail.com", " ");
        Assert.assertFalse(isSignInSuccessful(), "Sign-in should fail with empty password.");
    }

    @Test(priority = 5)
    public void testInvalidSignInWithInvalidEmail() throws InterruptedException {
        signIn("invalidemail", "Ruvinya1234");
        Assert.assertFalse(isSignInSuccessful(), "Sign-in should fail with invalid email format.");
    }
    
    @Test(priority = 6)
    public void testValidSignIn() throws InterruptedException {
        signIn("ruvinyafernando@gmail.com", "Ruvinya1234");
        Assert.assertTrue(isSignInSuccessful(), "Sign-in should be successful with correct email and password.");
    }

}


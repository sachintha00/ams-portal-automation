package optiomaxWebTestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class SignUpTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Janodya\\git\\optiomax-web-automation\\AutomateOptiomaxWeb\\Resource Files\\Chrome Driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://optiomax.com/");
       	driver.switchTo().frame(0);
       	
    	WebElement chooseButton = driver.findElement(By.xpath("/html/body/section/div/div[2]/div[1]/a/button"));
    	chooseButton.click();
    	
    	Thread.sleep(5000);
    }
    
    private void fillSignUpForm(String username, String password) {
        WebElement usernameField = driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/div[1]/div/input")); 
        WebElement passwordField = driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/div[2]/div/input")); 
        usernameField.clear();
        usernameField.sendKeys(username);
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    private void submitForm() {
        WebElement nextButton = driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/button")); 
        nextButton.click();
    }

    @Test(priority = 1)
    public void verifyAccountSuccessfulCreation() {
        fillSignUpForm("ValidUser", "Validuser123");
        submitForm();
        
        // Verify successful creation, assuming a success message or redirect
//        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(""))); 
//        Assert.assertTrue(successMessage.isDisplayed(), "Account creation failed.");
    }

//    @Test(priority = 2)
//    public void accountTypeSelection() {
//        WebElement accountTypeIndividual = driver.findElement(By.xpath("")); 
//        accountTypeIndividual.click();
//        WebElement accountTypeEnterprise = driver.findElement(By.xpath("//option[@value='Individual']")); // Adjust locator as necessary
//        accountTypeEnterprise.click();
//        Assert.assertEquals(accountTypeIndividual.getText(), "Individual", "Account type selection failed.");
//        Assert.assertEquals(accountTypeEnterprise.getText(), "Enterprise", "Account type selection failed.");
//    }

    @Test(priority = 3)
    public void verifySuccessfulNavigation() {
        fillSignUpForm("ValidUser2", "ValidPassword123");
        submitForm();
        
        // Verify navigation
//        driver.getPageSource().contains("");
    }

    @Test(priority = 4)
    public void verifyUnsuccessfulSignUpWithInvalidUsername() {
        fillSignUpForm("123", "ValidPassword123"); 
        submitForm();
        
        // Verify error message
//        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(""))); 
//        Assert.assertTrue(errorMessage.isDisplayed(), "Invalid username error not displayed.");
    }

    @Test(priority = 5)
    public void verifyUnsuccessfulSignUpWithInvalidPassword() {
        fillSignUpForm("ValidUser3", "short"); 
        submitForm();
        
        // Verify error message
//        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(""))); 
//        Assert.assertTrue(errorMessage.isDisplayed(), "Invalid password error not displayed.");
    }

    @Test(priority = 6)
    public void verifyUnsuccessfulSignUpWithEmptyUsername() throws InterruptedException {
        fillSignUpForm("", "ValidPassword123");
        submitForm();
        
        Thread.sleep(5000);
        
        // Verify error message
        WebElement errorMessage = driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/div[1]/div/div")); 
        Assert.assertTrue(errorMessage.isDisplayed(), "Username is required");
    }

    @Test(priority = 7)
    public void verifyUnsuccessfulSignUpWithEmptyPassword() throws InterruptedException {
        fillSignUpForm("validUser4", "");
        submitForm();
        
        Thread.sleep(5000);
        
        // Verify error message
        WebElement errorMessage = driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/div[2]/div/div")); 
        Assert.assertTrue(errorMessage.isDisplayed(), "Password is required");
    }

    @Test(priority = 8)
    public void verifyUnsuccessfulSignUpWithEmptyUsernameAndPassword() throws InterruptedException {
        fillSignUpForm("", "");
        submitForm();

        Thread.sleep(2000);
        
        // Verify error message
        WebElement usernameError = driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/div[1]/div/div")); 
        WebElement passwordError = driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/div[2]/div/div"));
        Assert.assertTrue(usernameError.isDisplayed(), "Username is required");
        Assert.assertTrue(passwordError.isDisplayed(), "Password is required");
    }

//    @Test(priority = 9)
//    public void verifyPasswordFieldObscurity() throws InterruptedException {
//        WebElement passwordField = driver.findElement(By.xpath("/html/body/section/div/div/div/div[2]/form/div[2]/div/input")); 
//        
//        String passwordFieldType = passwordField.getAttribute("type");
//        Thread.sleep(3000);
//        Assert.assertEquals(passwordFieldType, "password", " ");
//    }
    

    @Test(priority = 10)
    public void specialCharactersInUsername() {
        fillSignUpForm("user!@#", "ValidPassword123");
        submitForm();
        
        // Verify error message
//        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(""))); 
//        Assert.assertTrue(errorMessage.isDisplayed(), "Special characters in username error not displayed.");
    }

    @Test(priority = 11)
    public void exceedingCharacterLimitInUsername() {
        fillSignUpForm("VeryLongUsernameExceedingLimitaVeryLongUsernameExceedingLimit", "ValidPassword123"); 
        submitForm();
        
        // Verify error message
//        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(""))); 
//        Assert.assertTrue(errorMessage.isDisplayed(), "Exceeding character limit error not displayed.");
    }

    @Test(priority = 12)
    public void verifyUnsuccessfulSignUpWithWeakPassword() {
        fillSignUpForm("validUser5", "weak");
        submitForm();
        
        // Verify error message
//        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("")));
//        Assert.assertTrue(errorMessage.isDisplayed(), "Weak password error not displayed.");
    }

    @Test(priority = 13)
    public void verifyUnsuccessfulSignUpWithAlreadyTakenUsername() {
        fillSignUpForm("Ruvinya", "ValidPassword123"); 
        submitForm();
        
        // Verify error message
//        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("")));
//        Assert.assertTrue(errorMessage.isDisplayed(), "Username already taken error not displayed.");
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
        	driver.switchTo().defaultContent();
            driver.quit();
        }
    }
}

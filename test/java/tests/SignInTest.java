package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.SignInPage;

public class SignInTest {
    WebDriver driver;
    SignInPage signInPage;

    @BeforeClass
    public void setUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Janodya\\git\\optiomax-web-automation\\AutomateOptiomaxWeb\\Resource Files\\Chrome Driver\\chromedriver.exe");
        driver = new ChromeDriver();
        signInPage = new SignInPage(driver);
        driver.manage().window().maximize();
        signInPage.openSignInPage();
    }
 
    @AfterClass
    public void tearDown() {
        driver.switchTo().defaultContent();
        driver.quit();
    }

    private void signIn(String email, String password) throws InterruptedException {
        Thread.sleep(3000);
        signInPage.enterEmail(email);
        Thread.sleep(3000);
        signInPage.enterPassword(password);
        signInPage.clickSignIn();
        Thread.sleep(3000);
    }

    @Test(priority = 1)
    public void testInvalidSignInWithIncorrectEmail() throws InterruptedException {
        signIn("invalid@example.com", "Ruvinya1234");
        Assert.assertFalse(signInPage.isSignInSuccessful(), "Sign-in should fail with incorrect email.");
    }

    @Test(priority = 2)
    public void testInvalidSignInWithIncorrectPassword() throws InterruptedException {
        signIn("ruvinyafernando@gmail.com", "incorrect123");
        Thread.sleep(6000);
        Assert.assertFalse(signInPage.isSignInSuccessful(), "Sign-in should fail with incorrect password.");
    }

    @Test(priority = 3)
    public void testInvalidSignInWithEmptyEmail() throws InterruptedException {
        signIn(" ", "Ruvinya1234");
        Assert.assertFalse(signInPage.isSignInSuccessful(), "Sign-in should fail with empty email.");
    }

    @Test(priority = 4)
    public void testInvalidSignInWithEmptyPassword() throws InterruptedException {
        signIn("ruvinyafernando@gmail.com", " ");
        Assert.assertFalse(signInPage.isSignInSuccessful(), "Sign-in should fail with empty password.");
    }

    @Test(priority = 5)
    public void testInvalidSignInWithInvalidEmail() throws InterruptedException {
        signIn("invalidemail", "Ruvinya1234");
        Assert.assertFalse(signInPage.isSignInSuccessful(), "Sign-in should fail with invalid email format.");
    }

    @Test(priority = 6)
    public void testValidSignIn() throws InterruptedException {
        signIn("ruvinyafernando@gmail.com", "Ruvinya1234");
        Assert.assertTrue(signInPage.isSignInSuccessful(), "Sign-in should be successful with correct email and password.");
    }
}


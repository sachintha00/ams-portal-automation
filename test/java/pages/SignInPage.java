package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInPage {
    WebDriver driver;
    WebDriverWait wait;

    By choosePlanButton = By.xpath("/html/body/section/div/div[2]/div[2]/a/button");
    By signInButtonInitial = By.xpath("/html/body/section/div/div/div/div[3]/p/a");
    By emailField = By.xpath("/html/body/section/div/div/div/div[2]/form/div[1]/div/input");
    By passwordField = By.xpath("/html/body/section/div/div/div/div[2]/form/div[2]/div/input");
    By signInButton = By.xpath("/html/body/section/div/div/div/div[2]/form/button");

    public SignInPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public void openSignInPage() throws InterruptedException {
        driver.get("http://optiomax.com/");
        driver.switchTo().frame(0);
        wait.until(ExpectedConditions.elementToBeClickable(choosePlanButton)).click();
        Thread.sleep(3000);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)", "");
        
        wait.until(ExpectedConditions.elementToBeClickable(signInButtonInitial)).click();
        Thread.sleep(3000);
    }

    public void enterEmail(String email) {
        WebElement emailElement = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        emailElement.clear();
        emailElement.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement passwordElement = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        passwordElement.clear();
        passwordElement.sendKeys(password);
    }

    public void clickSignIn() {
        wait.until(ExpectedConditions.elementToBeClickable(signInButton)).click();
    }

    public boolean isSignInSuccessful() {
        // Check if the page contains "Dashboard"
        return driver.getPageSource().contains("Dashboard");
    }
}


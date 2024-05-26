package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SignUpPage {
    WebDriver driver;
 
    By chooseButton = By.xpath("/html/body/section/div/div[2]/div[1]/a/button");
    By usernameField = By.xpath("/html/body/section/div/div/div/div[2]/form/div[1]/div/input");
    By passwordField = By.xpath("/html/body/section/div/div/div/div[2]/form/div[2]/div/input");
    By nextButton = By.xpath("/html/body/section/div/div/div/div[2]/form/button");
    By usernameError = By.xpath("/html/body/section/div/div/div/div[2]/form/div[1]/div/div");
    By passwordError = By.xpath("/html/body/section/div/div/div/div[2]/form/div[2]/div/div");

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickChooseButton() {
        driver.findElement(chooseButton).click();
    }

    public void fillSignUpForm(String username, String password) {
        WebElement usernameElement = driver.findElement(usernameField);
        WebElement passwordElement = driver.findElement(passwordField);
        usernameElement.clear();
        usernameElement.sendKeys(username);
        passwordElement.clear();
        passwordElement.sendKeys(password);
    }

    public void submitForm() {
        driver.findElement(nextButton).click();
    }

    public boolean isUsernameErrorDisplayed() {
        return driver.findElement(usernameError).isDisplayed();
    }

    public boolean isPasswordErrorDisplayed() {
        return driver.findElement(passwordError).isDisplayed();
    }
}


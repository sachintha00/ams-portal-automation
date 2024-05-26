package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignUpEmailPage {
    WebDriver driver;

    public SignUpEmailPage(WebDriver driver) {
        this.driver = driver;
    }

    // Page Elements
    By chooseButton = By.xpath("/html/body/section/div/div[2]/div[3]/a/button");
    By usernameField = By.xpath("/html/body/section/div/div/div/div[2]/form/div[1]/div/input");
    By passwordField = By.xpath("/html/body/section/div/div/div/div[2]/form/div[2]/div/input");
    By nextButton = By.xpath("/html/body/section/div/div/div/div[2]/form/button");
    By ownerNameField = By.xpath("/html/body/section/div/div/div/div[2]/form/div[1]/input");
    By phoneField = By.xpath("/html/body/section/div/div/div/div[2]/form/div[2]/input");
    By addressField = By.xpath("/html/body/section/div/div/div/div[2]/form/div[3]/input");
    By emailField = By.xpath("/html/body/section/div/div/div/div[2]/form/div[4]/input");
    By websiteField = By.xpath("/html/body/section/div/div/div/div[2]/form/div[5]/input");
    By altPhoneField = By.xpath("/html/body/section/div/div/div/div[2]/form/div[6]/input");
    By signUpEmailField = By.xpath("/html/body/section/div/div/div/div[2]/form/div[1]/input");
    By signUpButton = By.xpath("/html/body/section/div/div/div/div[2]/form/div[2]/button[2]");
    By nextBtn = By.xpath("/html/body/section/div/div/div/div[2]/form/button[2]");
    By errorMessage = By.xpath("/html/body/section/div/div/div/div[2]/form/div[1]/div");

    // Page Actions
    public void clickChooseButton() {
        driver.findElement(chooseButton).click();
    }

    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    public void enterOwnerDetails(String ownerName, String phone, String address, String email, String website, String altPhone) {
        driver.findElement(ownerNameField).sendKeys(ownerName);
        driver.findElement(phoneField).sendKeys(phone);
        driver.findElement(addressField).sendKeys(address);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(websiteField).sendKeys(website);
        driver.findElement(altPhoneField).sendKeys(altPhone);
        driver.findElement(nextBtn).click();
    }

    public void enterSignUpEmail(String email) {
        driver.findElement(signUpEmailField).sendKeys(email);
    }

    public void clickSignUpButton() {
        driver.findElement(signUpButton).click();
    }

    public boolean isErrorMessageDisplayed() {
        return driver.findElement(errorMessage).isDisplayed();
    }
}



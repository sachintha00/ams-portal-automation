package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignUpIndividualPage {
    WebDriver driver;

    // Locators
    By chooseButton = By.xpath("/html/body/section/div/div[2]/div[3]/a/button");
    By usernameField = By.xpath("/html/body/section/div/div/div/div[2]/form/div[1]/div/input");
    By passwordField = By.xpath("/html/body/section/div/div/div/div[2]/form/div[2]/div/input");
    By nextButton = By.xpath("/html/body/section/div/div/div/div[2]/form/button");

    By nameField = By.xpath("/html/body/section/div/div/div/div[2]/form/div[1]/input");
    By contactNoField = By.xpath("/html/body/section/div/div/div/div[2]/form/div[2]/input");
    By addressField = By.xpath("/html/body/section/div/div/div/div[2]/form/div[3]/input");
    By emailField = By.xpath("/html/body/section/div/div/div/div[2]/form/div[4]/input");
    By websiteField = By.xpath("/html/body/section/div/div/div/div[2]/form/div[5]/input");
    By contactPersonField = By.xpath("/html/body/section/div/div/div/div[2]/form/div[6]/input");
    By submitButton = By.xpath("/html/body/section/div/div/div/div[2]/form/button[2]");

    // Constructor
    public SignUpIndividualPage(WebDriver driver) {
        this.driver = driver;
    }

    // Methods to interact with elements
    public void chooseIndividualSignUp() {
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

    public void fillForm(String name, String contactNo, String address, String email, String website, String contactPerson) {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(contactNoField).sendKeys(contactNo);
        driver.findElement(addressField).sendKeys(address);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(websiteField).sendKeys(website);
        driver.findElement(contactPersonField).sendKeys(contactPerson);
    }

    public void clickSubmitButton() {
        driver.findElement(submitButton).click();
    }

    // Methods to verify error messages (example for name field, extend for others)
    public boolean isErrorMessageDisplayed(By locator) {
        return driver.findElement(locator).isDisplayed();
    }
}

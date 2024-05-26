package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignUpEnterprisePage {
    WebDriver driver;

    // Locators
    By chooseButton = By.xpath("/html/body/section/div/div[2]/div[4]/a/button");
    By usernameField = By.xpath("/html/body/section/div/div/div/div[2]/form/div[1]/div/input");
    By passwordField = By.xpath("/html/body/section/div/div/div/div[2]/form/div[2]/div/input");
    By nextButton = By.xpath("/html/body/section/div/div/div/div[2]/form/button");
    By enterpriseSignUpOption = By.xpath("/html/body/section/div/div/div/div[2]/div/div/label[2]");
    By companyNameField = By.xpath("/html/body/section/div/div/div/div[2]/form/div[1]/input");
    By contactNoField = By.xpath("/html/body/section/div/div/div/div[2]/form/div[2]/input");
    By contactPersonField = By.xpath("/html/body/section/div/div/div/div[2]/form/div[3]/input");
    By addressField = By.xpath("/html/body/section/div/div/div/div[2]/form/div[4]/input");
    By emailField = By.xpath("/html/body/section/div/div/div/div[2]/form/div[5]/input");
    By websiteField = By.xpath("/html/body/section/div/div/div/div[2]/form/div[6]/input");
    By submitButton = By.xpath("/html/body/section/div/div/div/div[2]/form/button[2]");

    // Constructor
    public SignUpEnterprisePage(WebDriver driver) {
        this.driver = driver;
    }

    // Page actions
    public void openPage() {
        driver.get("http://optiomax.com/");
        driver.switchTo().frame(0);
    }

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

    public void selectEnterpriseOption() {
        driver.findElement(enterpriseSignUpOption).click();
    }

    public void fillForm(String companyName, String contactNo, String contactPerson, String address, String email, String website) {
        driver.findElement(companyNameField).sendKeys(companyName);
        driver.findElement(contactNoField).sendKeys(contactNo);
        driver.findElement(contactPersonField).sendKeys(contactPerson);
        driver.findElement(addressField).sendKeys(address);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(websiteField).sendKeys(website);
    }

    public void submitForm() {
        driver.findElement(submitButton).click();
    }

    public boolean isErrorMessageDisplayed(By locator) {
        return driver.findElement(locator).isDisplayed();
    }
}


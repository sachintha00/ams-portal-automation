package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PricingPage {
    WebDriver driver;

    public PricingPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    By introPlan = By.xpath("/html/body/section/div/div[2]/div[1]");
    By basePlan = By.xpath("/html/body/section/div/div[2]/div[2]");
    By popularPlan = By.xpath("/html/body/section/div/div[2]/div[3]");
    By enterprisePlan = By.xpath("/html/body/section/div/div[2]/div[4]");

    By monthlyToggle = By.xpath("/html/body/section/div/div[1]/div[2]/div/label[1]");
    By yearlyToggle = By.xpath("/html/body/section/div/div[1]/div[2]/div/label[2]");

    By introPlanFeatures = By.xpath("/html/body/section/div/div[2]/div[1]/div");
    By basePlanFeatures = By.xpath("/html/body/section/div/div[2]/div[2]/div");
    By popularPlanFeatures = By.xpath("/html/body/section/div/div[2]/div[3]/div");
    By enterprisePlanFeatures = By.xpath("/html/body/section/div/div[2]/div[4]/div");

    By introChooseButton = By.xpath("/html/body/section/div/div[2]/div[1]/a/button");
    By baseChooseButton = By.xpath("/html/body/section/div/div[2]/div[2]/a/button");
    By popularChooseButton = By.xpath("/html/body/section/div/div[2]/div[3]/a/button");
    By enterpriseChooseButton = By.xpath("/html/body/section/div/div[2]/div[4]/a/button");

    By firstPlanText = By.xpath("/html/body/section/div/div[2]/div[1]/p[1]");
    By secondPlanText = By.xpath("/html/body/section/div/div[2]/div[2]/p[1]");
    By thirdPlanText = By.xpath("/html/body/section/div/div[2]/div[3]/p[1]");
    By fourthPlanText = By.xpath("/html/body/section/div/div[2]/div[4]/p[1]");

    // Methods
    public WebElement getIntroPlan() {
        return driver.findElement(introPlan);
    }

    public WebElement getBasePlan() {
        return driver.findElement(basePlan);
    }

    public WebElement getPopularPlan() {
        return driver.findElement(popularPlan);
    }

    public WebElement getEnterprisePlan() {
        return driver.findElement(enterprisePlan);
    }

    public void toggleYearlyBilling() {
        driver.findElement(yearlyToggle).click();
    }

    public void toggleMonthlyBilling() {
        driver.findElement(monthlyToggle).click();
    }

    public WebElement getIntroPlanFeatures() {
        return driver.findElement(introPlanFeatures);
    }

    public WebElement getBasePlanFeatures() {
        return driver.findElement(basePlanFeatures);
    }

    public WebElement getPopularPlanFeatures() {
        return driver.findElement(popularPlanFeatures);
    }

    public WebElement getEnterprisePlanFeatures() {
        return driver.findElement(enterprisePlanFeatures);
    }

    public void clickIntroChooseButton() {
        driver.findElement(introChooseButton).click();
    }

    public void clickBaseChooseButton() {
        driver.findElement(baseChooseButton).click();
    }

    public void clickPopularChooseButton() {
        driver.findElement(popularChooseButton).click();
    }

    public void clickEnterpriseChooseButton() {
        driver.findElement(enterpriseChooseButton).click();
    }

    public String getFirstPlanText() {
        return driver.findElement(firstPlanText).getText();
    }

    public String getSecondPlanText() {
        return driver.findElement(secondPlanText).getText();
    }

    public String getThirdPlanText() {
        return driver.findElement(thirdPlanText).getText();
    }

    public String getFourthPlanText() {
        return driver.findElement(fourthPlanText).getText();
    }
}


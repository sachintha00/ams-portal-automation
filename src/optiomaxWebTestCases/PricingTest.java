package optiomaxWebTestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PricingTest {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Janodya\\git\\optiomax-web-automation\\AutomateOptiomaxWeb\\Resource Files\\Chrome Driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://optiomax.com/"); 
        driver.switchTo().frame(0);
    }

    @Test(priority = 1)
    public void testPricingPlansPresence() throws InterruptedException {
        WebElement introPlan = driver.findElement(By.xpath("/html/body/section/div/div[2]/div[1]"));
        WebElement basePlan = driver.findElement(By.xpath("/html/body/section/div/div[2]/div[2]"));
        WebElement popularPlan = driver.findElement(By.xpath("/html/body/section/div/div[2]/div[3]"));
        WebElement enterprisePlan = driver.findElement(By.xpath("/html/body/section/div/div[2]/div[4]"));

        Assert.assertTrue(introPlan.isDisplayed(), "Intro plan should be displayed.");
        Assert.assertTrue(basePlan.isDisplayed(), "Base plan should be displayed.");
        Assert.assertTrue(popularPlan.isDisplayed(), "Popular plan should be displayed.");
        Assert.assertTrue(enterprisePlan.isDisplayed(), "Enterprise plan should be displayed.");
        
        Thread.sleep(3000);
    }
    
    @Test(priority = 2)
    public void testBillingToggleFunctionality() {
    	
        WebElement monthlyToggle = driver.findElement(By.xpath("/html/body/section/div/div[1]/div[2]/div/label[1]"));
        WebElement yearlyToggle = driver.findElement(By.xpath("/html/body/section/div/div[1]/div[2]/div/label[2]"));

        // Click and verify toggle functionality
        yearlyToggle.click();
        // Add assertions
        
        monthlyToggle.click();
        // Add assertions
    }
    
    @Test(priority = 3)
    public void testPlanFeatures() {
        WebElement introPlanFeatures = driver.findElement(By.xpath("/html/body/section/div/div[2]/div[1]/div"));
        WebElement basePlanFeatures = driver.findElement(By.xpath("/html/body/section/div/div[2]/div[2]/div"));
        WebElement popularPlanFeatures = driver.findElement(By.xpath("/html/body/section/div/div[2]/div[3]/div"));
        WebElement enterprisePlanFeatures = driver.findElement(By.xpath("/html/body/section/div/div[2]/div[4]/div"));

        Assert.assertTrue(introPlanFeatures.isDisplayed(), "Intro plan features should be displayed.");
        Assert.assertTrue(basePlanFeatures.isDisplayed(), "Base plan features should be displayed.");
        Assert.assertTrue(popularPlanFeatures.isDisplayed(), "Popular plan features should be displayed.");
        Assert.assertTrue(enterprisePlanFeatures.isDisplayed(), "Enterprise plan features should be displayed.");
    }

   
    @Test(priority = 4)
    public void testPageUsabilityOnDifferentDevices() {
        int[] widths = {360, 768, 1024, 1280}; // Example screen widths for different devices
        for (int width : widths) {
            driver.manage().window().setSize(new Dimension(width, 800));
            // Add assertions to verify the usability and layout for each screen size
        }
        driver.manage().window().maximize();
    }

    @Test(priority = 5)
    public void testPricingPlansOrder() {
        WebElement firstPlan = driver.findElement(By.xpath("/html/body/section/div/div[2]/div[1]/p[1]"));
        WebElement secondPlan = driver.findElement(By.xpath("/html/body/section/div/div[2]/div[2]/p[1]"));
        WebElement thirdPlan = driver.findElement(By.xpath("/html/body/section/div/div[2]/div[3]/p[1]"));
        WebElement fourthPlan = driver.findElement(By.xpath("/html/body/section/div/div[2]/div[4]/p[1]"));

        Assert.assertEquals(firstPlan.getText(), "Intro", "The first plan should be Intro.");
        Assert.assertEquals(secondPlan.getText(), "Base", "The second plan should be Base.");
        Assert.assertEquals(thirdPlan.getText(), "Popular", "The third plan should be Popular.");
        Assert.assertEquals(fourthPlan.getText(), "Enterprise", "The fourth plan should be Enterprise.");
    }
    
    @Test(priority = 6)
    public void testChoosePlanButtonFunctionality() throws InterruptedException {
    	

    	
        WebElement introChooseButton = driver.findElement(By.xpath("/html/body/section/div/div[2]/div[1]/a/button"));
        introChooseButton.click();
   
        driver.navigate().refresh();
        
        Thread.sleep(6000);
        
        WebElement baseChooseButton = driver.findElement(By.xpath("/html/body/section/div/div[2]/div[2]/a/button"));
        baseChooseButton.click();
 
        driver.navigate().refresh();
        
        Thread.sleep(6000);
        
        WebElement popularChooseButton = driver.findElement(By.xpath("/html/body/section/div/div[2]/div[3]/a/button"));
        popularChooseButton.click();

        driver.navigate().refresh();
        
        Thread.sleep(6000);
        
        WebElement enterpriseChooseButton = driver.findElement(By.xpath("/html/body/section/div/div[2]/div[4]/a/button"));
        enterpriseChooseButton.click();

        driver.navigate().refresh();
        
        Thread.sleep(6000);

        Assert.assertTrue(introChooseButton.isDisplayed() && introChooseButton.isEnabled(), "Intro plan Choose Plan button should be visible and enabled.");
        Assert.assertTrue(baseChooseButton.isDisplayed() && baseChooseButton.isEnabled(), "Base plan Choose Plan button should be visible and enabled.");
        Assert.assertTrue(popularChooseButton.isDisplayed() && popularChooseButton.isEnabled(), "Popular plan Choose Plan button should be visible and enabled.");
        Assert.assertTrue(enterpriseChooseButton.isDisplayed() && enterpriseChooseButton.isEnabled(), "Enterprise plan Choose Plan button should be visible and enabled.");
       
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}


package tests;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.PricingPage;


public class PricingTest {

    WebDriver driver;
    PricingPage pricingPage;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Janodya\\git\\optiomax-web-automation\\AutomateOptiomaxWeb\\Resource Files\\Chrome Driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://optiomax.com/"); 
        driver.switchTo().frame(0);
        pricingPage = new PricingPage(driver);
    }

    @Test(priority = 1)
    public void testPricingPlansPresence() throws InterruptedException {
        Assert.assertTrue(pricingPage.getIntroPlan().isDisplayed(), "Intro plan should be displayed.");
        Assert.assertTrue(pricingPage.getBasePlan().isDisplayed(), "Base plan should be displayed.");
        Assert.assertTrue(pricingPage.getPopularPlan().isDisplayed(), "Popular plan should be displayed.");
        Assert.assertTrue(pricingPage.getEnterprisePlan().isDisplayed(), "Enterprise plan should be displayed.");
        
        Thread.sleep(3000);
    }
    
    @Test(priority = 2)
    public void testBillingToggleFunctionality() {
        pricingPage.toggleYearlyBilling();
        // Add assertions to verify yearly billing state
        
        pricingPage.toggleMonthlyBilling();
        // Add assertions to verify monthly billing state
    }
    
    @Test(priority = 3)
    public void testPlanFeatures() {
        Assert.assertTrue(pricingPage.getIntroPlanFeatures().isDisplayed(), "Intro plan features should be displayed.");
        Assert.assertTrue(pricingPage.getBasePlanFeatures().isDisplayed(), "Base plan features should be displayed.");
        Assert.assertTrue(pricingPage.getPopularPlanFeatures().isDisplayed(), "Popular plan features should be displayed.");
        Assert.assertTrue(pricingPage.getEnterprisePlanFeatures().isDisplayed(), "Enterprise plan features should be displayed.");
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
        Assert.assertEquals(pricingPage.getFirstPlanText(), "Intro", "The first plan should be Intro.");
        Assert.assertEquals(pricingPage.getSecondPlanText(), "Base", "The second plan should be Base.");
        Assert.assertEquals(pricingPage.getThirdPlanText(), "Popular", "The third plan should be Popular.");
        Assert.assertEquals(pricingPage.getFourthPlanText(), "Enterprise", "The fourth plan should be Enterprise.");
    }
    
    @Test(priority = 6)
    public void testChoosePlanButtonFunctionality() throws InterruptedException {
        pricingPage.clickIntroChooseButton();
        driver.navigate().refresh();
        Thread.sleep(6000);
        
        pricingPage.clickBaseChooseButton();
        driver.navigate().refresh();
        Thread.sleep(6000);
        
        pricingPage.clickPopularChooseButton();
        driver.navigate().refresh();
        Thread.sleep(6000);
        
        pricingPage.clickEnterpriseChooseButton();
        driver.navigate().refresh();
        Thread.sleep(6000);

        Assert.assertTrue(pricingPage.getIntroPlan().isDisplayed() && pricingPage.getIntroPlan().isEnabled(), "Intro plan Choose Plan button should be visible and enabled.");
        Assert.assertTrue(pricingPage.getBasePlan().isDisplayed() && pricingPage.getBasePlan().isEnabled(), "Base plan Choose Plan button should be visible and enabled.");
        Assert.assertTrue(pricingPage.getPopularPlan().isDisplayed() && pricingPage.getPopularPlan().isEnabled(), "Popular plan Choose Plan button should be visible and enabled.");
        Assert.assertTrue(pricingPage.getEnterprisePlan().isDisplayed() && pricingPage.getEnterprisePlan().isEnabled(), "Enterprise plan Choose Plan button should be visible and enabled.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

package Home;

import Frameworks.Framework;
import Helper.HelperClass;
import Pages.HomePage;
import Sections.Ads;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.testng.Tag;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.FileNotFoundException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

@Epic("Automation Exercise") 
@Feature("Home Page")
public class HomeTest {
    Framework F;
    WebDriver Browser;
    HomePage home;  
    Ads ad;
    SubscribeClass[] Subscribe;

    @BeforeTest
    public void DefineTest2(){
        System.out.println("Test 2: This test is for testing different actions on home page and navigation buttons.");
        System.out.println("It includes test cases: 6, 7, 10, 11, 25, 26.");
    }
    
    @BeforeClass
    public void Launch() throws FileNotFoundException {
        F = new Framework();
        Browser = F.getDriver();
        
        home = new HomePage(Browser);
        ad = new Ads(Browser);
        
        Subscribe = HelperClass.ReadUsers("SubscriptionData.json", SubscribeClass[].class);
        
        System.out.println("Class 1: This class is for testing small actions related to home page like subscribe and scroll. Test cases 10,25,26");
    }
        
    @BeforeMethod
    public void CheckHomePage(){        
        F.navigateToURL(home.GetHomeUrl());
        Assert.assertTrue(home.HomePageVisibilty());
        ad.CheckAd();
    }
    
    @AfterMethod
    public void ReturnToHomePage(){
        F.newTab();
    }
        
    @AfterClass
    public void Quit(){
        F.closeBrowser();
        System.out.println("Class 1 is Done");
    }
    
    @AfterTest
    public void Test2Done(){
        System.out.println("Test 2 is Done");
    }
    
    @DataProvider(name = "SubscribeData")
    public Object[] SubscribeData() throws FileNotFoundException {
        return Subscribe;
    }
    
    @Test(dataProvider="SubscribeData")
    @Tag("Subscription")
    @Story("User scrolls to the home page bottom, "
            + "enters their email and submit it, "
            + "and waits for success message visibility.")
    @Severity(SeverityLevel.MINOR)  
    @Description("This test verifies that user can subscribe from home page.")
    public void TC10_HomeSubscription(SubscribeClass subscribe){
        home.ScrollDown();
        ad.CheckAd();
        Assert.assertTrue(home.SubscriptionVisibilty(),"Text 'SUBSCRIPTION' is not visible");
        
        home.Subscribe(subscribe.SubscriptionEmail);
        Assert.assertTrue(home.SuccessMessageVisibilty(),"Subscription failed");
    }
    
    @Test
    @Tag("Scroll")
    @Story("User scrolls to the home page bottom, "
        + "checks that they reached the subscription section, "
        + "clicks the scroll up arrow, "
        + "and checks that the 'Full-Fledged practice website for Automation Engineers' text is visible on screen.")
    @Severity(SeverityLevel.MINOR)  
    @Description("This test verifies that user can scroll up using the scroll up arrow.")
    public void TC25_ArrowScrollUp(){
        home.ScrollDown();
        ad.CheckAd();
        Assert.assertTrue(home.SubscriptionVisibilty(),"Text 'SUBSCRIPTION' is not visible");
        home.ScrollButtonClick();
        Assert.assertTrue(home.FullFledgedVisibilty(),"'Full-Fledged practice website for Automation Engineers' text is not visible");
    }
    
    @Test
    @Tag("Scroll")
    @Story("User scrolls to the home page bottom, "
        + "checks that they reached the subscription section, "
        + "scrolls back up without using the arrow, "
        + "and checks that the 'Full-Fledged practice website for Automation Engineers' text is visible on screen.")
    @Severity(SeverityLevel.MINOR)  
    @Description("This test verifies that user can scroll up without using the arrow.")
    public void TC26_ScrollUp(){
        home.ScrollDown();
        ad.CheckAd();
        Assert.assertTrue(home.SubscriptionVisibilty(),"Text 'SUBSCRIPTION' is not visible");
        home.ScrollUp();
        Assert.assertTrue(home.FullFledgedVisibilty(),"'Full-Fledged practice website for Automation Engineers' text is not visible");
    }
    
}

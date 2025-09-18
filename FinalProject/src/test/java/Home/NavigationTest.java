package Home;

import Helper.HelperClass;
import Frameworks.Framework;
import Pages.HomePage;
import Pages.ContactUsPage;
import Sections.NavigationBar;
import Pages.TestCasesPage;
import Pages.CartPage;
import Sections.Ads;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.testng.Tag;
import java.io.FileNotFoundException;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Epic("Automation Exercise") 
@Feature("Navigation Bar")
public class NavigationTest {
          
    Framework F;
    WebDriver Browser;
    HomePage home;
    NavigationBar nav;
    ContactUsPage contact;
    TestCasesPage tests;
    CartPage cart;
    Ads ad;
    ContactUsClass[] ContactUs;
    SubscribeClass[] Subscribe;
    
    @BeforeClass
    public void Setup() throws FileNotFoundException{
        F = new Framework();
        Browser = F.getDriver();
        
        nav = new NavigationBar(Browser);
        
        ad = new Ads(Browser);
        
        home = new HomePage(Browser);
        contact = new ContactUsPage(Browser);
        tests = new TestCasesPage(Browser);
        cart = new CartPage(Browser);
        
        ContactUs = HelperClass.ReadUsers("ContactUsData.json", ContactUsClass[].class);
        Subscribe = HelperClass.ReadUsers("SubscriptionData.json", SubscribeClass[].class);
        
        System.out.println("Class 2: This class is for testing actions related to other pages on the navigation bar. Test cases 6,7,11");
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
        System.out.println("Class 2 is Done.");
    }
    
    @DataProvider(name = "ContactUsData")
    public Object[] ContactUsData() throws FileNotFoundException {
        return ContactUs;
    }
    
    @DataProvider(name = "SubscribeData")
    public Object[] SubscribeData() throws FileNotFoundException {
        return Subscribe;
    }

    @Test(dataProvider="ContactUsData")
    @Tag("Navigation")
    @Story("User navigates to Contact Us page, "
         + "checks that the page is opened successfully, "
         + "fills the form with all required data, "
         + "uploads a file and submits, "
         + "and finally waits for success message visibility.")
    @Severity(SeverityLevel.NORMAL)  
    @Description("This test verifies that user can subscribe from cart page.")
    public void TC6_ContactUsForm(ContactUsClass contactus){
        nav.ContactUsClick();
        Assert.assertTrue(contact.InTouchVisibility());
        contact.FillForm(contactus.Name, contactus.Email, contactus.Subject, contactus.Message);
        String projectPath = System.getProperty("user.dir");
        String filePath = projectPath + contactus.FilePath;
        contact.ChooseFile(filePath);
        contact.Submit();
     
        Assert.assertTrue(contact.SuccessVisibility());
        
        contact.HomeClick();
        
        Assert.assertTrue(home.HomePageVisibilty());
    }
    
    @Test
    @Tag("Navigation")
    @Story("User navigates to Test Cases page, "
         + "and checks that the page is opened successfully.")
    @Severity(SeverityLevel.MINOR)  
    @Description("This test verifies that user can navigate to test cases page.")
    public void TC7_TestCasesPage(){
        nav.TestCasesClick();
        Assert.assertTrue(tests.TestsTextVisibility());
    }
    
    @Test(dataProvider="SubscribeData")
    @Tag("Subscription")
    @Story("User navigates to cart page, "
         + "scrolls to the bottom, "
         + "enters their email and submit it, "
         + "and waits for success message visibility.")
    @Severity(SeverityLevel.MINOR)  
    @Description("This test verifies that user can subscribe from cart page.")
    public void TC11_CartSubscription(SubscribeClass subscribe){
        nav.CartClick();
        cart.ScrollDown();
        ad.CheckAd();
        Assert.assertTrue(cart.SubscriptionVisibilty(),"Text 'SUBSCRIPTION' is not visible");
        
        cart.Subscribe(subscribe.SubscriptionEmail);
        Assert.assertTrue(cart.SuccessMessageVisibilty(),"Subscription failed");
    }
    
}

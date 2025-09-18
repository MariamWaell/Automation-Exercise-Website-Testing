package Checkout;

import Helper.HelperClass;
import Frameworks.Framework;

import Pages.AccountCreatedPage;
import Pages.AccountDeletedPage;
import Pages.CartPage;
import Pages.CheckoutPage;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.PaymentDonePage;
import Pages.PaymentPage;
import Pages.SignupPage;
import Register.NewRegisterClass;
import Sections.Ads;
import Sections.NavigationBar;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import java.io.FileNotFoundException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Epic("Automation Exercise") 
@Feature("Checkout")
public class CheckoutTest {
    
    Framework F;
    WebDriver Browser;
    HomePage home;
    NavigationBar nav;
    LoginPage login;
    SignupPage signup;
    CartPage cart;
    CheckoutPage checkout;
    AccountCreatedPage create;
    AccountDeletedPage delete;
    PaymentPage payment;
    PaymentDonePage done;
    Ads ad;
    NewRegisterClass[] NewUser;
    PaymentClass[] PaymentData;
    
    @BeforeTest
    public void DefineTest4(){
        System.out.println("Test 4: This test is for testing different checkout actions.");
        System.out.println("It includes test cases: 14, 15, 16, 23, 24.");
    }
    
    @BeforeClass
    public void Launch()throws FileNotFoundException{
        F = new Framework();
        Browser = F.getDriver();
        
        home = new HomePage(Browser);
        nav = new NavigationBar(Browser);
        login = new LoginPage(Browser); 
        signup = new SignupPage(Browser);
        cart = new CartPage(Browser);
        checkout = new CheckoutPage(Browser);
        payment = new PaymentPage(Browser);
        done = new PaymentDonePage(Browser);
        ad = new Ads(Browser);

        NewUser = HelperClass.ReadUsers("NewRegisterData.json", NewRegisterClass[].class);
        PaymentData = HelperClass.ReadUsers("PaymentData.json", PaymentClass[].class);
    }
    
    @BeforeMethod
    public void GoToHome(){
        F.navigateToURL(home.GetHomeUrl());
        Assert.assertTrue(home.HomePageVisibilty());
    }
    
    @AfterMethod
    public void GoToNewHome(){
        F.newTab();
    }
    
    @AfterClass
    public void Quit(){
        F.closeBrowser();
    }
    
    @AfterTest
    public void Test4Done(){
        System.out.println("Test 4 is Done");
    }
    
    @DataProvider(name = "NewSignupData")
    public Object[] NewSignupData() {
        return NewUser;
    }
    
    @DataProvider(name = "PaymentData")
    public Object[] PaymentData() {
        return PaymentData;
    }

    @Test(dataProvider="PaymentData")
    @Story("User adds some products from the home page to the cart, "
            + "navigates to the cart page, "
            + "clicks proceed to checkout, "
            + "chooses to signup, "
            + "creates a new account, "
            + "navigates again to the cart page and checkout, "
            + "checks that their addresss details are accurate, "
            + "adds a comment and place the order, "
            + "enters all payment related data and confirm payment, "
            + "waits for the success message, "
            + "and finally, deletes the account and confirm that it is deleted.")
    @Severity(SeverityLevel.CRITICAL)  
    @Description("This test verifies that user can place an order by signing up while checking out.")
    public void TC14_RegisterAfterCheckout(PaymentClass Payment){
        
        ad.CheckAd();       
        home.AddSelectedProductToCart(6);
        home.ContinueShopping();
        ad.CheckAd();
        home.AddSelectedProductToCart(18);
        home.ContinueShopping();
        
        nav.CartClick();
        Assert.assertTrue(cart.CartPageVisibility(),"Cart Page is not visible");
        
        cart.CheckOut();
        cart.RegisterLoginClick();
        
        Assert.assertTrue(login.SignUpTextVisibility());
        
        login.Signup("karim","karim@ok.com");
        
        Assert.assertTrue(signup.EnterInfoTextVisibility());
        
        signup.AccountInformation("Mr", "Karim", "ok", 21, 10 , "2003");
        signup.AddressInformation("Karim", "Ahmed", "Tech", "456 Street", "Apt 10", "United States", "California", 
                "LA", "65m4", "3456");
        
        create = new  AccountCreatedPage(Browser);
        
        Assert.assertTrue(create.AccountCreatedVisibility());
        create.ContinueClick();
        
        Assert.assertTrue(nav.LoggedInAsVisibility());
        
        nav.CartClick();
        cart.CheckOut();
        
        Assert.assertTrue(checkout.AddressDetailsVisibility(),"Address Details are not available");
        
        checkout.AddComment(Payment.Comment);
        checkout.PlaceOrder();
        
        payment.EnterPaymentData(Payment.NameOnCard, Payment.CardNo, Payment.Cvc , Payment.ExpiryMonth, Payment.ExpiryYear);
        ad.CheckAd();
        payment.ConfirmPayment();

        Assert.assertTrue(done.CheckPaymentSuccess(),"Your Payment has been failed");
        
        nav.DeleteAccount();
        
        delete = new  AccountDeletedPage(Browser);

        Assert.assertTrue(delete.AccountDeletedVisibility());
        delete.ContinueClick();
    }
     
    @Test(dataProvider="PaymentData")
    @Story("User naviagte to login/signup page, "
         + "creates a new account, "
         + "adds some products from the home page to the cart, "
         + "navigates to the cart page, "
         + "clicks proceed to checkout, "
         + "checks that their addresss details are accurate, "
         + "adds a comment and place the order, "
         + "enters all payment related data and confirm payment, "
         + "waits for the success message, "
         + "and finally, deletes the account and confirm that it is deleted.")
    @Severity(SeverityLevel.CRITICAL)  
    @Description("This test verifies that user can sign up then proceed to check out and place an order.")
    public void TC15_RegisterBeforeCheckout(PaymentClass Payment){
        
        nav.LoginClick();
        Assert.assertTrue(login.SignUpTextVisibility());
        
        login.Signup("mariam","mariam@mm.com");
        
        Assert.assertTrue(signup.EnterInfoTextVisibility());
        
        signup.AccountInformation("Mrs", "Mariam", "mm", 29, 12 , "2000");
        signup.AddressInformation("Mariam", "Salvatore", "Tech", "456 Street", "Apt 10", "United States", "California", 
                "LA", "65m4", "3456");
        
        create = new  AccountCreatedPage(Browser);
        
        Assert.assertTrue(create.AccountCreatedVisibility());
        create.ContinueClick();
        
        Assert.assertTrue(nav.LoggedInAsVisibility());
        
        ad.CheckAd();
        home.AddSelectedProductToCart(8);
        home.ContinueShopping();
        ad.CheckAd();
        home.AddSelectedProductToCart(3);
        home.ContinueShopping();
        
        nav.CartClick();
        Assert.assertTrue(cart.CartPageVisibility(),"Cart Page is not visible");
        
        cart.CheckOut();
        
        Assert.assertTrue(checkout.AddressDetailsVisibility(),"Address Details are not available");
        
        checkout.AddComment(Payment.Comment);
        checkout.PlaceOrder();
        
        payment.EnterPaymentData(Payment.NameOnCard, Payment.CardNo, Payment.Cvc , Payment.ExpiryMonth, Payment.ExpiryYear);
        ad.CheckAd();
        payment.ConfirmPayment();

        Assert.assertTrue(done.CheckPaymentSuccess(),"Your Payment has been failed");
        
        nav.DeleteAccount();
        
        delete = new  AccountDeletedPage(Browser);

        Assert.assertTrue(delete.AccountDeletedVisibility());
        delete.ContinueClick();
    }
      
    @Test(dataProvider="PaymentData")
    @Story("User naviagte to login/signup page, "
         + "logs in with correct data "
         + "adds some products from the home page to the cart, "
         + "navigates to the cart page, "
         + "clicks proceed to checkout, "
         + "checks that their addresss details are accurate, "
         + "adds a comment and place the order, "
         + "enters all payment related data and confirm payment, "
         + "waits for the success message, "
         + "and finally, deletes the account and confirm that it is deleted.")
    @Severity(SeverityLevel.CRITICAL)  
    @Description("This test verifies that user can login then proceed to check out and place an order.")
    public void TC16_LoginBeforeCheckout(PaymentClass Payment){
        
        nav.LoginClick();
        
        //new account to use it in login (not a part of this test case)
        NewSignUp();
        
        nav.LoginClick();
        
        login.Login("st@f.com","st");
        F.implicitWait(10);
        
        ad.CheckAd();   
        home.AddSelectedProductToCart(11);
        home.ContinueShopping();
        ad.CheckAd();
        home.AddSelectedProductToCart(22);
        home.ContinueShopping();
        
        nav.CartClick();
        Assert.assertTrue(cart.CartPageVisibility(),"Cart Page is not visible");
        
        cart.CheckOut();
        
        Assert.assertTrue(checkout.AddressDetailsVisibility(),"Address Details are not available");
        
        checkout.AddComment(Payment.Comment);
        checkout.PlaceOrder();
        
        payment.EnterPaymentData(Payment.NameOnCard, Payment.CardNo, Payment.Cvc , Payment.ExpiryMonth, Payment.ExpiryYear);
        ad.CheckAd();
        payment.ConfirmPayment();

        Assert.assertTrue(done.CheckPaymentSuccess(),"Your Payment has been failed");
        
        nav.DeleteAccount();
        
        delete = new  AccountDeletedPage(Browser);

        Assert.assertTrue(delete.AccountDeletedVisibility());
        delete.ContinueClick();
    }   
    
    @Test(dataProvider="NewSignupData")
    @Story("User naviagte to login/signup page, "
         + "creates a new account, "
         + "adds some products from the home page to the cart, "
         + "navigates to the cart page, "
         + "clicks proceed to checkout, "
         + "checks that their addresss details are the same as the ones entered during signing up, "
         + "and finally, deletes the account and confirm that it is deleted.")
    @Severity(SeverityLevel.CRITICAL)  
    @Description("This test verifies that user's delivery and billing addresses are the same as the address entered at the account registration time.")
    public void TC23_VerifyAddressDetails(NewRegisterClass newuser){
        nav.LoginClick();
        login.Signup(newuser.SignUpName,newuser.SignUpEmail);
        
        Assert.assertTrue(signup.EnterInfoTextVisibility());
        
        signup.AccountInformation(newuser.Title, newuser.SignUpName, newuser.Password, newuser.BirthDay, newuser.BirthMonth, newuser.BirthYear);
        signup.AddressInformation(newuser.FirstName, newuser.LastName, newuser.Company, newuser.Address, newuser.Address2, newuser.Country, newuser.State, 
                newuser.City, newuser.ZipCode, newuser.MobileNumber);
        
        create = new  AccountCreatedPage(Browser);
        
        Assert.assertTrue(create.AccountCreatedVisibility());
        create.ContinueClick();
        
        Assert.assertTrue(nav.LoggedInAsVisibility());
        
        ad.CheckAd();
        
        home.AddSelectedProductToCart(8);
        home.ContinueShopping();
        home.AddSelectedProductToCart(3);
        home.ContinueShopping();
        
        nav.CartClick();
        Assert.assertTrue(cart.CartPageVisibility(),"Cart Page is not visible");
        
        cart.CheckOut();
        
        Assert.assertTrue(checkout.CheckDeliveryAddressDetails(newuser.Title, newuser.FirstName, newuser.LastName, 
                newuser.Company, newuser.Address, newuser.Address2, newuser.City, newuser.State, 
                newuser.ZipCode, newuser.Country, newuser.MobileNumber),"Delivery address is not the same address filled at the time registration of account");
        
        Assert.assertTrue(checkout.CheckBillingAddressDetails(newuser.Title, newuser.FirstName, newuser.LastName, 
                newuser.Company, newuser.Address, newuser.Address2, newuser.City, newuser.State, 
                newuser.ZipCode, newuser.Country, newuser.MobileNumber),"Billing address is not the same address filled at the time registration of account");
        
        nav.DeleteAccount();
        
        delete = new  AccountDeletedPage(Browser);

        Assert.assertTrue(delete.AccountDeletedVisibility());
        delete.ContinueClick();    
    }
      
    @Test(dataProvider="PaymentData")
    @Story("User adds some products from the home page to the cart, "
            + "navigates to the cart page, "
            + "clicks proceed to checkout, "
            + "chooses to signup, "
            + "creates a new account, "
            + "navigates again to the cart page and checkout, "
            + "checks that their addresss details are accurate, "
            + "adds a comment and place the order, "
            + "enters all payment related data and confirm payment, "
            + "waits for the success message, "
            + "clicks on the download invoice button and continues, "
            + "and finally, deletes the account and confirm that it is deleted.")
    @Severity(SeverityLevel.NORMAL)  
    @Description("This test verifies that user can download the payment invoice.")
    public void TC24_DownloadInvoice(PaymentClass Payment){
        
        ad.CheckAd();
        
        home.AddSelectedProductToCart(1);
        home.ContinueShopping();
        home.AddSelectedProductToCart(2);
        home.ContinueShopping();
        
        nav.CartClick();
        Assert.assertTrue(cart.CartPageVisibility(),"Cart Page is not visible");
        
        cart.CheckOut();
        cart.RegisterLoginClick();
        
        Assert.assertTrue(login.SignUpTextVisibility());
        
        login.Signup("laila","laila@lol.com");
        
        Assert.assertTrue(signup.EnterInfoTextVisibility());
        
        signup.AccountInformation("Mrs", "Laila", "lol", 5, 9 , "2010");
        signup.AddressInformation("Laila", "Gilbert", "Tech", "456 Street", "Apt 10", "United States", "California", 
                "LA", "65m4", "3456");
        
        create = new  AccountCreatedPage(Browser);
        
        Assert.assertTrue(create.AccountCreatedVisibility());
        create.ContinueClick();
        
        Assert.assertTrue(nav.LoggedInAsVisibility());
        
        nav.CartClick();
        
        cart.CheckOut();
        
        Assert.assertTrue(checkout.AddressDetailsVisibility(),"Address Details are not available");
        
        checkout.AddComment(Payment.Comment);
        checkout.PlaceOrder();
        
        payment.EnterPaymentData(Payment.NameOnCard, Payment.CardNo, Payment.Cvc , Payment.ExpiryMonth, Payment.ExpiryYear);
        ad.CheckAd();
        payment.ConfirmPayment();
        
        Assert.assertTrue(done.CheckPaymentSuccess(),"Your Payment has been failed");
        
        done.DownloadInvoice();
        done.ContinueClick();
        
        nav.DeleteAccount();
        
        delete = new  AccountDeletedPage(Browser);

        Assert.assertTrue(delete.AccountDeletedVisibility());
        delete.ContinueClick();
    }
        
    
    //SignUp Method for login instead of making many accounts that will be deleted
    public void NewSignUp(){
        Assert.assertTrue(login.SignUpTextVisibility());
        
        login.Signup("Stefan","st@f.com");
        
        Assert.assertTrue(signup.EnterInfoTextVisibility());
        
        signup.AccountInformation("Mr", "Stefan", "st", 26, 6 , "2006");
        signup.AddressInformation("Stefan", "Forbes", "Tech", "456 Street", "Apt 10", "United States", "California", 
                "LA", "65m4", "3456");
        
        create = new  AccountCreatedPage(Browser);
        
        Assert.assertTrue(create.AccountCreatedVisibility());
        create.ContinueClick();
        
        Assert.assertTrue(nav.LoggedInAsVisibility());
        
        nav.LogOut();
    }
    
}

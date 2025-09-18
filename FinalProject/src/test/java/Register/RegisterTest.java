package Register;


import Helper.HelperClass;
import Frameworks.Framework;
import Pages.AccountCreatedPage;
import Pages.AccountDeletedPage;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.SignupPage;
import Sections.NavigationBar;
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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Epic("Automation Exercise") 
@Feature("Sign up")
public class RegisterTest {

    Framework F;
    WebDriver Browser;
    HomePage home;
    LoginPage login;
    NavigationBar nav;
    SignupPage signup;
    AccountCreatedPage create;
    AccountDeletedPage delete;
    NewRegisterClass[] NewUser;
    ExistRegisterClass[] ExistUser;

    @BeforeTest
    public void DefineTest1(){
        System.out.println("Test 1: This test is for testing different users through register and login actions.");
        System.out.println("It includes test cases: 1, 2, 3, 4, 5");
    }
    
    @BeforeClass
    public void Launch() throws FileNotFoundException{
        F = new Framework();
        Browser = F.getDriver();
    
        home = new HomePage(Browser);
        login = new LoginPage(Browser); 
        nav = new NavigationBar(Browser);
        signup = new SignupPage(Browser);
        
        NewUser = HelperClass.ReadUsers("NewRegisterData.json", NewRegisterClass[].class);
        ExistUser = HelperClass.ReadUsers("ExistUserData.json", ExistRegisterClass[].class);
        
        System.out.println("Class 1: This class is for testing SignUp. Test cases 1,5");
    }
    
    @BeforeMethod
    public void GoToSignPage(){
        F.navigateToURL(home.GetHomeUrl());
        Assert.assertTrue(home.HomePageVisibilty());
        nav.LoginClick();       
    }
    
    @AfterMethod
    public void ReturnToHome(){
       F.newTab();
    }
    
    @AfterClass
    public void Quit(){
        F.closeBrowser();
         System.out.println("Class 1 is Done");
    }
    
    @AfterTest
    public void Test1Done(){
        System.out.println("Test 1 is Done");
    }
    
    @DataProvider(name = "NewSignupData")
    public Object[] NewSignupData() {
        return NewUser;
    }
    
    @DataProvider(name = "ExistSignupData")
    public Object[] ExistSignupData(){
        return ExistUser;
    }
 
    @Test(dataProvider="NewSignupData")
    @Tag("New User")
    @Story("User navigates to the login/signup page, "
            + "enters a new name and email and signup, "
            + "navigates to the signup page, "
            + "enters all other necessary information and submit, "
            + "checks that the account is created, "
            + "checks that their name is showing on navigation bar, "
            + "clicks delete account, "
            + "and finally checks that the account is deleted.")
    @Severity(SeverityLevel.BLOCKER)  
    @Description("This test verifies that user can sign up with a new data.")
    public void TC1_RegisterUser(NewRegisterClass newuser){
       
        Assert.assertTrue(login.SignUpTextVisibility());
        
        login.Signup(newuser.SignUpName,newuser.SignUpEmail);
        
        Assert.assertTrue(signup.EnterInfoTextVisibility());
        
        signup.AccountInformation(newuser.Title, newuser.SignUpName, newuser.Password, newuser.BirthDay, newuser.BirthMonth, newuser.BirthYear);
        signup.AddressInformation(newuser.FirstName, newuser.LastName, newuser.Company, newuser.Address, newuser.Address2, newuser.Country, newuser.State, 
                newuser.City, newuser.ZipCode, newuser.MobileNumber);
        
        create = new  AccountCreatedPage(Browser);
        
        Assert.assertTrue(create.AccountCreatedVisibility());
        create.ContinueClick();
        
        Assert.assertTrue(nav.LoggedInAsVisibility());
        
        nav.DeleteAccount();
        
        delete = new  AccountDeletedPage(Browser);

        Assert.assertTrue(delete.AccountDeletedVisibility());
        delete.ContinueClick();
    }
    
    @Test(dataProvider="ExistSignupData")
    @Tag("Existing User")
    @Story("User navigates to the login/signup page, "
            + "enters an already existing name and email and signup, "
            + "and checks that the message for existing account is visible.")
    @Severity(SeverityLevel.CRITICAL)  
    @Description("This test verifies that user can noy sign up with an already existing data.")
    public void TC5_ExistRegister(ExistRegisterClass existuser){
        Assert.assertTrue(login.SignUpTextVisibility());
        
        login.Signup(existuser.ExistName,existuser.ExistEmail);
        
        Assert.assertTrue(login.EmailExistsVisibility());
    }
}

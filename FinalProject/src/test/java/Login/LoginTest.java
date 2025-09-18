/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Login;

import Frameworks.Framework;
import Helper.HelperClass;

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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Epic("Automation Exercise") 
@Feature("Login")
public class LoginTest {

    Framework F;
    WebDriver Browser;
    HomePage home;
    LoginPage login;
    NavigationBar nav;
    SignupPage signup;
    AccountCreatedPage create;
    AccountDeletedPage delete;
    LoginClass[] CorrrectLoginUser;
    LoginClass[] InCorrectLoginUser;
    LoginClass[] LogoutUser;
    
    @BeforeClass
    public void Launch() throws FileNotFoundException {
        F = new Framework();
        Browser = F.getDriver();
    
        home = new HomePage(Browser);
        login = new LoginPage(Browser); 
        nav = new NavigationBar(Browser);
        signup = new SignupPage(Browser);
        
        CorrrectLoginUser = HelperClass.ReadUsers("CorrectLoginData.json",LoginClass[].class);
        InCorrectLoginUser = HelperClass.ReadUsers("InCorrectLoginData.json",LoginClass[].class);
        LogoutUser = HelperClass.ReadUsers("LogoutLoginData.json",LoginClass[].class);
        
        System.out.println("Class 2: This class is for testing Login. Test Cases: 2,3,4");
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
        System.out.println("Class 2 is Done");
    }
    
    @DataProvider(name = "CorrectLoginData")
    public Object[] CorrectLoginData() {
        return CorrrectLoginUser;
    }
    
    @DataProvider(name = "InCorrectLoginData")
    public Object[] InCorrectLoginData() {
       return InCorrectLoginUser;
    }
    
    @DataProvider(name = "LogoutData")
    public Object[] LogoutData() {
        return LogoutUser;
    }
    
    @Test(dataProvider="CorrectLoginData")
    @Tag("Correct Login")
    @Story("User navigates to the login/signup page, "
          + "enters their email and password and login, "
          + "checks that their name is showing on navigation bar, "
          + "clicks delete account, "
          + "and finally checks that the account is deleted.")
    @Severity(SeverityLevel.BLOCKER)  
    @Description("This test verifies that user can login with their correct data.")
    public void TC2_CorrectLogin(LoginClass correct){
        
        //new account to use it in login
        NewSignUp();
        
        Assert.assertTrue(login.LoginTextVisibility());
        
        login.Login(correct.Email, correct.Password);
        
        Assert.assertTrue(nav.LoggedInAsVisibility());
        
        nav.DeleteAccount();
        
        delete = new  AccountDeletedPage(Browser);

        Assert.assertTrue(delete.AccountDeletedVisibility());
        delete.ContinueClick();
    }
    
    @Test(dataProvider="InCorrectLoginData")
    @Tag("InCorrect Login")
    @Story("User navigates to the login/signup page, "
          + "enters an incorrect email or password and login, "
          + "and checks that 'Your email or password is incorrect!' message is visible")
    @Severity(SeverityLevel.CRITICAL)  
    @Description("This test verifies that user can not login with incorrect data.")
    public void TC3_InCorrectLogin(LoginClass incorrect){
        Assert.assertTrue(login.LoginTextVisibility());
        
        login.Login(incorrect.Email, incorrect.Password);
        
        Assert.assertTrue(login.IncorrectTextVisibility());
    }
    
    @Test(dataProvider="LogoutData")
    @Tag("Logout")
    @Story("User navigates to the login/signup page, "
          + "enters correct email and password and login, "
          + "checks that their name is showing on navigation bar, "
          + "and clicks logout.")
    @Severity(SeverityLevel.CRITICAL)  
    @Description("This test verifies that user can login and logout successfully.")
    public void TC4_LogoutUser(LoginClass lgout){
        Assert.assertTrue(login.LoginTextVisibility());
        
        login.Login(lgout.Email, lgout.Password);
        
        Assert.assertTrue(nav.LoggedInAsVisibility());
        
        nav.LogOut();
        Assert.assertTrue(login.LoginTextVisibility());
    }
    
    //Method for correct login   
    public void NewSignUp(){
        Assert.assertTrue(login.SignUpTextVisibility());
        
        login.Signup("Andrew","an@d.com");
        
        Assert.assertTrue(signup.EnterInfoTextVisibility());
        
        signup.AccountInformation("Mr", "Andrew", "an", 26, 6 , "2006");
        signup.AddressInformation("Andrew", "Forbes", "Tech", "456 Street", "Apt 10", "United States", "California", 
                "LA", "65m4", "3456");
        
        create = new  AccountCreatedPage(Browser);
        
        Assert.assertTrue(create.AccountCreatedVisibility());
        create.ContinueClick();
        
        Assert.assertTrue(nav.LoggedInAsVisibility());
        nav.LogOut();
    }
    
}


package Pages;

import Frameworks.Framework;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
   
    Framework F;

    //Locators for items in Login page only
    private final By LoginText = By.cssSelector("div.login-form > h2 ");
    private final By LoginEmail = By.cssSelector("input[data-qa=\"login-email\"]");
    private final By LoginPassword = By.cssSelector("input[data-qa=\"login-password\"]");
    private final By LoginButton = By.cssSelector("button[data-qa=\"login-button\"]");
    
    private final By SignUpText = By.cssSelector("div.signup-form > h2 ");
    private final By SignUpName = By.cssSelector("input[data-qa=\"signup-name\"]");
    private final By SignUpEmail = By.cssSelector("input[data-qa=\"signup-email\"]");
    private final By SignUpButton = By.cssSelector("button[data-qa=\"signup-button\"]");
    
    private final By IncorrectLoginText = By.xpath("//p[contains(.,\"incorrect\")]");
    private final By EmailExistsText = By.xpath("//p[contains(.,\"exist\")]");
    
    
    //Constructor
    public LoginPage(WebDriver Browser) {
        F = new Framework(Browser);
    } 
    
    //Methods      
    public boolean LoginTextVisibility(){
        return F.CheckVisibility(LoginText);
    }
    
    public boolean SignUpTextVisibility(){
        return F.CheckVisibility(SignUpText);
    }
    
    public boolean IncorrectTextVisibility(){
        return F.CheckVisibility(IncorrectLoginText);
    }
    
    public boolean EmailExistsVisibility(){
        return F.CheckVisibility(EmailExistsText);
    }
    
    // Login
    public void Login(String email,String password){
        F.sendKeys(LoginEmail, email);
        F.sendKeys(LoginPassword, password);
        F.click(LoginButton);
    }
    
    //Signup
    public void Signup(String name, String email){
        F.sendKeys(SignUpName, name);
        F.sendKeys(SignUpEmail, email);
        F.click(SignUpButton);
    }

}

package Sections;

import Frameworks.Framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class NavigationBar {
    
    Framework F;

    
    //Locators for Nav Bar
    private final By LoginButton = By.className("fa-lock");
    private final By ProductsButton = By.className("card_travel");
    private final By CartButton = By.cssSelector("div.col-sm-8 .fa-shopping-cart");
    private final By TestCasesButton = By.xpath("//div[@class=\"col-sm-8\"] //a[contains(.,\"Test Cases\")]");
    private final By ApiTestingButton = By.xpath(" //div[@class=\"col-sm-8\"] //a[contains(.,\"API Testing\")]");
    private final By ContactUsButton = By.className("fa-envelope");
    private final By LoggedInAsText= By.className("fa-user");
    private final By LogOutButton = By.className("fa-lock");  
    private final By DeleteAccountButton = By.className("fa-trash-o");

    //Constructor
    public NavigationBar(WebDriver Browser) {
        F = new Framework(Browser);
    }
    
    //Methods
    public void LoginClick(){
        F.click(LoginButton);
    }
    
    public void ProductsClick(){
        F.click(ProductsButton);
    }
    
    public void CartClick(){
        F.click(CartButton);
    }
    
    public void TestCasesClick(){
        F.click(TestCasesButton);
    }

    public void ApiTestingClick(){
        F.click(ApiTestingButton);
    }

    public void ContactUsClick(){
        F.click(ContactUsButton);
    }
    
    public void DeleteAccount(){
        F.click(DeleteAccountButton);
    }
    
    public void LogOut(){
        F.click(LogOutButton);
    }
    
    public boolean LoggedInAsVisibility(){
        return F.CheckVisibility(LoggedInAsText);
    }
    
}

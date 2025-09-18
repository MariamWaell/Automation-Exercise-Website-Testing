
package Pages;

import Frameworks.Framework;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class AccountCreatedPage {
    
    Framework F;

    //Locators
    private final By AccountCreatedText = By.className("text-center");
    private final By ContinueButton = By.className("btn-primary");
    
    // Constructor
    public AccountCreatedPage(WebDriver Browser) {
        F = new Framework(Browser);
    }
    //Methods
    public boolean AccountCreatedVisibility(){
        return F.CheckVisibility(AccountCreatedText);
    }
    
    public void ContinueClick(){
        F.click(ContinueButton);
    }
    
}

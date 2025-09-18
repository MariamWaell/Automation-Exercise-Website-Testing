
package Pages;

import Frameworks.Framework;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountDeletedPage {
    
    Framework F;
    
    //Locators
    private final By AccountDeletedText = By.className("text-center");
    private final By ContinueButton = By.className("btn-primary");
    
    // Constructor
    public AccountDeletedPage(WebDriver Browser) {
        F = new Framework(Browser);
    }
    
    //Methods    
    public boolean AccountDeletedVisibility(){
        return F.CheckVisibility(AccountDeletedText);
    }
    
    public void ContinueClick(){
        F.click(ContinueButton);
    }
        
}

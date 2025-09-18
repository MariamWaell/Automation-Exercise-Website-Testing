package Pages;

import Frameworks.Framework;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TestCasesPage {
    Framework F ;
    
    //Locator
    public final By TestCasesText = By.className("text-center");

    //Constructor
    public TestCasesPage(WebDriver Browser) {
        F = new Framework(Browser);
    }
    
    //Methods
    public boolean TestsTextVisibility(){
        return F.CheckVisibility(TestCasesText);
    }
    
}

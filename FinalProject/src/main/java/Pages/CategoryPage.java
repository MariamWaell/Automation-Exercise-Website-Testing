package Pages;

import Pages.HomePage;
import Frameworks.Framework;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CategoryPage {
    Framework F;
    HomePage home;
    
    private final By CategoryTitle = By.cssSelector("h2.text-center");
    
    //Constructor
    public CategoryPage(WebDriver Browser) {
        F = new Framework(Browser);
        home = new HomePage(Browser);
    }

    //Methods
    public String GetTitle(){
        return F.getText(CategoryTitle);
    }
    
    //Category
    public String ClickOnCategory(String category, int productsorder){
        return home.ChooseCategory(category, productsorder);
    }
}

package Pages;

import Frameworks.Framework;
import Pages.ProductDetailsPage;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class SearchPage {
    Framework F;
    ProductDetailsPage details;
    
    //Locators
    private final By SearchedProductsText = By.xpath("//h2[contains(.,\"Searched\")]");
    private final By SearchedProductsList = By.className("features_items");
    private final By SearchedProducts = By.cssSelector("div[class=\"features_items\"] > div[class=\"col-sm-4\"]");
      
    //Constructor
    public SearchPage(WebDriver Browser) {
        F = new Framework(Browser);
        details = new ProductDetailsPage(Browser);
    }
    
    //Searched Products
    public boolean SearchedProductsTextVisibility(){
        return F.CheckVisibility(SearchedProductsText);
    }
    
    public boolean SearchedProductsListVisibility(){
        return F.CheckVisibility(SearchedProductsList);
    }
    
    public boolean CheckSearch(String productname){
        boolean check =true;
        By SearchedProductViewDetailsButton ;

        for(int i =1; i <= SearchedProductsCount(); i++){
            SearchedProductViewDetailsButton = By.cssSelector("div[class=\"features_items\"] > div[class=\"col-sm-4\"]:nth-of-type("+ (i+1) +") > div > div[class=\"choose\"] > ul>li>a");
            F.click(SearchedProductViewDetailsButton);
            if(details.Category().toLowerCase().contains(productname.toLowerCase())){
                check = true;
            }  
            else{
                check = false;
                break;
            }
            F.returnPage();
        }
        return check;
    }

    //Other Methods
    public int SearchedProductsCount(){
        return F.findElementCount(SearchedProducts);
    }
    
}

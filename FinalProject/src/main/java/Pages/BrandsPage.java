
package Pages;

import Frameworks.Framework;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BrandsPage {
    Framework F;
    ProductsPage products;
    
    private final By BrandTitle = By.cssSelector("h2.text-center");
    
    //Constructor
    public BrandsPage(WebDriver Browser) {
        F = new Framework(Browser);
        products = new ProductsPage(Browser);
    }

    //Methods
    public String GetTitle(){
        return F.getText(BrandTitle);
    }
    
    //Category
    public String ChooseBrand(int brandid){
        return products.ChooseBrand(brandid);
    }
}

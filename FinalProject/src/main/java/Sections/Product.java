package Sections;

import Frameworks.Framework;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Product {
    
    Framework F ;
    public String Name;
    public int Price;
    public By SelectedProductCartButton;
    public By SelectedProductDetailsButton;
    public int Quantity = 0;
    
    public Product(WebDriver Browser) {
        F = new Framework(Browser);
    }

    public Product(Framework F,int Id) {
        this.F = F ;
        
        String NameLocator = "div.features_items > div.col-sm-4:nth-of-type("+ (Id+1) +") > div> div > div > p";
        this.Name = F.getText(By.cssSelector(NameLocator));

        String PriceLocator = "div.features_items > div.col-sm-4:nth-of-type("+ (Id+1) +") > div> div > div > h2";
        String price = F.getText(By.cssSelector(PriceLocator));
        this.Price = Integer.parseInt(price.split(" ")[1]);

        String Cartlocator ="//div[@class=\"col-sm-9 padding-right\"]/div[@class=\"features_items\"]/div[@class=\"col-sm-4\"]/div[@class=\"product-image-wrapper\"]/div[@class=\"single-products\"]/div[@class=\"productinfo text-center\"]/a[@data-product-id=\""+Id+"\"]";
        this.SelectedProductCartButton = By.xpath(Cartlocator);

        String DetailsLocator ="div.features_items > div.col-sm-4:nth-of-type("+(Id+1)+") > div > div.choose  > ul";
        this.SelectedProductDetailsButton = By.cssSelector(DetailsLocator);
    }       

    public void IncrementQuantity() {
        this.Quantity++;
    }
    
    

}

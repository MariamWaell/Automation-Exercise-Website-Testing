package Pages;

import Frameworks.Framework;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class ProductDetailsPage {
    
    Framework F;

    // Locators for selected product
    private final By SelectedProductDetails = By.className("product-information");
    private final By SelectedProductName = By.cssSelector("div.product-information >h2");
    private final By SelectedProductCategory = By.xpath("//p[contains(.,\"Category:\")]");
    private final By SelectedProductPrice = By.xpath("//span /span[contains(.,\"Rs.\")]");
    private final By SelectedProductAvailability = By.xpath("//b[contains(.,\"Availability\")]");
    private final By SelectedProductCondition = By.xpath("//b[contains(.,\"Condition\")]");
    private final By SelectedProductBrand = By.xpath("//b[contains(.,\"Brand\")]");
    private final By SelectedProductQuantityInput = By.cssSelector("input[id=\"quantity\"]");
    private final By AddToCartButton = By.cssSelector("button[class=\"btn btn-default cart\"]");
    
    //Add Review Locators
    private final By WriteReviewText = By.cssSelector("a[href=\"#reviews\"]");
    private final By ReviewName = By.id("name");
    private final By ReviewEmail = By.id("email");
    private final By ReviewArea = By.id("review");
    private final By SubmitButton = By.id("button-review");
    private final By ReviewSuccess = By.cssSelector("div[class=\"alert-success alert\"] > span");

    //Constructor
    public ProductDetailsPage(WebDriver Browser) {
        F = new Framework(Browser);
    }
    
    //View Details of Project
    public boolean ProductDetailsVisibility() {
        return F.CheckVisibility(SelectedProductDetails);
    }
    
    public boolean ProductNameVisibility() {
        return F.CheckVisibility(SelectedProductName);
    }

    public boolean ProductCategoryVisibility() {
        return F.CheckVisibility(SelectedProductCategory);
    }

    public boolean ProductPriceVisibility() {
        return F.CheckVisibility(SelectedProductPrice);
    }

    public boolean ProductAvailabilityVisibility() {
        return F.CheckVisibility(SelectedProductAvailability);
    }

    public boolean ProductConditionVisibility() {
        return F.CheckVisibility(SelectedProductCondition);
    }

    public boolean ProductBrandVisibility() {
        return F.CheckVisibility(SelectedProductBrand);
    }
    
    //Category
    public String Category(){
        return F.getText(SelectedProductCategory);
    }
    
    //Increase Quantity
    public void AddQuantity(int x){
        for(int i=1; i<x; i++ ){
            F.sendKeys(SelectedProductQuantityInput, ""+Keys.ARROW_UP);
        }
    }
    
    //Cart
    public void AddtoCart(){
        F.click(AddToCartButton);
    }
    
    //Write Review
    public boolean WriteReviewVisibility() {
        return F.CheckVisibility(WriteReviewText);
    }
    
    public void WriteReview(String name, String email, String review){
        F.sendKeys(ReviewName, name);
        F.sendKeys(ReviewEmail, email);
        F.sendKeys(ReviewArea, review);
        F.click(SubmitButton);
    }
    
    public boolean CheckReviewSuccess(){
        return F.CheckVisibility(ReviewSuccess);
    }
  
}



package Pages;

import Frameworks.Framework;
import Sections.Ads;
import Sections.Product;

import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage {
    
    Framework F;
    Ads ad;
    private Map<Integer, Product> productMap = new HashMap<>();
    
    //Locators for All products page
    private final By AllProductsText = By.cssSelector(".title.text-center");
    private final By Productslist = By.className("features_items");
    private final By SearchEngine = By.cssSelector("input[id=\"search_product\"]");
    private final By SearchButton = By.cssSelector("button[id=\"submit_search\"]");
    
    //Locators for Brand Section
    private final By SideBarBrands = By.className("brands_products");
    
    //Locators for adding to cart
    private final By ContinueShopping = By.className("close-modal");
    private final By ViewCart = By.xpath("//u[contains(.,View)]");   

    //Constructor
    public ProductsPage(WebDriver Browser) {
        F = new Framework(Browser);
        ad = new Ads(Browser);
    }

    //Visibility Methods
    public boolean AllProductsVisibility(){
        return F.CheckVisibility(AllProductsText);
    }
    
    public boolean ProductsListVisibility(){
        return F.CheckVisibility(Productslist);
    }
    
    //Search Methods
    public void Search(String name){
        F.sendKeys(SearchEngine, name);
        F.click(SearchButton);
    }
    
    //View Details Methods
    public void ViewDetails(int x){
        Product P= new Product(F,x);
        F.click(P.SelectedProductDetailsButton);
    }
    
    //Add to Cart Methods
    public void AddSelectedProductToCart(int x){
        Product P;
        if(productMap.containsKey(x)) {
            P = productMap.get(x); // already exists
        } 
        else {
            P = new Product(F,x); // new product
            productMap.put(x, P);
        }
        P.IncrementQuantity(); // increment quantity= new Product(x);
        F.click(P.SelectedProductCartButton);
    }
    
    public void AddAllProductsToCart(){
        String AllListProducts ="div.features_items > div.col-sm-4";
        int ProductsCount = F.findElementCount(By.cssSelector(AllListProducts));
        for (int i =1; i <= ProductsCount ; i++){
            String cartbutton = AllListProducts + ":nth-of-type(" + (i+1) + ") > div >div >div> a";
            ad.CheckAd();
            F.click(By.cssSelector(cartbutton));
            F.explicitWait(ContinueShopping, 10);
            ContinueShopping();
        }
    }
    
    public void ContinueShopping(){
        F.click(ContinueShopping);
    }
        
    public void ViewCart(){
        F.click(ViewCart);
    }
    
    //Product Details Methods
    public int GetQuantity(int id){
       return productMap.get(id).Quantity;
    }
    
    public String GetName(int id){
        return productMap.get(id).Name;
    }
    
    public int GetPrice(int id){
        return productMap.get(id).Price;
    }
    
    public int GetTotal(int id){
        return productMap.get(id).Price * productMap.get(id).Quantity;
    }
    
    //Brand Methods
    public boolean CheckBrandVisibility(){
        return F.CheckVisibility(SideBarBrands);
    }
    
    public String ChooseBrand(int brandid){
        String BrandsList = "div[class=\"brands_products\"] > div > ul > li";
        String BrandProduct = BrandsList + ":nth-of-type(" + brandid + ") > a";
        By BrandProductLocator = By.cssSelector(BrandProduct);
        F.click(BrandProductLocator);
        return (F.getText(BrandProductLocator).substring(4));
    }
    
}

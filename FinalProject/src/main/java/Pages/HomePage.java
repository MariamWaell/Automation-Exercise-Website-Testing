package Pages;

import Frameworks.Framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    
    private final String HomeUrl = "https://automationexercise.com/";
    Framework F ;
    ProductsPage Product;
        
    //Page Locators
    private final By Logo = By.cssSelector(".logo.pull-left");
    private final By FullFledgedSection = By.cssSelector("section[id=\"slider\"]");
    private final By ScrollButton = By.className("fa-angle-up");
    private final By Footer = By.cssSelector("div.footer-widget .container");
    private final By FooterBottom = By.cssSelector("div.footer-bottom .container ");
    private final By SubscriptionText = By.xpath("//h2[contains(.,\"Subscription\")]");
    private final By EmailInput = By.id("susbscribe_email");
    private final By SubscribeButton = By.cssSelector("button[id=\"subscribe\"]");
    private final By SubscriptionMessage = By.cssSelector(".alert-success.alert");
    
    private final By ProductsList = By.className("features_items");
    //Category Section Locators
    private final By SideBarCategories = By.className("category-products");
    private final By WomenCategory = By.cssSelector("a[href='#Women']");
    private final By MenCategory = By.cssSelector("a[href='#Men']");
    private final By KidsCategory = By.cssSelector("a[href='#Kids']");
    //Women
    private final By WomenDress = By.cssSelector("div[id=\"Women\"] > div > ul > li:nth-of-type(1) > a");
    private final By WomenTops = By.cssSelector("div[id=\"Women\"] > div > ul > li:nth-of-type(2) > a");
    private final By WomenSaree = By.cssSelector("div[id=\"Women\"] > div > ul > li:nth-of-type(3) > a");
    //Men
    private final By MenTshirt = By.cssSelector("div[id=\"Men\"] > div > ul > li:nth-of-type(1) > a");
    private final By MenJeans = By.cssSelector("div[id=\"Men\"] > div > ul > li:nth-of-type(2) > a");
    //Kids
    private final By KidsDress = By.cssSelector("div[id=\"Kids\"] > div > ul > li:nth-of-type(1) > a");
    private final By KidsTops = By.cssSelector("div[id=\"Kids\"] > div > ul > li:nth-of-type(2) > a");

    //Recommended Items
    private final By RecommendedItems = By.className("recommended_items");
    private final By SlideButton = By.cssSelector("a[class=\"right recommended-item-control\"] >i[class=\"fa fa-angle-right\"]");
    
    //Constructor
    public HomePage(WebDriver Browser) {
        F = new Framework(Browser);
        Product = new ProductsPage(Browser);
    }
    
    // Getter
    public String GetHomeUrl(){
        return HomeUrl;
    } 
    
    //Methods
    public boolean HomePageVisibilty(){
        return F.CheckVisibility(FullFledgedSection);
    }
    
    public boolean FullFledgedVisibilty(){
        return F.CheckVisibility(FullFledgedSection);
    }
    
    public boolean SubscriptionVisibilty(){
        F.scrollToElement(Footer);
        return F.CheckVisibility(SubscriptionText);
    }
    
    public boolean SuccessMessageVisibilty(){
        return F.CheckVisibility(SubscriptionMessage);
    }
    
    public void ScrollUp(){
        F.scrollToElement(Logo);
    }
    
    public void ScrollDown(){
        F.scrollToElement(FooterBottom);
    }
    
    public void ScrollButtonClick(){
        F.click(ScrollButton);
    }
    
    public void Subscribe(String email){
        F.sendKeys(EmailInput, email);
        F.click(SubscribeButton);
    }
                
    //Add to Cart From Home Page
    
    public void AddSelectedProductToCart(int x){
        Product.AddSelectedProductToCart(x);
    }
    
    public void ContinueShopping(){
        Product.ContinueShopping();
    }
        
    public void ViewCart(){
        Product.ViewCart();
    }
    
    public void ViewDetails(int x){
        Product.ViewDetails(x);
    }
    
    //Recommended Items
    public void ScrollToRecommended(){
        F.scrollToElement(RecommendedItems);
    }
    
    public boolean RecommendedItemsVisibilty(){
        return F.CheckVisibility(RecommendedItems);
    }
    
    public String AddToCartFromRecommendedItems(int section , int order){
        String product = "div[id=\"recommended-item-carousel\"] > div > div:nth-of-type("+section+") > div:nth-of-type("+order+") > div > div > div";
        By cartbutton =  By.cssSelector(product + "> a");
        By name = By.cssSelector(product + "> p");
        if (!F.CheckVisibility(cartbutton)){
            F.click(SlideButton);
        }
        F.click(cartbutton);
        return F.getText(name);
    }


    
    //Category Methods
    public boolean CheckCategoryVisibility(){
        return F.CheckVisibility(SideBarCategories);
    }
    
    public String ChooseCategory(String category, int productsorder){
        if(category.equalsIgnoreCase("women")){
            F.click(WomenCategory);
            switch (productsorder) {
                case 1 -> {
                    return ClickCategory(WomenDress);
                }
                case 2 -> {
                    return ClickCategory(WomenTops);
                }
                case 3 -> {
                    return ClickCategory(WomenSaree);  
                }
                default -> {
                }
            }
        }
        else if(category.equalsIgnoreCase("men")){
            F.click(MenCategory);
            switch (productsorder) {
                case 1 -> {
                    return ClickCategory(MenTshirt);
                }
                case 2 -> {
                    return ClickCategory(MenJeans);
                }
                default -> {
                }
            }
        }
        else if(category.equalsIgnoreCase("kids")){
            F.click(KidsCategory);
            switch (productsorder) {
                case 1 -> {
                    return ClickCategory(KidsDress);
                }
                case 2 -> {
                    return ClickCategory(KidsTops);
                }
                default -> {
                }
            }
        }
        return null;
    }
    
    public String ClickCategory(By category){
        F.explicitWait(category, 10);
        String name = F.getText(category).trim();
        F.click(category);
        return name;
    }
    
}

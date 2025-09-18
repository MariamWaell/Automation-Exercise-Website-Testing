package Pages;

import Frameworks.Framework;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class CartPage {
    
    Framework F;
    ProductsPage P;
    CartProduct c;

    private class CartProduct{
        int Id;
        String Name;
        int Price;
        int Quantity;
        int Total;
        public By DeleteButton;

        public CartProduct(int Id) {
            String NameLocator = "tr:nth-of-type(" +Id+") > td[class=\"cart_description\"] > h4 >a";
            this.Name = F.getText(By.cssSelector(NameLocator));

            String PriceLocator = "tr:nth-of-type("+ Id +") > td[class=\"cart_price\"] > p";
            String price = F.getText(By.cssSelector(PriceLocator));
            this.Price = Integer.parseInt(price.replaceAll("[^0-9]", ""));
                      
            String Quantitylocator ="tr:nth-of-type(" + Id + ") > td[class=\"cart_quantity\"] > button";
            String quantity = F.getText(By.cssSelector(Quantitylocator));
            this.Quantity = Integer.parseInt(quantity.replaceAll("[^0-9]", ""));
            
            String TotalLocator = "tr:nth-of-type("+ Id +") > td[class=\"cart_total\"] > p";
            String total = F.getText(By.cssSelector(TotalLocator));
            this.Total = Integer.parseInt(total.replaceAll("[^0-9]", ""));
            
            String DeleteButtonLocator = "tr:nth-of-type("+Id+") > td[class=\"cart_delete\"] > a > i";
            DeleteButton= By.cssSelector(DeleteButtonLocator);

        }       
    }
    
    //Locators
    private final By ShoppingCartText = By.cssSelector("li.active");
    private final By ShoppingCartProducts = By.cssSelector("table > tbody > tr");
    private final By CheckOutButton = By.cssSelector("a[class =\"btn btn-default check_out\"]");
    private final By RegisterLoginButton = By.xpath("//p[@class=\"text-center\"] //u[contains(.,\"Login\")]");
    private final By ContinueOnCartButton = By.className("btn-success");
    private final By Footer = By.cssSelector("div.footer-widget .container");
    private final By FooterBottom = By.cssSelector("div.footer-bottom .container ");
    private final By SubscriptionText = By.xpath("//h2[contains(.,\"Subscription\")]");
    private final By EmailInput = By.id("susbscribe_email");
    private final By SubscribeButton = By.cssSelector("button[id=\"subscribe\"]");
    private final By SubscriptionMessage = By.cssSelector(".alert-success.alert");
        
    //Constructor
    public CartPage(WebDriver Browser) {
        F = new Framework(Browser);
        P = new ProductsPage(Browser);
    }
    
    //Page Methods
    public boolean CartPageVisibility(){
        return F.CheckVisibility(ShoppingCartText);
    }       
    
   //Cart Details Methods
    public int GetQuantity(int id){
        c = new CartProduct(id);
       return c.Quantity;
    }
    
    public String GetName(int id){
        c = new CartProduct(id);
        return c.Name;
    }
    
    public int GetPrice(int id){
        c = new CartProduct(id);
        return c.Price;
    }
    
    public int GetTotal(int id){
        c = new CartProduct(id);
        return c.Total;
    }
    
    public List<String> AllProductsNames(){
        List<String> ProductsNames = new ArrayList<>();
        for(int i=1; i < ShoppingCartProductsCount(); i++ ){
            ProductsNames.add(GetName(i));
        }
        return ProductsNames;
    }
    
    //Delete Methods
    public void DeleteProduct(int id){
        c = new CartProduct(id);
        F.click(c.DeleteButton);
    }
    
    public boolean CheckCartProducts(String Productname){
        for(int i =1; i <= ShoppingCartProductsCount(); i++){
            if(Productname.equals(GetName(i))){
                return false;
            }
        }
        return true;
    }
    
    //Checkout Methods
    public void CheckOut(){
        F.click(CheckOutButton);
    }
    
    public void RegisterLoginClick(){
        F.click(RegisterLoginButton);
    }
    
    public void ContinueOnCartClick(){
        F.click(ContinueOnCartButton);
    }
    
    //Subscribe Methods
    public boolean SubscriptionVisibilty(){
        F.scrollToElement(Footer);
        return F.CheckVisibility(SubscriptionText);
    }
    
    public boolean SuccessMessageVisibilty(){
        return F.CheckVisibility(SubscriptionMessage);
    }
    
    public void ScrollDown(){
        F.scrollToElement(FooterBottom);
    }
    
    public void Subscribe(String email){
        F.sendKeys(EmailInput, email);
        F.click(SubscribeButton);
    }
       
    //Other Methods
    public int ShoppingCartProductsCount(){
        return F.findElementCount(ShoppingCartProducts);
    }
    
}

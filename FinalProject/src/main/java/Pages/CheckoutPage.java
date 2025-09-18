package Pages;

import Frameworks.Framework;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
    
    Framework F;
    
    //Locators
    private final By AddressDetailsText = By.xpath("//h2[contains(.,\"Details\")]");
    private final By CommentArea = By.tagName("textarea");
    private final By PlaceOrderButton = By.cssSelector("a[class=\"btn btn-default check_out\"]");
    
    //Delivery Address Details
    private final By DeliveryName = By.cssSelector("ul[id=\"address_delivery\"] > li[class=\"address_firstname address_lastname\"]");
    private final By DeliveryCompany = By.cssSelector("ul[id=\"address_delivery\"] > li[class=\"address_address1 address_address2\"]:nth-of-type(3)");
    private final By DeliveryAddress1 = By.cssSelector("ul[id=\"address_delivery\"] > li[class=\"address_address1 address_address2\"]:nth-of-type(4)");
    private final By DeliveryAddress2 = By.cssSelector("ul[id=\"address_delivery\"] > li[class=\"address_address1 address_address2\"]:nth-of-type(5)");
    private final By DeliveryCity = By.cssSelector("ul[id=\"address_delivery\"] > li[class=\"address_city address_state_name address_postcode\"]"); 
    private final By DeliveryCountry = By.cssSelector("ul[id=\"address_delivery\"] > li[class=\"address_country_name\"]") ;
    private final By DeliveryPhone = By.cssSelector("ul[id=\"address_delivery\"] > li[class=\"address_phone\"]");
    
    //Billing Address Details
    private final By BillingName = By.cssSelector("ul[id=\"address_invoice\"] > li[class=\"address_firstname address_lastname\"]");
    private final By BillingCompany = By.cssSelector("ul[id=\"address_invoice\"] > li[class=\"address_address1 address_address2\"]:nth-of-type(3)");
    private final By BillingAddress1 = By.cssSelector("ul[id=\"address_invoice\"] > li[class=\"address_address1 address_address2\"]:nth-of-type(4)");
    private final By BillingAddress2 = By.cssSelector("ul[id=\"address_invoice\"] > li[class=\"address_address1 address_address2\"]:nth-of-type(5)");
    private final By BillingCity = By.cssSelector("ul[id=\"address_invoice\"] > li[class=\"address_city address_state_name address_postcode\"]");
    private final By BillingCountry = By.cssSelector("ul[id=\"address_invoice\"] > li[class=\"address_country_name\"]");
    private final By BillingPhone = By.cssSelector("ul[id=\"address_invoice\"] > li[class=\"address_phone\"]");
    
    //Constructor
    public CheckoutPage(WebDriver Browser) {
        F = new Framework(Browser);
    }
    
    //Checkout Methods
    public boolean AddressDetailsVisibility(){
        return F.CheckVisibility(AddressDetailsText);
    }
      
    public boolean CheckDeliveryAddressDetails(String title,String firstname, String lastname, String company, String address1, 
           String address2, String city, String state, String zipcode, String country, String mobile){
        
        String Name = title + ". "+ firstname +" " + lastname;
        String City = city + " " + state + " " + zipcode;
        
        if (!(Name.equals(F.getText(DeliveryName)))||
            !(company.equals(F.getText(DeliveryCompany)))||
            !(address1.equals(F.getText(DeliveryAddress1)))||
            !(address2.equals(F.getText(DeliveryAddress2)))||
            !(City.equals(F.getText(DeliveryCity)))||
            !(country.equals(F.getText(DeliveryCountry)))||
            !(mobile.equals(F.getText(DeliveryPhone)))){
            return false;
        }    
        else{
            return true;
        }
    }
    
    public boolean CheckBillingAddressDetails(String title,String firstname, String lastname, String company, String address1, 
            String address2, String city, String state, String zipcode, String country, String mobile){
        
        String Name = title + ". "+ firstname +" " + lastname;
        String City = city + " " + state + " " + zipcode;
        
        if ((!Name.equals(F.getText(BillingName)))||
            (!company.equals(F.getText(BillingCompany)))||
            (!address1.equals(F.getText(BillingAddress1)))|| 
            (!address2.equals(F.getText(BillingAddress2)))||
            (!City.equals(F.getText(BillingCity)))||
            (!country.equals(F.getText(BillingCountry)))||
            (!mobile.equals(F.getText(BillingPhone)))){
            return false;
        }
        else{
            return true;
        }                 
    }

    public void AddComment(String text){
        F.sendKeys(CommentArea, text);
    }
    
    public void PlaceOrder(){
        F.click(PlaceOrderButton);
    }
}

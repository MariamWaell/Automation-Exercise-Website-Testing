
package Pages;

import Frameworks.Framework;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class PaymentPage {
    
    Framework F;
    
    //Locators
    private final By NameOnCardArea = By.name("name_on_card");
    private final By CardNumberArea = By.name("card_number");
    private final By CVCArea = By.cssSelector("input.card-cvc");
    private final By ExpiryMonth = By.name("expiry_month");
    private final By ExpiryYear = By.className("card-expiry-year");
    private final By ConfirmButton = By.className("submit-button");
//    private final By SuccessMessage = By.cssSelector("div[id=\"success_message\"] > div[class=\"alert-success alert\"]");
//    private final By SuccessMessage = By.className("alert-success");

    //Constructor
    public PaymentPage(WebDriver Browser) {
        F = new Framework(Browser);
    }
    
    //Payment Methods
    public void EnterPaymentData(String name, String cardnumber, String cvc, String month, String year){
        F.sendKeys(NameOnCardArea, name);
        F.sendKeys(CardNumberArea, cardnumber);
        F.sendKeys(CVCArea, cvc);
        F.sendKeys(ExpiryMonth, month);
        F.sendKeys(ExpiryYear, year);
    }
    
    public void ConfirmPayment(){
        F.click(ConfirmButton);
    }
    
    //Success
//    public boolean CheckPaymentSuccess(){
//        return F.CheckVisibility(SuccessMessage);
//    }

}

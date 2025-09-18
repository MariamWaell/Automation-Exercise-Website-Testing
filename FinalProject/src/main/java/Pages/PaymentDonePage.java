
package Pages;

import Frameworks.Framework;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class PaymentDonePage {
    
    Framework F;
    
    //Locators
    private final By OrderConfirmedText = By.cssSelector("div.col-sm-9 > p");
    private final By ContinueButton = By.className("btn-primary");
    private final By DownloadInvoiceButton = By.xpath("//a[contains(.,\"Invoice\")]");

    //Constructor
    public PaymentDonePage(WebDriver Browser) {
        F = new Framework(Browser);
    }
    
    public boolean CheckPaymentSuccess(){
        return F.CheckVisibility(OrderConfirmedText);
    }
    
    public void ContinueClick(){
        F.click(ContinueButton);
    }
    
    public void DownloadInvoice(){
        F.click(DownloadInvoiceButton);
    }

}

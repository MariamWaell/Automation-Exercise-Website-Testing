
package Sections;

import Frameworks.Framework;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Ads {
    Framework F;
    WebDriver Browser;

    public Ads(WebDriver Browser) {
        F = new Framework(Browser);
        this.Browser = Browser;
    }
    
    public void CheckAd() {
        String js =
            "document.querySelectorAll('[id^=\"aswift_\"]').forEach(function(el) { el.remove(); });" +
            "document.querySelectorAll('[id^=\"aswift_\"][id$=\"_host\"]').forEach(function(el) { el.remove(); });";

        ((JavascriptExecutor) Browser).executeScript(js);
    }

    
//    public void CheckAd() {
//        String js = "var removed = false;" +
//                    "['aswift_1','aswift_2','aswift_3','aswift_4','aswift_5'].forEach(function(id) {" +
//                    "   var iframe = document.getElementById(id);" +
//                    "   if(iframe){ iframe.remove(); removed = true; }" +
//                    "   var host = document.getElementById(id + '_host');" +
//                    "   if(host){ host.remove(); removed = true; }" +
//                    "});" +
//                    "return removed;";
//
//        Boolean removed = (Boolean) ((JavascriptExecutor) Browser).executeScript(js);
//
//        if (removed) {
////            System.out.println("Ad iframe/host (aswift_3 or aswift_5) was found and removed.");
//        }
    

}

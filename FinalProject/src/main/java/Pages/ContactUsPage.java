package Pages;

import Frameworks.Framework;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactUsPage {

    Framework F ;

    
    //Locators for items in Contact Us page only
    private final By IntouchText = By.xpath("//h2[contains(.,\"Touch\")]");
    private final By NameForm = By.name("name");
    private final By EmailForm = By.name("email");
    private final By SubjectForm = By.name("subject");
    private final By MessageForm = By.tagName("textarea");
    private final By ChooseFileButton = By.name("upload_file");
    private final By SubmitFormButton = By.className("btn-primary");
    
    //Locators for submitting
    private final By SuccessText = By.cssSelector("div[class=\"status alert alert-success\"]");
    private final By HomeButton = By.cssSelector("a[class=\"btn btn-success\"]");

    //Constructor
    public ContactUsPage(WebDriver Browser) {
        F = new Framework(Browser); 
    }
    
    //Methods
    public Boolean InTouchVisibility(){
        return F.CheckVisibility(IntouchText);
    }
    
    public void FillForm(String name, String email, String subject, String message){
        F.sendKeys(NameForm, name);
        F.sendKeys(EmailForm, email);
        F.sendKeys(SubjectForm, subject);
        F.sendKeys(MessageForm, message);
    }
    public void ChooseFile(String FilePath){
//        F.click(ChooseFileButton);
        F.sendKeys(ChooseFileButton,FilePath);
    }
    
    public void Submit(){
        F.click(SubmitFormButton);
        F.acceptAlert();
    }   
    
    public boolean SuccessVisibility(){
        return F.CheckVisibility(SuccessText);
    }
    
    public void HomeClick(){
        F.click(HomeButton);
    }

 
    
    

        
//    public void NameSendkeys(String name){
//        F.sendKeys(NameForm, name);
//    }
//    
//    public void EmailSendkeys(String email){
//        F.sendKeys(EmailForm, email);
//    }
//    
//    public void SubjectSendkeys(String subject){
//        F.sendKeys(SubjectForm, subject);
//    }
//    
//    public void MessageSendkeys(String message){
//        F.sendKeys(MessageForm, message);
//    }
//    
    
    
}

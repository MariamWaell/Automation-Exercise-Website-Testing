package Pages;

import Frameworks.Framework;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignupPage {
    
    Framework F ;
    
    // Locators for Account Information
    private final By EnterInformationText = By.cssSelector("div.login-form > h2");
    private final By NameField = By.cssSelector("input[id=\"name\"]");
    private final By PasswordField = By.cssSelector("input[id=\"password\"]");
    private final By DayDropMenu = By.cssSelector("select[id=\"days\"]");
    private final By MonthsDropMenu= By.cssSelector("select[id=\"months\"]");
    private final By YearsDropMenu = By.cssSelector("select[id=\"years\"]");
    private final By NewsletterCheckbox = By.cssSelector("input[id=\"newsletter\"]");
    private final By SpecialOffersCheckbox = By.cssSelector("input[id=\"optin\"]");

    //Locators for Address Information
    private final By FisrtName = By.cssSelector("input[id=\"first_name\"]");
    private final By LastName = By.cssSelector("input[id=\"last_name\"]");
    private final By Company = By.cssSelector("input[id=\"company\"]");
    private final By Address1 = By.cssSelector("input[id=\"address1\"]");
    private final By Address2 = By.cssSelector("input[id=\"address2\"]");
    private final By CountryDropMenu = By.cssSelector("select[id=\"country\"]");
    private final By State = By.cssSelector("input[id=\"state\"]");
    private final By City = By.cssSelector("input[id=\"city\"]");
    private final By Zipcode = By.cssSelector("input[id=\"zipcode\"]");
    private final By MobileNumber = By.cssSelector("input[id=\"mobile_number\"]");
    private final By CreateAccountButton = By.cssSelector("button[data-qa=\"create-account\"]");
    
   

    // Constructor
    public SignupPage(WebDriver Browser) {
        F = new Framework(Browser);
    }
    
    //Methods
    public boolean EnterInfoTextVisibility(){
        return F.CheckVisibility(EnterInformationText);
    }
        
    public void AccountInformation(String title, String name, String password, int day, int month, String year){
        ChooseTitle(title);
        F.deleteKeys(NameField);
        F.sendKeys(NameField, name);
        F.sendKeys(PasswordField, password);
        F.selectDropdownByIndex(DayDropMenu, day);
        F.selectDropdownByIndex(MonthsDropMenu, month);
        F.selectDropdownByValue(YearsDropMenu, year);
        F.checkCheckbox(NewsletterCheckbox);
        F.checkCheckbox(SpecialOffersCheckbox);
        
    }
    
    public void AddressInformation(String firstname, String lastname, String company, String address1, 
            String address2, String country, String state, String city, String zipcode, String mobile){
        F.sendKeys(FisrtName, firstname);
        F.sendKeys(LastName, lastname);
        F.sendKeys(Company, company);
        F.sendKeys(Address1, address1);
        F.sendKeys(Address2, address2);
        F.selectDropdownByValue(CountryDropMenu, country);
        F.sendKeys(State, state);
        F.sendKeys(City, city);
        F.sendKeys(Zipcode, zipcode);
        F.sendKeys(MobileNumber, mobile);
        F.click(CreateAccountButton);       
    }
     
    public void ChooseTitle(String gender){
        String gendertext = "//label[contains(.,\""+gender+".\")]";
        By genderlocator = By.xpath(gendertext);
        F.selectRadioButton(genderlocator);
    }
      
}

package Frameworks;


import java.time.Duration;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Framework {
    
    private WebDriver Browser;
    private Actions a1;

    
    public Framework() {
        initializeBrowser();
        a1 = new Actions(Browser);
    }
    
    public Framework(WebDriver Browser) {
        this.Browser = Browser;
        a1 = new Actions(Browser);
    }
    
    public void implicitWait(int seconds){
        Browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }
    
    public void explicitWait(By locator, int timeoutSeconds){
        new WebDriverWait(Browser, Duration.ofSeconds(timeoutSeconds)).
                until(ExpectedConditions.visibilityOfElementLocated((locator)));
    }
    
    public void fluentWait(By locator, int timeoutSeconds, int pollingMillis, String timeoutMessage){
        new FluentWait<>(Browser)
            .withTimeout(Duration.ofSeconds(timeoutSeconds))           
            .pollingEvery(Duration.ofMillis(pollingMillis))          
            .withMessage(timeoutMessage)
            .ignoring(NoSuchElementException.class)
            .until(ExpectedConditions.visibilityOfElementLocated(locator));     
    }
    
    public void initializeBrowser(){
        Browser = new ChromeDriver();
        Browser.manage().window().maximize();
    }
    
    public WebDriver getDriver() {
        return Browser;
    }
    
    public void navigateToURL(String url){
        Browser.navigate().to(url);
    }
    
    public void returnPage(){
        Browser.navigate().back();
    }
    
    public String getPageTitle(){
        return Browser.getTitle();
    }
    
    public String getCurrentURL(){
        return Browser.getCurrentUrl();
    }
    
    public void click(By locator){
        explicitWait(locator, 10);
        WebElement element = Browser.findElement(locator);
        element.click();
    }
    
    public void rightClick(By locator){
        a1.contextClick(Browser.findElement(locator)).perform();
    }
    
    public int findElementCount(By locator) {
        List<WebElement> elements = Browser.findElements(locator);
        return elements.size();
    }
    
    public void sendKeys(By locator, String text){
        Browser.findElement(locator).sendKeys(text);
    }
    
    public void deleteKeys(By locator) {
        WebElement element = Browser.findElement(locator);
        a1.click(element).doubleClick(element).sendKeys(Keys.DELETE).perform();
        implicitWait(5);
    }
    
    public String getText(By locator){
        return Browser.findElement(locator).getText();
    }
    
    public void selectDropdownByVisibleText(By locator, String visibleText){
        Select DropDownMenu = new Select(Browser.findElement(locator));
        DropDownMenu.selectByVisibleText(visibleText); 
    }
    
    public void selectDropdownByValue(By locator, String value){
        Select DropDownMenu = new Select(Browser.findElement(locator));
        DropDownMenu.selectByValue(value); 
    }
    
    public void selectDropdownByIndex(By locator, int index){
        Select DropDownMenu = new Select(Browser.findElement(locator));
        DropDownMenu.selectByIndex(index); 
    }
    
    public void dragAndDrop(By sourceLocator, By targetLocator){
        a1.dragAndDrop(Browser.findElement(sourceLocator),Browser.findElement(targetLocator)).perform();
    }
    
    public boolean CheckVisibility(By locator){
        try {
            explicitWait(locator, 20);
            return Browser.findElement(locator).isDisplayed();
        } 
        catch (Exception e) {
            return false;
        }
    }
    
    public void checkCheckbox(By locator){
        WebElement element = Browser.findElement(locator);
        if (! element.isSelected()){
           element.click();
        }
        else{
            System.out.println("Checkbox is already selected");
        }
    }
    
    public void uncheckCheckbox(By locator){
        WebElement element = Browser.findElement(locator);
        if (element.isSelected()){
           element.click();
        }
        else{
            System.out.println("Checkbox is not selected");
        }
    }
    
    public void selectRadioButton(By locator){
        WebElement element = Browser.findElement(locator);
        if (! element.isSelected()){
           element.click();
        }
        else{
            System.out.println("Radio Button is already selected");
        }
    }
    
    public void switchToWindowByTitle(String windowTitle){
        String CurrentHandle = Browser.getWindowHandle();
        Set<String> allWindowHandles = Browser.getWindowHandles();
        for (String windowHandle : allWindowHandles) {
            Browser.switchTo().window(windowHandle);
            String currentWindowTitle = Browser.getTitle();
            if (currentWindowTitle.equals(windowTitle)) {
                return;
            }
        }
        Browser.switchTo().window(CurrentHandle);
        System.out.println("This window title (" + windowTitle + ") doesn't exist");
    }
    
    public void switchToWindowByHandle(String windowHandle){
        if (Browser.getWindowHandles().contains(windowHandle)){
            Browser.switchTo().window(windowHandle);
        }
        else{
            System.out.println("This Window Handle (" + windowHandle + ") doesn't exist");
        }
    }
    
    public void closeCurrentWindow(){
        Browser.close();
    }
    
    public void navigateBack(){
        Browser.navigate().back();
    }
    
    public void navigateForward(){
        Browser.navigate().forward();
    }
    
    public void refreshPage(){
        Browser.navigate().refresh();
    }
    
    public void scrollToElement(By locator){
        a1.scrollToElement(Browser.findElement(locator)).perform();
    }
    
    public void acceptAlert(){
        Browser.switchTo().alert().accept();
    }
    
    public void dismissAlert(){
        Browser.switchTo().alert().dismiss();
    }
    
    public String getAlertText(){
        return  Browser.switchTo().alert().getText();
    }
    
    public void sendTextToAlert(String text){
        Browser.switchTo().alert().sendKeys(text);
        Browser.switchTo().alert().accept();
    }
    
    public void newTab(){
        Browser.switchTo().newWindow(WindowType.TAB);
    }
    
    public void closeTab(){
        Browser.close();
    }
    public void closeBrowser(){
        Browser.quit();
    }
}

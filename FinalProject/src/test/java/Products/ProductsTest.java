package Products;

import Helper.HelperClass;
import Frameworks.Framework;
import Pages.BrandsPage;
import Pages.HomePage;
import Sections.NavigationBar;
import Pages.ProductDetailsPage;
import Pages.ProductsPage;
import Pages.SearchPage;
import Pages.CartPage;
import Pages.LoginPage;
import Sections.Ads;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.testng.Tag;
import java.io.FileNotFoundException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


@Epic("Automation Exercise") 
@Feature("All Products Page Managment")
public class ProductsTest {
    
    Framework F;
    WebDriver Browser;
    HomePage home;
    NavigationBar nav;
    ProductsPage products;
    ProductDetailsPage details;
    SearchPage search;
    Ads ad;
    ProductsClass[] Product;
    
    @BeforeTest
    public void DefineTest3(){
        System.out.println("Test 3: This test is for testing different products-related actions on both home and products pages.");
        System.out.println("It includes test cases: 8, 9, 12, 13, 17, 18, 19, 20, 21, 22.");
    }
    
    @BeforeClass
    public void ReadData()throws FileNotFoundException {
        Product = HelperClass.ReadUsers("ProductsData.json",ProductsClass[].class);
        System.out.println("Class 1: This class is for testing actions related to products on the all products page. Test cases 8,9,12,19,20,21");
    }
    
    @BeforeMethod
    public void Launch()throws FileNotFoundException {
        F = new Framework();
        Browser = F.getDriver();
        
        home = new HomePage(Browser);
        nav = new NavigationBar(Browser);
        products = new ProductsPage(Browser);
        details= new ProductDetailsPage(Browser);
        search = new SearchPage(Browser);

        F.navigateToURL(home.GetHomeUrl());
        ad = new Ads(Browser);
        
        Assert.assertTrue(home.HomePageVisibilty());
    }
    
    @AfterMethod
    public void Quit(){
        F.closeBrowser();
    }
    
    @AfterClass
    public void Class1Done(){
        System.out.println("Class 1 is Done");
    }
    
    @AfterTest
    public void Test3Done(){
        System.out.println("Test 3 is Done");
    }
    
    @DataProvider(name = "ProductsData")
    public Object[] ProductsData() {
        return Product;
    }

    @Test 
    @Story("User navigates to the products page, "
            + "chooses a product, "
            + "views its details, "
            + "and checks that they are navigated to the that product's details page.")
    @Severity(SeverityLevel.NORMAL)  
    @Description("This test verifies that user can view a product and navigate to its detail page.")
    public void TC8_ProductDetails(){
        nav.ProductsClick();
        Assert.assertTrue(products.AllProductsVisibility(),"'All Products' text is not Visible");
        Assert.assertTrue(products.ProductsListVisibility(),"Products List is not Visible");
        ad.CheckAd();
        products.ViewDetails(1);
        String ExpectedUrl ="https://automationexercise.com/product_details/"+1;
        String ActualUrl = F.getCurrentURL();
        Assert.assertEquals(ExpectedUrl, ActualUrl);
        Assert.assertTrue(details.ProductNameVisibility(),"Name is not Visible");
        Assert.assertTrue(details.ProductPriceVisibility(),"Price is not Visible");
        Assert.assertTrue(details.ProductCategoryVisibility(),"Category is not Visible");
        Assert.assertTrue(details.ProductAvailabilityVisibility(),"Availability is not Visible");
        Assert.assertTrue(details.ProductConditionVisibility(),"Condition is not Visible");
        Assert.assertTrue(details.ProductBrandVisibility(),"Brand is not Visible");
    }
    
    @Test(dataProvider="ProductsData")
    @Tag("Search")
    @Story("User navigates to the products page, "
            + "searches for a certain product, "
            + "and verifies that all resulted products are related to that search.")
    @Severity(SeverityLevel.NORMAL)  
    @Description("This test verifies that user can search for certain products.")
    public void TC9_SearchProducts(ProductsClass product){
        nav.ProductsClick();
        Assert.assertTrue(products.AllProductsVisibility(),"'All Products' text is not Visible");
        products.Search(product.ProductName);
        Assert.assertTrue(search.SearchedProductsTextVisibility(),"'Serached Products' text is not Visible");
        Assert.assertTrue(search.SearchedProductsListVisibility(),"Serached Products List is not Visible");
        Assert.assertTrue(search.CheckSearch(product.ProductName),"The Searched Products are not related to search '"+product.ProductName+"'");
    }
    
    @Test
    @Tag("Cart Management")
    @Story("User navigates to the products page, "
            + "adds two different products to cart, "
            + "navigates to the cart page, "
            + "and verifies the name, price, and quantity for every added product.")
    @Severity(SeverityLevel.BLOCKER)  
    @Description("This test verifies that user can add products to cart")
    public void TC12_AddProductsToCart(){
        nav.ProductsClick();
        ad.CheckAd();
        products.AddSelectedProductToCart(1);
        products.ContinueShopping();
        ad.CheckAd();
        products.AddSelectedProductToCart(2);
        products.ViewCart();
        
        CartPage cart = new CartPage(Browser);
        
        //9. Verify both products are added to Cart
        Assert.assertEquals(cart.GetName(1),products.GetName(1),"First Product's name in cart doesn't the match the First added product's name");
        Assert.assertEquals(cart.GetName(2),products.GetName(2), "Second Product's name in cart doesn't the match the Second added product's name");
        
        //10. Verify their prices, quantity and total price
        Assert.assertEquals(cart.GetPrice(1),products.GetPrice(1),"First Product's price in cart doesn't the match the First added product's price");
        Assert.assertEquals(cart.GetQuantity(1),products.GetQuantity(1),"First Product's quantity in cart doesn't the match the First added product's quantity");
        Assert.assertEquals(cart.GetTotal(1),products.GetTotal(1),"First Product's total price in cart doesn't the match the First added product's total price");
        
        Assert.assertEquals(cart.GetPrice(2),products.GetPrice(2),"Second Product's price in cart doesn't the match the Second added product's price");
        Assert.assertEquals(cart.GetQuantity(2),products.GetQuantity(2),"Second Product's quantity in cart doesn't the match the Second added product's quantity");
        Assert.assertEquals(cart.GetTotal(2),products.GetTotal(2),"Second Product's total price in cart doesn't the match the Second added product's total price");
    }
    
    @Test 
    @Tag("Navigation")
    @Story("User navigates to the products page, "
            + "chooses a certain prodct brand from the brands section, "
            + "checks that the page for this brand is opened, "
            + "chooses a new prodct brand from the brands section in the current page, "
            + "and finally checks that the page for the new brand is opened.") 
    @Severity(SeverityLevel.NORMAL)  
    @Description("This test verifies that user can view and navigate to product detail page.")
    public void TC19_BrandProducts(){
        nav.ProductsClick();
        Assert.assertTrue(products.CheckBrandVisibility(), "Brands Options are not visible");
        ad.CheckAd();
        String FirstBrandName= products.ChooseBrand(2); //H&M
        
        BrandsPage brands = new BrandsPage(Browser);

        String ExpectedUrl = "https://automationexercise.com/brand_products/" + FirstBrandName;
        Assert.assertEquals(F.getCurrentURL().toLowerCase(),ExpectedUrl.toLowerCase(), "Current Url doesn't match the expected Url");
        
        Assert.assertTrue(brands.GetTitle().toLowerCase().contains(FirstBrandName.toLowerCase()),"The title doesn't match the selected brand");
        
        ad.CheckAd();
        String SecondBrandName= brands.ChooseBrand(5); //babyhug
        ExpectedUrl = "https://automationexercise.com/brand_products/" + SecondBrandName;
        Assert.assertEquals(F.getCurrentURL().toLowerCase(),ExpectedUrl.toLowerCase(),"Current Url doesn't match the expected Url");
    }
    
    @Test(dataProvider="ProductsData")
    @Tag("Cart Management")
    @Tag("Search")
    @Story("User navigates to the products page, "
            + "searches for a certain product, "
            + "verifies that all resulted products are related to that search, "
            + "adds all resulted products to cart, "
            + "navigates to the cart page and checks that is contains those products, "
            + "navigates to login page and login, "
            + "and finally navigates again to the cart page and checks that is contains the same products as before login.")
    @Severity(SeverityLevel.CRITICAL)  
    @Description("This test verifies that user can search and add product to the cart then login and still have these products in the cart.")
    public void T20_SearchProductsLogin(ProductsClass product){
        nav.ProductsClick();
        Assert.assertTrue(products.AllProductsVisibility(),"'All Products' text text is not Visible");
        
        products.Search(product.ProductName);
        Assert.assertTrue(search.SearchedProductsTextVisibility(),"'Serached Products' text is not Visible");
        Assert.assertTrue(search.CheckSearch(product.ProductName),"The Searched Products are not related to search '"+product.ProductName+"'");
        
        products.AddAllProductsToCart();
        
        nav.CartClick();
        
        CartPage cart = new CartPage(Browser);
        Assert.assertTrue(cart.CartPageVisibility(),"Cart Page is not visible");
        
        List<String> BeforeLoginProductsNames = cart.AllProductsNames();
        
        nav.LoginClick();
        LoginPage login = new LoginPage(Browser); 
        Assert.assertTrue(login.LoginTextVisibility(), "'Login to your account' text is not visible");
        login.Login("mw@mw.com","mm");
        
        nav.CartClick();
        
        List<String> AfterLoginProductsNames = cart.AllProductsNames();
        
        Assert.assertEquals(AfterLoginProductsNames, BeforeLoginProductsNames,"There is a mismatch in the cart products before and after login");
    }
    
    @Test
    @Tag("Reviews")
    @Story("User navigates to the products page, "
            + "chooses a product and views its details, "
            + "checks that they are navigated to the that product's details page, "
            + "submits a review in the write review section, "
            + "and waits for the success message.")
    @Severity(SeverityLevel.MINOR)
    @Description("This test verifies that user can write a review on a product.")
    public void T21_AddReview(){
        nav.ProductsClick(); 
        Assert.assertTrue(products.AllProductsVisibility(),"'All Products' text is not Visible");
        Assert.assertTrue(products.ProductsListVisibility(),"Products List is not Visible");
        
        ad.CheckAd();
        products.ViewDetails(1);

        Assert.assertTrue(details.WriteReviewVisibility(), "'WRITE YOUR REVIEW' text is not visible");
        ad.CheckAd();
        details.WriteReview("xx", "ss@s.com", "No review");
        
        Assert.assertTrue(details.CheckReviewSuccess(),"Failure in submitting your Review");  
    }
}

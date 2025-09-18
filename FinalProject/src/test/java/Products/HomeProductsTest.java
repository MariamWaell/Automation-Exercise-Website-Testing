package Products;

import Frameworks.Framework;
import Pages.CartPage;
import Pages.CategoryPage;
import Pages.HomePage;
import Pages.ProductDetailsPage;
import Pages.ProductsPage;
import Sections.Ads;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.testng.Tag;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


@Epic("Automation Exercise") 
@Feature("Home Page Products Managment")
public class HomeProductsTest {
    
    Framework F;
    WebDriver Browser;
    HomePage home;
    ProductsPage products;
    ProductDetailsPage details;   
    Ads ad;
    
    @BeforeClass
    public void DefineClass2(){
        System.out.println("Class 2: This class is for testing actions related to products on the home page. Test cases 13,17,18,22");
    }
    
    @BeforeMethod
    public void Launch(){ 
        F = new Framework();
        Browser = F.getDriver();
                
        home = new HomePage(Browser);   
        F.navigateToURL(home.GetHomeUrl());
        ad = new Ads(Browser);
        
        Assert.assertTrue(home.HomePageVisibilty());
        ad.CheckAd();
    }
    
    @AfterMethod
    public void ReturnToHomePage(){
        F.closeBrowser();
    }
    
    @AfterClass
    public void Class2Done(){
        System.out.println("Class 2 is Done");
    }
    
    @Test
    @Tag("Cart Management")
    @Story("User chooses a products from the home page, "
            + "views its details, "
            + "adjusts the quantity, "
            + "adds the product with the required quantity to the cart, "
            + "and finally checks that the quantity in reflected correctly in the cart page.")
    @Severity(SeverityLevel.NORMAL)  
    @Description("This test verifies that user can add a product to the cart with correct quantity.")
    public void TC13_ProductQuantiy(){
        ad.CheckAd();
        home.ViewDetails(5); //View Fifth product to cart
        details = new ProductDetailsPage(Browser);
        Assert.assertTrue(details.ProductDetailsVisibility(),"Product Details are not visible");
        
        details.AddQuantity(4);
        details.AddtoCart();
        
        products = new  ProductsPage(Browser);
        products.ViewCart();
        
        CartPage cart = new CartPage(Browser);

        Assert.assertEquals(cart.GetQuantity(1),4,"Cart quantity is not equal 4");
    }
    
    @Test
    @Tag("Cart Management")
    @Story("User adds some products from the home page to cart, "
            + "navigates to the cart page, "
            + "removes the first added product, "
            + "refreshes the page, "
            + "and finally checks that the product was removed.")      
    @Severity(SeverityLevel.CRITICAL)  
    @Description("This test verifies that user can remove a product from the cart.")
    public void TC17_RemoveProductsFromCart(){
        home.AddSelectedProductToCart(3);  //Add third product to cart
        home.ContinueShopping();
        ad.CheckAd();
        home.AddSelectedProductToCart(4);  //Add fourth product to cart 
        home.ViewCart();
        
        CartPage cart = new CartPage(Browser);
        Assert.assertTrue(cart.CartPageVisibility(),"Cart Page is not visible");
        
        //Delete first project in cart
        String DeletedProductName = cart.GetName(1);
        cart.DeleteProduct(1);
        F.refreshPage();
        
        Assert.assertTrue(cart.CheckCartProducts(DeletedProductName), DeletedProductName + " is still present in the cart");
    }
    
    @Test
    @Tag("Navigation")
    @Story("User chooses a certain prodct category from the categories section in the sidebar in home page, "
            + "checks that the page for this category is opened, "
            + "chooses a new prodct category from the categories section in the current page, "
            + "and finally checks that the page for the new category is opened.")      
    @Severity(SeverityLevel.NORMAL)
    @Description("This test verifies that user can view different category products.")
    public void TC18_CategoryProducts(){
        Assert.assertTrue(home.CheckCategoryVisibility(),"Categories are not visible");
        ad.CheckAd();

        String FirstproductsName = home.ChooseCategory("Women", 2); //Tops
        CategoryPage category = new CategoryPage(Browser);
        
        Assert.assertTrue(category.GetTitle().toLowerCase().contains(FirstproductsName.toLowerCase()),"This category is not the "+FirstproductsName+" Category");

        //confirm text 'WOMEN - TOPS PRODUCTS'
        String ExpectedTitle = "Women - " + FirstproductsName + " Products";
        Assert.assertTrue(category.GetTitle().equalsIgnoreCase(ExpectedTitle),"Current title: " +category.GetTitle()+" doesn't match the expected title: "+ ExpectedTitle);
        
        //New Category
        ad.CheckAd();
        String SecondproductsName = category.ClickOnCategory("Men", 1); //Tshirts
        Assert.assertTrue(category.GetTitle().toLowerCase().contains(SecondproductsName.toLowerCase()),"This category is not the "+SecondproductsName+" Category");
    }
    
    @Test
    @Tag("Cart Management")
    @Story("User scrolls to the recommended items section, "
            + "slides to the second coursel section, "
            + "adds one product to the cart, "
            + "navigates to the cart page, "
            + "and finally checks that the product was added.")      
    @Severity(SeverityLevel.MINOR) 
    @Description("This test verifies that user can add products to the cart from the recommended items section on the home page.")
    public void TC22_RecommendedItems(){
        home.ScrollToRecommended();
        ad.CheckAd();
        Assert.assertTrue(home.RecommendedItemsVisibilty());
        ad.CheckAd();
        String ProductName = home.AddToCartFromRecommendedItems(1, 2); // Second Item from recommended items after slide(1st section)
        
        home.ViewCart();
        CartPage cart = new CartPage(Browser);

        Assert.assertEquals(cart.GetName(1),ProductName);
    }
}

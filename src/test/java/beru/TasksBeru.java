package beru;

import org.testng.annotations.*;

@Listeners({TestListener.class})
public class TasksBeru extends MethodsClass {

    @DataProvider(name = "testChangeCity")
    public Object[][] createData(){
        return new Object[][]{
                {"Хвалынск"},
                {"Волгоград"}
        };
    }

    @Test
    public void testAuthorization(){
        //login account
        MainPage page1 = new MainPage(getDriver());
        page1.clickLogBtn();
        AuthorizationPage login = new AuthorizationPage(getDriver());
        login.writeLogin();
        login.clickLogBtn();
        login.writePassword();
        login.clickLogBtn();

        //check login and profile
        page1.checkLogin();
        page1.checkProfile();
    }

    @Test(dataProvider = "testChangeCity")
    public void testChangeCity(String city){
        //change city
        MainPage page1 = new MainPage(getDriver());
        page1.clickCityButton();
        page1.writeCity(city);
        page1.selectCity();
        page1.changeCity();
        page1.checkCity(city);

        //login account
        page1.clickLogBtn();
        AuthorizationPage login = new AuthorizationPage(getDriver());
        login.writeLogin();
        login.clickLogBtn();
        login.writePassword();
        login.clickLogBtn();

        //go to settings
        page1.clickSettings();

        //compare cities
        SettingsPage settings = new SettingsPage(getDriver());
        settings.compareCities();
    }

    @Test
    public void testBrushes(){
        //go to brushes page
        MainPage page1 = new MainPage(getDriver());
        page1.findBrushes();

        //enter and check the price range
        BrushesPage brushes = new BrushesPage(getDriver());
        brushes.writeRange();
        brushes.openAllBrushes();
        brushes.checkRange();

        //add brush and go to basket
        brushes.addBrush();
        brushes.goToBasket();

        //free delivery, total price, add brushes
        BasketPage basket = new BasketPage(getDriver());
        basket.freeDelivery();
        int totalPrice = basket.checkTotalPrice();
        basket.addBrushesForFreeDelivery(totalPrice);
        basket.checkFreeDelivery();
        basket.checkAllValues();
    }
}
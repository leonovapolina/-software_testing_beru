package beru;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class TasksBeru {
    private ChromeDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver",
                "D:\\github\\test_beru\\chromedriver.exe");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://beru.ru/");
    }

    @AfterMethod
    public void tearDown(){
        /*Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//span[@title='Мой профиль']"))).build().perform();
        driver.findElement(By.className("header2-user-menu")).findElement(By.linkText("Выход")).click();*/
        //driver.close();
    }

    /*@Test
    public void testAuthorization(){
        //login account
        MainPage page1 = new MainPage(driver);
        page1.clickLogBtn();
        AuthorizationPage login = new AuthorizationPage(driver);
        login.writeLogin();
        login.clickLogBtn();
        login.writePassword();
        login.clickLogBtn();

        //check login and profile
        page1.checkLogin();
        page1.checkProfile();
    }

    @Test
    public void testChangeCity(){
        //change city
        MainPage page1 = new MainPage(driver);
        page1.clickCityButton();
        page1.writeCity();
        page1.selectCity();
        page1.changeCity();
        page1.checkCity();

        //login account
        page1.clickLogBtn();
        AuthorizationPage login = new AuthorizationPage(driver);
        login.writeLogin();
        login.clickLogBtn();
        login.writePassword();
        login.clickLogBtn();

        //go to settings
        page1.clickSettings();

        //compare cities
        SettingsPage settings = new SettingsPage(driver);
        settings.compareCities();
    }*/

    @Test
    public void testBrushes(){
        //go to brushes page
        MainPage page1 = new MainPage(driver);
        page1.findBrushes();

        //enter and check the price range
        BrushesPage brushes = new BrushesPage(driver);
        brushes.writeRange();
        brushes.openAllBrushes();
        //brushes.checkRange();

        //add brush and go to basket
        //brushes.addBrush();
        //brushes.goToBasket();

    }

}
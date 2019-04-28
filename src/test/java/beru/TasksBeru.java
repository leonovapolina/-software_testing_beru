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
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//span[@title='Мой профиль']"))).build().perform();
        driver.findElement(By.className("header2-user-menu")).findElement(By.linkText("Выход")).click();
        driver.close();
    }

    @Test
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

}
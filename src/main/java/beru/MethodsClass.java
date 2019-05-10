package beru;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.util.concurrent.TimeUnit;

public class MethodsClass {
    private static EventFiringWebDriver  driver;

    public static WebDriver getDriver(){
        return driver;
    }

    @BeforeMethod
    public void setUp(){
        DoListener qw = new DoListener();
        System.setProperty("webdriver.chrome.driver",
                "D:\\github\\test_beru\\chromedriver.exe");
        ChromeDriver chr = new ChromeDriver();
        driver = new EventFiringWebDriver (chr);
        driver.register(qw);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://beru.ru/");
    }

    @AfterMethod
    public void tearDown(){
        if((driver.findElement(By.cssSelector("span.header2-nav-item__icon.header2-nav-item__icon_type_profile")).
                getAttribute("title")).equals("Мой профиль")){
            Actions actions = new Actions(driver);
            actions.moveToElement(driver.findElement(By.xpath("//span[@title='Мой профиль']"))).build().perform();
            driver.findElement(By.className("header2-user-menu")).findElement(By.linkText("Выход")).click();
        }
        driver.close();
    }
}

package beru;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    @Step("Find and click loginButton")
    public void clickLogBtn(){
        driver.findElement(By.className("header2-nav__content")).click();
    }

    @Step("Check login")
    public void checkLogin(){
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//span[@title='Мой профиль']"))).build().perform();
        Assert.assertEquals(driver.findElement(By.className("header2-user-menu__email")).getText(),
                "leonovapolina97@yandex.ru");
    }

    @Step("Check button Profile")
    public void checkProfile(){
        Assert.assertEquals(driver.findElement(By.cssSelector("span.header2-nav-item__icon.header2-nav-item__icon" +
                "_type_profile")).getAttribute("title"),"Мой профиль");
    }

}

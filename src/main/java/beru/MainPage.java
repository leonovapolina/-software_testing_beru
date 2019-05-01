package beru;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

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

    @Step("Find and click cityButton")
    public void clickCityButton(){
        driver.findElement(By.className("unique-selling-proposition-line__region")).click();
    }

    @Step("Find and write city")
    public void writeCity(){
        driver.findElement(By.xpath("//input[@class='input__control']")).sendKeys("Хвалынск");
    }

    @Step("Select city")
    public void selectCity(){
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("div.region-suggest__list-item")));
        driver.findElement(By.cssSelector("div.region-suggest__list-item")).click();
    }

    @Step
    public void changeCity(){
        driver.findElement(By.xpath("//div[@class='header2-region-popup']//button")).click();
    }

    @Step("Check city")
    public void checkCity(){
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
                (By.className("footer__wrapper")));
        Assert.assertEquals(driver.findElement(By.className("link__inner")).getText(), "Хвалынск");
    }

    @Step
    public void clickSettings(){
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//span[@title='Мой профиль']"))).build().perform();
        driver.findElement(By.cssSelector("li.header2-user-menu__item.header2-user-menu__item_type_settings")).click();
    }


}
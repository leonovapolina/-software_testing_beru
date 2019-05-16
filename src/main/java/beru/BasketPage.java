package beru;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class BasketPage {
    private WebDriver driver;
    private By basketInfo = By.cssSelector("div._1n63a5bOO8");
    private By price = By.cssSelector("span._1oBlNqVHPq");
    private By brushCost = By.xpath("//div[contains(@data-auto, 'total-items')]//span[2]");

    public BasketPage(WebDriver driver){
        this.driver = driver;
    }

    @Step("Check 'until free delivery left'")
    public void freeDelivery(){
        WebElement delivery = driver.findElement(By.cssSelector("span._3EX9adn_xp"));
        Assert.assertTrue(delivery.getText().contains("До бесплатной доставки осталось"));
    }

    @Step("Check total price")
    public int checkTotalPrice(){
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.
                visibilityOfAllElementsLocatedBy(basketInfo));
        int totalPrice = Integer.parseInt(driver.findElement(price).getText().replaceAll("\\D",""));
        int totalItem = Integer.parseInt(driver.findElement(brushCost).getText().replaceAll("\\D",""));
        int totalDelivery = Integer.parseInt(driver.findElement(By.xpath("//div[contains(@data-auto, " +
                "'total-delivery')]//span[2]")).getText().replaceAll("\\D",""));
        Assert.assertEquals(totalItem + totalDelivery, totalPrice);
        return totalItem;
    }

    @Step("Add brushes in basket")
    public void addBrushesForFreeDelivery(int totalItem){
        int total = totalItem;
        while(total <= 2999){
            driver.findElement(By.cssSelector("button._4qhIn2-ESi._2sJs248D-A._18c2gUxCdP._3hWhO4rvmA")).click();
            total += totalItem;
        }
    }

    @Step("Check free delivery")
    public void checkFreeDelivery(){
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy
                (By.xpath("//span[text()='бесплатно']")));
        WebElement delivery = driver.findElement(By.cssSelector("span._3EX9adn_xp"));
        Assert.assertTrue(delivery.getText().contains("бесплатную доставку"));
    }

    @Step("Check with free delivery")
    public void checkAllValues(){
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy
                (basketInfo));
        int totalPrice = Integer.parseInt(driver.findElement(price).getText().replaceAll("\\D",""));
        int totalItem = Integer.parseInt(driver.findElement(brushCost).getText().replaceAll("\\D",""));
        Assert.assertEquals(totalItem, totalPrice);
    }
}

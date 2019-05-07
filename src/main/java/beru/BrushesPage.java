package beru;

import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.json.JSONObject;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class BrushesPage {
    private WebDriver driver;

    public BrushesPage(WebDriver driver){
        this.driver = driver;
    }

    @Step("Write price range")
    public void writeRange(){
        driver.findElement(By.id("glpricefrom")).sendKeys("999");
        driver.findElement(By.id("glpriceto")).sendKeys("1999");
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
                ((By.cssSelector("div.NZiH_Kn8Fj"))));
    }

    @Step
    public void openAllBrushes(){
        WebElement showMore = driver.findElement(By.xpath("//div[@class='n-pager-more__button " +
                "pager-loader_preload']"));

        while(showMore.isDisplayed()){
            showMore.click();
            //wait while all brushes will be load
            (new WebDriverWait(driver, 20)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy
                    (By.cssSelector("div.grid-snippet.grid-snippet_react.b-zone.b-spy-visible")));
        }
    }

    @Step("Check price range")
    public void checkRange(){
        //add all brushes to list
        List<WebElement> brushesList = driver.findElements(By.cssSelector("div.grid-snippet.grid-snippet_react." +
                "b-zone.b-spy-visible.i-bem.b-spy-visible_js_inited"));
        //check prices
        for(int i = 0; i < brushesList.size(); i++) {
            JSONObject obj = new JSONObject(brushesList.get(i).getAttribute("data-bem"));
            int price = obj.getJSONObject("grid-snippet").getInt("price");
            Assert.assertTrue(price >= 999 && price <= 1999);
        }
    }

    @Step("Add brush")
    public void addBrush(){
        List<WebElement> cartButtonList = driver.findElements(By.cssSelector("div.search-result-snippet-cart-button." +
                "grid-snippet__button"));

        cartButtonList.get(cartButtonList.size() - 2).click();

    }

    @Step
    public void goToBasket(){
        (new WebDriverWait(driver, 20)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy
                (By.linkText("Перейти в корзину")));

        driver.findElement(By.linkText("Перейти в корзину")).click();
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy
                (By.cssSelector("div._3AlSA6AOKL")));
    }

}

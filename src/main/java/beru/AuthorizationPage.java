package beru;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AuthorizationPage {
    private WebDriver driver;

    public AuthorizationPage(WebDriver driver){
        this.driver = driver;
    }

    @Step("Find and write login")
    public void writeLogin(){
        driver.findElement(By.id("passp-field-login")).sendKeys("leonovapolina97");
    }

    @Step("Click loginButton")
    public void clickLogBtn(){
        driver.findElement(By.cssSelector("button.control.button2.button2_view_classic.button2_size_l." +
                "button2_theme_action.button2_width_max.button2_type_submit.passp-form-button")).click();
    }

    @Step("Find and write password")
    public void writePassword(){
        driver.findElement(By.id("passp-field-passwd")).sendKeys("fktrcfylhbz");
    }

}
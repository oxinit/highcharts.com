package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickOnElement(WebElement webElement){
        try{
            webElement.click();}catch(Exception e){
            JavascriptExecutor executor =(JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();",webElement);
        }
    }
}

package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BasePage {
    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void waitImplicitly(int sec) {
        driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);//not working smh
    }
    public void waitFluently(int timeout,WebElement elem){
        Wait wait  = new FluentWait(driver).withTimeout(Duration.ofSeconds(timeout)).pollingEvery(Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.elementToBeClickable(elem));
    }
    public void waitForPageLoadComplete(long timeToWait) {
        new WebDriverWait(driver, Duration.ofSeconds(timeToWait)).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }
    public void waitForVisibilityOfElementComplete(long timeToWait, WebElement elem) {
        new WebDriverWait(driver, Duration.ofSeconds(timeToWait)).until(ExpectedConditions.visibilityOf(elem));
    }

    public void waitForElementToBeClickable(long timeToWait, WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(timeToWait)).until(ExpectedConditions.elementToBeClickable(element));
    }
    public void magickWaiter(long timeToWait) throws InterruptedException {
    Thread.sleep(timeToWait);
    }

}

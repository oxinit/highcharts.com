package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class HomePage extends BasePage{
    @FindBy(xpath ="//a[@id='CybotCookiebotDialogBodyButtonAccept']")
            private WebElement Cookie ;
    @FindBy(xpath ="//li/button[contains(@class,'highcharts')]")
    private List<WebElement> GraphsButtons;
    @FindBy(xpath = "//button/@aria-pressed")
    private List<WebElement> s;
    public HomePage(WebDriver driver) {super(driver);}

    public void openHomePage(String url) {
        driver.get(url);
    }
    public void waitForPageLoading(){
        waitForPageLoadComplete(60,Cookie);
    }
    public void clickGraphsButton1(){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS) ;
        waitVisibilityOfElement(60,Cookie);

        Cookie.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS) ;
        GraphsButtons.get(1).click();
    }

    public Boolean isQueryContainSearchKeyWords(final String query){
        return s.get(1).getText()==query;}
}

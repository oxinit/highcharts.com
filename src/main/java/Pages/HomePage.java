package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class HomePage extends BasePage {
    //"//a[@id='CybotCookiebotDialogBodyButtonAccept']"
    @FindBy(css = "a[id='CybotCookiebotDialogBodyButtonAccept']")
    private WebElement cookie;
    @FindBy(xpath = "//li/button[contains(@class,'highcharts')]")
    private List<WebElement> graphsButtons;
    ////button/@aria-pressed"
    //[contains(@aria-label,'Anita')]
    @FindBy(xpath="//*[local-name() = 'text'][@x='8']")//знаходить текст але тре наводити і чіпати зі спанів
    private WebElement tooltipForAnitaEmployee;
    @FindBy(xpath = " //*[local-name() = 'path'][@fill='#90ed7d']")
    private List<WebElement> pathForHighsoftEmployeeGraph;
   //path for highsoft employee graph 15elements might be some trash
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void openHomePage(String url) {
        driver.get(url);
    }

    public void waitForPageLoading() {
        waitForPageLoadComplete(60, cookie);
    }

    public void clickFirstGraphsButton() {
        waitVisibilityOfElement(60, cookie);
        cookie.click();
        waitVisibilityOfElement(60,graphsButtons.get(1));
        graphsButtons.get(0).click();
    }
    public void clickSecondGraphsButton() {
        graphsButtons.get(1).click();
    }
    public void checkTooltip(String text) throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        Actions ac =new Actions(driver);
        Thread.sleep(500);
        int i=0;
        while(i<pathForHighsoftEmployeeGraph.size()){
            ac.moveToElement(pathForHighsoftEmployeeGraph.get(i)).perform();
        i++;
        Thread.sleep(500);
        String kk = driver.findElement(By.xpath("//*[local-name() = 'text'][@x='8']")).getAttribute("innerHTML");
            softAssert.assertTrue(kk.contains(text));}
    }
}
//Cookie newCookie = new Cookie("new-cookie-key", "new-cookie-value");
//        driver.manage().addCookie(newCookie);
//        String readValue = driver.manage().getCookieNamed(newCookie.getName())
//                .getValue();
//        assertThat(newCookie.getValue()).isEqualTo(readValue);
//
//        driver.findElement(By.id("refresh-cookies")).click();
//    }
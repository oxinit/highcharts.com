package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class HomePage extends BasePage {
    @FindBy(css = "a[id='CybotCookiebotDialogBodyButtonAccept']")
    private WebElement cookie;
    @FindBy(xpath = "//li/button[contains(@class,'highcharts')]")
    private List<WebElement> graphsButtons;
    @FindBy(xpath="//*[local-name() = 'text'][@x='8']")
    private WebElement tooltipForAnitaEmployee;
    @FindBy(xpath = " //*[local-name() = 'path'][@fill='#90ed7d']")
    private List<WebElement> pathForHighsoftEmployeeGraph;
    @FindBy(xpath="//*[local-name() = 'path'][contains(@aria-label,'Anita')]")
    private WebElement coordinateForAnitaAtHighsoftEmployeeGraph;
    @FindBy(id="CybotCookiebotDialog")
    private WebElement cookiePopUpDialog;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void openHomePage(String url) throws InterruptedException {
        driver.get(url);
        magickWaiter(500);
        cookie.click();
    }

    public void clickFirstGraphsButton(){
        waitForElementToBeClickable(60,graphsButtons.get(0));
        graphsButtons.get(0).click();
    }
    public void clickSecondGraphsButton() {
        graphsButtons.get(1).click();
    }
    public void checkTooltip(String text) throws InterruptedException, IOException {
       // SoftAssert softAssert = new SoftAssert();
        Actions ac =new Actions(driver);
        magickWaiter(500);
      //  int i=0;
        //  while(i<pathForHighsoftEmployeeGraph.size()){
            ac.moveToElement(coordinateForAnitaAtHighsoftEmployeeGraph).clickAndHold(coordinateForAnitaAtHighsoftEmployeeGraph).perform();
      //  i++;
        magickWaiter(500);
        String kk = driver.findElement(By.xpath("//*[local-name() = 'text'][@x='8']")).getText();
        BufferedWriter writer = new BufferedWriter(new FileWriter("tooltipSpanTextForGraphCoordinate"));
        writer.write(kk);
        writer.close();
            //softAssert.
        Assert.assertTrue(kk.contains(text));
    //}
    }
}

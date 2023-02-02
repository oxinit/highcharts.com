package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class HomePage extends BasePage {
    @FindBy(css = "a[id='CybotCookiebotDialogBodyButtonAccept']")
    private WebElement cookie;
    @FindBy(xpath = "//li/button[contains(@class,'highcharts')]")
    private List<WebElement> graphsButtons;
    @FindBy(xpath = "//*[local-name() = 'text'][@x='8']")
    private WebElement tooltipForHighsoftEmployees;
    @FindBy(xpath = "//*[local-name() = 'path'][@fill='#90ed7d'][contains(@class,'highcharts-point')]")
    private List<WebElement> pathForHighsoftEmployeeGraph;
    @FindBy(xpath = "//*[local-name() = 'path'][contains(@aria-label,'Anita')]")
    private WebElement coordinateForAnitaAtHighsoftEmployeeGraph;
    @FindBy(id = "CybotCookiebotDialog")
    private WebElement cookiePopUpDialog;
    @FindBy(xpath = "//*[local-name() = 'g'][contains(@class,'highcharts-markers')][contains(@aria-label,'Highsoft')]")
    private WebElement boxForHighsoftGraph;
    String tooltipForGraphsElements="//*[local-name() = 'text'][@x='8']";
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void openHomePage(String url)  {
        driver.get(url);
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions
                            .elementToBeSelected(cookiePopUpDialog));
            //this condition never pass so we click on cookie after wait
        } catch (Exception e) {
            cookie.click();
        }
    }

    public void clickFirstGraphsButton() {
        graphsButtons.get(0).click();

    }

    public void clickSecondGraphsButton() {
        graphsButtons.get(1).click();
    }

    public void checkTooltip() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("tooltipSpanTextForGraphCoordinate"));
        String line;
        Actions ac = new Actions(driver);
        //here action for first element as he reacts differently
        ac.moveToElement(boxForHighsoftGraph)
                .moveToElement(boxForHighsoftGraph, -400, 50).perform();
        line = reader.readLine();
        Assert.assertTrue(driver.findElement(By.xpath(tooltipForGraphsElements)).getText().contains(line),
                "The tooltip text is wrong expected " + line + " but found " + driver.findElement(By.xpath(tooltipForGraphsElements)).getText());
        //main test
        int i = 1;
        while (i < pathForHighsoftEmployeeGraph.size() && (line = reader.readLine()) != null) {
            ac
                    .moveToElement(pathForHighsoftEmployeeGraph.get(i)).perform();
            i++;
            Assert.assertTrue(driver.findElement(By.xpath(tooltipForGraphsElements)).getText().contains(line),
                    "The tooltip text is wrong expected " + line + " but found " + driver.findElement(By.xpath(tooltipForGraphsElements)).getText());
        }

    }
}

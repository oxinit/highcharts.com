package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import util.model.TooltipEmployeeForExpectedValue;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import static util.OpenCSVReader.readFromCSVExpectedValue;


public class HomePage extends BasePage {
    @FindBy(css = "a[id='CybotCookiebotDialogBodyButtonAccept']")
    private WebElement cookie;
    @FindBy(xpath = "//li/button[contains(@class,'highcharts')]")
    private List<WebElement> graphsButtons;
    @FindBy(xpath = "//*[local-name() = 'text'][@x='21']")
    private List<WebElement> graphsButtonsForHighlight;
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
    @FindBy(xpath = "//*[contains(@class,'highcharts-exporting-group')]")
    private WebElement chartContextMenu;
    @FindBy(xpath = "//*[local-name() = 'rect'][@class='highcharts-button-box']")
    private WebElement chartContextMenuForHighlight;
    @FindBy(xpath = "//*[text()='Download CSV']")
    private WebElement downloadCsvButton;
    String tooltipForGraphsElements = "//*[local-name() = 'text'][@x='8']";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void openHomePage(String url) {
        driver.get(url);
        try {
            new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions
                            .elementToBeSelected(cookiePopUpDialog));

            //this condition never pass so we click on cookie after wait
        } catch (Exception e) {
            highLightElement(cookie, driver);
            cookie.click();
        }
    }

    public void clickFirstGraphsButton() {
        highLightElement(graphsButtonsForHighlight.get(0), driver);
        graphsButtons.get(0).click();

    }

    public void clickSecondGraphsButton() {
        unHighLightElement(graphsButtonsForHighlight.get(0), driver);
        highLightElement(graphsButtonsForHighlight.get(1), driver);
        graphsButtons.get(1).click();
    }

    public void clickChartContextMenu() {
        unHighLightElement(graphsButtonsForHighlight.get(1), driver);
        highLightElement(chartContextMenuForHighlight, driver);
        new Actions(driver).click(chartContextMenu).perform();
    }

    public void clickDownloadCsvButton() {
        highLightElement(downloadCsvButton, driver);
        downloadCsvButton.click();
    }

    public void checkTooltip() throws IOException {
        unHighLightElement(graphsButtons.get(1), driver);
        List<TooltipEmployeeForExpectedValue> tooltips =
                readFromCSVExpectedValue("src/test/resources/tooltips_expected_values.csv");
        final int FIRST_ELEMENT = 0;
        SoftAssert softAssert = new SoftAssert();
        Actions ac = new Actions(driver);
        highLightElement(boxForHighsoftGraph, driver);
        ac.moveToElement(boxForHighsoftGraph)
                .moveToElement(boxForHighsoftGraph, -400, 50).perform();
        highLightElement(driver.findElement(By.xpath(tooltipForGraphsElements)), driver);
        softAssert.assertTrue(driver.findElement(By.xpath(tooltipForGraphsElements)).getText()
                        .contains(tooltips.get(FIRST_ELEMENT).getDate())
                        && driver.findElement(By.xpath(tooltipForGraphsElements)).getText()
                        .contains(tooltips.get(FIRST_ELEMENT).getEmployeeNameAndStatus())
                        && driver.findElement(By.xpath(tooltipForGraphsElements)).getText()
                        .contains(tooltips.get(FIRST_ELEMENT).getQuantity()),
                "The tooltip text is wrong expected= "
                        + tooltips.get(FIRST_ELEMENT).getEmployeeNameAndStatus() + ' '
                        + tooltips.get(FIRST_ELEMENT).getDate() + ' '
                        + tooltips.get(FIRST_ELEMENT).getQuantity() + ' '
                        + " but found " + driver.findElement(By.xpath(tooltipForGraphsElements)).getText());
        int i = 1;
        while (i < tooltips.size()) {
            highLightElement(pathForHighsoftEmployeeGraph.get(i), driver);
            ac.moveToElement(pathForHighsoftEmployeeGraph.get(i)).perform();
            highLightElement(driver.findElement(By.xpath(tooltipForGraphsElements)), driver);
            softAssert.assertTrue(driver.findElement(By.xpath(tooltipForGraphsElements)).getText()
                            .contains(tooltips.get(i).getDate())
                            && driver.findElement(By.xpath(tooltipForGraphsElements)).getText()
                            .contains(tooltips.get(i).getEmployeeNameAndStatus())
                            && driver.findElement(By.xpath(tooltipForGraphsElements)).getText()
                            .contains(tooltips.get(i).getQuantity()),
                    "The tooltip text is wrong expected "
                            + tooltips.get(i).getEmployeeNameAndStatus() + ' '
                            + tooltips.get(FIRST_ELEMENT).getDate() + ' '
                            + tooltips.get(FIRST_ELEMENT).getQuantity() + ' '
                            + " but found " + driver.findElement(By.xpath(tooltipForGraphsElements)).getText());
            i++;
        }
        softAssert.assertAll();
    }

}

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import util.model.TipForEmployee;

import java.io.IOException;
import java.util.List;

import static util.OpenCSVReader.readFromCSV;

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
    @FindBy(xpath="//*[contains(@class,'highcharts-exporting-group')]")
    private WebElement chartContextMenu;
    @FindBy(xpath="//*[text()='Download CSV']")
    private WebElement downloadCsvButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }
    String tooltipForGraphsElements = "//*[local-name() = 'text'][@x='8']";
    public void openHomePage(String url)  {
        driver.get(url);
         clickOnElement(cookie);
    }
    public void clickFirstGraphsButton() {graphsButtons.get(0).click();}
    public void clickSecondGraphsButton() {
        graphsButtons.get(1).click();
    }
    public void clickChartContextMenu() {
        new Actions(driver).click(chartContextMenu).perform();
        }
    public void clickDownloadCsvButton() {
        downloadCsvButton.click();}

    public void checkTooltip() throws IOException {
        List<TipForEmployee> tooltips = readFromCSV("src/test/resources/highcharts-and-highsoft.csv");
        final int FIRST_ELEMENT = 0;
        Actions ac = new Actions(driver);
        ac.moveToElement(boxForHighsoftGraph)
                .moveToElement(boxForHighsoftGraph, -400, 50).perform();
        Assert.assertTrue(driver.findElement(By.xpath(tooltipForGraphsElements)).getText()
                        .contains(tooltips.get(FIRST_ELEMENT).getDate())
                        && driver.findElement(By.xpath(tooltipForGraphsElements)).getText()
                        .contains(tooltips.get(FIRST_ELEMENT).getEmployeeNameAndStatus())
                        && driver.findElement(By.xpath(tooltipForGraphsElements)).getText()
                        .contains(tooltips.get(FIRST_ELEMENT).getQuantity()),
                "The tooltip text is wrong expected= " + tooltips.get(FIRST_ELEMENT).getEmployeeNameAndStatus()
                        + tooltips.get(FIRST_ELEMENT).getDate()
                        + tooltips.get(FIRST_ELEMENT).getQuantity()+
                        " but found " + driver.findElement(By.xpath(tooltipForGraphsElements)).getText());
        int i = 1;
        while (i < tooltips.size()) {
            ac.moveToElement(pathForHighsoftEmployeeGraph.get(i)).perform();
            Assert.assertTrue(driver.findElement(By.xpath(tooltipForGraphsElements)).getText()
                            .contains(tooltips.get(i).getDate())
                            && driver.findElement(By.xpath(tooltipForGraphsElements)).getText()
                            .contains(tooltips.get(i).getEmployeeNameAndStatus())
                            && driver.findElement(By.xpath(tooltipForGraphsElements)).getText()
                            .contains(tooltips.get(i).getQuantity()),
                    "The tooltip text is wrong expected " + tooltips.get(i).getEmployeeNameAndStatus()
                            + tooltips.get(FIRST_ELEMENT).getDate()
                            + tooltips.get(FIRST_ELEMENT).getQuantity()+
                            " but found " + driver.findElement(By.xpath(tooltipForGraphsElements)).getText());
            i++;
        }
    }
}

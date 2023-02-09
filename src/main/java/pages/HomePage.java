package pages;

import com.opencsv.exceptions.CsvException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import util.model.TipForEmployee;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import static util.OpenCSVReader.readFromCSVWithOpenCSV;

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
            new WebDriverWait(driver, Duration.ofSeconds(4))
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

    public void checkTooltip() throws IOException, CsvException {
            //To handle the error of missing lines you can add try-catch block,
             //and in the catch block please add a throw of an exception,
             //something like this:throw new Exception("Some text about missing lines")
        List<TipForEmployee> tooltips = readFromCSVWithOpenCSV("src/test/resources/tooltips_expected_values.csv");
        Actions ac = new Actions(driver);
        ac.moveToElement(boxForHighsoftGraph)
                .moveToElement(boxForHighsoftGraph, -400, 50).perform();
        Assert.assertTrue(driver.findElement(By.xpath(tooltipForGraphsElements)).getText()
                        .contains(tooltips.get(0).getEmployeeNameAndStatus()),
                "The tooltip text is wrong expected " + tooltips.get(1).getEmployeeNameAndStatus() + " but found " + driver.findElement(By.xpath(tooltipForGraphsElements)).getText());
        int i = 1;
        while (i < tooltips.size()){

        ac.moveToElement(pathForHighsoftEmployeeGraph.get(i)).perform();

        Assert.assertTrue(driver.findElement(By.xpath(tooltipForGraphsElements)).getText()
                        .contains(tooltips.get(i).getEmployeeNameAndStatus())&&
                        driver.findElement(By.xpath(tooltipForGraphsElements)).getText()
                                .contains(tooltips.get(i).getDate()),
                "The tooltip text is wrong expected " + tooltips.get(i).getEmployeeNameAndStatus() + " but found " + driver
                        .findElement(By.xpath(tooltipForGraphsElements)).getText());
            i++;

        }
    }
}

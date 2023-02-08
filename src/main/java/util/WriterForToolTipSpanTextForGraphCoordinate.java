package util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

public class WriterForToolTipSpanTextForGraphCoordinate extends BasePage {

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

    public WriterForToolTipSpanTextForGraphCoordinate(WebDriver driver)  {
        super(driver);
     try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/test/resources/tooltipSpanTextForGraphCoordinate")))
     {
        Actions ac = new Actions(driver);
        ac.moveToElement(boxForHighsoftGraph)
                .moveToElement(boxForHighsoftGraph, -400, 50).perform();
        writer.write(driver.findElement(By.xpath("//*[local-name() = 'text'][@x='8']")).getText() + "\n");
        int i = 1;
        while (i < pathForHighsoftEmployeeGraph.size()) {
            ac.moveToElement(boxForHighsoftGraph).moveToElement(pathForHighsoftEmployeeGraph.get(i)).perform();
            i++;
            writer.write(driver.findElement(By.xpath("//*[local-name() = 'text'][@x='8']")).getText() + "\n");

        }}catch (Exception e){ e.printStackTrace();}
    }
}
package pages;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import static util.OpenCSVReader.readFromCSV;
import static util.OpenCSVReader.readFromCSVExpectedValue;

public class BasePage {

    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void compareTwoDifferentCSV() throws IOException {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent());
        } catch (Exception e) {
            int i = 0;
            SoftAssert softAssert = new SoftAssert();
            List<?> expectedTooltips = readFromCSVExpectedValue("src/test/resources/tooltips_expected_values.csv");
            List<?> importedTooltips = readFromCSV("src/test/resources/tempcsv/highcharts-and-highsoft.csv");
            while (i < expectedTooltips.size()) {
                softAssert.assertTrue(expectedTooltips.get(i).equals(importedTooltips.get(i)));
                i++;
            }
            softAssert.assertAll();
        }

    }

    public void cleanTempCsvFolder() throws IOException {
        FileUtils.cleanDirectory(new File(System.getProperty("user.dir") + File.separator + "src" +
                File.separator + "test" +
                File.separator + "resources" +
                File.separator + "tempcsv"));
    }

    public void clickOnElement(WebElement webElement) {
        try {
            webElement.click();
        } catch (Exception e) {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", webElement);
        }
    }

    public void highLightElement(WebElement ele, WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].setAttribute('style',  arguments[1])", ele,
                "border: 5px solid red;background:yellow;color:green;fill:blue;");

    }

    public void unHighLightElement(WebElement ele, WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", ele, "");
    }
}

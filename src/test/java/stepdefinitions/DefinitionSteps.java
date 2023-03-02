package stepdefinitions;

import com.epam.reportportal.testng.ReportPortalTestNGListener;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import manager.PageFactoryManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Listeners;
import pages.HomePage;
import testngcucumberrunner.RunnerTests;
import util.CustomMethodInvokedListener;
import util.CustomTestListener;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;


@Listeners({CustomTestListener.class , CustomMethodInvokedListener.class, ReportPortalTestNGListener.class})
public class DefinitionSteps extends RunnerTests {
    WebDriver driver;
    HomePage homePage;
    PageFactoryManager pageFactoryManager;
    Logger logger = LoggerFactory.getLogger(DefinitionSteps.class);
    @Before
    public void testsSetUp() {

        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("download.default_directory",  System.getProperty("user.dir")+ File.separator + "src" +
                File.separator + "test"+
                File.separator + "resources"+
                File.separator + "tempcsv");
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        logger.trace("download default directory has been set");
        String browser="chrome";
        driver = WebDriverManager.getInstance(browser).capabilities(options).create() ;
        driver.manage().window().maximize();
        pageFactoryManager = new PageFactoryManager(driver);
        logger.debug("driver has been set");
    }


    @After
    public void tearDown(Scenario scenario)  {
        if (scenario.isFailed()){
        logger.info("Screenshot taken on scenario.isFailed()"+
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss").format(LocalDateTime.now()));
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+ File.separator + "src" +
                        File.separator + "test"+
                        File.separator + "resources"+
                        File.separator + "screenshots"+
                        File.separator +
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH_mm_ss").format(LocalDateTime.now())+
                        ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            byte[] src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(src, "image/png", "screenshot");

        }
            driver.quit();
        logger.warn("driver quited");
        }

    @And("User open {string} page")
    public void openPage(final String url) throws IOException {
        homePage = pageFactoryManager.getHomePage();
        homePage.openHomePage(url);
        homePage.cleanTempCsvFolder();
        logger.warn("folder cleaning must be done before file opened as open csv hold them opened somehow");
    }

    @When("User click button below graph with name Google search for highcharts")
    public void userClickingButtonBelowGraphWithNameGoogleSearchForHighcharts()  {
        homePage.clickFirstGraphsButton();
        logger.info("step with click on 'Google search for highcharts' graph button");
    }

    @And("User click button with name Revenue")
    public void userClickingButtonToItWithNameRevenue() {
        homePage.clickSecondGraphsButton();
        logger.info("step with click on revenue graph button");
    }

    @Then("User check tooltips")
    public void userChecksTooltipText() throws IOException {
        homePage.checkTooltip();
        logger.info("step for tooltip check");
    }

    @And("User click on graph menu button")
    public void userClickOnGraphMenuButton()  {
        homePage.clickChartContextMenu();
        logger.info("step for click on menu graph button");
    }

    @And("User click download as csv file")
    public void userClickDownloadAsCsvFile()  {
        homePage.clickDownloadCsvButton();
        logger.info("step for click download as csv file");
    }

    @And("User check does downloaded csv has expected values")
    public void userChecksCsvHasExpectedValues() throws IOException {
        homePage.compareTwoDifferentCSV();
        logger.info("step for check of two csv");
    }
}


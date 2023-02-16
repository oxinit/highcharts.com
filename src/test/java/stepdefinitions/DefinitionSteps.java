package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import manager.PageFactoryManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.HomePage;
import testngcucumberrunner.RunnerTests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DefinitionSteps extends RunnerTests {
    WebDriver driver;
    HomePage homePage;
    PageFactoryManager pageFactoryManager;

    @Before
    public void testsSetUp() {

        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("download.default_directory",  System.getProperty("user.dir")+ File.separator + "src" +
                File.separator + "test"+
                File.separator + "resources");
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        String browser="chrome";
        driver = WebDriverManager.getInstance(browser).capabilities(options).create() ;
        driver.manage().window().maximize();
        pageFactoryManager = new PageFactoryManager(driver);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @And("User open {string} page")
    public void openPage(final String url) throws InterruptedException {
        homePage = pageFactoryManager.getHomePage();
        homePage.openHomePage(url);
    }

    @When("User click button below graph with name Google search for highcharts")
    public void userClickingButtonBelowGraphWithNameGoogleSearchForHighcharts()  {
        homePage.clickFirstGraphsButton();
    }

    @And("User click button with name Revenue")
    public void userClickingButtonToItWithNameRevenue() {
        homePage.clickSecondGraphsButton();
    }

    @Then("User check tooltips")
    public void userChecksTooltipText() throws  IOException {
        homePage.checkTooltip();
    }

    @And("User click on graph menu button")
    public void userClickOnGraphMenuButton() throws InterruptedException {
        homePage.clickChartContextMenu();
    }

    @And("User click download as csv file")
    public void userClickDownloadAsCsvFile()  {
        homePage.clickDownloadCsvButton();
    }
}


package stepdefinitions;

import com.opencsv.exceptions.CsvException;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import manager.PageFactoryManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import testngcucumberrunner.RunnerTests;

import java.io.IOException;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;

public class DefinitionSteps extends RunnerTests {
    WebDriver driver;
    HomePage homePage;
    PageFactoryManager pageFactoryManager;

    @Before
    public void testsSetUp() {
        chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        pageFactoryManager = new PageFactoryManager(driver);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @And("User open {string} page")
    public void openPage(final String url)  {
        homePage = pageFactoryManager.getHomePage();
        homePage.openHomePage(url);
    }

    @When("User click button below graph with name Google search for highcharts")
    public void userClickingButtonBelowGraphWithNameGoogleSearchForHighcharts()  {
        homePage.clickFirstGraphsButton();
    }

    @And("User click button next to it with name Revenue")
    public void userClickingButtonToItWithNameRevenue() {
        homePage.clickSecondGraphsButton();
    }

    @Then("User check tooltips")
    public void userChecksTooltipText() throws IOException, CsvException {
        homePage.checkTooltip();
    }
}


package stepdefinitions;

import pages.HomePage;
import TestngCucumberRunner.RunnerTests;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import manager.PageFactoryManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;

public class DefinitionSteps extends RunnerTests {
    private static final long DEFAULT_TIMEOUT = 60;
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

    // @Given("I use {string}")
    //public void iUse(String browser) {
    //    driver = WebDriverManager.getInstance(browser).create();
    // }
    @After
    public void tearDown() {
        driver.quit();
    }

    @And("User opens {string} page")
    public void openPage(final String url) throws InterruptedException {
        homePage = pageFactoryManager.getHomePage();
        homePage.openHomePage(url);
    }

    @When("User clicking button below graph with name Google search for highcharts")
    public void userClickingButtonBelowGraphWithNameGoogleSearchForHighcharts()  {
        homePage.clickFirstGraphsButton();
    }

    @And("User clicking button next to it with name Revenue")
    public void userClickingButtonToItWithNameRevenue() {
        homePage.clickSecondGraphsButton();
    }

    @Then("User checks tooltip {string}")
    public void userChecksTooltipText(final String text) throws InterruptedException, IOException {
        homePage.checkTooltip(text);
    }
}


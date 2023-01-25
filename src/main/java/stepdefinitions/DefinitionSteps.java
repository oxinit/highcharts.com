package stepdefinitions;

import Pages.HomePage;
import TestngCucumberRunner.RunnerTests;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import manager.PageFactoryManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;
import static org.testng.Assert.assertTrue;

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
    @After
    public void tearDown() {
        driver.quit();
    }

    @And("User opens {string} page")
    public void openPage(final String url) {
        homePage = pageFactoryManager.getHomePage();
        homePage.openHomePage(url);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS) ;
        homePage.waitForPageLoading();
    }
    @When("User clicking button below graph with name Google search for highcharts")
    public void userClickingButtonBelowGraphWithNameGoogleSearchForHighcharts() {
        homePage.clickGraphsButton1();
    }
    @Then("User checks query generated {string}")
    public void userCheckQueryGenerated(final String query) {
    assertTrue( homePage.isQueryContainSearchKeyWords(query));
    }

}


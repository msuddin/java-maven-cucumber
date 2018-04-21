package definition;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StepDefinitions {

    WebDriver driver = new ChromeDriver();

    @Given("^I navigate to site$")
    public void i_navigate_to_site() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver.navigate().to("https://office.tradecoinclub.com/login");
    }

    @When("^I enter username ([^\"]*)$")
    public void i_enter_username(String username) {
        driver.findElement(By.name("login")).sendKeys(username);
    }

    @When("^I enter password ([^\"]*)$")
    public void i_enter_password(String password) {
        driver.findElement(By.name("password")).sendKeys(password);
    }

    @When("^I press sign in")
    public void i_press_sign_in() {
        driver.findElement(By.id("sign-in")).click();
    }

    @When("^I wait for google authenticator to be completed$")
    public void i_wait_for_google_authenticator_to_be_completed() {
        findTextOnPage("Successfully logged in!");
    }

    @Then("^I turn on trading$")
    public void i_turn_on_trading() throws Throwable {
        throw new PendingException();
    }

    @Then("^wait for the confirmation$")
    public void wait_for_the_confirmation() {
        findTextOnPage("Risk saved and shit!");
    }

    private void findTextOnPage(String text) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions
                .visibilityOfElementLocated(
                        By.xpath("//*[contains(text(), " + text + ")]")
                )
        );
    }

    @After
    public void tearDown() {
        driver.close();
    }
}

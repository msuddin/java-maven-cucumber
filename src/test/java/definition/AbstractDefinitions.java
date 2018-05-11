package definition;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.hamcrest.Matchers.is;

public class AbstractDefinitions extends DefinitionUtils {

    @Given("^I navigate to site with user ([^\"]*)$")
    public void navigateTo(String userFile) throws IOException {
        InputStream input = getClass().getClassLoader()
                .getResourceAsStream(userFile + ".properties");
        prop.load(input);

        driver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

        driver.manage().window().maximize();
        driver.navigate().to("https://office.tradecoinclub.com/login");
    }

    @When("^I enter username$")
    public void enterUsername() {
        waitForElementTobeVisible(By.name("login"));
        driver.findElement(By.name("login")).sendKeys(prop.getProperty("username"));
    }

    @When("^I enter password$")
    public void enterPassword() {
        waitForElementTobeVisible(By.name("password"));
        driver.findElement(By.name("password")).sendKeys(prop.getProperty("password"));
    }

    @When("^I press sign in")
    public void signIn() {
        waitForElementTobeVisible(By.id("sign-in"));
        driver.findElement(By.id("sign-in")).click();
        waitForElementTobeVisible(By.id("validateCode"));
    }

    @When("^I wait for google authenticator to be completed$")
    public void googleAuthenticator() {
        waitForElementTobeVisible(By.id("validateCode"));
        driver.findElement(By.id("validateCode")).sendKeys(generateGoogleAuthenticatorCode());

        waitForElementTobeVisible(By.id("BTNvalidateCode"));
        driver.findElement(By.id("BTNvalidateCode")).click();

        waitForElementTobeVisible(By.cssSelector("#jquery-notific8-notification-1"));

        if (!prop.getProperty("username").contains("soyzun")) {
            waitForElementTobeVisible(By.cssSelector("#md-founder-tcoin button"));
            driver.findElement(By.cssSelector("#md-founder-tcoin button")).click();
        }

        waitForElementTobeVisible(By.id("item-package"));
    }

    @Then("^I log out$")
    public void logOut() {
        sleep(5);
        List<WebElement> h2 = driver.findElements(By.tagName("h2"));
        for (int i = 0; i < h2.size(); i++) {
            if (h2.get(i).getText().contains(prop.getProperty("firstName")) &&
                    h2.get(i).getText().contains(prop.getProperty("surName"))) {
                h2.get(i).click();
                break;
            }
        }

        waitForElementTobeVisible(By.linkText("Logout"));
        driver.findElement(By.linkText("Logout")).click();

        Assert.assertThat(driver.getCurrentUrl(), is("https://office.tradecoinclub.com/login"));
    }

    @Then("^close the browser$")
    public void tearDown() {
        driver.close();
    }
}

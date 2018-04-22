package definition;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.jboss.aerogear.security.otp.Totp;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.Matchers.is;

public class StepDefinitions {

    private WebDriver driver = new ChromeDriver();
    private Properties prop = new Properties();

    @Given("^I navigate to site with user ([^\"]*)$")
    public void navigateTo(String userFile) throws IOException {
        InputStream input = getClass().getClassLoader()
                .getResourceAsStream(userFile + ".properties");
        prop.load(input);

        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver.manage().window().maximize();
        driver.navigate().to(prop.getProperty("url"));
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
        waitForElementTobeVisible(By.id("item-package"));
    }

    @Then("^I set trading to \"([^\"]*)\"$")
    public void setTrading(String tradeSetting) {
        throw new PendingException();
    }

    @Then("^wait for trading confirmation$")
    public void tradingConfirmation() {
        throw new PendingException();
    }

    @Then("^I log out$")
    public void logOut() {
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

        Assert.assertThat(driver.getCurrentUrl(), is(prop.getProperty("url")));
    }

    @Then("^I capture current commission amount$")
    public void commissionWalletAmount() throws IOException {
        waitForElementTobeVisible(By.cssSelector(".comission-bg"));
        String commission = driver.findElement(By.cssSelector(".comission-bg .value-panel")).getText();
        commission = commission.replace("TCOIN", "");

        writeToFile(commission, "report-commission.csv");
    }

    @Then("^I capture current trade amount$")
    public void tradeWalletAmount() throws IOException {
        waitForElementTobeVisible(By.cssSelector(".trade-bg"));
        String trade = driver.findElement(By.cssSelector(".comission-bg .value-panel")).getText();
        trade = trade.replace("TCOIN", "");

        writeToFile(trade, "report-trade.csv");
    }

    private void writeToFile(String trade, String s) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(s, true));
        writer.println(prop.getProperty("username") + "," + trade);
        writer.close();
    }

    private String generateGoogleAuthenticatorCode() {
        String otpKeyStr = prop.getProperty("secret");
        Totp totp = new Totp(otpKeyStr);
        return totp.now();
    }

    private void waitForElementTobeVisible(By element) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    private void sleep(int seconds) {
        try {
            Thread.sleep(1000 * seconds);
        } catch (Exception e) {}
    }

    @After
    public void tearDown() {
        driver.close();
    }
}

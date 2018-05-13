package definition;

import org.jboss.aerogear.security.otp.Totp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class DefinitionUtils {

    protected static WebDriver driver;
    protected static Properties prop = new Properties();
    protected static boolean printedColumns = false;
    protected static final String reportFileName = "report " + getDate() +".csv";

    protected void writeToFile(String myPackage,
                               String trade,
                               String commission,
                               List<String> binaryPoints,
                               List<String> tradingDays,
                               String totalTradeEarning)
            throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(reportFileName, true));
        if (!printedColumns) {
            writer.println(
                    "USERNAME,MY PACKAGE,COMMISSION,TRADE,LEFT TEAM,RIGHT TEAM,CYCLE 01,CYCLE 02,CYCLE 03,REMAINING DAYS,TOTAL TRADE EARNING"
            );
            printedColumns = true;
        }
        writer.println(
                prop.getProperty("username") + ","
                        + myPackage + ","
                        + commission + ","
                        + trade + ","
                        + binaryPoints.get(0).replace("\n", "") + ","
                        + binaryPoints.get(3).replace("\n", "") + ","
                        + tradingDays.get(0).replace("/", "--") + ","
                        + tradingDays.get(1).replace("/", "--") + ","
                        + tradingDays.get(2).replace("/", "--") + ","
                        + tradingDays.get(3).replace("/", "--") + ","
                        + totalTradeEarning
        );
        writer.close();
    }

    protected String generateGoogleAuthenticatorCode() {
        String otpKeyStr = prop.getProperty("secret");
        Totp totp = new Totp(otpKeyStr);
        return totp.now();
    }

    protected void waitForElementTobeVisible(By element) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    protected void clickOnSideMenu(By by) {
        waitForElementTobeVisible(by);
        driver.findElement(by).click();
    }

    protected void sleep(int seconds) {
        try {
            Thread.sleep(1000 * seconds);
        } catch (Exception e) {}
    }

    private static String getDate() {
        return new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    }
}

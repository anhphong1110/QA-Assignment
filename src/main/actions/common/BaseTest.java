package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    private final String projectLocation = System.getProperty("user.dir");
    private WebDriver driver;
    private enum BROWSER{
        CHROME , FIREFOX
    }
    protected WebDriver getBrowserDrive(String browserName){
        BROWSER browser = BROWSER.valueOf(browserName.toUpperCase());
        if(browser == BROWSER.CHROME)
        {
            System.setProperty("webdriver.chrome.driver", "resources/driver/chromedriver.exe");
            driver = new ChromeDriver();
        }
        else if(browser == BROWSER.FIREFOX)
        {
            System.setProperty("webdrive.gecko.drive",projectLocation +"\\resources\\geckodriver.exe");
            driver = new FirefoxDriver();
        }
        else throw new RuntimeException("Please Enter browser correctly");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("https://openweathermap.org/");
        driver.manage().window().maximize();
        return driver;
    }
}

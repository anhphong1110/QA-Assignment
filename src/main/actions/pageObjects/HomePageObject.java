package pageObjects;

import common.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pageUI.HomePageLocator;

public class HomePageObject extends BasePage {
    private WebDriver driver;

    public HomePageObject (WebDriver driver) {
        this.driver = driver;
    }

    public String getHomePageTitle(){
        return getPageTitle(driver);
    }
    public void inputTextIntoSearchbox(String city){
        sendkeyToElement(driver,HomePageLocator.HOMEPAGE_INPUT_SEARCH,city);
    }
    public void pressEnterIntoSearchbox(){
        pressKeyToElement(driver,HomePageLocator.HOMEPAGE_INPUT_SEARCH, Keys.ENTER);
    }
}

package pageObjects;

import common.BasePage;
import org.openqa.selenium.WebDriver;
import pageUI.SearchPageLocator;

import java.util.Hashtable;

public class SearchPageObject extends BasePage {
    private WebDriver driver;

    public SearchPageObject (WebDriver driver) {
        this.driver = driver;
    }

    public String getSearchPageTitle(){
        return getPageTitle(driver);
    }

    public Hashtable getWeatherInformation(){
        Hashtable<String, String> dictWeatherInformation = new Hashtable<String, String>();
        waitForElementVisible(driver,SearchPageLocator.SEARCHPAGE_LABEL_CITYNAME);
        waitForElementVisible(driver,SearchPageLocator.SEARCHPAGE_LABEL_GEO);
        dictWeatherInformation.put("CityName", getElementText(driver,SearchPageLocator.SEARCHPAGE_LABEL_CITYNAME));
        dictWeatherInformation.put("Geo", getElementText(driver,SearchPageLocator.SEARCHPAGE_LABEL_GEO));
        return dictWeatherInformation;
    }

    public boolean isTemperatureDisplayed(){
        waitForElementVisible(driver,SearchPageLocator.SEARCHPAGE_LABEL_TEMPERRATURE);
        return isElementDisplayed(driver,SearchPageLocator.SEARCHPAGE_LABEL_TEMPERRATURE);
    }

    public String getSearchText(){
        return getElementAttribute(driver,SearchPageLocator.SEARCHPAGE_INPUT_SEARCH,"value");
    }

}


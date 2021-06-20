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
        dictWeatherInformation.put("CityName", getElementText(driver,SearchPageLocator.SEARCHPAGE_LABEL_CITYNAME));
        dictWeatherInformation.put("Temperature", getElementText(driver,SearchPageLocator.SEARCHPAGE_LABEL_CITYNAME));
        System.out.println(getElementText(driver,"//div[@id='forecast_list_ul']//tr[1]//p[2])"));
        return dictWeatherInformation;
    }

}


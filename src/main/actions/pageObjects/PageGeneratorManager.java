package pageObjects;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
    private static HomePageObject homePage;
    private static SearchPageObject searchPage;

    public static HomePageObject getHomePage(WebDriver driver){
        if (homePage == null)
        {
            homePage = new HomePageObject(driver);
        }
        return homePage;
    }
    public static SearchPageObject getSearchPage(WebDriver driver){
        if (searchPage == null)
        {
            searchPage = new SearchPageObject(driver);
        }
        return searchPage;
    }
}

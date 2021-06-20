package pageObjects;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
    private static HomePageObject homePage;

    public static HomePageObject getHomePage(WebDriver drive){
        if (homePage == null)
        {
            homePage = new HomePageObject(drive);
        }
        return homePage;
    }
}

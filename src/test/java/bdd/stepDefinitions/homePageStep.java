package bdd.stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import common.BaseTest;
import pageObjects.HomePageObject;
import pageObjects.SearchPageObject;
import pageObjects.PageGeneratorManager;
import testData.testData;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class homePageStep extends BaseTest{
    private WebDriver driver;
    private HomePageObject homePage;
    private SearchPageObject searchPage;

    @Before
    public void beforeClass(){
        driver = getBrowserDrive("chrome");
        homePage = PageGeneratorManager.getHomePage(driver);
        searchPage = PageGeneratorManager.getSearchPage(driver);
    }

    @After
    public void tearDownClass() {
        driver.quit();
    }

    @Given("^User is on homepage$")
    public void user_is_on_homepage(){
        Assert.assertEquals("Page Title is NOT matching",testData.HOME_PAGE_TITLE,homePage.getHomePageTitle());
    }

    @When("^User input '(.*)' into search box")
    public void user_input_into_city_search_box(String city){
        homePage.inputTextIntoSearchbox(city);
    }

    @And("^User press enter to search$")
    public void user_press_enter_to_search(){
        homePage.pressEnterIntoSearchbox();
    }

    @Then("^Search page is displayed$")
    public void search_page_is_displayed(){
        Assert.assertEquals("Page Title is NOT matching", testData.SEARCH_PAGE_TITLE, searchPage.getSearchPageTitle());
        searchPage.getWeatherInformation();
    }

    @And("^Weather information is displayed$")
    public void weather_information_is_displayed(DataTable dataTable) {
        List<Map<String, String>> expected = dataTable.asMaps(String.class, String.class);
        Hashtable<String, String> actual = searchPage.getWeatherInformation();
        Assert.assertEquals(expected.get(0).get("CityNameAndCountry"),actual.get("CityName"));
        Assert.assertEquals(expected.get(0).get("Geo"),actual.get("Geo"));
        Assert.assertTrue(searchPage.isTemperatureDisplayed());

    }

    @And("^Search form is displayed with entered city$")
    public void search_form_is_displayed_with_entered_city(DataTable dataTable){
        List<Map<String, String>> expected = dataTable.asMaps(String.class, String.class);
        Assert.assertEquals(expected.get(0).get("CityName"),searchPage.getSearchText());
    }


}

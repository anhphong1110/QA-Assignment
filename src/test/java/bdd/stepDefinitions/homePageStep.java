package bdd.stepDefinitions;

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

public class homePageStep extends BaseTest{
    private WebDriver driver;

    private HomePageObject homePage;
    private SearchPageObject searchPage;

    @Before
    public void beforeClass(){
        driver = getBrowserDrive("chrome");
        homePage = PageGeneratorManager.getHomePage(driver);
    }

    @After
    public void tearDownClass() {
        driver.quit();
    }

    @Given("^User is on homepage$")
    public void user_is_on_homepage(){
        Assert.assertEquals("Page Title is matching",testData.HOME_PAGE_TITLE,homePage.getHomePageTitle());
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
        searchPage = PageGeneratorManager.getSearchPage(driver);
        Assert.assertEquals("Page Title is matching", testData.SEARCH_PAGE_TITLE, searchPage.getSearchPageTitle());
    }

//    @And("^find page header as Weather in your city is displayed$")
//    public void find_page_header_is_displayed() {
//        Assert.assertEquals("Headline is matching", OpenWeatherMapData.HEADLINE_WEATHER_IN_YOUR_CITY, findPage.headlineInFindPage.getText());
//    }
//
//    @And("^search form is displayed with the previous city entered$")
//    public void search_form_is_displayed_with_previous_city_entered(){
//        Assert.assertEquals("City is matching", getCity(), findPage.searchBoxInForm.getAttribute("value"));
//        Assert.assertEquals("Search button is matching", OpenWeatherMapData.SEARCH_BUTTON_TEXT, findPage.searchButtonInForm.getText());
//    }
//
//    @And("^forecast list is displayed$")
//    public void forecast_list_is_displayed(){
//        Assert.assertNotNull(findPage.forecastList);
//    }
//
//    @And("^forecast list is NOT displayed$")
//    public void forecast_list_is_not_displayed(){
//        if(driver.findElements(By.xpath("//*[@id='forecast_list_ul']//a")).isEmpty()){
//            Assert.assertTrue(true);
//        }else{
//            Assert.fail();
//        }
//    }
}

package bdd.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import common.BaseTest;
import pageObjects.HomePageObject;
import pageObjects.PageGeneratorManager;

public class homePageStep extends BaseTest{
    private WebDriver driver;
    private String homePageTile;
    private HomePageObject homePage;

    @Before
    public void beforeClass(){
        driver = getBrowserDrive("chrome");
        homePageTile = "Сurrent weather and forecast - OpenWeatherMap";
        homePage = PageGeneratorManager.getHomePage(driver);
    }

    @After
    public void tearDownClass() {
        driver.quit();
    }

    @Given("^user is on homepage$")
    public void user_is_on_homepage(){
        Assert.assertEquals("Page Title is matching",homePageTile,homePage.getHomePageTitle());
    }

    @When("^user enters '(.*)' into search box")
    public void user_enters_valid_city_in_the_navigation_search_box(String city){
        homePage.inputTextIntoSearchbox(city);
    }

//    @And("^user selects enter on keyboard to search$")
//    public void user_selects_enter_on_keyboard_to_search(){
//        homePage.pressEnterIntoSearchbox();
//    }
//
//    @Then("^find page is displayed with correct page title$")
//    public void find_page_is_displayed_with_correct_page_title(){
//        Assert.assertEquals("Page Title is matching", OpenWeatherMapData.FIND_PAGE_TITLE, driver.getTitle());
//    }
//
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

package common;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

public class BasePage {
    private Alert alert;
    private WebDriverWait explicitWait;
    private final long timeout = 30;
    private Select select;
    private JavascriptExecutor jsExecutor;
    private Actions action;

    protected static BasePage getBasepage(){
        return new BasePage();
    }

    protected void openPageURL(WebDriver driver, String pageURL) {
        driver.get(pageURL);
    }

    protected String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    protected String getPageURL(WebDriver driver, String pageURL) {
        return driver.getCurrentUrl();
    }

    protected String getPageSource(WebDriver driver) {
        return driver.getPageSource();
    }

    protected void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    protected void refreshCurrentPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    protected void forwardPage(WebDriver driver) {
        driver.navigate().forward();
    }

    protected Alert waitForAlertPresence(WebDriver driver) {
        explicitWait = new WebDriverWait(driver, timeout);
        return explicitWait.until(ExpectedConditions.alertIsPresent());
    }

    protected void acceptAlert(WebDriver driver) {
        alert = waitForAlertPresence(driver);
        alert.accept();
    }

    protected void cancelAlert(WebDriver driver) {
        alert = waitForAlertPresence(driver);
        alert.dismiss();
    }

    protected void sendkeyToAlert(WebDriver driver, String value) {
        alert = waitForAlertPresence(driver);
        alert.sendKeys(value);
    }

    protected String getAlertText(WebDriver driver) {
        alert = waitForAlertPresence(driver);
        return alert.getText();
    }

    protected void swithToWindowByID(WebDriver driver, String parentID) {
        Set<String> allWindowsID = driver.getWindowHandles();
        for (String windowID : allWindowsID) {
            if (!windowID.equals(parentID)) {
                driver.switchTo().window(windowID);
                break;
            }
        }
    }

    protected void swithToWindowByTitle(WebDriver driver, String expecteddwindownTitle) {
        Set<String> allWindowsID = driver.getWindowHandles();
        for (String windowID : allWindowsID) {
            driver.switchTo().window(windowID);
            String actualWindowTitle = driver.getTitle();
            if (actualWindowTitle.equals(expecteddwindownTitle))
                break;

        }
    }

    protected void closeAllWindowsExpectParent(WebDriver driver, String parentID) {
        Set<String> allWindowsID = driver.getWindowHandles();
        for (String windowID : allWindowsID) {
            if (!windowID.equals(parentID)) {
                driver.switchTo().window(windowID);
                driver.close();
                sleepInSecond(1);
            }
            if (driver.getWindowHandles().size() == 1) {
                driver.switchTo().window(parentID);
                break;
            }
        }
    }

    protected void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected By getByXpath(String locator) {
        return By.xpath(locator);
    }

    protected WebElement getElement(WebDriver driver, String locator) {
        return driver.findElement(getByXpath(locator));
    }

    protected List<WebElement> getElements(WebDriver driver, String locator) {
        return driver.findElements(getByXpath(locator));
    }

    protected void clickToElement(WebDriver driver, String locator) {
        getElement(driver, locator).click();
    }

    protected void sendkeyToElement(WebDriver driver, String locator, String value) {
        waitForElementVisible(driver,locator);
        getElement(driver, locator).clear();
        getElement(driver, locator).sendKeys(value);
    }

    protected int getElementSize(WebDriver driver, String locator) {
        return getElements(driver, locator).size();
    }

    protected void selectDropdownByText(WebDriver driver, String locator, String itemText) {
        select = new Select(getElement(driver, locator));
        select.selectByVisibleText(itemText);
    }

    protected String getSelectedItemDropdown(WebDriver driver, String locator) {
        select = new Select(getElement(driver, locator));
        return select.getFirstSelectedOption().getText();
    }

    protected boolean isDropdownMultiple(WebDriver driver, String locator) {
        select = new Select(getElement(driver, locator));
        return select.isMultiple();
    }

    protected void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
        getElement(driver, parentLocator).click();
        sleepInSecond(1);

        explicitWait = new WebDriverWait(driver, timeout);
        List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childItemLocator)));

        for (WebElement item : allItems) {
            if (item.getText().trim().equals(expectedItem)) {
                jsExecutor = (JavascriptExecutor) driver;
                jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
                sleepInSecond(1);

                item.click();
                sleepInSecond(1);
                break;
            }
        }
    }

    protected String getElementAttribute(WebDriver driver, String locator, String attributeName) {
        return getElement(driver, locator).getAttribute(attributeName);
    }

    protected String getElementText(WebDriver driver, String locator) {
        return getElement(driver, locator).getText();
    }

    protected void checkToCheckboxOrRadio(WebDriver driver, String locator) {
        if (!isElementSelected(driver, locator)) {
            getElement(driver, locator).click();
        }
    }

    protected void uncheckToCheckbox(WebDriver driver, String locator) {
        if (isElementSelected(driver, locator)) {
            getElement(driver, locator).click();
        }
    }

    protected boolean isElementDisplayed(WebDriver driver, String locator) {
        return getElement(driver, locator).isDisplayed();
    }

    protected boolean isElementEnabled(WebDriver driver, String locator) {
        return getElement(driver, locator).isEnabled();
    }

    protected boolean isElementSelected(WebDriver driver, String locator) {
        return getElement(driver, locator).isSelected();
    }

    protected WebDriver switchToIframeElement(WebDriver driver, String locator) {
        return driver.switchTo().frame(getElement(driver, locator));
    }

    protected WebDriver switchToDefaultContent(WebDriver driver, String locator) {
        return driver.switchTo().defaultContent();
    }

    protected void hoverToElement(WebDriver driver, String locator) {
        action = new Actions(driver);
        action.moveToElement(getElement(driver, locator)).perform();
    }

    protected void doubleClickToElement(WebDriver driver, String locator) {
        action = new Actions(driver);
        action.doubleClick(getElement(driver, locator)).perform();
    }

    protected void rightClickToElement(WebDriver driver, String locator) {
        action = new Actions(driver);
        action.contextClick(getElement(driver, locator)).perform();
    }

    protected void dragAndDropElement(WebDriver driver, String sourceLocator, String targetLocator) {
        action = new Actions(driver);
        action.dragAndDrop(getElement(driver, sourceLocator), getElement(driver, targetLocator));
    }

    protected void pressKeyToElement(WebDriver driver, String locator, Keys key) {
        action = new Actions(driver);
//        action.sendKeys(getElement(driver, locator), key).perform();
        action.moveToElement(getElement(driver, locator)).sendKeys(key).build().perform();
    }

    protected Object executeForBrowser(WebDriver driver, String javaScript) {
        jsExecutor = (JavascriptExecutor) driver;
        return jsExecutor.executeScript(javaScript);
    }

    protected String getInnerText(WebDriver driver) {
        jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }

    protected boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
        jsExecutor = (JavascriptExecutor) driver;
        String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
        return textActual.equals(textExpected);
    }

    protected void scrollToBottomPage(WebDriver driver) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    protected void navigateToUrlByJS(WebDriver driver, String url) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.location = '" + url + "'");
    }

    protected void highlightElement(WebDriver driver, String locator) {
        jsExecutor = (JavascriptExecutor) driver;
        WebElement element = getElement(driver, locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
        sleepInSecond(1);
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
    }

    protected void clickToElementByJS(WebDriver driver, String locator) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", getElement(driver, locator));
    }

    protected void scrollToElement(WebDriver driver, String locator) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(driver, locator));
    }

    protected void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(driver, locator));
    }

    protected void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(driver, locator));
    }

    protected boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
        explicitWait = new WebDriverWait(driver, timeout);
        jsExecutor = (JavascriptExecutor) driver;

        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }

    protected String getElementValidationMessage(WebDriver driver, String locator) {
        jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(driver, locator));
    }

    protected boolean isImageLoaded(WebDriver driver, String locator) {
        jsExecutor = (JavascriptExecutor) driver;
        return (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getElement(driver, locator));
    }

    protected void waitForElementVisible(WebDriver driver, String locator){
        explicitWait = new WebDriverWait(driver,timeout);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));
    }

    protected void waitForAllElementVisible(WebDriver driver, String locator){
        explicitWait = new WebDriverWait(driver,timeout);
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(locator)));
    }

    protected void waitForElementClickable(WebDriver driver, String locator){
        explicitWait = new WebDriverWait(driver,timeout);
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(locator)));
    }

    protected void waitForElementInVisible(WebDriver driver, String locator){
        explicitWait = new WebDriverWait(driver,timeout);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));
    }


}

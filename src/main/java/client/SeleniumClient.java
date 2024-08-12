package client;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Getter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * The SeleniumClient abstract class provides a base class for Selenium-based web automation clients.
 * It includes common methods and configurations for working with Selenium WebDriver.
 */
abstract class SeleniumClient {

    /**
     * Initializes the WebDriver and sets up the ChromeDriver using WebDriverManager.
     */
    SeleniumClient() {
        WebDriverManager.chromedriver().setup();
    }

    /**
     * Gets the WebDriver instance for web automation.
     */
    @Getter
    WebDriver driver = new ChromeDriver();

    // WebDriverWait with a timeout of 10 seconds
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    /**
     * Retrieves the WebDriverWait instance for waiting for specific conditions.
     *
     * @return The WebDriverWait instance with the configured timeout.
     */
    WebDriverWait getWebDriverWait() {
        return wait;
    }

    /**
     * Clicks on a WebElement using JavaScript to trigger the click event.
     *
     * @param element The WebElement to click on.
     */
    void clickJs(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    /**
     * Scrolls the web page to bring a specific WebElement into view.
     *
     * @param element The WebElement to scroll into view.
     */
    void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Waits for the web page to finish loading and become in a ready state.
     */
    void waitForLoad() {
        wait.until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

    /**
     * Goes to a specific URL in the web browser.
     *
     * @param url The URL to go to.
     */
    void goByUrl(String url) {
        driver.get(url);
    }
}

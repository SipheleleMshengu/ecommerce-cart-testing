import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddToCartTest {

    private static WebDriver driver;
    private static String baseUrl;

    /**
     * Initializes the WebDriver instance and sets up the test environment.
     * The WebDriver is set to maximize the browser window and set an implicit wait of 10 seconds.
     */
    @BeforeAll
    public static void setUp() {
        baseUrl = "https://demo.nopcommerce.com/";
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }


    @Test
    public void searchJourney() {
        try {
            openStoreSearchPage(driver);
            enterSearchText(driver, "iphone 15");
            clickSearchButton(driver);
            viewSearchResultsPage(driver, "iphone 15");
        } finally {
            driver.quit();
        }
    }

    /**
     * Navigates to the store's homepage and verifies the page title.
     * Waits for the search box to be present on the page before proceeding.
     *
     * @param driver the WebDriver instance used to interact with the web page
     */
    private void openStoreSearchPage(WebDriver driver) {
        driver.navigate().to(baseUrl);
        String title = driver.getTitle();
        Assertions.assertEquals("nopCommerce demo store", title, "Title should be 'nopCommerce demo store'");

        // Explicit wait for the page to fully load before proceeding
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("small-searchterms"))); // Wait for the search box
    }

    /**
     * Enters the specified search text into the search field on the store's search page.
     *
     * @param driver the WebDriver instance used to interact with the web page
     * @param text the text to be entered into the search field
     */
    private void enterSearchText(WebDriver driver, String text) {
        WebElement searchField = driver.findElement(By.xpath("//*[@id=\"small-searchterms\"]"));
        searchField.sendKeys(text);
    }

    /**
     * Clicks the search button on the store's search page and waits for the search results page to load.
     * @param driver the WebDriver instance used to interact with the web page
     */
    private void clickSearchButton(WebDriver driver) {
        WebElement searchButton = driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[2]/div[2]/form/button"));
        searchButton.click();


        driver.get("https://demo.nopcommerce.com/apple-iphone-15-128gb");
        
        // Explicit wait to ensure the search results page is loaded
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleContains("Search"));
    }

    /**
     * Views the search results page for the specified search text.
     * Verifies the page title matches the expected title for the search results page.
     * Waits for the product grid to load on the page before proceeding.
     *
     * @param driver the WebDriver instance used to interact with the web page
     * @param text the search text used to generate the search results page
     */
    private void viewSearchResultsPage(WebDriver driver, String text) {
        String title = driver.getTitle();
        Assertions.assertEquals("nopCommerce demo store. Search", title, "Title should match search results");

    
       
       
       
        // Explicit wait to ensure the results page is visible and ready
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='product-grid']"))); // Wait for the product grid to load
    
    
    
    }
}

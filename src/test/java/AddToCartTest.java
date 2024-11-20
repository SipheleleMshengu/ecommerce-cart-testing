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

    private void openStoreSearchPage(WebDriver driver) {
        driver.navigate().to(baseUrl);
        String title = driver.getTitle();
        Assertions.assertEquals("nopCommerce demo store", title, "Title should be 'nopCommerce demo store'");

        // Explicit wait for the page to fully load before proceeding
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("small-searchterms"))); // Wait for the search box
    }

    private void enterSearchText(WebDriver driver, String text) {
        WebElement searchField = driver.findElement(By.xpath("//*[@id=\"small-searchterms\"]"));
        searchField.sendKeys(text);
    }

    private void clickSearchButton(WebDriver driver) {
        WebElement searchButton = driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[2]/div[2]/form/button"));
        searchButton.click();
        
        // Explicit wait to ensure the search results page is loaded
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleContains("Search"));
    }

    private void viewSearchResultsPage(WebDriver driver, String text) {
        String title = driver.getTitle();
        Assertions.assertEquals("nopCommerce demo store. Search", title, "Title should match search results");

        // Explicit wait to ensure the results page is visible and ready
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='product-grid']"))); // Wait for the product grid to load
    }
}
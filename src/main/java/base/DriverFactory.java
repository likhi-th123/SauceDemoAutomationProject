package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>(); //ThreadLocal ensures that
    																			//each test thread gets its own WebDriver instance.

    public static void initDriver() {
        if (tlDriver.get() == null) {
        	System.setProperty("webdriver.edge.driver", 
        		    "C:\\Users\\HP\\Downloads\\edgedriver_win64 (1)\\msedgedriver.exe");

            EdgeOptions options = new EdgeOptions();
            WebDriver driver = new EdgeDriver(options);

            driver.manage().window().maximize();
            tlDriver.set(driver);
        }
    }

    public static WebDriver getDriver() {
        return tlDriver.get();
    }

    public static void quitDriver() {
        if (tlDriver.get() != null) {
            tlDriver.get().quit(); //closes the browser.
            tlDriver.remove();		//clears the saved browser driver from memory
        }
    }
}

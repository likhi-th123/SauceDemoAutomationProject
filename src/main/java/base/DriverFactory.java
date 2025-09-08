package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    // Initialize WebDriver
    public static void initDriver() {
        if (tlDriver.get() == null) {
            // Automatically download and setup EdgeDriver
            WebDriverManager.edgedriver().setup();

            EdgeOptions options = new EdgeOptions();
            WebDriver driver = new EdgeDriver(options);

            driver.manage().window().maximize();
            tlDriver.set(driver);
        }
    }

    // Get the current thread's driver
    public static WebDriver getDriver() {
        return tlDriver.get();
    }

    // Quit the driver and remove from ThreadLocal
    public static void quitDriver() {
        if (tlDriver.get() != null) {
            tlDriver.get().quit();
            tlDriver.remove();
        }
    }
}

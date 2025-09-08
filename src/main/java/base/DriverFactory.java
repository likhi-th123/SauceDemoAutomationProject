package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import java.io.File;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    // Initialize WebDriver
    public static void initDriver() {
        if (tlDriver.get() == null) {
            // Local EdgeDriver path
            System.setProperty("webdriver.edge.driver", "C:\\Webdrivers\\msedgedriver.exe");

            EdgeOptions options = new EdgeOptions();

            // Important options to prevent crash
            options.addArguments("--remote-allow-origins=*");  // required for latest Edge versions
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("start-maximized");
            options.addArguments("user-data-dir=" + new File(System.getProperty("java.io.tmpdir"), "EdgeProfile").getAbsolutePath());

            WebDriver driver = new EdgeDriver(options);

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

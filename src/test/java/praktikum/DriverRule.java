package praktikum;

import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;

public class DriverRule extends ExternalResource {
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    @Override
    protected void before() throws Throwable {
        initDriver();
    }

    public void initDriver() {
        //Определение по параметру запуска "browser"
        if("firefox".equals(System.getProperty("browser"))) {
            startFirefox();
        } else {
            startChrome();
        }
        //Неявное ожидание в 5 сек
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public void startChrome() {
        WebDriverManager.chromedriver().clearDriverCache();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    public void startFirefox() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();

    }

    @Override
    protected void after() {
        driver.quit();
    }
}

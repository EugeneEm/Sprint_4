package praktikum.pages;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


// Класс для экрана "Для кого самокат" с контактными данными по заказу
public class OrderContactPage {
    private WebDriver driver;

    //Заголовок экрана
    private By header = By.xpath(".//div[contains(@class, 'Order_Header') and text()='Для кого самокат']");
    //Поле "Имя"
    private By firstNameInput = By.xpath(".//input[contains(@placeholder, 'Имя')]");
    //Поле "Фамилия"
    private By lastNameInput = By.xpath(".//input[contains(@placeholder, 'Фамилия')]");
    //Поле "Адрес"
    private By addressInput = By.xpath(".//input[contains(@placeholder, 'Адрес')]");
    //Поле "Станция метро"
    private By stationInput = By.xpath(".//input[contains(@placeholder, 'метро')]");
    //Поле "Телефон"
    private By phoneInput = By.xpath(".//input[contains(@placeholder, 'Телефон')]");
    //Кнопка "Далее"
    private By nextButton = By.xpath(".//button[contains(text(), 'Далее')]");
    //Префикс для индексов станций метро
    String stationPrefix = ".//li[@data-index = '";

    public OrderContactPage(WebDriver driver){
        this.driver = driver;
    }

    public void fillFirstName(String firstName) {
        driver.findElement(firstNameInput).clear();
        driver.findElement(firstNameInput).sendKeys(firstName);
    }

    public void fillLastName(String lastName) {
        driver.findElement(lastNameInput).clear();
        driver.findElement(lastNameInput).sendKeys(lastName);
    }

    public void fillAddress(String address) {
        driver.findElement(addressInput).clear();
        driver.findElement(addressInput).sendKeys(address);
    }

    public void fillPhone(String phone) {
        driver.findElement(phoneInput).clear();
        driver.findElement(phoneInput).sendKeys(phone);
    }

    public WebElement getIndexedStation(int index) {
        String indexedXpath = stationPrefix + index + "']";
        System.out.println("Сформированный XPath: " + indexedXpath);
        driver.findElement(stationInput).click();
        WebElement element = driver.findElement(By.xpath(indexedXpath));
        return element;
    }

    public void nextOrderStep() {
        driver.findElement(nextButton).click();
    }
}

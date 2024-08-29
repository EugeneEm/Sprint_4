package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


// Класс для главного экрана
public class MainPage {
    private WebDriver driver;

    //Кнопка "да все привыкли" для согласия сбора Cookies
    private By confirmCookiesButton = By.id("rcc-confirm-button");
    //Кнопка "Заказать" вверху страницы
    private By orderHeaderButton = By.xpath(".//div[contains(@class, 'Header_Nav')]/button[contains(text(), 'Заказать')]");
    //Кнопка "Заказать" внизу страницы
    private By orderButton = By.xpath("//button[contains(@class, 'Button_Middle')]");

    //Аккордеон содержит вопросы и ответы с одинаковыми префиксами в id
    String accordeonQuestionPrefix = "//div[contains(@id, 'accordion__heading-";
    String accordeonAnswerPrefix = "//div[contains(@id, 'accordion__panel-";

    public MainPage(WebDriver driver) {
        this.driver = driver;
        System.out.println("WebDriver инициализирован: " + (driver != null));
    }

    public void acceptCookies() {
        driver.findElement(confirmCookiesButton).click();
    }

    private String getIndexedLocator(String baseLocator, int index) {
        String indexedXpath = baseLocator + index + "')]";
        System.out.println("Сформированный XPath: " + indexedXpath);
        return indexedXpath;
    }

    // Метод для получения вопроса по индексу
    public WebElement getQuestion(int index) {
        By indexedQuestionLocator = By.xpath(getIndexedLocator(accordeonQuestionPrefix, index));
        System.out.println("Используем локатор: " + indexedQuestionLocator);
        WebElement element = driver.findElement(indexedQuestionLocator);
        System.out.println("Элемент найден: " + (element != null));
        return element;
    }

    // Метод для получения ответа по индексу
    public WebElement getAnswer(int index) {
        By indexedAnswerLocator = By.xpath(getIndexedLocator(accordeonAnswerPrefix, index));
        System.out.println("Используем локатор: " + indexedAnswerLocator);
        WebElement element = driver.findElement(indexedAnswerLocator);
        System.out.println("Элемент найден: " + (element != null));
        return element;
    }

    public void clickQuestion(int index) {
        getQuestion(index).click();
    }

    public String getQuestionText(int index) {
        return getQuestion(index).getText();
    }

    public String getAnswerText(int index) {
        return getAnswer(index).getText();
    }

    public void clickOrderHeaderButton() {
        driver.findElement(orderHeaderButton).click();
    }

    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

}
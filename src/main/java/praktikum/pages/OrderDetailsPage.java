package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import praktikum.constants.Color;

// Класс для экрана "Про Аренду" с контактными данными по заказу
public class OrderDetailsPage {
    private WebDriver driver;

    //Заголовок экрана
    private By header = By.xpath(".//div[contains(@class, 'Order_Header') and text()='Про аренду']");
    //Поле "Когда привезти самокат"
    private By dateInput = By.xpath(".//input[contains(@placeholder, 'Когда привезти самокат')]");
    //Поле "Срок аренды"
    private By termDropdown = By.xpath(".//div[contains(@class, 'Dropdown-placeholder')]");
    //Префикс опций выбора поля "Срок Аренды"
    String dropdownOptionPrefix = ".//div[contains(@class, 'Dropdown-option') and contains(text(), '";
    //Чекбоксы для поля "Цвет самоката"
    private By colorBlack = By.id("black");
    private By colorGrey = By.id("grey");
    //Поле "Комментарий для курьера"
    private By commentInput = By.xpath(".//input[contains(@placeholder, 'Комментарий')]");
    //Кнопка "Заказать"
    private By submitOrderButton = By.xpath(".//button[contains(@class, 'Button_Middle') and text()='Заказать']");
    //Кнопка "Да"
    private By confirmButton = By.xpath(".//button[contains(text(), 'Да')]");
    //Заголовок модального окна после оформления заказа
    private By modalHeader = By.xpath(".//div[contains(@class, 'ModalHeader')]");


    public OrderDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setDropdownOption(String term) {
        String indexedXpath = dropdownOptionPrefix + term + "')]";
        System.out.println("Сформированный XPath: " + indexedXpath);
        //Клик по заголовку, чтоб скрыть календарь
        driver.findElement(header).click();
        driver.findElement(termDropdown).click();
        driver.findElement(By.xpath(indexedXpath)).click();
    }

    public void setOrderDate(String date) {
        driver.findElement(dateInput).sendKeys(date);
    }

    public void setColor(String color) {
        if(color.equals(Color.BLACK)){
            driver.findElement(colorBlack).click();
        } else if (color.equals(Color.GREY)) {
            driver.findElement(colorGrey).click();
        } else {
            System.out.println("Выбранный цвет недоступен");
        }
    }

    public void setComment(String comment) {
        driver.findElement(commentInput).sendKeys(comment);
    }

    public void submitOrder() {
        driver.findElement(submitOrderButton).click();
        driver.findElement(confirmButton).click();
    }

    public String getHeaderText() {
        return driver.findElement(modalHeader).getText();
    }
}

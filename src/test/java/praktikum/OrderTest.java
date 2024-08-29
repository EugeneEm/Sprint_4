package praktikum;

import org.hamcrest.MatcherAssert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import praktikum.pages.MainPage;
import praktikum.pages.OrderContactPage;
import praktikum.pages.OrderDetailsPage;
import java.util.Arrays;
import java.util.Collection;
import static org.hamcrest.CoreMatchers.containsString;
import static praktikum.Env.BASE_URL;

@RunWith(Parameterized.class)
public class OrderTest {
    private WebDriver driver;

    private String firstName;
    private String lastName;
    private String address;
    private int stationId;
    private String phone;
    private String orderDate;
    private String orderTerm;
    private String color;
    private String comment;

    @Rule
    public DriverRule driverRule = new DriverRule();

    public OrderTest(String firstName, String lastName, String address, int stationId, String phone, String orderDate, String orderTerm, String color, String comment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.stationId = stationId;
        this.phone = phone;
        this.orderDate = orderDate;
        this.orderTerm = orderTerm;
        this.color = color;
        this.comment = comment;

    }

    @Parameterized.Parameters
    public static Collection<Object[]> getOrderTestData() {
        return Arrays.asList(new Object[][]{
                {"Гарри","Поттер","Москва,ул. Обручева, 7", 22, "+79161234455", "31.08.2024", "сутки", "black", "comment 1"},
                {"Рон","Уизли","Москва,ул. Новаторов, 12", 3, "+79161110203", "01.09.2024", "семеро суток", "grey", "comment 2"}
        });
    }

    //Тест для создания заказа через кнопку "Заказать" вверху страницы
    @Test
    public void checkNewOrder() {
        driver = driverRule.getDriver();
        driver.get(BASE_URL);
        MainPage mainPage = new MainPage(driverRule.getDriver());
        mainPage.acceptCookies();
        mainPage.clickOrderHeaderButton();
        OrderContactPage orderContactPage = new OrderContactPage(driver);
        orderContactPage.fillFirstName(firstName);
        orderContactPage.fillLastName(lastName);
        orderContactPage.fillAddress(address);
        orderContactPage.fillPhone(phone);
        orderContactPage.getIndexedStation(stationId).click();
        orderContactPage.nextOrderStep();
        OrderDetailsPage orderDetailsPage = new OrderDetailsPage(driver);
        orderDetailsPage.setOrderDate(orderDate);
        orderDetailsPage.setDropdownOption(orderTerm);
        orderDetailsPage.setColor(color);
        orderDetailsPage.setComment(comment);
        orderDetailsPage.submitOrder();
        String actualHeaderText = orderDetailsPage.getHeaderText();
        MatcherAssert.assertThat(actualHeaderText, containsString("Заказ оформлен"));
    }

    //Тест для создания заказа через кнопку "Заказать" внизу страницы
    @Test
    public void checkNewOrderSecondButton() {
        driver = driverRule.getDriver();
        driver.get(BASE_URL);
        MainPage mainPage = new MainPage(driverRule.getDriver());
        mainPage.acceptCookies();
        mainPage.clickOrderButton();
        OrderContactPage orderContactPage = new OrderContactPage(driver);
        orderContactPage.fillFirstName(firstName);
        orderContactPage.fillLastName(lastName);
        orderContactPage.fillAddress(address);
        orderContactPage.fillPhone(phone);
        orderContactPage.getIndexedStation(stationId).click();
        orderContactPage.nextOrderStep();
        OrderDetailsPage orderDetailsPage = new OrderDetailsPage(driver);
        orderDetailsPage.setOrderDate(orderDate);
        orderDetailsPage.setDropdownOption(orderTerm);
        orderDetailsPage.setColor(color);
        orderDetailsPage.setComment(comment);
        orderDetailsPage.submitOrder();
        String actualHeaderText = orderDetailsPage.getHeaderText();
        MatcherAssert.assertThat(actualHeaderText, containsString("Заказ оформлен"));
    }
}

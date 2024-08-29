package praktikum;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import praktikum.pages.MainPage;

import java.util.Arrays;
import java.util.Collection;

import static praktikum.Env.BASE_URL;

@RunWith(Parameterized.class)
public class FaqTest {
    private WebDriver driver;
//    private WebDriverWait wait;
    private int questionIndex;
    private String question;
    private String answer;

    public FaqTest(int questionIndex, String question, String answer) {
        this.questionIndex = questionIndex;
        this.question = question;
        this.answer = answer;
    }


    @Rule
    public DriverRule driverRule = new DriverRule();

    @Parameterized.Parameters
    public static Collection<Object[]> getTestData() {
        return Arrays.asList(new Object[][]{
                {0,"Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
        });
    }


    @Test
    public void checkFaq() {
        driver = driverRule.getDriver();
        driver.get(BASE_URL);

        MainPage mainPage = new MainPage(driverRule.getDriver());
        // Закрываем уведомление про назойливые куки, которые могут перекрывать элементы страницы
        mainPage.acceptCookies();
        mainPage.clickQuestion(questionIndex);

        // Проверяем текст вопроса
        String actualQuestionText = mainPage.getQuestionText(questionIndex);
        System.out.println("actualQuestionText: " + actualQuestionText);
        Assert.assertEquals("Текст вопроса не совпадает с ожидаемым для вопроса #" + (questionIndex+1),
                question, actualQuestionText);

        // Проверяем текст ответа
        String actualAnswerText = mainPage.getAnswerText(questionIndex);
        System.out.println("actualAnswerText: " + actualAnswerText);
        Assert.assertEquals("Текст ответа не совпадает с ожидаемым для вопроса #" + (questionIndex+1),
                answer, actualAnswerText);
    }
}

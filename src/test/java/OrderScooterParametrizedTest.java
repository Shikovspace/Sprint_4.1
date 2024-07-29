import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageobject.OrderScooterPage;

import static constants.URL.HOME_PAGE;
import static pageobject.MainScooterGeneralPage.UPPER_BUTTON;

@RunWith(Parameterized.class)
public class OrderScooterParametrizedTest {
    private WebDriver driver;
    private final String name;
    private final String surname;
    private final String address;
    private final String subway;
    private final String phone;
    private final String date;
    private final String term;
    private final String color;
    private final String comment;
    private final String button;

    public OrderScooterParametrizedTest(String name,String surname,String address,String subway,String phone,
                                        String date,String term,String color,String comment,String button) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.subway = subway;
        this.phone = phone;
        this.date = date;
        this.term = term;
        this.color = color;
        this.comment = comment;
        this.button = button;
    }

    @Parameterized.Parameters
    public static Object[][] testOrderData() {
        return new Object[][] {
                {"Лев", "Толстой",
                        "Ивановская д5, к50", "Сокольники",
                        "12345678900", "01.08.2024", "трое суток",
                        "black", "Позвоните за час до привоза!",UPPER_BUTTON},
                {"Фёдор", "Достоевский",
                        "Красная Площадь д1", "Черкизовская",
                        "12345678900", "02.08.2024", "сутки",
                        "gray", " ",UPPER_BUTTON}
        };
    }

    //Проверка оформления заказа
    @Test
    public void checkMakingOrder() throws InterruptedException {
        // Экземпляр класса страницы с параметрами заказа
        OrderScooterPage objOrderForm = new OrderScooterPage(driver);

        // Сценарий с параметрами (выбор кнопки в параметре):
        objOrderForm
                .chooseTheOrderButtonToClick(button)
                .fillInUserPersonalData(name, surname, address, subway, phone)
                .fillInOrderDetails(date, term, color, comment)
                .checkOrderConfirmationModal();

        objOrderForm.checkOrderStatusModal();
        objOrderForm.goToMain();

    }

    @Before
    public void beforeTest() {
        // драйвер для браузера
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        // driver = new ChromeDriver(options);
        driver = new FirefoxDriver();

        // переход на страницу тестового приложения
        driver.get(HOME_PAGE);
        // объект класса страницы с параметрами заказа
        OrderScooterPage objOrderForm = new OrderScooterPage(driver);
        // Убираем всплывающее окно
        objOrderForm.cookieButtonClick();
    }

    @After
    public void teardown() {
        // Закрыть браузер
        driver.quit();
    }
}

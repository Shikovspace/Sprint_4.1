import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageobject.MainScooterGeneralPage;

import java.time.Duration;

import static constants.URL.HOME_PAGE;

public class MainTests {
    private WebDriver driver;

    @Before
    public void beforeTest() {
        // драйвер для браузера
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        // driver = new FirefoxDriver();

        // переход на страницу тестового приложения
        driver.get(HOME_PAGE);
        // Ожидания
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        // Экземпляр класса главной страницы
        MainScooterGeneralPage objScooterPage = new MainScooterGeneralPage(driver);
        //Закрываем всплывающее окно
        objScooterPage.cookieButtonClick();
    }

    //Проверяет название раздела с вопросами
    @Test
    public void checkQuestionsHeaderTitle() {
        // Экземпляр класса главной страницы
        MainScooterGeneralPage objScooterPage = new MainScooterGeneralPage(driver);
        // Проверить соответствие текста подзаголовка
        objScooterPage.checkQuestionsHeader();
    }

    //Нажимает верхнюю кнопку оформления заказа
    @Test
    public void clickUpperOrderButtonOpensOrderPage() {
        // Экземпляр класса главной страницы
        MainScooterGeneralPage objScooterPage = new MainScooterGeneralPage(driver);
        // Нажать на верхнюю кнопку
        objScooterPage.clickHeaderOrderButton();
        // убедиться, что есть переход на страницу заказа
        objScooterPage.checkOrderPageOpened();
    }

    //Нажимает нижнюю кнопку оформления заказа
    @Test
    public void clickLowerOrderButtonOpensOrderPage() {
        // Экземпляр класса главной страницы
        MainScooterGeneralPage objScooterPage = new MainScooterGeneralPage(driver);
        // Нажать на нижнюю кнопку
        objScooterPage.clickFooterOrderButton();
        // убедиться, что есть переход на страницу заказа
        objScooterPage.checkOrderPageOpened();
    }

    @After
    public void teardown() {
        // Закрыть браузер
        driver.quit();
    }
}

package pageobject;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

import static constants.URL.ORDER_PAGE;

public class MainScooterGeneralPage {
    private final WebDriver driver;

    // Кнопка "Заказать" вверху страницы
    private final By headerOrderButton = By.cssSelector(".Button_Button__ra12g");
    // Кнопка "Заказать" внизу страницы
    private final By footerOrderButton = By.xpath("//div[contains(@class, 'Home_FinishButton')]/button");
    //Подзаголовок вопросов
    private final By questionsTitle = By.cssSelector(".Home_FourPart__1uthg > div:nth-child(1)");
    //Ожидаемый текст названия раздела вопросов
    private static final String QUESTIONS_HEADER_TEXT = "Вопросы о важном";
    public static final String UPPER_BUTTON = "upper";
    public static final String LOWER_BUTTON = "lower";

    private final By cookButtonLocator = By.xpath("//button[contains(@class, 'App_CookieButton')]");

    //Конструктор класса
    public MainScooterGeneralPage(WebDriver driver) {
        this.driver = driver;
    }

    // Убираем всплывающее окно
    public void cookieButtonClick() {
        WebElement cookieButton = driver.findElement(cookButtonLocator);
        cookieButton.click();
    }

    //Проверить корректность текста подзаголовка
    public void checkQuestionsHeader() {
        WebElement questionsTitleElement = driver.findElement(questionsTitle);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", questionsTitleElement);

        Assert.assertEquals("Заголовок раздела вопросов неверный: ожидался " + QUESTIONS_HEADER_TEXT + ", фактический " +
                questionsTitleElement.getText(), QUESTIONS_HEADER_TEXT, questionsTitleElement.getText());
    }

    //Проверить, что открылась страница оформления заказа
    public void checkOrderPageOpened(){

        Assert.assertEquals("Страница оформления заказа не открылась!", ORDER_PAGE, driver.getCurrentUrl());
    }

    public void clickHeaderOrderButton() {
        //Найти верхнюю кнопку
        WebElement upperButton = driver.findElement(headerOrderButton);
        //Проскролить к ней и нажать
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", upperButton);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        upperButton.click();
    }

    public void clickFooterOrderButton() {
        //Найти нижнюю кнопку
        WebElement lowerButton = driver.findElement(footerOrderButton);
        //Проскролить и нажать
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", lowerButton);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        lowerButton.click();
    }
}

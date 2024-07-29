package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainScooterQuestionsPage {
    private final WebDriver driver;

    private final By cookButtonLocator = By.xpath("//button[contains(@class, 'App_CookieButton')]");

    public MainScooterQuestionsPage(WebDriver driver){
        this.driver = driver;
    }

    // Убираем всплывающее окно
    public void cookieButtonClick() {
        WebElement cookieButton = driver.findElement(cookButtonLocator);
        cookieButton.click();
    }

    // Найти элемент вопроса
    public String getQuestionActualText(By importantQuestionLocator){
        WebElement answerElement = driver.findElement(importantQuestionLocator);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", answerElement);
        return answerElement.getText();
    }

    // Найти элемент ответа
    public String getAnswerActualText(By importantAnswerLocator){
        WebElement answerElement = driver.findElement(importantAnswerLocator);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", answerElement);
        return answerElement.getText();
    }

    //Нажать на вопрос
    public void clickQuestion(By importantQuestionLocator){
        WebElement questionElement = driver.findElement(importantQuestionLocator);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", questionElement);
        questionElement.click();
    }
}

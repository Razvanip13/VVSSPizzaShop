package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends BaseClass{

    private By title= By.className("title");

    public MainPage() {
        PageFactory.initElements(driver, this);
    }
    public String getTitle(){
        return driver.findElement(title).getText();
    }
}

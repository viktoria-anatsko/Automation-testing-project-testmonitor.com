package pages;

import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import services.WaitService;

public class UserProfilePage extends BasePage {

    WaitService waitService = new WaitService(driver);

    public UserProfilePage(WebDriver driver) {
        super(driver);
    }

    @FindBy (xpath = "//button[@class = 'button is-white']/span")
    public WebElement moreOptionButton;

    @FindBy (xpath = "//div[@class='dropdown-content']//div[contains(text(), 'Delete...')]")
    public WebElement deleteOption;

    @Override
    protected By getPageIdentifier() {
        return By.xpath("//button[contains(text(), 'Make Admin...')]");
    }

}

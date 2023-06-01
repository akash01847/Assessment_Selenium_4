package checkout;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;
import java.time.Duration;
import java.util.logging.Logger;

public class SeleniumCheckout {

    WebDriver driver;

    WebDriverWait wait;
    Logger log;

    @FindBy(css = ".card-img-top.img-fluid ")
    WebElement product;

    @FindBy(css=".btn.btn-success.btn-lg")
    WebElement addToCartButton;

    @FindBy(css="#cartur")
    WebElement cartButton;

    @FindBy(css=".btn.btn-success")
    WebElement placeOrder;

    @FindBy(css="#name")
    WebElement name;

    @FindBy(css="#country")
    WebElement country;

    @FindBy(css="#city")
    WebElement city;

    @FindBy(css="#card")
    WebElement card;

    @FindBy(css="#month")
    WebElement month;

    @FindBy(css="#year")
    WebElement year;

    @FindBy(css="button[onclick='purchaseOrder()']")
    WebElement purchase;

    @FindBy(css=".sweet-alert.showSweetAlert.visible h2")
    WebElement msg;

    @FindBy(css=".sa-placeholder")
    WebElement check;

    @FindBy(css=".lead.text-muted ")
    WebElement details;

    @FindBy(css=".confirm.btn.btn-lg.btn-primary")
    WebElement button;

    SeleniumCheckout() throws IOException {
        Base b=new Base();
        driver=b.initializeDriver();
        wait=new WebDriverWait(driver,Duration.ofSeconds(10));
        PageFactory.initElements(driver,this);
    }

    @Test
    public void enterDetails()
    {
        wait.until(ExpectedConditions.visibilityOf(name));
        name.sendKeys("Akash Singh");
        wait.until(ExpectedConditions.visibilityOf(country));
        country.sendKeys("India");
        wait.until(ExpectedConditions.visibilityOf(city));
        city.sendKeys("Noida");
        wait.until(ExpectedConditions.visibilityOf(card));
        card.sendKeys("343434424");
        wait.until(ExpectedConditions.visibilityOf(month));
        month.sendKeys("05");
        wait.until(ExpectedConditions.visibilityOf(year));
        year.sendKeys("2022");
        wait.until(ExpectedConditions.visibilityOf(purchase));
        purchase.click();
    }


    @BeforeTest
    public void setup() throws InterruptedException {

        driver.get("https://www.demoblaze.com/");
        wait.until(ExpectedConditions.visibilityOf(product));
        product.click();
        wait.until(ExpectedConditions.visibilityOf(addToCartButton));
        addToCartButton.click();
        Thread.sleep(2000);
        driver.switchTo().alert().accept();
        wait.until(ExpectedConditions.visibilityOf(cartButton));
        cartButton.click();
        wait.until(ExpectedConditions.visibilityOf(placeOrder));
        placeOrder.click();
        enterDetails();
    }


    @Test
    public void validateMsg()
    {
        wait.until(ExpectedConditions.visibilityOf(msg));
        System.out.println(msg.getText());
        Assert.assertTrue(msg.isDisplayed());
    }

    @Test
    public void validateCheckSymbol()
    {
        wait.until(ExpectedConditions.visibilityOf(check));
        Assert.assertTrue(check.isDisplayed());
    }

    @Test
    public void validateDetails()
    {
        wait.until(ExpectedConditions.visibilityOf(details));
        System.out.println(details.getText());
        Assert.assertTrue(details.isDisplayed());
    }

    @Test
    public void validateOkButton()
    {
        wait.until(ExpectedConditions.visibilityOf(button));
        System.out.println(button.getText());
        Assert.assertTrue(button.isDisplayed());

    }

    @AfterTest
    public void tearDown()
    {
        driver.quit();
    }



}
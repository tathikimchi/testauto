package chi.base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestPage {
    public WebDriver driver ;
    public JavascriptExecutor js;
    @BeforeMethod
    public void setup(){
        System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--user-data-dir=C:\\Users\\admin\\AppData\\Local\\Google\\Chrome\\User Data");
        options.addArguments("--profile-directory=Profile 24");

        options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
        options.addArguments("--no-sandbox"); // Bypass OS security model
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        js = (JavascriptExecutor) driver;
    }

    @Test
    public void login(){
        driver.get(Constants.LINK_LOGIN);
        WebElement mail = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/div/div[2]/form/div[1]/div[2]/div[2]/input"));
        if(mail.isEnabled()){
            mail.sendKeys(Constants.USER_NAME);
        }
        WebElement pass = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/div/div[2]/form/div[2]/div[2]/div[2]/input"));
        if(pass.isEnabled()){
            pass.sendKeys(Constants.USER_PASS);
        }
        WebElement buttonLogin = driver.findElement(By.xpath("//*[@id=\"button-compo\"]"));
        if (buttonLogin.isEnabled()){
            buttonLogin.click();

        }
        pause(5);
    }

 public void clickk(String link){
        WebElement element = driver.findElement(By.xpath(link));
        if(element.isEnabled()){
        element.click();
        }
 }

    public void submit(String link){
        WebElement submitForm = driver.findElement(By.xpath(link));
        if(submitForm.isEnabled()){
            submitForm.submit();
        }
    }



    public void pause(Integer giay){
        try {
            TimeUnit.MILLISECONDS.sleep(giay*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void close(){
        if(driver!=null){
            driver.quit();

        }
    }
}



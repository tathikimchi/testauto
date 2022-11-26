import chi.base.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class Login {
    WebDriver driver;
    String linkLogin = "https://lb-fe-testing.fireapps.tech/signin";



    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }




    private void login() {
        // mở màn hình login
        driver.get("https://lb-fe-testing.fireapps.tech");


        // nhập email
        WebElement inputemail = timTheoXpath("//*[@id=\"app\"]/div/div[2]/div/div[2]/form/div[1]/div[2]/div[2]/input");

        inputemail.sendKeys(Constants.USER_NAME);

        // nhập pass
        WebElement inputPass = timTheoXpath("//*[@id=\"app\"]/div/div[2]/div/div[2]/form/div[2]/div[2]/div[2]/input");

        inputPass.sendKeys(Constants.USER_PASS);

        //click button login
        click("//*[@id=\"button-compo\"]");

        pause(5);
        assertEquals("https://lb-fe-testing.fireapps.tech/inbox/groups/assign_to_you", driver.getCurrentUrl());

    }

    private void click(String s) {
        WebElement element = driver.findElement(By.xpath(s));
        if (element.isDisplayed()){
              element.click();
        } else{
            System.out.println("click hong dc");
        }
    }

    public void pause(Integer giay){
        try {
            TimeUnit.MILLISECONDS.sleep(giay*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private WebElement timTheoXpath(String s) {
        WebElement element = driver.findElement(By.xpath(s));
        if (element.isDisplayed()){
            return element;
        }else{
            return null;
        }
    }

    @Test
    public void testCase1(){
        login();


        click("//*[@id=\"app\"]/div/div/div[1]/div[2]/div/div/div[1]/button/div/div/span/div");
        String  name = getTextNe("//*[@id=\"app\"]/div/div/div[1]/div[2]/div/div/div[2]/div/div[1]/div/div[2]");

       Assert.assertEquals(name,Constants.ACCOUNTNAME);



           }

    private String getTextNe(String s) {
        WebElement element = driver.findElement(By.xpath(s));
        if (element.isDisplayed()){
           return element.getText();
        } else{
            return "hong lay dc";
        }
    }

    @Test
    public void testCase2(){
        login();

    }

    @AfterMethod
    public void tearDown(){
        if(driver != null) {
            driver.close();
            System.out.println("thoat ");
        }
    }


}

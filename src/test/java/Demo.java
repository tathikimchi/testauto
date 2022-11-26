import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Demo {

    WebDriver driver;
    static final String APP_URL = "https://www.google.com.vn/";
    static final String HOST_URL = "http://localhost:4444/wd/hub";

    static final String AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36";



    @BeforeMethod
    public void setUp(){
        // dev
        //System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/test/resources/chromedriver.exe");

        ChromeOptions opt = new ChromeOptions();
        try {
            opt.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
            opt.setExperimentalOption("useAutomationExtension", false);
            opt.addArguments("--user-agent="+AGENT);
           driver = new RemoteWebDriver(new URL(HOST_URL), opt);
            //dev
           // driver = new ChromeDriver(opt);
            driver.manage().window().setSize(new Dimension(1366,768));
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(APP_URL);
        System.out.println("setUp ok ");
    }

    @Test
    public void searchKeyWord(){
        if(driver.getCurrentUrl().contains(APP_URL)){
            System.out.println("dang chay bắt đầu search");
            WebElement inputSearch  = waitForElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/input"));
            screenshot();
            if(inputSearch.isDisplayed()){
                // sendKeyInput(inputSearch,"phan mem vetgo");
                typeInField(inputSearch,"Phan mem vetgo");
            }
             findLinkAndViewWeb();


        }

    }
    public void screenshot ()
    {
        try {
            //Capture entire page screenshot and then store it to destination drive
            File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            String newName = "image/screenshot"+new Date().getTime() +".jpg";
            FileUtils.copyFile(screenshot, new File(newName));
            System.out.println("Screenshot is captured and stored in "+newName);
        }catch (Exception e){
            System.out.println("fail" +e.getMessage());
        }

    }

    public void findLinkAndViewWeb(){
        System.out.println("findLinkAndViewWeb");
        screenshot();
        if(driver.getTitle().toLowerCase().contains("tìm trên google")){
            System.out.println("Đang kiếm link");
            scroll(5);
            try {
                List<WebElement> findElements = driver.findElements(By.tagName("h3"));

                // this are all the links you like to visit
                for (WebElement webElement : findElements)
                {
                    String link = webElement.getText();
                    System.out.println(link);
                    if(link.toLowerCase().contains("vetgo")){
                        moveMouseclickLink(webElement);
                    }
                    break;
                }

                viewFB();

            }catch (Exception e){

            }

        }

    }

    private void viewFB() {

        System.out.println("viewFB "+driver.getCurrentUrl());
        screenshot();
        if(driver.getCurrentUrl().toLowerCase().contains("vetgo.vn")){

            System.out.println("Đang kiếm link fb");
            WebElement  findElements = waitForElement(By.linkText("GIỚI THIỆU"));
            moveMouseclickLink(findElements);
            scroll(10);


        }}

    private void scroll(int k) {
        Actions a = new Actions(driver);
        for (int i = 0; i < k; i++) {
            a.sendKeys(Keys.PAGE_DOWN).build().perform();
            pause(1000);
        }
        pause(2000);
        for (int i = 0; i < k; i++) {
            pause(1000);
            a.sendKeys(Keys.PAGE_UP).build().perform();
        }


    }


    private void moveMouseclickLink(WebElement link) {
        Actions ref = new Actions (driver);
        // Di chuyển chuột đến phần tử link và click chuột.
        ref.moveToElement(link).click();
        ref.build().perform();
    }


    public void pause(Integer milliseconds){
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void typeInField(WebElement inputSearch, String value){
        Actions ref = new Actions (driver);
        ref.moveToElement(inputSearch).click();
        String val = value;
        inputSearch.clear();
        for (int i = 0; i < val.length(); i++){
            char c = val.charAt(i);
            String s = new StringBuilder().append(c).toString();
            inputSearch.sendKeys(s);
            pause(300);
        }
        inputSearch.sendKeys(Keys.ENTER);
    }

    public WebElement waitForElement(By selector) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);

        return wait.until(webdriver-> {
            return webdriver.findElement(selector);
        });
    }
    @AfterMethod
    public void tearDown(){
        if(driver != null) {
            pause(20000);
            driver.quit();
            System.out.println("thoat ");
        }
    }
}

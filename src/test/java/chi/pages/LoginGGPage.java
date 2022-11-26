package chi.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;

public class LoginGGPage {
    static final String URL = "https://developers.google.com/oauthplayground/";
    WebDriver driver;

    @FindBy(xpath = "//*[@id=\"oauthConfigButton\"]")
    WebElement btSetting;

    @FindBy(xpath = "//*[@id=\"useDefaultOauthCredLabel\"]")
    WebElement checkbox;

    @FindBy(xpath = "//*[@id=\"oauthClientId\"]")
    WebElement inputClientID;

    @FindBy(xpath = "//*[@id=\"oauthClientSecret\"]")
    WebElement inputClientSecret;

    @FindBy(xpath = "//*[@id=\"closeOauthConfigBubble\"]")
    WebElement btClose;

    @FindBy(xpath = "//*[@id=\"api-Blogger-API-v3\"]")
    WebElement selectboxApiV3;

    @FindBy(xpath = "//*[@id=\"scopesContainer\"]/li[104]")
    WebElement cbUrlBlog;

    @FindBy(xpath = "//*[@id=\"authorizeApisButton\"]")
    WebElement btAuthorApis;

    @FindBy(xpath = "//*[@id=\"view_container\"]/div/div/div[2]/div/div[1]/div/form/span/section/div/div/div/div/ul")
    WebElement listAccount;

    @FindBy(xpath = "//*[@id=\"view_container\"]/div/div/div[2]/div/div[2]/div/div[2]")
    WebElement btContinue;

    @FindBy(xpath = "//*[@id=\"submit_approve_access\"]/div/button/div[3]")
    WebElement btContinueV2;

    @FindBy(xpath = "//*[@id=\"exchangeCode\"]")
    WebElement btexchangeCode;

     JavascriptExecutor js;
    public LoginGGPage(WebDriver driver,JavascriptExecutor js) {

        this.driver = driver;
        this.js = js;
        PageFactory.initElements(driver,this);
        driver.get(URL);

    }

    public void getToken(String clientID,String clientSecrect){
        btSetting.click();
        checkbox.click();
        inputClientID.sendKeys(clientID);
        inputClientSecret.sendKeys(clientSecrect);
        btClose.click();
        js.executeScript("arguments[0].scrollIntoView(true);", selectboxApiV3);
        selectboxApiV3.click();
        cbUrlBlog.click();
        btAuthorApis.click();
       List<WebElement>  accounts =  (List<WebElement>) js.executeScript("return arguments[0].children;",listAccount);


       if (!CollectionUtils.isEmpty(accounts)){
           accounts.forEach(li -> {
               WebElement div0 = (WebElement) js.executeScript("return arguments[0].children[0];",li);
               String email = div0.getAttribute("data-identifier");
               if (email.equals("vetgotech@gmail.com")){
                   li.click();
                   return;
               }
           });
       }

       btContinue.click();
       btContinueV2.click();
       btexchangeCode.click();




//



    }


}

package chi.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InboxPage {

    @FindBy(xpath="//*[@id=\"app\"]/div/div[2]/div/div[1]")
     WebElement titleSignUp;

    @FindBy(xpath="//*[@id=\"app\"]/div/div[2]/div/div[1]/a")
     WebElement linkSignUp;

    @FindBy(xpath="//*[@id=\"app\"]/div/div[2]/div/div[2]/h3")
    WebElement titleHello;

    @FindBy(xpath="//*[@id=\"app\"]/div/div[2]/div/div[2]/h4")
    WebElement titleLogin;

    @FindBy(xpath="//*[@id=\"app\"]/div/div[2]/div/div[2]/form/div[1]/div[2]/div[2]/input")
    WebElement inputEmail;

    @FindBy(xpath="//*[@id=\"app\"]/div/div[2]/div/div[2]/form/div[2]/div[2]/div[2]/input")
    WebElement inputPass;

    @FindBy(xpath="//*[@id=\"app\"]/div/div[2]/div/div[2]/form/div[3]/div/div/label/div")
    WebElement titleRememberMe;

    @FindBy(xpath="//*[@id=\"app\"]/div/div[2]/div/div[2]/form/div[3]/a")
    WebElement titleForgetPass;

   @FindBy(css="#button-compo > div > div")
    WebElement btLogin;

    static final String URL_PAGE = "https://lb-fe-testing.fireapps.tech/inbox/groups/assign_to_you";


    public InboxPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
        driver.get(URL_PAGE);
    }


    public boolean testTitleSignUp(String titleExpect){
        String titleActual = titleSignUp.getText();
        if (titleActual.equals(titleExpect)){
            return true;
        }
        return false;
    }

    public void login(String email, String pass){
        if (inputEmail.isEnabled() && inputPass.isEnabled()){
            inputEmail.sendKeys(email);
            inputPass.sendKeys(pass);
            btLogin.click();
        }
    }




}

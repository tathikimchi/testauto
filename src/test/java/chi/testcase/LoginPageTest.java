package chi.testcase;

import chi.base.TestPage;
import chi.pages.InboxPage;
import chi.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends TestPage {
    LoginPage loginPage ;
    InboxPage inboxPage ;

    @Test
    public void testTitle(){
        loginPage = new LoginPage(driver);
        Assert.assertEquals(loginPage.testTitleSignUp("New to LineBase? Sign up"),true);//
    }

    @Test
    public void testLogin(){
        loginPage = new LoginPage(driver);
        inboxPage = loginPage.login("report_chittk_2@yopmail.com" , "15130016@iTi");
        System.out.println("ok la");
        pause(15);


    }
}

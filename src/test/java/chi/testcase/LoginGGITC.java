package chi.testcase;

import chi.base.TestPage;
import chi.pages.LoginGGPage;
import org.testng.annotations.Test;

public class LoginGGITC extends TestPage {
    LoginGGPage loginGG;

    @Test
    public void openURL(){
        loginGG = new LoginGGPage(driver,js);
        loginGG.getToken("989056490384-i28gs2im66b04ulae8a4cme3cu78ork8.apps.googleusercontent.com", "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");
        pause(2);


    }


}

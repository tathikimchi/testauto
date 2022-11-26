import chi.base.TestPage;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

public class YourAccount extends TestPage {


   static String BT_ACCOUNT = "//*[@id=\"app\"]/div/div/div[1]/div[2]/div/div/div[1]/button/div/div/span/div";

    @FindBy( xpath = "//*[@id=\"app\"]/div/div/div[2]/div[2]/div/div[2]/div/div[2]")
    WebElement popup;

    public YourAccount() {
        PageFactory.initElements(driver,this);
    }

    public void pageYourAccount() {
        login();
        clickk(BT_ACCOUNT);
        pause(5);
        clickk("//*[@id=\"app\"]/div/div/div[1]/div[2]/div/div/div[2]/div/div[4]/a[1]");
    }

    @Test
    public void testTitle() {
        pageYourAccount();
        WebElement title = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div[2]/div/h2"));
        if (title.isDisplayed()) {
            String titlevalue = title.getText();
            Assert.assertEquals(titlevalue, "Account Details");
        }

    }


    @Test
    public void testCheckBox(){
        login();
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://lb-fe-testing.fireapps.tech/content/article/list");
        List<WebElement> listCheckbox = driver.findElements(By.className("checkbox-component-custom"));
//       if (!CollectionUtils.isEmpty(listCheckbox)){
//           for (int i = 0; i < listCheckbox.size() ; i++) {
//             WebElement checkbox =  listCheckbox.get(i);
//               checkbox.click();
//           }
//           for (WebElement checkbox: listCheckbox ) {
//               checkbox.click();
//           }
//
//           listCheckbox.forEach( checkbox ->{
//               checkbox.click();
//           });
//
//
//           }


    //   }




    }


    @Test
    public void testUserNameValue() {
        pageYourAccount();
        WebElement username = driver.findElement(By.xpath("//*[@id=\"username\"]/div[2]/div/input"));

        if (username.isDisplayed()) {
            String value = username.getAttribute("value");
            Assert.assertEquals(value, "report_chittk_2");
        }
        WebElement email = driver.findElement(By.xpath("//*[@id=\"email\"]/div[2]/div/input"));
        if (email.isDisplayed()) {
            Assert.assertEquals(email.isEnabled(), false);
            String valueEmail = email.getAttribute("value");
            Assert.assertEquals(valueEmail, "report_chittk_2@yopmail.com");
        }
        pause(2);
        // click button change

        //click("//*[@id=\"button-compo\"]/div/div");

        WebElement inputPassword =  driver.findElement(By.xpath("//*[@id=\"password\"]"));
        js.executeScript("arguments[0].scrollIntoView(true);", inputPassword);


        js.executeScript("document.querySelector(\"#password\").nextSibling.click();");

//        WebElement buttonChange = (WebElement) js.executeScript("return arguments[0].nextSibling;",inputPassword);
//
//        if(buttonChange.isDisplayed()){
//                buttonChange.click();
//            }

        pause(3);
       // WebElement popup = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div[2]/div/div[2]/div/div[2]"));
        Assert.assertEquals(popup.isDisplayed(),true);
        if(popup.isDisplayed()){
            WebElement inputCurrentPass = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div[2]/div/div[2]/div/div[2]/div[2]/div/form/div[2]/div[2]/div/input"));
        String placeholderCurrentPass = inputCurrentPass.getAttribute("placeholder");
        Assert.assertEquals(placeholderCurrentPass,"Enter current password");
        String value = inputCurrentPass.getAttribute("value");
        Assert.assertEquals(StringUtils.isEmpty(value) ,true);

        WebElement inputNewPass = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div[2]/div/div[2]/div/div[2]/div[2]/div/form/div[4]/div[2]/div/input"));
        String placeholderNewpass = inputNewPass.getAttribute("placeholder");
        Assert.assertEquals(placeholderNewpass,"Enter new password");
        System.out.println("đã check place new pass");

        WebElement confirmNewPass = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div[2]/div/div[2]/div/div[2]/div[2]/div/form/div[7]/div[2]/div/input"));
        String placeholderConfirmNewpass = inputNewPass.getAttribute("placeholder");
        Assert.assertEquals(placeholderConfirmNewpass,"Enter new password");
        System.out.println("đã check place  confirm new pass");

        inputCurrentPass.sendKeys("15130016@iTi");
        inputNewPass.sendKeys("15130016@iTi");
        confirmNewPass.sendKeys("15130016@iTi");
        clickk("/html/body/div[1]/div/div/div[2]/div[2]/div/div[2]/div/div[2]/div[3]/div/button[2]");
        pause(2);

       // popup = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div[2]/div/div[2]/div/div[2]"));
        if(popup.isDisplayed()){
            System.out.println("chưa đóng popup");
        }else {
            System.out.println("Đóng popup rồi");

        }

            driver.switchTo().newWindow(WindowType.TAB);
            driver.get("https://lb-fe-testing.fireapps.tech/inbox/groups/assign_to_you");
            driver.switchTo().newWindow(WindowType.TAB);
            driver.get("https://lb-fe-testing.fireapps.tech/inbox/groups/assign_to_you");
            driver.switchTo().newWindow(WindowType.TAB);
            driver.get("https://lb-fe-testing.fireapps.tech/inbox/groups/assign_to_you");
            driver.switchTo().newWindow(WindowType.TAB);
            driver.get("https://lb-fe-testing.fireapps.tech/inbox/groups/assign_to_you");
            driver.switchTo().newWindow(WindowType.TAB);
            driver.get("https://lb-fe-testing.fireapps.tech/inbox/groups/assign_to_you");




        pause(3);

            //lấy tất cả các tab đang mở
            List<String> listTab =  driver.getWindowHandles().stream().collect(Collectors.toList());

            for (String tab : listTab ){
                System.out.println(tab);
            }

//            for (int i = 0; i < listTab.size() ; i++) {
//                String tab = listTab.get(i);
//                System.out.println(tab);
//            }
            //chuyển qua tab co sẵn


            driver.switchTo().window(listTab.get(0));
            pause(3);


        }






        }



}




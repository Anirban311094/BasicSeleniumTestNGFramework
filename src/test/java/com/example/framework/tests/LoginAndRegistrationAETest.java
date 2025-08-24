package com.example.framework.tests;


import com.aventstack.extentreports.Status;
import com.example.framework.base.BaseTest;
import com.example.framework.core.DriverFactory;
import com.example.framework.listeners.ExtentTestNGListener;
import com.example.framework.pages.automationExercisePages.LoginAndRegistrationAEPage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class LoginAndRegistrationAETest extends BaseTest {
    SoftAssert softAssert = new SoftAssert();

    @Test(description = "Validate Registration")
    @Parameters({"name", "email", "gender",  "password",  "dob_SplittedByDash",  "newsletterSignUpYN",
            "rcvSpclOffrYN",  "first_name",  "last_name",  "company",  "address1",  "address2",
            "country",  "state",  "city",  "zipCode",  "mobileNo"})
    public void testValidRegistration(String name, String email, String gender, String password, String dob_SplittedByDash, String newsletterSignUpYN,
                                      String rcvSpclOffrYN, String first_name, String last_name, String company, String address1, String address2,
                                      String country, String state, String city, String zipCode, String mobileNo) throws Exception{
        email = email.split("@")[0] +System.currentTimeMillis()+"@"+email.split("@")[1];
        System.out.println("Email testValidRegistration: "+email);
        var loginAndRegistrationAEPage = new LoginAndRegistrationAEPage(DriverFactory.getDriver());
        loginAndRegistrationAEPage.enterSignUpOrLogInPage();
        Assert.assertEquals(loginAndRegistrationAEPage.isSignUpDisplayed(), true);
        loginAndRegistrationAEPage.fillup_RegistrationNameAndEmail(name, email);
        loginAndRegistrationAEPage.click_Signup();

        softAssert.assertNotEquals(loginAndRegistrationAEPage.getTitle_EnterAccountInfo(), "Enter Account Information");
        loginAndRegistrationAEPage.form_EnterAccountInfo(gender,  password,  dob_SplittedByDash,  newsletterSignUpYN,
                rcvSpclOffrYN,  first_name,  last_name,  company,  address1,  address2,
                country,  state,  city,  zipCode,  mobileNo);
        loginAndRegistrationAEPage.click_btn_createAccount();
        softAssert.assertEquals(loginAndRegistrationAEPage.successMsg_AccCreated(), "ACCOUNT CREATED!", "VERIFY ACCOUNT CREATION");
        softAssert.assertAll();
        ExtentTestNGListener.logWithScreenshot(DriverFactory.getDriver(), Status.PASS, "ACCOUNT CREATED");
    }

    @Test(description = "Validate Password field is Required")
    @Parameters({"name", "email"})
    public void verify_RequiredField_Password(String name, String email) throws Exception{
        email = email.split("@")[0] +System.currentTimeMillis()+"@"+email.split("@")[1];
        System.out.println("Email verify_RequiredField_Password: "+email);
        var loginAndRegistrationAEPage = new LoginAndRegistrationAEPage(DriverFactory.getDriver());
        loginAndRegistrationAEPage.enterSignUpOrLogInPage();
        loginAndRegistrationAEPage.fillup_RegistrationNameAndEmail(name, email);
        loginAndRegistrationAEPage.click_Signup();
        loginAndRegistrationAEPage.click_btn_createAccount();
        softAssert.assertEquals(loginAndRegistrationAEPage.getValidationMsg_Password(), "Please fill out this field.", "VERIFY ACCOUNT CREATION");
        softAssert.assertAll();
    }

    @Test(description = "Validate error Message - Account Already Exists")
    @Parameters({"name", "email"})
    public void errorMsg_AccountAlreadyExists(String name, String email) throws Exception{
        var loginAndRegistrationAEPage = new LoginAndRegistrationAEPage(DriverFactory.getDriver());
        loginAndRegistrationAEPage.enterSignUpOrLogInPage();
        loginAndRegistrationAEPage.fillup_RegistrationNameAndEmail(name, email);
        loginAndRegistrationAEPage.click_Signup();
        Assert.assertEquals(loginAndRegistrationAEPage.getMsg_AccAlreadyExists(), "Email Address already exist!");
    }

}
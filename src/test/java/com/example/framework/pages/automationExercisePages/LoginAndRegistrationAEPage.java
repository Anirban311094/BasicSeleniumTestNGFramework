package com.example.framework.pages.automationExercisePages;


import com.aventstack.extentreports.Status;
import com.example.framework.listeners.ExtentTestNGListener;
import com.example.framework.utils.ActionUtil;
import com.example.framework.utils.SyncUtil;
import com.example.framework.utils.WaitUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginAndRegistrationAEPage {
    private final WebDriver driver;
    WaitUtil waitUtil;
    SyncUtil syncUtil;
    ActionUtil actionUtil;
    JavascriptExecutor js;

    public LoginAndRegistrationAEPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        waitUtil = new WaitUtil(driver);
        syncUtil = new SyncUtil(driver);
        actionUtil = new ActionUtil(driver);
        js = (JavascriptExecutor) driver;
    }

    @FindBy (xpath = "//*[text()=' Signup / Login']")
    WebElement btn_signUpLogin;

    @FindBy(xpath = "//*[text()='Login to your account']")
    WebElement label_Login;

    @FindBy(xpath = "//*[text()='New User Signup!']")
    WebElement label_SignUp;

    @FindBy(xpath = "//input[@placeholder='Name']")
    WebElement input_signUp_name;

    @FindBy(xpath = "//input[@data-qa='signup-email']")
    WebElement input_signUp_Email;

    @FindBy(xpath = "//button[@data-qa='signup-button']")
    WebElement btn_SignUp;

    @FindBy(xpath = "//button[@data-qa='login-button']")
    WebElement btn_Login;

    @FindBy(xpath = "//b[text()='Enter Account Information']")
    WebElement label_EnterAccInfo;

    @FindBy(id = "id_gender1")
    WebElement radio_genderM;

    @FindBy(id = "id_gender2")
    WebElement radio_genderF;

    @FindBy(id = "password")
    WebElement input_Password;

    @FindBy(id = "days")
    WebElement select_Days_DOB;

    @FindBy(id = "months")
    WebElement select_Months_DOB;

    @FindBy(id = "years")
    WebElement select_Years_DOB;

    @FindBy(id = "newsletter")
    WebElement checkBx_newsletterSignUp;

    @FindBy(xpath = "//label[text()='Receive special offers from our partners!']/preceding-sibling::div//input")
    WebElement checkBx_rcvSpclOffr;

    @FindBy(id = "first_name")
    WebElement input_first_name;

    @FindBy(id = "last_name")
    WebElement input_last_name;

    @FindBy(id = "company")
    WebElement input_company;

    @FindBy(id = "address1")
    WebElement input_address1;

    @FindBy(id = "address2")
    WebElement input_address2;

    @FindBy(id = "country")
    WebElement select_country;

    @FindBy(id = "state")
    WebElement input_state;

    @FindBy(id = "city")
    WebElement input_city;

    @FindBy(id = "zipcode")
    WebElement input_zipcode;

    @FindBy(id = "mobile_number")
    WebElement input_mobile_number;

    @FindBy(xpath = "//button[@data-qa='create-account']")
    WebElement btn_createAccount;

    @FindBy(xpath = "//*[@data-qa='account-created']")
    WebElement msg_successAccCreated;

    @FindBy(xpath = "//*[text()='Email Address already exist!']")
    WebElement msg_AccAlreadyExists;




    /// ---  Page --- ///

    public void enterSignUpOrLogInPage() throws Exception{
        syncUtil.fluentWaitForPageLoad(10, 500);
        btn_signUpLogin.click();
    }

    /// ---  SignUp --- ///

    public boolean isSignUpDisplayed() throws Exception{
        if(syncUtil.isElementDisplayed(label_SignUp)){
            return true;
        }
        return false;
    }
    public void fillup_RegistrationNameAndEmail(String name, String email) throws Exception{
        input_signUp_name.sendKeys(name);
        input_signUp_Email.sendKeys(email);
    }
    public void click_Signup() throws Exception{
        btn_SignUp.click();
    }
    public String getTitle_EnterAccountInfo() throws Exception{
        return label_EnterAccInfo.getText().trim();
    }
    public void form_EnterAccountInfo(String gender, String password, String dob_SplittedByDash, String newsletterSignUpYN,
                                        String rcvSpclOffrYN, String first_name, String last_name, String company, String address1, String address2,
                                        String country, String state, String city, String zipCode, String mobileNo) throws Exception{
        if(gender.equalsIgnoreCase("F") || gender.equalsIgnoreCase("Female")){
            radio_genderF.click();
        }else {
            radio_genderM.click();
        }
        input_Password.sendKeys(password);
        actionUtil.selectByVisibleText(select_Days_DOB, dob_SplittedByDash.split("-")[0]);
        actionUtil.selectByVisibleText(select_Months_DOB, dob_SplittedByDash.split("-")[1]);
        actionUtil.selectByVisibleText(select_Years_DOB, dob_SplittedByDash.split("-")[2]);
        if (newsletterSignUpYN.equalsIgnoreCase("Y")) {
            checkBx_newsletterSignUp.click();
        }
        if(rcvSpclOffrYN.equalsIgnoreCase("Y")){
            checkBx_rcvSpclOffr.click();
        }
        input_first_name.sendKeys(first_name);
        input_last_name.sendKeys(last_name);
        input_company.sendKeys(company);
        input_address1.sendKeys(address1);
        input_address2.sendKeys(address2);
        actionUtil.selectByVisibleText(select_country, country);
        input_state.sendKeys(state);
        input_city.sendKeys(city);
        input_zipcode.sendKeys(zipCode);
        input_mobile_number.sendKeys(mobileNo);
    }
    public void click_btn_createAccount(){
        btn_createAccount.click();
    }

    public String successMsg_AccCreated() throws Exception{
        return msg_successAccCreated.getText().trim();
    }

    public String getValidationMsg_Password() throws Exception{
        return getValidationMsg(input_Password);
    }

    public String getValidationMsg(WebElement element){
        return (String) js.executeScript("return arguments[0].validationMessage;", element);
    }

    public String getMsg_AccAlreadyExists() throws Exception{
        return msg_AccAlreadyExists.getText().trim();
    }

    /// ---  Login --- ///

    public boolean isLoginDisplayed() throws Exception{
        if(syncUtil.isElementDisplayed(label_Login)){
            return true;
        }
        return false;
    }

}
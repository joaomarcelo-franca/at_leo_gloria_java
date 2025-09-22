package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class RegisterPage {

    private WebDriver driver;

    public RegisterPage(WebDriver driver){
        this.driver = driver;
    }

    private By SignupLoginButton = By.cssSelector("a[href='/login']");
    private By nameField = By.name("name");
    private By emailAdressField = By.cssSelector("input[data-qa='signup-email']");
    private By signUpButton = By.cssSelector("button[data-qa='signup-button']");
    private By radioMrField = By.cssSelector("label[for='id_gender1']");
    private By radioMrsField = By.cssSelector("label[for='id_gender2']");
    private By passWordField = By.id("password");
    private By dayDateOfBirthField = By.id("days");
    private By monthDateOfBirthField = By.id("months");
    private By yearDateOfBirthField = By.id("years");
    private By checkBoxNewsletterField = By.id("newsletter");
    private By checkBoxSpecialOffersField = By.id("optin");
    private By firstNameField = By.id("first_name");
    private By lastNameField = By.id("last_name");
    private By companyField = By.id("company");
    private By addressField = By.id("address1");
    private By address2Field = By.id("address2");
    private By dropDownCountryField = By.id("country");
    private By stateField = By.id("state");
    private By cityField = By.id("city");
    private By zipCodeField = By.id("zipcode");
    private By mobileNumberField = By.id("mobile_number");
    private By buttonCreateAccount = By.cssSelector("button[data-qa='create-account']");
    private By buttonContinue = By.cssSelector("a[data-qa='continue-button']");
    private By buttonDeleteAccount = By.cssSelector("a[href='/delete_account']");

    public void clickSignupLoginButton(){
        driver.findElement(SignupLoginButton).click();
    }
    public void setNameField(String name){
        driver.findElement(nameField).sendKeys(name);
    }
    public void setEmailAdressField(String email){
        driver.findElement(emailAdressField).sendKeys(email);
    }
    public void clickSignUpButton(){
        driver.findElement(signUpButton).click();
    }
    public void setTitleField(String title){
        if (title.equalsIgnoreCase("Mr")) {
            driver.findElement(radioMrField).click(); }
        else if (title.equalsIgnoreCase("Mrs")) {
            driver.findElement(radioMrsField).click();
        }
    }
    public void setPassWordField(String password){
        driver.findElement(passWordField).sendKeys(password);
    }
    public void setDropDownDateOfBirthField(String dateOfBirth){
        Select selectDay = new Select(driver.findElement(dayDateOfBirthField));
        Select selectMonth = new Select(driver.findElement(monthDateOfBirthField));
        Select selectYear = new Select(driver.findElement(yearDateOfBirthField));

        List<Select> selectsList = new ArrayList<>();
        selectsList.add(selectDay);
        selectsList.add(selectMonth);
        selectsList.add(selectYear);

        String[] values = dateOfBirth.split("/");

        selectsList.get(0).selectByValue(values[0]);
        selectsList.get(1).selectByValue(values[1]);
        selectsList.get(2).selectByValue(values[2]);
    }
    public void clickCheckBoxNewsletter(){
        driver.findElement(checkBoxNewsletterField).click();
    }
    public void clickCheckBoxOptin(){
        driver.findElement(checkBoxSpecialOffersField).click();
    }
    public void setFirstNameField(String firstName){
        driver.findElement(firstNameField).sendKeys(firstName);
    }
    public void setLastNameField(String lastName){
        driver.findElement(lastNameField).sendKeys(lastName);
    }
    public void setCompanyField(String company){
        driver.findElement(companyField).sendKeys(company);
    }
    public void setAddressField(String address){
        driver.findElement(addressField).sendKeys(address);
    }
    public void setAddress2Field(String address2){
        driver.findElement(address2Field).sendKeys(address2);
    }
    public void setDropDownCountryField(String country){
        Select select = new Select(driver.findElement(dropDownCountryField));
        select.selectByValue(country);
    }
    public void setStateField(String state){
        driver.findElement(stateField).sendKeys(state);
    }
    public void setCityField(String city){
        driver.findElement(cityField).sendKeys(city);
    }
    public void setZipCodeField(String zipcode){
        driver.findElement(zipCodeField).sendKeys(zipcode);
    }
    public void setMobileNumberField(String mobileNumber){
        driver.findElement(mobileNumberField).sendKeys(mobileNumber);
    }
    public void clickCreateAccountButton(){
        driver.findElement(buttonCreateAccount).click();
    }
    public void clickContinueButton(){
        driver.findElement(buttonContinue).click();
    }
    public void clickButtonDeleteAccount(){
        driver.findElement(buttonDeleteAccount).click();
    }
    public boolean containsDeleteMessage() {
        return !driver.findElements(By.cssSelector("h2[data-qa='account-deleted']")).isEmpty();
    }
    public boolean containsDeleteButton() {
        return !driver.findElements(buttonDeleteAccount).isEmpty();
    }

}

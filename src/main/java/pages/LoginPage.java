package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private By SignupLoginButton = By.cssSelector("a[href='/login']");
    private By emailAddressField = By.name("email");
    private By passWordField = By.name("password");
    private By buttonLogin = By.cssSelector("button[data-qa='login-button']");
    private By buttonDeleteAccount = By.cssSelector("a[href='/delete_account']");
    public void clickSignupLoginButton(){
        driver.findElement(SignupLoginButton).click();
    }

    public void setEmailAddressField(String emailAddress){
        driver.findElement(emailAddressField).sendKeys(emailAddress);
    }
    public void setPassWordField(String password){
        driver.findElement(passWordField).sendKeys(password);
    }
    public void clickButtonLogin(){
        driver.findElement(buttonLogin).click();
    }
    public void clickButtonDeleteAccount(){
        driver.findElement(buttonDeleteAccount).click();
    }
    public boolean containsDeleteMessage() {
        return !driver.findElements(By.cssSelector("h2[data-qa='account-deleted']")).isEmpty();
    }

    public boolean containsErrorMessage(){
        return !driver.findElements(By.cssSelector("p[style='color: red;']")).isEmpty();
    }

}

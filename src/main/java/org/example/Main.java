package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import pages.RegisterPage;

public class Main {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://automationexercise.com/");

////            RegisterPage
//            RegisterPage registerPage = new RegisterPage(driver);
//            registerPage.clickSignupLoginButton();
//            registerPage.setNameField("Joao");
//            registerPage.setEmailAdressField("joaomarcelo0506@gmail.com");
//            registerPage.clickSignUpButton();
//            registerPage.setTitleField("Mrs");
//            registerPage.setPassWordField("Joao12345");
//            registerPage.setDropDownDateOfBirthField("6/6/2004");
//            registerPage.clickCheckBoxNewsletter();
//            registerPage.clickCheckBoxOptin();
//            registerPage.setFirstNameField("Joao Marcelo");
//            registerPage.setLastNameField("Franca");
//            registerPage.setCompanyField("INFNET");
//            registerPage.setAddressField("Rua Cajoeiro");
//            registerPage.setAddress2Field("Rua Mangueira");
//            registerPage.setDropDownCountryField("Singapore");
//            registerPage.setStateField("RJ");
//            registerPage.setCityField("Florida");
//            registerPage.setZipCodeField("34714");
//            registerPage.setMobileNumberField("99 99999-9999");
//            registerPage.clickCreateAccountButton();
//            registerPage.clickContinueButton();
//            System.out.println(registerPage.containsDeleteButton());

//            Login Page
//            LoginPage loginPage = new LoginPage(driver);
//            loginPage.clickSignupLoginButton();
//            loginPage.setEmailAddressField("joaomarcelo0506@gmail.com");
//            loginPage.setPassWordField("Joao12345");
//            loginPage.clickButtonLogin();
//            loginPage.clickButtonDeleteAccount();
//            System.out.println(loginPage.containsDeleteMessage());

//            LoginError Page
//            LoginPage loginPage1 = new LoginPage(driver);
//            loginPage1.clickSignupLoginButton();
//            loginPage1.setEmailAddressField("joaojunior@gmail.com");
//            loginPage1.setPassWordField("Junior1");
//            loginPage1.clickButtonLogin();
            Thread.sleep(5000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        finally {
            driver.quit();
        }
    }
}
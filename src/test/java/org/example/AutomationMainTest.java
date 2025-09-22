package org.example;

import com.sun.source.tree.AssertTree;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.core.util.Assert;
import org.apache.logging.log4j.core.util.FileUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import pages.RegisterPage;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AutomationMainTest {
    private WebDriver driver;

    @BeforeAll
    static void setupClass(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest(){
        driver = new ChromeDriver();
        driver.get("https://automationexercise.com");

    }

    @AfterEach
    void teardown(TestInfo testInfo){
        try {
            // Se o teste falhou, captura screenshot
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String fileName = "screenshots/" + testInfo.getDisplayName().
                    replaceAll(" ", "_") + ".png";
            File destFile = new File(fileName);
            destFile.getParentFile().mkdirs(); // cria a pasta se não existir
            java.nio.file.Files.copy(screenshot.toPath(), destFile.toPath());
            System.out.println("Screenshot salvo em: " + fileName);
        } catch (Exception e) {
            System.out.println("Erro ao capturar screenshot: " + e.getMessage());
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    @Test
    @Order(1)
    @DisplayName("Cadastro de usuario com dados validos")
    public void testRegisterUser(){
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickSignupLoginButton();
        registerPage.setNameField("Joao");
        registerPage.setEmailAdressField("joao.franca@al.infnet.edu.br");
        registerPage.clickSignUpButton();
        registerPage.setTitleField("Mrs");
        registerPage.setPassWordField("Joao12345");
        registerPage.setDropDownDateOfBirthField("6/6/2004");
        registerPage.clickCheckBoxNewsletter();
        registerPage.clickCheckBoxOptin();
        registerPage.setFirstNameField("Joao Marcelo");
        registerPage.setLastNameField("Franca");
        registerPage.setCompanyField("INFNET");
        registerPage.setAddressField("Rua Cajoeiro");
        registerPage.setAddress2Field("Rua Mangueira");
        registerPage.setDropDownCountryField("Singapore");
        registerPage.setStateField("RJ");
        registerPage.setCityField("Florida");
        registerPage.setZipCodeField("34714");
        registerPage.setMobileNumberField("99 99999-9999");
        registerPage.clickCreateAccountButton();
        registerPage.clickContinueButton();

        assertTrue(registerPage.containsDeleteButton(),
                "Conta nao criada corretamente");
    }

    @Test
    @Order(2)
    @DisplayName("Login com credencias validas")
    public void testLoginValidUser(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickSignupLoginButton();
        loginPage.setEmailAddressField("joao.franca@al.infnet.edu.br");
        loginPage.setPassWordField("Joao12345");
        loginPage.clickButtonLogin();
        loginPage.clickButtonDeleteAccount();

        assertTrue(loginPage.containsDeleteMessage(),
                "Usuario nao logou corretamente");
    }

    @Test
    @Order(3)
    @DisplayName("Login com credencias invalidas")
    public void testLoginInvalidUser(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickSignupLoginButton();
        loginPage.setEmailAddressField("juninho@gmail.com");
        loginPage.setPassWordField("senha12");
        loginPage.clickButtonLogin();

        assertTrue(loginPage.containsErrorMessage(),
                "Mensagem de erro nao apareceu");
    }

}



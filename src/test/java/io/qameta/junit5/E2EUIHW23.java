package io.qameta.junit5;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.junit.jupiter.api.*;
import pages.LoginPage;
import pages.OrderPage;


public class E2EUIHW23 {

    // Homework23
    LoginPage loginPage;
    OrderPage orderPage;


    @BeforeEach
    public void setUp(){
        Selenide.open("http://51.250.6.164:3000/signin");
    }

    @BeforeEach
    public void setupLoginPage(){
        loginPage = new LoginPage();
        loginPage.locateUsernameAndInsertText("user-v-07");
        loginPage.locatePasswordAndInsertText("pwd876543");

    }

    @BeforeEach
    public void setupOrderPage(){
        orderPage = new OrderPage();
    }

    @AfterEach
    public void tearDown(){
        Selenide.closeWebDriver();
    }

    // a) Login and creating order
    @Step
    public void firstStepLogIn() {

        orderPage.clickButtonSignInAndGoToOrder();
    }

    @Step
    public void secondStepCreateOrder(){

        orderPage.usernameInsertText("New order");
        orderPage.phoneInsertNumbers("+37255566677");
        orderPage.commentInsertNumbers("Created today");

        orderPage.buttonCreateOrder();

        orderPage.buttonOkWithCreatedOrderVisible();
    }


    // b) Login and search non-existing order
//    @Step
//    public void firstStepLogInForNonExistOrder() {
//
//        orderPage.clickButtonSignInAndGoToOrder();
//    }

    @Step
    public void secondStepSearchNonExistOrder() {
        orderPage.clickButtonStatusToSearchOrder();

        orderPage.fieldSearchOrder("1234567");
        orderPage.clickButtonSearchOrder();
        orderPage.checkErrorOrderNotFound();
    }

    @Test
    void logInAndCreatingOrder(){
        firstStepLogIn();
        secondStepCreateOrder();
    }

    @Test
    void loginAndSearchNonExistingOrder(){
     firstStepLogIn();
     secondStepSearchNonExistOrder();
    }
}

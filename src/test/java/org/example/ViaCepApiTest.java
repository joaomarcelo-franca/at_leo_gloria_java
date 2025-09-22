package org.example;

import org.junit.jupiter.api.Test;

import java.net.HttpURLConnection;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class ViaCepApiTest {

    @Test
    void testCepValido(){
        given()
                .when()
                .get("https://viacep.com.br/ws/01001000/json/")
                .then()
                .statusCode(200)
                .body("logradouro", equalTo("Praça da Sé"));
    }
    @Test
    void testCepValidoComHifen(){
        given()
                .when()
                .get("https://viacep.com.br/ws/01001-000/json/")
                .then()
                .statusCode(200);
    }
    @Test
    void testCepInvalidoComLetras(){
        given()
                .when()
                .get("https://viacep.com.br/ws/abcde/json/")
                .then()
                .statusCode(400);
    }
    @Test
    void testCepInvalidoComMaisDigitos(){
        given()
                .when()
                .get("https://viacep.com.br/ws/010010001/json/")
                .then()
                .statusCode(400);
    }
    @Test
    void testCepInvalidoComMenosDigitos(){
        given()
                .when()
                .get("https://viacep.com.br/ws/0100100/json/")
                .then()
                .statusCode(400);
    }
    @Test
    void testCepInvalidoComCepInexistente(){
        given()
                .when()
                .get("https://viacep.com.br/ws/22222222/json/")
                .then()
                .statusCode(200)
                .body("erro", equalTo("true"));
    }
    @Test
    void testCepInvalidoComCepInexistenteComHifen(){
        given()
                .when()
                .get("https://viacep.com.br/ws/22222-222/json/")
                .then()
                .statusCode(200)
                .body("erro", equalTo("true"));
    }
    @Test
    void testCepInvalidoComCepVazio(){
        given()
                .when()
                .get("https://viacep.com.br/ws//json/")
                .then()
                .statusCode(400);
    }

}
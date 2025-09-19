package org.example;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class ViaCepApiHttpTest {

    // ----------------- Método auxiliar -----------------
    private String getJsonFromUrl(String urlString, int expectedStatus) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int status = connection.getResponseCode();
        assertEquals(expectedStatus, status, "Status code inesperado: " + status);

        BufferedReader in = new BufferedReader(new InputStreamReader(
                status == 200 ? connection.getInputStream() : connection.getErrorStream()
        ));
        StringBuilder content = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            content.append(line);
        }
        in.close();
        connection.disconnect();

        return content.toString();
    }

    // ----------------- Testes -----------------
//    UF: 1 CIDADE: 1 RUA: 1
    @Test
    void testEnderecoValido() throws Exception {
        String json = getJsonFromUrl(
                "https://viacep.com.br/ws/SP/Sao%20Paulo/Avenida%20Paulista/json/",
                200
        );
        assertTrue(json.contains("Paulista"), "JSON não contém logradouro esperado");
    }

    //UF: 1 CIDADE: 1 RUA: 0
    @Test
    void testUfValidoCidadeValidaLogradouroInvalido() throws Exception {
        String json = getJsonFromUrl(
                "https://viacep.com.br/ws/SP/Sao%20Paulo/RuaInexistente123/json/",
                200
        );
        assertEquals("[]", json, "JSON deveria ser um array vazio");
    }

    //UF: 1 CIDADE: 0 RUA: 0
    @Test
    void testUfValidoCidadeInvalidaLogradouroInvalido() throws Exception {
        String json = getJsonFromUrl(
                "https://viacep.com.br/ws/SP/Cidade%20Inexistente/RuaInexistente123/json/",
                200
        );
        assertEquals("[]", json, "JSON deveria ser um array vazio");
    }

    //UF: 1 CIDADE: 0 RUA: 1
    @Test
    void testUfValidoCidadeInvalidaLogradouroValido() throws Exception {
        String json = getJsonFromUrl(
                "https://viacep.com.br/ws/SP/Cidade%20Inexistente/Avenida%20Paulista/json/",
                200
        );
        assertEquals("[]", json, "JSON deveria ser um array vazio");
    }

    //    UF: 0 CIDADE: 1 RUA : 0
    @Test
    void testUfInvalidoCidadeValidaLogradouroInvalido() throws Exception {
        String json = getJsonFromUrl(
                "https://viacep.com.br/ws/XX/Sao%20Paulo/RuaInexistente123/json/",
                200
        );
        assertEquals("[]", json, "JSON deveria ser um array vazio");
    }

    //UF : 0 CIDADE: 0 RUA: 0
    @Test
    void testUfInvalidoCidadeInvalidaLogradouroInvalido() throws Exception {
        String json = getJsonFromUrl(
                "https://viacep.com.br/ws/XX/Cidade%20Inexistente/RuaInexistente123/json/",
                200
        );
        assertEquals("[]", json, "JSON deveria ser um array vazio");
    }

    // UF : 0 CIDADE: 0 RuA: 1
    @Test
    void testUfInvalidoCidadeInvalidaLogradouroValido() throws Exception {
        String json = getJsonFromUrl(
                "https://viacep.com.br/ws/XX/Cidade%20Inexistente/Avenida%20Paulista/json/",
                200
        );
        assertEquals("[]", json, "JSON deveria ser um array vazio");
    }

//    UF: 0 CIDADE: 1 RUA: 1
    @Test
    void testUfInvalidoCidadeValidaLogradouroValido() throws Exception {
        String json = getJsonFromUrl(
                "https://viacep.com.br/ws/XX/Sao%20Paulo/Avenida%20Paulista/json/",
                200
        );
        assertEquals("[]", json, "JSON deveria ser um array vazio");
    }


    @Test
    void testCidadeInexistente() throws Exception {
        String json = getJsonFromUrl(
                "https://viacep.com.br/ws/SP/CidadeInexistente/RuaQualquer/json/",
                200
        );
        assertEquals("[]", json, "JSON deveria ser um array vazio");
    }

    @Test
    void testUfInvalidaLetra() throws Exception {
        String json = getJsonFromUrl(
                "https://viacep.com.br/ws/XX/Sao%20Paulo/Avenida%20Paulista/json/",
                200
        );
        assertEquals("[]", json, "UF inválida letra deveria retornar array vazio");
    }

    @Test
    void testUfInvalidaNumero() throws Exception {
        URL url = new URL("https://viacep.com.br/ws/12/Sao%20Paulo/Avenida%20Paulista/json/");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int status = connection.getResponseCode();
        assertEquals(400, status, "UF inválida número deveria retornar 400");
        connection.disconnect();
    }
}

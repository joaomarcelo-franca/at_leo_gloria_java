package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculoIMCTest {

    @Test
    public void testCalcularPeso_ValorNormal(){
        double imc = CalculoIMC.calcularPeso(70, 1.75);
        assertEquals(22.86,imc, 0.01);
    }

    @Test
    public void testClassificarIMC_Limites() {
        assertEquals("Magreza grave", CalculoIMC.classificarIMC(16.0)); // atenção: sua lógica do if tem '==' errado
        assertEquals("Magreza moderada", CalculoIMC.classificarIMC(17.0));
        assertEquals("Magreza leve", CalculoIMC.classificarIMC(18.5));
        assertEquals("Saudável", CalculoIMC.classificarIMC(25.0));
        assertEquals("Sobrepeso", CalculoIMC.classificarIMC(30.0));
        assertEquals("Obesidade Grau I", CalculoIMC.classificarIMC(35.0));
        assertEquals("Obesidade Grau II", CalculoIMC.classificarIMC(40.0));
    }


    @Test
    public void testCalcularPeso_ValorInvalido(){
        assertThrows(IllegalArgumentException.class, () -> CalculoIMC.calcularPeso(-70, 1.75));
        assertThrows(IllegalArgumentException.class, () -> CalculoIMC.calcularPeso(70, 0));
    }

    @Test
    public void testClassificarIMC_MagrezaGrave(){
        assertEquals("Magreza grave", CalculoIMC.classificarIMC(15.9));
    }

    @Test
    public void testClassificarIMC_MagrezaModerada(){
        assertEquals("Magreza moderada", CalculoIMC.classificarIMC(16.5));
    }

    @Test
    public void testClassificarIMC_MagrezaLeve() {
        assertEquals("Magreza leve", CalculoIMC.classificarIMC(17.5));
    }

    @Test
    public void testClassificarIMC_Saudavel() {
        assertEquals("Saudável", CalculoIMC.classificarIMC(22.0));
    }

    @Test
    public void testClassificarIMC_Sobrepeso() {
        assertEquals("Sobrepeso", CalculoIMC.classificarIMC(27.0));
    }

    @Test
    public void testClassificarIMC_ObesidadeGrauI() {
        assertEquals("Obesidade Grau I", CalculoIMC.classificarIMC(33.0));
    }

    @Test
    public void testClassificarIMC_ObesidadeGrauII() {
        assertEquals("Obesidade Grau II", CalculoIMC.classificarIMC(38.0));
    }

    @Test
    public void testClassificarIMC_ObesidadeGrauIII() {
        assertEquals("Obesidade Grau III", CalculoIMC.classificarIMC(45.0));
    }

    @Test
    public void testValoresExtremos(){
        double imcBaixo = CalculoIMC.calcularPeso(30, 1.80);
        assertEquals("Magreza grave", CalculoIMC.classificarIMC(imcBaixo));

        double imcAlto = CalculoIMC.calcularPeso(200, 1.50);
        assertEquals("Obesidade Grau III", CalculoIMC.classificarIMC(imcAlto));
    }

}
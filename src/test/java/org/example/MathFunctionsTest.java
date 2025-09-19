package org.example;

import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;
import org.junit.jupiter.api.DisplayName;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MathFunctionsTest {

    @Property(tries = 1000)
    @Label("MultiplyByTwo: valide que o resultado é sempre par.")
    void testMultiplyByTwo(@ForAll @IntRange
            (min = 1, max = Integer.MAX_VALUE /2 ) int number)
    {
        int numeroOriginal = MathFunctions.multiplyByTwo(number);
//        System.out.println(number);
//        System.out.println(numeroOriginal);
//        System.out.println(numeroOriginal % 2);
        assertEquals(0, numeroOriginal % 2,
                "Dobro de " + number + " não resultou em um número par");
    }

    @Property(tries = 1000)
    @Label("GenerateMultiplicationTable: valide que todos os elementos são " +
            "múltiplos do número original.")
    void testGenerateMultiplicationTable(@ForAll @IntRange
            (min = 1, max = Integer.MAX_VALUE / 5 ) int number){
//        Devido INTEGER VALUEMAX o numero do parametro limit da funcao generateMultiplicationTable
////        deve ser o mesmo valor que o max = Integer Valuemax for dividido
        int numeroOriginal = number;

        int[] tabelaMultiplos = MathFunctions.generateMultiplicationTable
                (numeroOriginal, 5);

        for (int numeroMultiplo : tabelaMultiplos){
            ////            System.out.println(numeroMultiplo);
            assertEquals(0, numeroMultiplo % numeroOriginal,
                    "O numero " + numeroMultiplo + " deveria ser multiplo" +
                            " de " + numeroOriginal + " mas nao é");
        }

    }

//    @Property(tries = 1000)
//    @Label("GenerateMultiplicationTable: valide que todos os elementos são múltiplos do número original.")
//    void testGenerateMultiplicationTable(@ForAll @IntRange(min = 1, max = Integer.MAX_VALUE / 5 ) int number){
////        Devido INTEGER VALUEMAX o numero do parametro limit da funcao generateMultiplicationTable
////        deve ser o mesmo valor que o max = Integer Valuemax for dividido
//        int numeroOriginal = number;
//        int[] tabelaMultiplos = MathFunctions.generateMultiplicationTable(numeroOriginal, 5);
//
//        for (int numeroMultiplo : tabelaMultiplos){
////            System.out.println(numeroMultiplo);
//            assertEquals(0, numeroMultiplo % numeroOriginal, "O numero " + numeroMultiplo + " deveria ser multiplo de " + numeroOriginal + " mas nao é");
//        }
//
//    }


    @Property(tries = 1000)
    @Label("IsPrime: Nenhum número <= 1 é primo\"")
    void testIsPrime1(@ForAll @IntRange(max = 1) int number){
        assertFalse(MathFunctions.isPrime(number),
                "Nenhum numero <= 1 deve ser primo " + number +
                        " nao se aplicou a regra");
    }

    @Property(tries = 1000)
    @Label("IsPrime: Nenhum número par maior que 2 é primo")
    void testIsPrime2(
            @ForAll @IntRange(min = 4, max = 10000) int number){
        if (number % 2 == 0){
            assertFalse(MathFunctions.isPrime(number));
        }
    }

    @Property(tries = 1000)
    @Label("IsPrime: Se for primo, não possui divisores além de 1 e ele mesmo")
    void testIsPrime3(
            @ForAll @IntRange(min = 2, max = 10000) int number){
        if (MathFunctions.isPrime(number)){
            for (int i = 2; i < number; i++){
                assertNotEquals(0, number % i,
                        "Número " + number + " não deveria ser " +
                                "divisível por " + i);
            }
        }
    }


//    Criando gerador
    @Provide
    Arbitrary<int[]> arrayIntPossitivos(){
        return Arbitraries.integers()
                .between(1, Integer.MAX_VALUE)
                .array(int[].class)
//        Se colocar ofMinSize(1) da erro, deveria haver validacao no metodo para nao receber null nem 1
                .ofMinSize(2).ofMaxSize(100);
    }



//    Bubble Sort para vetorizar do menor pro maior
public static int[] bubbleSort(int[] array) {
    int n = array.length;
    boolean trocou;
    for (int i = 0; i < n - 1; i++) {
        trocou = false;
        for (int j = 0; j < n - 1 - i; j++) {
            if (array[j] > array[j + 1]) {
                int temp = array[j];
                array[j] = array[j + 1];
                array[j + 1] = temp;
                trocou = true;
            }
        }
        if (!trocou) break;
    }

    return array;
}
    @Property(tries = 1000)
    @Label("CalculateAverage: verifique se o resultado está sempre entre o menor " +
            "e o maior valor do array.")
    void testCalculateAverage(@ForAll("arrayIntPossitivos") int[] numbers){
        int[] numbersBubbleSort = bubbleSort(numbers);
        int primeiroNumeroDoArray = numbersBubbleSort[0];
        int ultimoNumeroDoArray = numbersBubbleSort[numbersBubbleSort.length - 1];
        double mediaResultado = MathFunctions.calculateAverage(numbersBubbleSort);
        assertTrue(mediaResultado < ultimoNumeroDoArray
                && mediaResultado > primeiroNumeroDoArray,
                "A media: " + mediaResultado + " deveria ser maior " +
                        "que o " + primeiroNumeroDoArray + " e menor que o "
                        + ultimoNumeroDoArray);
    }
    //        System.out.println(primeiroNumeroDoArray);
//        System.out.println(ultimoNumeroDoArray);
//        System.out.println(Arrays.toString(numbersBubbleSort));
}
package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class  BubbleSortRecursiveTest {
    private final BubbleSortRecursive sortRecursive = new BubbleSortRecursive();

    @Test
    @DisplayName("testArrayVazio")
    void testEmptyArray() {
        Integer[] arr = new Integer[]{};
        Integer[] sorted = sortRecursive.sort(arr);
        assertArrayEquals(new Integer[]{}, sorted,
                "Array vazio deve permanecer vazio");
    }

    @Test
    @DisplayName("testArrayUmElemento")
    void testSingleElementArray() {
        Integer[] arr = new Integer[]{5};
        Integer[] sorted = sortRecursive.sort(arr);
        assertArrayEquals(new Integer[]{5}, sorted,
                "Array com 1 elemento deve permanecer igual");
    }

    @Test
    @DisplayName("testArrayOrdenado")
    void testAlreadySortedArray() {
        Integer[] arr = new Integer[]{1, 2, 3, 4, 5};
        Integer[] sorted = sortRecursive.sort(arr);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, sorted,
                "Array já ordenado não deve ser alterado");
    }

    @Test
    @DisplayName("testArrayDesordenado")
    void testReverseArray() {
        Integer[] arr = new Integer[]{5, 4, 3, 2, 1};
        Integer[] sorted = sortRecursive.sort(arr);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, sorted,
                "Array em ordem reversa deve ser ordenado");
    }

    @Test
    @DisplayName("testArrayComDuplicados")
    void testArrayWithDuplicates() {
        Integer[] arr = new Integer[]{3, 1, 2, 1, 3};
        Integer[] sorted = sortRecursive.sort(arr);
        assertArrayEquals(new Integer[]{1, 1, 2, 3, 3}, sorted,
                "Array com duplicados deve ser ordenado corretamente");
    }

    @Test
    @DisplayName("testArrayComString")
    void testStringArray() {
        String[] arr = new String[]{"banana", "apple", "cherry"};
        String[] sorted = sortRecursive.sort(arr);
        assertArrayEquals(new String[]{"apple", "banana", "cherry"}, sorted,
                "Array de Strings deve ser ordenado alfabeticamente");
    }
}
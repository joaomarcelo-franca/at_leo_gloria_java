package org.example;

public class MathFunctions1 {
    private final MathLogger logger;

    public MathFunctions1(MathLogger logger) {
        this.logger = logger;
    }


    public int multiplyByTwo(int number) {
        logger.log("multiplyByTwo", new int[]{number * 2});
        return number * 2;
    }

    public int[] generateMultiplicationTable(int number, int limit) {
        int[] result = new int[limit];
        for (int i = 0; i < limit; i++) {
            result[i] = number * (i + 1);
        }
        logger.log("generateMultiplicationTable", new int[]{number, limit});
        return result;
    }

    public boolean isPrime(int number) {
        if (number <= 1) return false;

        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                logger.log("isPrime", new int[]{number});
                return false;
            }
        }
        logger.log("isPrime", new int[]{number});
        return true;
    }

    public double calculateAverage(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            throw new IllegalArgumentException("Array não pode ser nulo ou vazio.");
        }
        double sum = 0;
        for (int n : numbers) {
            sum += n;
        }
        logger.log("calculateAverage", numbers);
        return sum / numbers.length;
    }
}

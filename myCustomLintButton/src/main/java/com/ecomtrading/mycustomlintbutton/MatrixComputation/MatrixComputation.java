package com.ecomtrading.mycustomlintbutton.MatrixComputation;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MatrixComputation {
    private static int[] matrixARowSum = new int[4];
    private static int[] matrixBRowSum = new int[4];
    private static int[] matrixCombinedRowSum = new int[4];
    private static int matrixASum = 0;
    private static int matrixBSum = 0;

    //On windows
    private static String WINDOWS_PATH = "E:\\Computation\\%s.txt";
    private static String WINDOWS_DIR = "E:\\Computation";
    //On MAC
    private static String MAC_PATH = "/Users/Nyame/Desktop/Computation/%s.txt";
    private static String MAC_DIR = "/Users/Nyame/Desktop/Computation/";

    public static void main(String[] args) {
        int[][] matrixA = new int[4][3];

        int[][] matrixB = new int[4][3];

        Scanner scanner = new Scanner(System.in);
        buildMat(scanner, "MatrixA");
        readFrom("MatrixA", matrixA);
        buildMat(scanner, "MatrixB");
        readFrom("MatrixB", matrixB);
        calRowSum(matrixA, MatrixType.MatrixA);
        System.out.println("------------------------------------------");
        calRowSum(matrixB, MatrixType.MatrixB);
        System.out.println("------------------------------------------");
        calCombinedRowSum();
        System.out.println("------------------------------------------");
        calMatrixSum(matrixARowSum, MatrixType.MatrixA);
        System.out.println("------------------------------------------");
        calMatrixSum(matrixBRowSum, MatrixType.MatrixB);
        System.out.println("------------------------------------------");
        calCombinedMatrixSum(matrixASum, matrixBSum);


    }


    //compute sum of respective row
    private static void calRowSum(int[][] matrix, String type) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int rowSum = 0;
            for (int j = 0; j < 3; j++) {
                rowSum += matrix[i][j];
            }

            if (type.equalsIgnoreCase(MatrixType.MatrixA)) {
                matrixARowSum[i] = rowSum;
            } else {
                matrixBRowSum[i] = rowSum;
            }

            System.out.format("Sum of %s Row %s : %s\n", type, i, rowSum);
            stringBuilder.append(String.format("Sum of %s Row %s : %s\n", type, i, rowSum));

        }
        writeToFile(type + "_Row_sum", stringBuilder.toString());
    }

    //common row sum of the two matrice
    private static void calCombinedRowSum() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 4; i++) {

            matrixCombinedRowSum[i] = matrixARowSum[i] + matrixBRowSum[i];

            System.out.format("Combined Row Sum of Matrix Row %s : %s\n", i, matrixCombinedRowSum[i]);

            stringBuilder.append(String.format("Combined Row Sum of Matrix Row %s : %s\n", i, matrixCombinedRowSum[i]));
        }

        writeToFile("Combined_Row_sum", stringBuilder.toString());
    }

    //the sum of each matrix
    private static void calMatrixSum(int[] matrix, String type) {
        int matrixSum = 0;
        for (int i = 0; i < 4; i++) {

            matrixSum += matrix[i];
            if (type.equalsIgnoreCase(MatrixType.MatrixA)) {
                matrixASum += matrix[i];
            } else {
                matrixBSum += matrix[i];
            }
        }

        System.out.format("Sum of Matrix %s : %s\n", type, matrixSum);
        writeToFile(type + "Total_sum", String.format("Sum of Matrix %s : %s\n", type, matrixSum));
    }

    //the combined sum of the two matrix
    private static void calCombinedMatrixSum(int matrixASum, int matrixBSum) {
        int sum = matrixASum + matrixBSum;

        System.out.format("Combined Sum for the two matrix : %s\n", sum);
        writeToFile("totalSum", String.format("Combined Sum for the two matrix : %s\n", sum));
    }

    //Writing to a file
    private static void writeToFile(String fileName, String text) {
        try {
            File file = new File(String.format(WINDOWS_PATH,fileName));
            File dir = new File(WINDOWS_DIR);
            if (!dir.exists()) {
                dir.mkdir();
            } else {
                dir.delete();
            }
            BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(file));
            fos.write(text.getBytes());
            fos.flush();

            System.out.println("FUll path To the file >>"+file.getAbsolutePath());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Reading from a file
    private static void readFrom(String fileName, int[][] array) {
        try {
            File file = new File(String.format(WINDOWS_PATH,fileName));

            StringBuilder stringBuilder = new StringBuilder();
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                stringBuilder.append(data);
            }

            String[] data = stringBuilder.toString().split(" ");

            build2DArray(array, data);

            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void build2DArray(int[][] array, String[] data) {
        int row = -1, col;
        for (int i = 0; i < data.length; i++) {
            col = i % 3;
            if (col == 0) {
                row++;
            }

            //System.out.println(data[i]);
            if (!data[i].trim().equalsIgnoreCase("")) {
                array[row][col] = Integer.parseInt(data[i]);

                //System.out.println(array[row][col]);
            }
        }
    }


    private static void buildMat(Scanner scanner, String matrix) {
        StringBuilder stringBuilder = new StringBuilder();
        System.out.println("Enter the value to build " + matrix);
        System.out.println("-------------------------------------------");
        for (int i = 0; i < 12; i++) {
            try {
                System.out.println("Enter the value row " + (i + 1));
                int input = (int) scanner.nextDouble();
                stringBuilder.append(input + " ");
            } catch (InputMismatchException e) {
                System.out.println("Wrong Entry. Please, type again and it should be numbers");
                int input = scanner.nextInt();
                stringBuilder.append(input + " ");
                e.printStackTrace();
            }
        }


        writeToFile(matrix, stringBuilder.toString());
    }


    private interface MatrixType {
        String MatrixA = "MatrixA";
        String MatrixB = "MatrixB";
    }
}

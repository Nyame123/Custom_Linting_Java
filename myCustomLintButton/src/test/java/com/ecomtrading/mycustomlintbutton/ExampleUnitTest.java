package com.ecomtrading.mycustomlintbutton;

import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        Scanner scanner = new Scanner(System.in);
        String result = scanner.nextLine();
        assertEquals(4, 2 + 2);
    }
}
package com.example;
import com.example.Calculator;
import org.junit.*;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {

    final double DELTA = 1e-5;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        System.out.println("before class");
    }
    @Before
    public void setUp() throws Exception {
        System.out.println("before a test method");
    }

    @Test
    public void testAdd(){
        System.out.println("test add");
        assertEquals(5, Calculator.add(1, 4));
        assertEquals(0, Calculator.add(-3, 3));
        assertEquals(6, Calculator.add(4, 2));
    }

    @Test
    public void testSubtract(){
        System.out.println("test subtract");
        assertEquals(-3, Calculator.subtract(1, 4));
        assertEquals(-6, Calculator.subtract(-3, 3));
        assertEquals(2, Calculator.subtract(4, 2));
    }

    @Test
    public void testMultiply(){
        System.out.println("test multiply");
        assertEquals(4, Calculator.multiply(1, 4));
        assertEquals(-9, Calculator.multiply(-3, 3));
        assertEquals(8, Calculator.multiply(4, 2));
    }

    @Test
    public void testDivide(){
        System.out.println("test divide");
        assertEquals(0.25, Calculator.divide(1, 4), DELTA);
        assertEquals(-1, Calculator.divide(-3, 3), DELTA);
        assertEquals(2, Calculator.divide(4, 2), DELTA);
    }

    @Test(expected = ArithmeticException.class)
    public void testDivideByZero(){
        System.out.println("test divide by zero");
        Calculator.inverse( 0);
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("after a test method");
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        System.out.println("after class");
    }
}


package com.ecomtrading.mycustomlintbutton.Calculator;

public class Substraction implements OperationDelegate {
    private double firstNumber;
    private double secondNumber;

    public double getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(double firstNumber) {
        this.firstNumber = firstNumber;
    }

    public double getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(double secondNumber) {
        this.secondNumber = secondNumber;
    }

    //Take two numbers and substract them
    public double execute(){
      return firstNumber - secondNumber;
    }
}

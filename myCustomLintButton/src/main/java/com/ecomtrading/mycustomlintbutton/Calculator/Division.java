package com.ecomtrading.mycustomlintbutton.Calculator;

public class Division implements OperationDelegate {
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

    //Take two numbers and divide them
    public double execute(){
      return firstNumber / secondNumber;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polynomialeval;

/**
 *
 * @author aliza
 */
public class PolynomialEval {

    /**
     * This program will compare the accuracy of two separate approaches to multiplying
     * polynomials
     * @author: Aliza Siddiqui
     */
    public static void main(String[] args) {
        double n = 1.365200000;
        double x = (n*n*n) + 4*(n*n) - 10;
        System.out.println(x);
        /**double x = -0.7;
        //System.out.println("Expected: " + 0.06859);
        double result = regularMult(x);
        double result2 = nestedMult(x);
        System.out.println("RegularMult: " + result);
        System.out.println("NestedMult: " + result2);
        
        double difference = result - result2;
        System.out.println("Difference between Regular Multiplicaton and Nested Multiplication: " + difference);
        */
    }
    
    public static double regularMult(double x){
      double sum = Math.pow(x, 5) + 0.9*(Math.pow(x, 4)) - 1.62*(Math.pow(x,3)) - 1.458*(Math.pow(x, 2)) + 0.6561*x + 0.59049;
      return sum;
        
    }
    
    public static double nestedMult(double x){
        double sum = 0.59049 + x*(0.6561 + x*(-1.458 + x*(-1.62 + x*(0.9 + x))));
        return sum;
    }
    
}


package summation;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Summation {

/**
* This program computes the summation of the following term, 1/j*(j*2), for 
* various values of n using two methods: 
* 1.) Summing smallest to largest term
* 2.) Summing largest to smallest term
* The program then calculates the true value of the summation and the errors 
* from the approximations --placing them into a formatted table
*
* CSC 2262 Programming project No 2
*
* @author Aliza Siddiqui
* @since 9/18/2020
*
*/
    public static void main(String[] args)throws FileNotFoundException {
        int[] numTerms = {10,50,100,500,1000,5000}; //array of all the n amount of terms to be calculated
        
        PrintWriter out = new PrintWriter("summation.txt");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-30s%-30s%-30s%-30s%-30s%-30s\n", "n", "True", "SL", "Error SL", "LS", "Error LS");
        out.printf("%-30s%-30s%-30s%-30s%-30s%-30s\n", "n", "True", "SL", "Error SL", "LS", "Error LS");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        for (int n: numTerms){ 
           double sumSL = SummationSLTerms(n); 
           double sumLS = SummationLSTerms(n);
           double trueVal = (3.0/4)- ((2*n + 3.0)/(2*(n+1)*(n+2))); //True value of summation for a specific n
           double errorSL = trueVal - sumSL; 
           double errorLS = trueVal - sumLS;
           System.out.printf("%-30d%-30f%-30f%-30.25f%-30f%-30.25f\n", n, trueVal, sumSL, errorSL, sumLS, errorLS);
           out.printf("%-30d%-30f%-30f%-30.25f%-30f%-30.25f\n", n, trueVal, sumSL, errorSL, sumLS, errorLS);
           
        }
        out.close();
        
        
    }
    /**
    * This method computes the summation of the n terms from largest to smallest
    * for the given formula
    *
    * method: SummationLSTerms
    *
    * parameters:
    * n [int] the number of terms to be summed/ values 1-n will be plugged into
    *         formula and added to give a sum
    * 
    * return type: double
    * @return the summation of the terms from largest to smallest
    * @author Aliza Siddiqui
    * @since  9/17/2020
    */
    public static double SummationLSTerms(int n){
         double sum = 0; //Total sum of all terms
         for(int j = 1; j<=n; j++){ /*NOTE: going from 1 -> n because the 
                                    smaller the j, the larger the  */
             double term = 1.0 / (j*(j+2)); //summation term to be added given a j
             sum += term;
         }
         return sum;
    }
    
    /**
    * This method computes the summation of the n terms from smallest to largest
    * for the given formula
    *
    * method: SummationSLTerms
    *
    * return type: double
    *
    * parameters:
    * n [int] the number of terms to be summed/ values n-1 will be plugged into
    *         formula and added to give a sum
    *
    * return type: double
    * @return the summation of the terms from smallest to largest
    * @author Aliza Siddiqui
    * @since  9/17/2020
    */
    public static double SummationSLTerms(int n){
         double sum = 0;
         for(int j = n; j>=1; j--){ /*NOTE: going from n -> 1 because the 
                                    larger the j, the smaller the term */
             double term = 1.0 / (j*(j+2)); 
             sum += term;
         }
         return sum;
    }
    
}

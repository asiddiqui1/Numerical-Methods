/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secantmethod;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 *
 * @author aliza
 */
public class SecantMethod {

/**
* This program computes the root of the following function, f(x) = cos(x)-x, for 
* 6 iterations using the secant method: 
* 
* The program also calculates the distance between the iterates--placing them into a formatted table
* 
* Variable Key:
* xn_1 :  x_(n-1)
* xn:     x_n
* f_xn_1: f(x_(n-1))
* f_xn:   f(x_n)
* x_n1:   Temporary variable to store value of new iterate for printing
* f_x:    Function value of new iterate
* 
*
* CSC 2262 Programming project No 3
*
* @author Aliza Siddiqui
* @since 9/24/2020
*
*/
    public static void main(String[] args) throws FileNotFoundException{
        double xn_1 = 0; //x(n-1) Initial guess 1 (x0)
        double xn = Math.PI/2.0; //xn Inital guess 2 (x1)
        
        PrintWriter out = new PrintWriter("secantMethod.txt");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-30s%-30s%-30s%-30s\n", "n", "x_n", "f(x_n)", "x_n - x_(n-1)");
        out.printf("%-30s%-30s%-30s%-30s\n", "n", "x_n", "f(x_n)", "x_n - x_(n-1)");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        
        //Calculating function values of two initial guesses
        double f_xn_1 = Math.cos(xn_1)- xn_1; //f(x_n-1)
        double f_xn = Math.cos(xn)- xn; //f(x_n)
        System.out.printf("%-30d%-30.25f%-30.25f\n", 0, xn_1 , f_xn_1);
        out.printf("%-30d%-30.25f%-30.25f\n", 0, xn_1 , f_xn_1);
        System.out.printf("%-30d%-30.25f%-30.25f%-30.25f\n", 1, xn , f_xn, xn - xn_1);
        out.printf("%-30d%-30.25f%-30.25f%-30.25f\n", 1, xn , f_xn, xn - xn_1);
        
        for(int i = 2; i < 8; i++){
           f_xn = Math.cos(xn)- xn;
           f_xn_1 = Math.cos(xn_1)- xn_1;
           double intermed = (xn - xn_1)/(f_xn - f_xn_1); //intermediate term: x_n - x_(n-1)/ f(x_n) - f(x_(n-1)
           double x_n1 = xn - ((f_xn)*intermed); //new x_n
           double f_x = Math.cos(x_n1) - x_n1; //new f(x_n)
           System.out.printf("%-30d%-30.25f%-30.25f%-30.25f\n", i, x_n1 , f_x, x_n1-xn);
           out.printf("%-30d%-30.25f%-30.25f%-30.25f\n", i, x_n1 , f_x, x_n1-xn);
           
           //Adjusting variables for next iteration
           xn_1 = xn; //place x_n value into x_(n-1)
           xn = x_n1; //place new x_n value into x_n variable
        }
        
        //Checking to make sure value xn is the root of function
        double f_x = Math.cos(xn) - xn;
        System.out.println("f(x) = " + f_x);
        out.printf("f(x) = " + f_x);
        
        out.close();
    }
    
}

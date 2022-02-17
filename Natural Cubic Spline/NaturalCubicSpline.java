/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package natural.cubic.spline;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
* This program will calculate the natural cubic spline coefficients
* a_j, b_j, c_j, d_j of the natural cubic spline:
* S(x) = S_j(x) = a_j + b_j (x-x_j) + c_j (x-x_j)^2 + d_j (x-x_j)^3
, for x_j <= x < x_{j +1}.
* 
* ALGORITHM:
* STEP 1: Calculating all the h_i values
* STEP 2: Calculating alpha values for i= 1, 2,....n-1
* STEP 3: Initialize L_0, mu_0, z_0 = 1, 0, 0
* STEP 4: Calculate l_i, mu_i, and z_i values for i= 1, 2,...n-1
* STEP 5: Set L_8, z_8, c_8 = 1, 0, 0
* STEP 6: Calculate coefficients b_j, c_j, and d_j for j= n-1, n-1,....0
* 
* CSC 3102 Programming project No 4
*
* @author Aliza Siddiqui
* @since 10/5/2020
*
*/
public class NaturalCubicSpline {

    
    public static void main(String[] args)throws FileNotFoundException{
        //Data structures to store values for later use
        ArrayList<Double> xValues = new ArrayList<>();
        ArrayList<Double> funcValues = new ArrayList<>(); //function values a_j = f(x_j)
        ArrayList<Double> hValues = new ArrayList<>();
        ArrayList<Double> muValues = new ArrayList<>();
        ArrayList<Double> zValues = new ArrayList<>();
        
        double[] bCoeff = new double[10]; //coefficients b_j
        double[] cCoeff = new double[10]; //coefficients c_j
        double[] dCoeff = new double[10]; //coefficients d_j
        
        //Reading in file with data in it
        File inputFile = new File("Practice.txt");
        Scanner in = new Scanner(inputFile);
        
        //Getting and storing x values and corresponding function values from
        //text file and into arraylists
        int n = -1; //counter to count how many values n there are
        while(in.hasNext()){
            int i = Integer.parseInt(in.next()); //Iteration (will not be used)
            double x_i = Double.parseDouble(in.next());
            xValues.add(x_i);
            double f_xi = Double.parseDouble(in.next());
            funcValues.add(f_xi);
            n++;
        }
        
        
        //STEP 1: Calculating all the h_i values
        for(int i=0; i<n; i++){
            double h_i = xValues.get(i+1) - xValues.get(i);
            hValues.add(h_i); //storing h values into respective data structure
        }
        
        /**STEP 2,3, and 4: Calculating alpha values, L values, mu values, and z values
                            and storing them in their respective data structures */
        //Initializing values
        double l_i = 0; 
        double mu_i = 0;
        muValues.add(mu_i);
        double z_i = 0;
        zValues.add(z_i);
        
        for(int i=1; i<n; i++){
            double alpha = 3*((funcValues.get(i+1)*hValues.get(i-1)) - 
                    (funcValues.get(i)*(xValues.get(i+1)-xValues.get(i-1))) + 
                    (funcValues.get(i-1)*hValues.get(i)))/(hValues.get(i-1)*hValues.get(i)); 
            l_i = 2*(xValues.get(i+1) - xValues.get(i-1))-(hValues.get(i-1)*mu_i);
            mu_i = hValues.get(i)/l_i;
            muValues.add(mu_i);
            z_i = (alpha-(hValues.get(i-1)*z_i))/l_i;
            zValues.add(z_i);
        }
        
        //Setting L_8, z_8, c_8 to their respective values 
        l_i = 1;
        z_i = 0;
        zValues.add(z_i);
        double c_j = 0;
        cCoeff[n] = c_j;
        
        /** STEP 6: Calculating the coefficients b_j, c_j, and d_j */
        for(int j=n-1; j>=0; j--){
            double c_j_1 = c_j;
            c_j = zValues.get(j) - (muValues.get(j)*c_j_1);
            cCoeff[j] = c_j;
            double b_j = ((funcValues.get(j+1) - funcValues.get(j))/(hValues.get(j))) - ((hValues.get(j)*(c_j_1 + 2*(c_j)))/3.0);
            bCoeff[j]= b_j;
            double d_j = (c_j_1 - c_j)/(3*hValues.get(j));
            dCoeff[j]= d_j;         
        }
        
        //Printing out results in the right order to textfile and console
        PrintWriter out = new PrintWriter("NaturalCubicSplineCoeff.txt");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-30s%-30s%-30s%-30s%-30s%-30s\n", "j", "x_j", "a_j = f(x_j)", "b_j", "c_j", "d_j");
        out.printf("%-30s%-30s%-30s%-30s%-30s%-30s\n", "j", "x_j", "a_j = f(x_j)", "b_j", "c_j", "d_j");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        for(int i=0; i<=n; i++){
            if(i!=n){
              System.out.printf("%-30d%-30.1f%-30.1f%-30.3f%-30.3f%-30.3f\n", i, xValues.get(i), funcValues.get(i), bCoeff[i], cCoeff[i], dCoeff[i]);  
              out.printf("%-30d%-30.1f%-30.1f%-30.3f%-30.3f%-30.3f\n", i, xValues.get(i), funcValues.get(i), bCoeff[i], cCoeff[i], dCoeff[i]);
            }
            else{
              System.out.printf("%-30d%-30.1f%-30.1f\n", i, xValues.get(i), funcValues.get(i));
              out.printf("%-30d%-30.1f%-30.1f\n", i, xValues.get(i), funcValues.get(i));
            }
        }
        
        out.close();
        
        
        
        
    }
    
}

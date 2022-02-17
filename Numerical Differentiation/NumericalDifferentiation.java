
package numericaldifferentiation;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.function.Function;

/**
* 
* The program approximates the derivative of a given function (in this case, sin(x))  using four methods
* -Forward Difference Formula (FDF)
* -Backward Difference Formula (BDF)
* -Lagrange Basis (LB)
* -Undetermined Coefficients (UC)
* 
* Variable Key:
* func: Function you want to approximate the derivative of
* x: value you want to approximate the derivative at
* hVals: all the values of the step sizes you want to look at
* 
* NOTE: Method of Undetermined Coefficients approximates second derivative of function at x
*       All other methods in program approximate first derivative
* 
*
* CSC 2262 Programming project No 4
*
* @author Aliza Siddiqui
* @since 11/06/2020
*
*/

public class NumericalDifferentiation {

    
    public static void main(String[] args) throws FileNotFoundException {
        //Printing Header
        PrintWriter out = new PrintWriter("numericalDifferentiation.txt");
        System.out.printf("%-30s%-30s%-30s%-30s%-30s\n", "h","FDF", "BDF", "LB", "UC");
        out.printf("%-30s%-30s%-30s%-30s%-30s\n", "h","FDF", "BDF", "LB", "UC");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
        out.println("-------------------------------------------------------------------------------------------------------------------------------------");
        
        //Defining function, x value, and step sizes
        Function <Double, Double> func = x -> Math.sin(x);
        double x = (2.0/3)*Math.PI;
        double y = 2/3;
        double z = (2.0/3) * Math.PI;
        System.out.println("2/3 = " + y);
        System.out.println("2.0/3 * PI = " + z);
        double[] hVals = {0.1, 0.05, 0.025, 0.0125,0.00625};
        
        //Printing out Formula Data for all methods
        for(int i=0; i<hVals.length; i++){
            double result1 = forwardDifference(hVals[i],x , func);
            double result2 = backwardDifference(hVals[i],x, func);
            double result3 = lagrangeBasis(hVals[i], x, func);
            double result4 = undeterminedCoefficients(hVals[i], x, func);
            System.out.printf("%-30.10f%-30.10f%-30.10f%-30.10f%-30.10f\n", hVals[i],result1, result2, result3, result4);
            out.printf("%-30.10f%-30.10f%-30.10f%-30.10f%-30.10f\n", hVals[i],result1, result2, result3, result4);
            System.out.println("=====================================================================================================================================");
            out.println("=====================================================================================================================================");
        }
        
        out.close();
        
    }
    
    /**
      * This method approximates the first derivative of the function sin(x) 
      * using Forward Difference Formula
      * 
      * Formula:
      * f'(x) = f(x+h) - f(x) / h
      * 
      * @parameters:
      *   h: step value you want to use
      *   x: value you want to approximate derivative at
      *   func: function you want to approximate derivative of
      * @return approximation of derivative using Forward Difference Formula
      * @author Aliza Siddiqui
      * @since  11/06/2020
      */
    public static double forwardDifference(double h, double x, Function <Double, Double> func){
        double firstTerm = func.apply(x+h); 
        double secondTerm = func.apply(x);
        double derivative = (firstTerm - secondTerm)/h;
        return derivative;
    }
    
    /**
      * This method approximates the first derivative of the function sin(x) 
      * using Backward Difference Formula
      * 
      * Formula:
      * f'(x) = f(x) - f(x-h) / h
      * 
      * @parameters:
      *   h: step value you want to use
      *   x: value you want to approximate derivative at
      *   func: function you want to approximate derivative of
      * @return approximation of derivative using Backward Difference Formula
      * @author Aliza Siddiqui
      * @since  11/06/2020
      */
    public static double backwardDifference(double h, double x, Function <Double, Double> func){
        double firstTerm = func.apply(x);
        double secondTerm = func.apply(x-h);
        double derivative = (firstTerm - secondTerm)/h;
        return derivative;
    }
    
    /**
      * This method approximates the first derivative of the function sin(x) 
      * using the Lagrange Basis
      * 
      * Formula:
      * f'(x) = f(x+h) - f(x-h) / 2h
      * 
      * @parameters:
      *   h: step value you want to use
      *   x: value you want to approximate derivative at
      *   func: function you want to approximate derivative of
      * @return approximation of derivative using Lagrange Basis
      * @author Aliza Siddiqui
      * @since  11/06/2020
      */
    public static double lagrangeBasis(double h, double x, Function <Double, Double> func){
        double firstTerm = func.apply(x+h);
        double secondTerm = func.apply(x-h);
        double derivative = (firstTerm - secondTerm)/ (2*h);
        return derivative; 
    }
    
    /**
      * This method approximates the second derivative of the function sin(x) 
      * using Method of Undetermined Coefficients
      * 
      * Formula:
      * f'(x) = f(x+h)- 2f(x)+ f(x-h) / h*h
      * 
      * @parameters:
      *   h: step value you want to use
      *   x: value you want to approximate derivative at
      *   func: function you want to approximate derivative of
      * 
      * @return approximation of derivative using Forward Difference Formula
      * @author Aliza Siddiqui
      * @since  11/06/2020
      */
    public static double undeterminedCoefficients(double h, double x, Function <Double, Double> func){
        double firstTerm = func.apply(x+h);
        double secondTerm = 2*func.apply(x);
        double thirdTerm = func.apply(x-h);
        double second_derivative = (firstTerm - secondTerm + thirdTerm)/(h*h);
        return second_derivative;
    }
    
}

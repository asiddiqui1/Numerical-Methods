
package simpson.and.trapezoidal.rule;

import java.io.FileNotFoundException;
import java.io.PrintWriter;


public class SimpsonAndTrapezoidalRule {

/**
* 
* The program approximates the integral of the function ln(x) using two methods
* -Trapezoidal Method
* -Simpson's Method
* 
* Variable Key:
* n - number of subintervals
* h - length of each subinterval
* x_i - numerical integration node points
* a - starting index of interval
* b - ending index of interval
* 
*
* CSC 2262 Programming project No 3
*
* @author Aliza Siddiqui
* @since 10/24/2020
*
*/
    public static void main(String[] args) throws FileNotFoundException{
       PrintWriter out = new PrintWriter("trapezoidalAndSimpsonRule.txt");
       
       //Printing Header
       System.out.printf("%-30s%-30s%-30s%-30s%-30s\n", "Method","f(x)", "n", "[a,b]", "Sum");
       out.printf("%-30s%-30s%-30s%-30s%-30s\n", "Method", "f(x)", "n", "[a,b]", "Sum");
       System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
       out.println("--------------------------------------------------------------------------------------------------------------------------------");
       
       //Printing Trapezoidal Rule Data
       double trap = trapezoidalRule(1, 3, 512);
       System.out.printf("%-30s%-30s%-30d%-30s%-30f\n", "Trapezoidal Rule", "ln(x)", 512, "[1,3]", trap);
       out.printf("%-30s%-30s%-30d%-30s%-30f\n", "Trapezoidal Rule", "ln(x)", 512, "[1,3]", trap);
       System.out.println("================================================================================================================================");
       out.println("================================================================================================================================");
       
       //Printing Simpson's Rule Data
       double simp = simpsonRule(1,3, 512);
       System.out.printf("%-30s%-30s%-30d%-30s%-30f\n", "Simpson's Rule", "ln(x)", 512, "[1,3]", simp);
       out.printf("%-30s%-30s%-30d%-30s%-30f\n", "Simpson's Rule", "ln(x)", 512, "[1,3]", simp);
       
       out.close();
    }
    
    /**
      * This method approximates the integral of the function ln(x) 
      * using Simpson's Rule
      * 
      * @parameters:
      *   a : starting index of interval
      *   b : ending index of interval
      *   n : number of subintervals
      * @return approximation of integral using Simpson's method
      * @author Aliza Siddiqui
      * @since  10/24/2020
      */
    public static double simpsonRule(int a, int b, int n){
        double h = (b-a)/(n*1.0); //length of subinterval
        double sum = 0;
        for(int j=0; j<=n; j++){ //for j= 0,1...n
            double x_i = a + (j*h); //node point
            if((j!=0 && j != n) && (j%2 == 0)){ //if the node point is not the first or last and its even...
                double term = 2*Math.log(x_i);
                sum+= term;   
            }
            else if((j!=0 && j!=n) && (j%2 != 0)){ //if the node point is not first or last and its odd...
                double term = 4* Math.log(x_i);
                sum += term;
            } else{
                double term = Math.log(x_i);
                sum+= term;
            }
        }
        sum = sum * (h/3.0);
        return sum;
        
    }
    
    /**
      * This method approximates the integral of the function ln(x) 
      * using Trapezoidal Rule
      * 
      * @parameters:
      *   a : starting index of interval
      *   b : ending index of interval
      *   n : number of subintervals
      * @return approximation of integral using Trapezoidal method
      * @author Aliza Siddiqui
      * @since  10/24/2020
      */
    public static double trapezoidalRule(int a, int b, int n){
        double h = (b-a)/(n*1.0);
        double sum = 0;
        for(int j=0; j<=n; j++){
            double x_i = a + (j*h);
            if(j == 0 || j == n){ //if the node point is the first or last term...
                double term = (1/2.0) * Math.log(x_i);
                sum += term;
            }
             else{
                double term = Math.log(x_i);
                sum += term;
            }
       }
       sum = h * sum;
       return sum;  
    }
    
}

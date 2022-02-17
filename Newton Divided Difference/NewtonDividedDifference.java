
package newton.divided.difference;


import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;

/**
* This program will calculate the Newton's Divided Difference and Calculate
* Newton's Interpolation Polynomials of a certain degree
* 
* CSC 3102 Programming project No 3
*
* @author Aliza Siddiqui
* @since 9/30/2020
*
*/
public class NewtonDividedDifference {
    public static double[] divdiff = new double[7]; //Array that will store all the
                                                    // divided differences
    
    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter out = new PrintWriter("NewtonDividedDiff.txt");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-30s%-30s%-30s%-30s\n", "i", "x_i", "sin(x_i)", "D_i");
        out.printf("%-30s%-30s%-30s%-30s\n", "i", "x_i", "sin(x_i)", "D_i");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        double[] allValues = {0.0, 0.2, 0.4, 0.6, 0.8, 1.0, 1.2}; //known points
        System.out.printf("%-30d%-30.1f%-30f%-30s\n", 0, allValues[0], Math.sin(allValues[0]), Math.sin(allValues[0]) );
        out.printf("%-30d%-30.1f%-30f%-30s\n", 0, allValues[0], Math.sin(allValues[0]), Math.sin(allValues[0]));
        divdiff[0] = Math.sin(allValues[0]); //adding first divided difference: f(x_0)
        
        
        for (int i=1; i<allValues.length; i++){
            double[] newArray = Arrays.copyOfRange(allValues, 0, i+1); //sending in new array with appropriate values each time
                                                                       // {0.0, 0.2}, {0.0, 0.2, 0.4}, etc.
            double func = dividedDiff(newArray);
            divdiff[i] = func; //storing divided difference in array
            System.out.printf("%-30d%-30.1f%-30f%-30s\n", i, allValues[i], Math.sin(allValues[i]), func );
            out.printf("%-30d%-30.1f%-30f%-30s\n", i, allValues[i], Math.sin(allValues[i]), func );
        } 
        
        System.out.println();
        out.println();
        
        out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        out.printf("%-30s%-30s%-30s%-30s\n", "n", "Pn(0.1)", "Pn(0.3)", "Pn(0.5)");
        System.out.printf("%-30s%-30s%-30s%-30s\n", "n", "Pn(0.1)", "Pn(0.3)", "Pn(0.5)");
        out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        for(int i=1; i<=6; i++){
            double func1 = calculatePolynomial(i, 0.1, allValues); //Calculating Newton polynomial for appropriate value and degree
            double func2 = calculatePolynomial(i, 0.3, allValues);
            double func3 = calculatePolynomial(i, 0.5, allValues);
            System.out.printf("%-30d%-30.7f%-30.7f%-30.7f\n", i, func1, func2, func3 );
            out.printf("%-30d%-30.7f%-30.7f%-30.7f\n", i, func1, func2, func3 );
        }
        
        out.close();
       
        
        
    }
     /**
      * This method calculates the coefficient (x-x_0)(x-x_1)....
      * for each term
      *
      * @parameters:
      *   i: specific degree
      *   values: array of the known points
      *   value: value that you are approximating for in (0.1, 0.3, 0.5)
      * @return coefficient
      * @author Aliza Siddiqui
      * @since  9/30/2020
      */
    public static double calculateCoefficients(int i, double[] values, double value){
        double coeff = 1;
        for(int j=0; j<=i; j++){
            coeff *= (value - values[j]);
        }
        return coeff;
        
    }
    
    /**
      * This method calculates the polynomial value of a given degree for a given value
      *
      * @parameters:
      *   degree: degree of polynomial
      *   values: array of the known points
      *   value: value that you are approximating for in (0.1, 0.3, 0.5)
      * @return newton polynomial
      * @author Aliza Siddiqui
      * @since  9/30/2020
      */
    public static double calculatePolynomial(int degree, double value ,double[] values){
        double func = divdiff[0];
        for(int i=1; i<=degree; i++){
            func += (divdiff[i]*calculateCoefficients(i-1, values, value));
        }
        return func;
    }
    
     /**
      * This method calculates the divided difference recursively
      * given an array of values that represents [x_0, x_1.....x_n] in 
      * f[x_0, x_1.....x_n]
      *
      * @parameters:
      *   values: array of the known points
      * @return divided difference
      * @author Aliza Siddiqui
      * @since  9/30/2020
      */
    public static double dividedDiff(double[] values){
        if(values.length == 2){ //base case: if its just a divided difference of two values f[x1, x2]
            double func = (Math.sin(values[1]) - Math.sin(values[0]))/(values[1] - values[0]);
            return func;
        }
        double[] listOne = fillArray(values, 0, values.length-2);
        double[] listTwo = fillArray(values, 1, values.length-1);
        double func = (dividedDiff(listTwo) - dividedDiff(listOne))/(values[values.length-1] - values[0]);
        return func;
    }
    
     /**
      * This method returns a new array with values filled from the known point array
      * from startPos to endPos
      * !!primarily used for creating two arrays: [x_0....x_n-1] and [x_1.....x_n]!!
      *
      * @parameters:
      *   values: array of the known points
      *   startPos: starting position
      *   endPos: ending position
      * @return newly filled array
      * @author Aliza Siddiqui
      * @since  9/30/2020
      */
    public static double[] fillArray(double[] values, int startPos, int endPos ){
        double[] list = new double[values.length-1];
        int j = 0;
        for(int i = startPos; i<= endPos; i++){
            list[j] = values[i];
            j++;
        }
        return list;
    }
    
}

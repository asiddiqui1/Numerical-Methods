
package eulermethod;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class EulerMethod {
/**
* 
* The program utilizes the Euler Method to solve any ordinary differential equation
* 
* Variable Key:
* ArrayList<String> equationTerms: This will store the terms of the differential equation for processing
* startingVal: This will store the starting x value for the iterations
* endingVal: This will store the ending x value for the iterations
* initialConX: This will store the x value for the initial condition. At what x is the initial condition being given?
* initialCond: This will store the initial condition
* h: This will store the step size for the iterations
*
* CSC 2262 Programming project No 8
*
* @author Aliza Siddiqui
* @since 11/30/2020
*
*/ 
    
    public static void main(String[] args) throws FileNotFoundException {
        
        //Printing out Headings and Initializing Output Text File
        PrintWriter out = new PrintWriter("EulerMethod.txt");
        System.out.printf("%-15s%-15s%-15s\n", "h", "x", "yh(x)");  
        out.printf("%-15s%-15s%-15s\n", "h", "x", "yh(x)");
        
        //Reading in Text File and initializing necessary data structures
        File inputFile = new File("EulerParameters.txt");
        Scanner in = new Scanner(inputFile);
        ArrayList<String> equationTerms = new ArrayList<>();
        
        //Adding equation terms to an arraylist
        String[] line = in.nextLine().trim().split(" ");
        for(int i=0; i<line.length; i++){
            equationTerms.add(line[i]);
        }
        
        //Gets the interval for x
        line = in.nextLine().trim().split(" ");
        double startingVal = Double.parseDouble(line[0]);
        double endingVal = Double.parseDouble(line[1]);
        
        //Gets the initial condition
        double initialConX = in.nextDouble();
        double initialCond = in.nextDouble();
        
        //Gets the step size
        double h = in.nextDouble();
        
        eulerMethodProcessing(out, equationTerms, startingVal, endingVal, initialConX, initialCond, h);
        
        out.close();
      
        
        
        
        
    }
    /**
      * This is the main method that will perform the Euler Method
      * It will iterate through all x values (incrementing by stepsize h) and calculate the y value
      * for each Euler Method iteration
      * 
      * Formula:
      * y_n+1 = y_n + hf(x_n, y_n)
      * 
      * @parameters:
      *   out: The output text file
      *   equationTerms: arraylist that stores the terms of the differential equation
      *   startingVal: The starting x value for the iterations
      *   endingVal: the ending x value for the iterations
      *   initialConX:  the x value for the initial condition. At what x is the initial condition being given?
      *   initialCond:  the initial condition
      *   h:  the step size for the iterations
      *   
      * @return nothing
      * @author Aliza Siddiqui
      * @since  11/30/2020
      */
    public static void eulerMethodProcessing(PrintWriter out, ArrayList<String> equationTerms, double startingVal, double endingVal, double initialConX, double initialCond, double h){
       double x = startingVal;
       double y = initialCond;
       double y_prev = y;
       while(x <= endingVal){
          double temp = functionVal(x, y_prev, equationTerms);
          y = y_prev + (h*temp);
          System.out.printf("%-15.2f%-15.2f%-15.4f\n", h, x, y);
          out.printf("%-15.2f%-15.2f%-15.4f\n", h, x, y);
          y_prev = y;
          x += h;
       }
        
    }
    
    
    /**
      * This method will calculate the differential equation value for a specific x and y
      * 
      * Formula:
      * y' = (what it will solve)
      * 
      * @parameters:
      *   x, y:specific x and y values that will be plugged into the function
      *   equationTerms: arraylist that stores the terms of the differential equation
      *   
      * @return function value for the specific x and y value
      * @author Aliza Siddiqui
      * @since  11/30/2020
      */
    public static double functionVal(double x, double y, ArrayList<String> equationTerms){
        double sum = 0;
        for(int i=0; i<equationTerms.size(); i++){
            String term = equationTerms.get(i);
            if(!term.equals("y'")){ //Look at everything besides the differential term...
              double termVal = 1; //temporary variable to be used for calculations
              for(int j=0; j<term.length(); j++){ //Looking at the individual characters of the term
                  if(term.charAt(j) == 'x'){ //if that character is an x..
                     termVal *= x;  //multiply by the x value
                  }
                  else if(term.charAt(j) == 'y'){
                     termVal*=y;
                  }
                  else if(term.charAt(j) == '-'){
                     termVal*= -1;
                  }
                  else if(term.charAt(j) == '/'){
                      if(term.charAt(j+1) == 'x'){
                         termVal /= x; 
                      }
                      if(term.charAt(j+1) == 'y'){
                          termVal /= y;
                      }
                      j++;
                  }
                  else{ //the character is probably a number and should be multiplied directly
                     termVal *= Double.parseDouble(Character.toString(term.charAt(j)));
                  }
                   
              }
              sum += termVal; //add that term result to total sum
            }
        }
        return sum;
    }
    
}

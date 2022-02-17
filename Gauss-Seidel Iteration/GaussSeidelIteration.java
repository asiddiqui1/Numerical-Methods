
package gauss.seidel.iteration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;



public class GaussSeidelIteration {
/**
* 
* The program solves a system of n equations of n unknowns using the Gauss Seidel Iteration method
* 
* Variable Key:
* ArrayList<String[] matrices: This will store the coefficients of the system (both for A matrix and b vector) for later printing
* double[] bVector: Array to store the elements of the b vector (in double datatype)
* initialValues: This will be the main dynamic array whose values will change as the iterations proceed. 
*                It starts off as being the zero vector
* normDiff: This will store the difference between the Euclidean Norms of the previous estimate and current estimate of x vector
* prevNorm: This will store the Euclidean norm of the previous estimate
* norm: This will store the norm of the current estimate
* epsilon: This will determine the maximum difference the euclidean norms must have; determines convergence
* k: This will count the number of iterations performed
*
* CSC 2262 Programming project No 7
*
* @author Aliza Siddiqui
* @since 11/20/2020
*
*/ 
    public static void main(String[] args) throws FileNotFoundException {
        //Reading in Text File and initializing necessary data structures
        PrintWriter out = new PrintWriter("GaussSeidelIteration.txt");
        File inputFile = new File("GaussSeidelEquations.txt");
        Scanner in = new Scanner(inputFile);
        ArrayList<String[]> matrices = new ArrayList<>();
        
        
        //Reading in n and equations
        int n = Integer.parseInt(in.nextLine());
        double[] bVector = new double[n]; //matrix to store the b vector elements and print them out later
        ArrayList<double[]> functions = new ArrayList<>();
        System.out.println("This is a system of " + n + " equations with " + n + " unknowns");
        out.println("This is a system of " + n + " equations with " + n + " unknowns");
        for(int i=0; i<n; i++){
          String[] line = in.nextLine().trim().split(" "); 
          matrices.add(line);
          bVector[i] = Double.parseDouble(line[line.length-1]);
          createEquations(line, i+1, functions);
        }
        
        //Printing out the Header
        System.out.printf("%-30s", "k");
        out.printf("%-30s", "k");
        for(int i=0; i<n; i++){
            System.out.printf("%-30.4s", "x " + i);
            out.printf("%-30.4s", "x " + i);
        } 
        System.out.printf("%-30s\n", "diff"); 
        out.printf("%-30s\n", "diff");
        System.out.println("================================================================================================================================================================================");
        out.println("================================================================================================================================================================================");
        
        //Make the initial x vector into an array of double values
        String[] initialVals = in.nextLine().trim().split(" "); //initial x vector
        double[] initialValues = new double[initialVals.length];
        for(int i=0; i<initialVals.length; i++){
            initialValues[i] = Double.parseDouble(initialVals[i]);
        }
        
        //Iterations 
        double normDiff = 3; //starting value of difference; chosen randomly
        double prevNorm = 0;
        double epsilon = 0.0001; 
        int k=0; //Iteration number
        System.out.printf("%-30d", k);
        out.printf("%-30d", k);
        printArray(initialValues, out);
        System.out.print("\n");
        out.print("\n");
        while(Math.abs(normDiff)> epsilon){
           k++;
           System.out.printf("%-30d", k);
           out.printf("%-30d", k);
           performIteration(functions, initialValues); 
           double norm = calculateEuclideanNorm(initialValues);
           normDiff = prevNorm - norm;
           printArray(initialValues, out);
           System.out.printf("%-30.4f\n", normDiff);
           out.printf("%-30.4f\n", normDiff);
           prevNorm = norm;
        }
        
        //Printing out Coefficient, x, and b matrices
        System.out.println();
        out.println();
        System.out.println("A = ");
        out.println("A = ");
        for(int i=0; i< matrices.size(); i++){
            String[] line = matrices.get(i);
            for(int j=0; j<line.length-1; j++){
                System.out.printf("| %.3f ", Double.parseDouble(line[j]) );
                out.printf("| %.3f ", Double.parseDouble(line[j]) );
            }
            System.out.println("\n");
            out.println("\n");
        }
        System.out.println();
        out.println();
        
        System.out.println("x = ");
        out.println("x = ");
        for(int i=0; i<initialValues.length; i++){
            System.out.printf("| %.3f ", initialValues[i]);
            out.printf("| %.3f ", initialValues[i]);
        }
        System.out.println("\n");
        out.println("\n");
        
        System.out.println("b = ");
        out.println("b = ");
        for(int i=0; i<bVector.length; i++){
           System.out.printf("| %.3f ", bVector[i]); 
           out.printf("| %.3f ", bVector[i]);
        }
        System.out.println("\n");
        out.println("\n");
       
        
        out.close();
        

        
        
        
        
        
        
        
        
        
    }
    
    /**
      * This method calculates the Euclidean Norm of a vector (in this case, the x vector)
      * 
      * Formula:
      * [Sum_j=1 ^n |x_j|^2] ^(1/2)
      * 
      * @parameters:
      *   initialValues: The x vector
      * @return The Euclidean Norm of the x vector
      * @author Aliza Siddiqui
      * @since  11/20/2020
      */
    public static double calculateEuclideanNorm(double[] initialValues){
        double sum = 0;
        for(int i=0; i<initialValues.length; i++){
            sum+= (initialValues[i]*initialValues[i]);
        }
        double norm = Math.sqrt(sum);
        return norm;
    }
    
    /**
      * This method will perform one iteration of the Gauss Seidel Method, generating
      * new values for each unknown in the system of equations
      * @parameters:
      *   initialValues: The x vector
      *   functions: array list storing the coefficients (each equation of the system)
      * @return nothing
      * @author Aliza Siddiqui
      * @since  11/20/2020
      */
    public static void performIteration(ArrayList<double[]> functions, double[] initialValues){
        for(int i=0; i<functions.size(); i++){
            double[] func = functions.get(i);
            double new_x_val = func[functions.size()]; //initializes sum to just b value for corresponding equation
            for(int j=0; j<func.length-1; j++){
               if(j != i){
                 new_x_val += func[j]*initialValues[j]; 
 
               }
            }
            new_x_val *= func[i]; //multiplies reciprocal of coefficent of variable you are finding to whole sum
            initialValues[i] = new_x_val; //updates the x value to be used in the following equations
            
        }
    }
    
    /**
      * This method prints out a given one dimensional array

      * @parameters:
      *   func: given array
      *   out: Textfile we are printing out to
      * @return nothing
      * @author Aliza Siddiqui
      * @since  11/20/2020
      */
    public static void printArray(double[] func, PrintWriter out){
        for(int i=0; i<func.length; i++){
            System.out.printf("%-29.4f", func[i]);;
            out.printf("%-29.4f", func[i]);
        }
    }
    
    /**
      * This method solves for the unknown variable for its respective equation
      * i.e. For first equation, given the line of coefficients, it will solve the equation for x_1
      *      4x_1 -x_2 -x_4 = 0
      *      x_1 = (1/4)[x_2 + x_4]
      * Then it will store them into the functions Array list
      * 
      * @parameters:
      *   line: Line of coefficients of a certain equation taken in from the text file
      *   functions: array list storing the coefficients (each equation of the system)
      * @return nothing
      * @author Aliza Siddiqui
      * @since  11/20/2020
      */
    public static void createEquations(String[] line, int var, ArrayList<double[]> functions){
        
        double[] coefficients = new double[line.length]; //will represent the equation
        for(int i=0; i<line.length; i++){
            if(i==(var-1)){ //the first argument must be the reciprocal of the first coefficient;
                      //equivalent of multiplying the coefficient of the variable you are solving for 
              
                coefficients[i] = (1.0/Double.parseDouble(line[i]));
            } else if(i== line.length-1){
                
                coefficients[i] = Double.parseDouble(line[i]); //the b vector coefficients must be added as is
            }
            else{
               
                if(Double.parseDouble(line[i]) != 0.0){
                   coefficients[i] = -1.0*Double.parseDouble(line[i]); //All other coefficients will reverse signs when brought to the other side 
                } else{
                   coefficients[i] = Double.parseDouble(line[i]);
                }
                
            }
        }
        functions.add(coefficients);
        
    }
    
}

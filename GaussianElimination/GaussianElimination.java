
package gaussian.elimination;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;


public class GaussianElimination {
/**
* 
* The program will perform Gaussian Elimination on A coefficient matrix (A) and its corresponding 
* augmented portion (b) using Pivoting
* It will solve Ax = b

* 
* Variable Key:
* n - the dimensions of the A matrix (n x n) (Assuming it is a square matrix)
* A - coefficient matrix
* b - B vector 
* 
*
* CSC 2262 Programming project No 4
*
* @author Aliza Siddiqui
* @since 11/13/2020
*
*/
    
    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter out = new PrintWriter("GaussianEliminationResults.txt");
        System.out.println("Ax = b");
        out.println("Ax = b");
        
        //Reading in Text File with matrix A and b
        Scanner sc = new Scanner(new BufferedReader(new FileReader("GaussianElimination.txt")));
        
        int n = Integer.parseInt(sc.nextLine()); //First Argument:size of the A matrix
        
        //Filling Matrix A with its elements
        double [][] A = new double[n][n];
        double[][] b = new double[n][n];
        for (int i=0; i<A.length; i++) {
           String[] line = sc.nextLine().trim().split(" ");
           for (int j=0; j<line.length; j++) {
              A[i][j] = Double.parseDouble(line[j]);
           }
         
        }
        
        //Printing out the A matrix 
        System.out.println();
        out.println();
        System.out.println("A =");
        out.println("A =");
        printOutArray(A, out);
        System.out.println();
        out.println();
        
        
        //Filling matrix B with its elements
        sc.nextLine(); //next white space line
        String[] lineB = sc.nextLine().trim().split(" ");
        for(int i=0; i<lineB.length; i++){
            b[i][0] = Double.parseDouble(lineB[i]);
        }
        
        //Printing out the b vector 
        System.out.println("b =");
        out.println("b =");
        for(int i=0; i<n; i++){
            System.out.printf("| %3.3f |\n", b[i][0]);
            out.printf("| %3.3f |\n", b[i][0]);
        }
        
        //Pivoting and Gaussian Elimination going down (Getting upper triangular matrix)
        for(int i=0; i<n; i++){
            pivoting(i,i, A, b);
            eliminateColD(i,i, A, b);
        }
        
        //Pivoting and Gaussian Elimination going up (Getting diagonal elements only)
        for(int i=n-1; i>=0; i--){
            if(i!= 0){
                pivoting(i,i, A, b);
                eliminateColU(i,i, A, b);
            }
        }
        
        //Solving the system (Since diagonal elements are not one)
        double[] x = new double[n]; //x vector 
        for(int i=0; i<n; i++){
            x[i] = b[i][0]/A[i][i];
        }
        
        //Printing out the x vector 
        System.out.println();
        out.println();
        System.out.println("x =");
        out.println("x =");
        for(int i=0; i<n; i++){
           System.out.printf("| %3.1f |\n", x[i]);
           out.printf("| %3.1f |\n", x[i]);
        }
        
        out.close();
        
    }
    
     /**
      * This method performs Gaussian Elimination going up, zeroing all the elements
      * above diagonal element being inspected
      * 
      * |x  0  0|
      * |0  y  0|
      * |0  0  z|
      * 
      * @parameters:
      *   colNum: column number of element being inspected
      *   rowNum: row number of element being inspected
      *   A: Coefficient Matrix
      *   b: "Augmented part" of matrix
      * @return nothing
      * @author Aliza Siddiqui
      * @since  11/13/2020
      */
    public static void eliminateColU(int colNum, int rowNum, double[][]A, double[][]b){
       for(int i=rowNum-1; i>=0; i--){
           double m = -1*(A[i][colNum])/(A[rowNum][colNum]); //multiplier
           for(int j=colNum; j>=0; j--){
               double temp = A[rowNum][j]; //copying row elements into new variable so they won't change while multiplying
               A[i][j] = A[i][j] + (m*temp);
           }
           //changing corresponding elements in b vector
           double temp = b[rowNum][0]; 
           b[i][0] = b[i][0] + (m*temp);
       }
    }
   
    /**
      * This method performs Gaussian Elimination going down, zeroing all the elements
      * below diagonal element being inspected
      * 
      * |x  l  m|
      * |0  y  n|
      * |0  0  z|
      * 
      * @parameters:
      *   colNum: column number of element being inspected
      *   rowNum: row number of element being inspected
      *   A: Coefficient Matrix
      *   b: "Augmented part" of matrix
      * @return nothing
      * @author Aliza Siddiqui
      * @since  11/13/2020
      */
    public static void eliminateColD(int colNum, int rowNum, double[][]A, double[][]b){
       for(int i=rowNum+1; i<A.length; i++){ //traverse the rows
           double m = -1*(A[i][colNum])/ (A[rowNum][colNum]); //multiplier
           for(int j=colNum; j<A.length; j++){
               double temp = A[rowNum][j]; //copying row elements into new variable so they won't change
               A[i][j] = A[i][j] + (m*temp);
           }
           //changing corresponding elements in b vector
           double temp = b[rowNum][0];
           b[i][0] = b[i][0] + (m*temp);
       }   
    }
    
    /**
      * This method performs pivoting of rows to prevent errors caused by floating point 
      * arithmetic. 
      * It will look through the entire column of the element being inspected and swap the inspected
      * row with another row whose leading element is larger than the element being inspected (if one exists)
      * 
      * @parameters:
      *   colNum: column number of element being inspected
      *   rowNum: row number of element being inspected
      *   A: Coefficient Matrix
      *   b: "Augmented part" of matrix
      * @return nothing
      * @author Aliza Siddiqui
      * @since  11/13/2020
      */
    public static void pivoting(int colNum, int rowNum, double[][] A, double[][] b){
        //Finding the row with a large first element 
        double maxNum = A[rowNum][colNum];
        int maxNumRow = rowNum;
        for(int i=rowNum; i<A[0].length; i++){
            if(A[i][colNum] > maxNum){
                maxNum = A[i][colNum];
                maxNumRow = i;
            }
        }
        
        //swapping the two rows
        if(maxNumRow != rowNum){
          for(int i=colNum; i<A.length; i++){
            double temp = A[rowNum][i]; //rowNum is the specific row the pivot element you are working with is in
            A[rowNum][i] = A[maxNumRow][i];
            A[maxNumRow][i] = temp;
          }
          //swapping corresponding elements in b vector...
          double temp = b[rowNum][0];
          b[rowNum][0] = b[maxNumRow][0];
          b[maxNumRow][0] = temp;
          
        }
        
        
 
    }
    
    /**
      * This method will print out a 2D array in proper form
      * 
      * |x  0  0|
      * |0  y  0|
      * |0  0  z|
      * 
      * @parameters:
      *   A: 2D Matrix
      * @return nothing
      * @author Aliza Siddiqui
      * @since  11/13/2020
      */
    public static void printOutArray(double [][] A, PrintWriter out){
        for(int i=0; i<A.length; i++){
            for(int j=0; j<A.length; j++){
                System.out.printf("| %.3f  ", A[i][j]);
                out.printf("| %.3f  ", A[i][j]);
            }
            System.out.println("\n");
            out.println("\n");
        }
        
    }
    
}

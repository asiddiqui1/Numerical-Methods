/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newton.s.method;

/**
 *
 * @author aliza
 */
public class NewtonSMethod {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        double x = 2;
        for(int i=0; i<5; i++){
           double f = Math.cos(x) - x;
           double df = -Math.sin(x) - 1;
           double x_n = x - (f/df);
           x = x_n;
           System.out.println(x_n);
        }
    }
    
}

/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package pratica03;

import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 *
 * @author aluno
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Running tests... Please wait...\n");
        
        //set a output file
        PrintStream fileStream = new PrintStream("../../output/output.txt");
        //set System.out.println to be printed on the file
        System.setOut(fileStream);

        //run tests
        Tests test = new Tests();        
        test.runTests();
    }
}

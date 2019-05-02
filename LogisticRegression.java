// logistic regression prediction in java

/*
* For tokenized sentence, create a TF vector for that sentence
 * Based on extracted vocab

* Take dot product of TF vector and logit model coeficients
 * Add intercept

* Pass into logit function
 * Multiply the textrank score by this probability

*/
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class LogisticRegression {

    // private ArrayList<String> vocabulary;
    // private ArrayList<Double> coefficients;
    // private Double intercept;
    public ArrayList<String> vocabulary;
    public ArrayList<String> coefficients;

    /**
     * Reads in the text file containing the vocabulary
     */
    public void setVocabulary() { //ArrayList<String>
        // String vocab;
        try {
            File fileName = new File("resources/LRVocabulary.txt");
            FileReader fileReader = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(fileReader);

            // ArrayList<String> vocab;
            String line = null;

            while ((line = reader.readLine()) != null) {
                this.vocabulary.add(line);
                // System.out.println(line);
                System.out.println("Just added a vocabulary word");
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Could not read in the file");
            e.printStackTrace();
        }
    }

    /**
     * Reads in the text file containing the coefficients
     */
    public void setCoefficients() { //ArrayList<String>
        // String vocab;
        try {
            File fileName = new File("resources/LRCoefficients.txt");
            FileReader fileReader = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(fileReader);

            // ArrayList<String> vocab;
            String line = null;

            while ((line = reader.readLine()) != null) {
                this.coefficients.add(line);
                // System.out.println(line);
                System.out.println("Just added a coefficient");
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Could not read in the file");
            e.printStackTrace();
        }
    }

    public ArrayList<String> getVocabulary() {
        return this.vocabulary;
    }

    public ArrayList<String> getCoefficients() {
        return this.coefficients;
    }

    /**
     * Takes a tokenized sentence as input
     * @return a vector or lenth 2000 representing the word counts of the input sentence
     */
    // private ArrayList<Int> vectorize() {

    // }

    // private Double calculateScore() {

    // }

    // private Double logit() {

    // }

    public void printVocab() {
        for (String word: vocabulary) {
            System.out.println(word);
        }
    }

    public void tester() {
        this.vocabulary.add("test");
    }

    public static void main(String[] args) {
        LogisticRegression model = new LogisticRegression();
        // model.setVocabulary();
        // model.setCoefficients();
        model.tester();
        model.printVocab();
        
        
        // vectorize()
        // calculateScore()
        // logit()
    }

}
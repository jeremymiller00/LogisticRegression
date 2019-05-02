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
    ArrayList<String> vocabulary = new ArrayList<String>();
    ArrayList<Double> coefficients = new ArrayList<Double>();

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
                vocabulary.add(line);
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
                Double c = Double.parseDouble(line)
                coefficients.add(c);
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

    public ArrayList<Double> getCoefficients() {
        return this.coefficients;
    }

    /**
     * Takes a tokenized sentence as input
     * @return a vector representing the part-of-speech counts of the input sentence
     */
    // private ArrayList<Int> vectorize() {

    // }

    /**
     * @param sentenceVector: Sentence represented as part-of-speech vector: ArrayList<Double>
     * @param coefficients: ArrayList<Double> of logistic regression coefficients
     * @param intercept: intercept term of logistic regression model
     * @return logistic regression score of sentence vector: probability that the sentence is a "body sentence"
     */
    private Double calculateLRScore(ArrayList<Double> sentenceVector, ArrayList<Double>coefficients, Double intercept) {
        double score = 0.0;
        score = score + intercept
        for (int i = 0, i < sentenceVector.size; i++)
            score = score + (sentenceVector[i] * coefficients[i]);
        return score
    }

    // private Double logit() {

    // }

    public void printVocab() {
        for (String word: vocabulary) {
            System.out.println(word);
        }
    }

    public static void main(String[] args) {
        LogisticRegression model = new LogisticRegression();
        model.setVocabulary();
        model.setCoefficients();
        // model.printVocab();
        
        
        // vectorize()
        // calculateScore()
        // logit()
    }

}
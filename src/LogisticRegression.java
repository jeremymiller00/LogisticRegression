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
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.lang.Math.*;
//import edu.stanford.nlp.coref.data.CorefChain;
import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.ie.util.*;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.semgraph.*;
import edu.stanford.nlp.trees.*;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;


public class LogisticRegression {

    // private ArrayList<String> vocabulary;
    // private ArrayList<Double> coefficients;
    // private Double intercept;
    public static ArrayList<String> partsOfSpeech = new ArrayList<String>();
    public static ArrayList<Double> coefficients = new ArrayList<Double>();
    public static Double intercept = 0.0;

    public ArrayList<String> getPartsOfSpeech() {
        return this.partsOfSpeech;
    }

    public ArrayList<Double> getCoefficients() {
        return this.coefficients;
    }

    public ArrayList<Double> getIntercept() {
        return this.intercept;
    }
    
    /**
     * Reads in the text file containing the coefficients
     */
    public static void setCoefficients() { //ArrayList<String>
        try {
            File fileName = new File("resources/LRCoefficients.txt");
            FileReader fileReader = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(fileReader);

            String line = null;

            while ((line = reader.readLine()) != null) {
                Double c = Double.parseDouble(line);
                coefficients.add(c);
            }
            System.out.println("Set model coefficients.");
            reader.close();
        } catch (Exception e) {
            System.out.println("Could not read in the file");
            e.printStackTrace();
        }
    }

    /**
     * Reads in the intercept value of the logistic regression model
     */
    public static void setIntercept() {
    	try {
    		File fileName = new File("resources/LRIntercept.txt");
    		FileReader fileReader = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(fileReader);

            String line = null;
            
            // need to make sure there is only one value!!
            line = reader.readLine();
            Double c = Double.parseDouble(line);
            intercept = intercept + c;
            System.out.println("Set model intercept");
            reader.close();            
            
    	} catch (Exception e) {
            System.out.println("Could not read in the file");
            e.printStackTrace();
    	}
    }
    /**
     * Read in parts of speech from a text file
     */
    public static void setPartsOfSpeech() { //ArrayList<String>
        try {
            File fileName = new File("resources/LRPartsOfSpeech.txt");
            FileReader fileReader = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(fileReader);

            String line = null;

            while ((line = reader.readLine()) != null) {
                partsOfSpeech.add(line);
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Could not read in the file");
            e.printStackTrace();
        }
    }
    
        
    /**
     * apply POS tagging to tokenized sentence WITH STOP WORDS INTACT
     * @param sentence
     * @return sentence tags
     */
    public List<String> setSentenceTags(String sentence) { //CoreSentence
//    	MaxentTagger tagger = new MaxentTagger("english-caseless-left3words-distsim.tagger");
    	List<String> posTags = sentence.posTags();
    	// return just the tags
    	return posTags;
    	}
    
    /**
     * Takes a tagged, tokenized sentence as input
     * @return a vector representing the part-of-speech counts of the input sentence
     * The sequence of features is vital here, but be intact from model on Databricks
     * https://cc-dev.cloud.databricks.com/?o=0#notebook/714597/command/729827
     */
     public static ArrayList<Integer> vectorizeSentence(List<String> posTags, ArrayList<String> partsOfSpeech) {
    	 ArrayList<Integer> tagVector = new ArrayList<Integer>();
    	 for (String tag : partsOfSpeech) {
    		 int value = 0;
    		 for (String t : posTags) {
    			 if (tag.equals(t)) {
    				 value ++;
    			 }
    		 }
    		 tagVector.add(value);
    	 }
    	 return tagVector;    	 
     }

    /**
     * @param sentenceVector: Sentence represented as part-of-speech vector: ArrayList<Double>
     * @param coefficients: ArrayList<Double> of logistic regression coefficients
     * @param intercept: intercept term of logistic regression model
     * @return logistic regression score of sentence vector: probability that the sentence is a "body sentence"
     */
    public static Double calculateLRScore(ArrayList<Double> sentenceVector, ArrayList<Double>coefficients, Double intercept) {
        double score = 0.0;
        score = score + intercept;
        for (int i = 0; i < sentenceVector.size(); i++) {
            score = score + (sentenceVector.get(i) * coefficients.get(i));
        }
        return score;
    }

    /**
     * 
     * @param score
     * @return probability that the sentence is a "body" sentence
     */
    public static Double logit(Double score) { 
        return 1 / (1 + Math.exp(-1 * score));
     }


    public static void main(String[] args) {
        LogisticRegression model = new LogisticRegression();
        Properties props = new Properties();
        props.setProperty("annotators", "pos");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        CoreDocument document = new CoreDocument(sentence); // not sure I need this
        pipeline.annotate(document); //
        
        model.setCoefficients();
        model.setPartsOfSpeech();
        model.setIntercept();
        // for each sentence
        // vectorizeSentence()
        // calculateScore()
        // logit()
    }

}
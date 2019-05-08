import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;


public class LogisticRegressionTest extends LogisticRegression {

	@Test
	public void testSetCoefficients() {
		System.out.println("Testing setCoefficients...");
		LogisticRegression.setCoefficients();
		assertNotNull("Coefficients not set correctly", LogisticRegression.coefficients);
	}

	@Test
	public void testSetIntercept() {
		System.out.println("Testing setIntercept...");
		LogisticRegression.setIntercept();
		assertNotEquals("Intercept not set correctly", 0.0, LogisticRegression.intercept, 0.01);
	}
	
	@Test
	public void testSetPartsOfSpeech() {
		System.out.println("Testing setPartsOfSpeech...");
		LogisticRegression.setPartsOfSpeech();
		assertNotNull("Parts of Speech not set correctly", LogisticRegression.partsOfSpeech);
		assertEquals("Parts of Speech not correct length", 36.0, LogisticRegression.partsOfSpeech.size(), 0.0);
	}

//	@Test
//	public void testGetCoefficients() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testCalculateLRScore() {
		System.out.println("Testing CalculateLRScore...");
        final ArrayList<Double> sentenceVector = new ArrayList<Double>();
        sentenceVector.add(1.0);
        sentenceVector.add(2.0);
        sentenceVector.add(3.0);
        
        final ArrayList<Double> coefficients = new ArrayList<Double>();
        coefficients.add(4.0);
        coefficients.add(5.0);
        coefficients.add(6.0);
        
        final Double intercept = 1.0;

        final Double expected = 33.0;
        final Double actual = LogisticRegression.calculateLRScore(sentenceVector, coefficients, intercept);
        assertEquals("Score calculation incorrect", expected, actual, 0.0); //
    }

	@Test
    public void testLogit() {
		System.out.println("Testing Logit...");
        final Double exponent1 = 0.0;
        final double expected1 = 0.5;
        final Double actual1 = LogisticRegression.logit(exponent1);
        assertEquals(expected1, actual1, 0.0);

        final Double exponent2 = 1.0;
        final Double expected2 = Math.exp(1) / (1 + Math.exp(1));
        final Double actual2 = LogisticRegression.logit(exponent2);
        assertEquals("Probability calculation incorrect", expected2, actual2, 0.0);
    }
	
	@Test
	public void testVectorizeSentence() {
		System.out.println("Testing vectorize sentence...");
		List<String> posTags = new ArrayList<String>();	
		posTags.add("CC");
		posTags.add("DT");
		posTags.add("JJ");
		posTags.add("MD");
		posTags.add("JJ");
		
		final ArrayList<Integer> tagVector = LogisticRegression.vectorizeSentence(posTags, LogisticRegression.partsOfSpeech);
		assertEquals("Tags vectorization not successful", 36.0, tagVector.size(), 0.0);
		int sum = 0;
		for (Integer i : tagVector) {
			sum = sum + i;
		}
		assertEquals("Tag vecgtorization incorrect", 5, sum, 0);
	}
}

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import org.junit.Test;


public class LogisticRegressionTest extends LogisticRegression {

	@Test
//	public void testSetCoefficients() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetCoefficients() {
//		fail("Not yet implemented");
//	}

	public void testCalculateLRScore() {
        final ArrayList<Double> sentenceVector = new ArrayList<Double>();
        sentenceVector.add(1.0);
        sentenceVector.add(2.0);
        sentenceVector.add(3.0);
        
        final ArrayList<Double> coefficients = new ArrayList<Double>();
        coefficients.add(4.0);
        coefficients.add(5.0);
        coefficients.add(6.0);
        
        final Double intercept = 1.0;

        final Double expected = 17.0;
        final Double actual = LogisticRegression.calculateLRScore(sentenceVector, coefficients, intercept);
        assertEquals(actual, expected, 0.0);        
    }

    public void testLogit() {
        final Double exponent1 = 0.0;
        final double expected1 = 0.5;
        final Double actual1 = LogisticRegression.logit(exponent1);
        assertEquals(expected1, actual1, 0.0);

        final Double exponent2 = 1.0;
        final Double expected2 = Math.exp(1) / (1 + Math.exp(1));
        final Double actual2 = LogisticRegression.logit(exponent2);
        assertEquals(expected2, actual2, 0.0);

    }
}

import java.lang.Math.*;

public class LogisticRegressionTests {

    public void testCalculateLRScore() {
        final ArrayList<Double> sentenceVector = new ArrayList<Double>(1.0,2.0,3.0);
        final ArrayList<Double> coefficients = new ArrayList<Double>(4.0,5.0,6.0);
        final Double intercept = 1.0;

        final Double expected = 17.0;
        final Double actual = LogisticRegression.testCalculateLRScore(sentenceVector, coefficients, intercept);

        Assert.assertEquals(actual, expected);        
    }

    public void testLogit() {
        final Double exponent1 = 0.0;
        final double expected1 = 0.5;
        final Double actual1 = LogisticRegression.logit(exponent1);
        Assert.assertEquals(expected1, actual1);

        final Double exponent2 = 1.0;
        final Double expected2 = Math.exp(1) / (1 + Math.exp(1));
        final Double actual2 = LogisticRegression.logit(exponent2);
        Assert.assertEquals(expected2, actual2);

    }

}
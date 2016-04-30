package com.timeseries.openforecast.var1;

/**
 * Created by mandark on 25-10-2015.
 */
import net.sourceforge.openforecast.ForecastingModel;
import net.sourceforge.openforecast.DataPoint;
import net.sourceforge.openforecast.DataSet;
import net.sourceforge.openforecast.Observation;
import net.sourceforge.openforecast.models.TripleExponentialSmoothingModel;
import junit.framework.TestCase;

import java.util.Iterator;

public class TripleExponentialSmoothingTest extends TestCase {

    /**
     * The amount of error in the forecast values where the forecast is
     * considered "correct" by the test.
     */
    private static double TOLERANCE = 0.0005;

    //    /**
    //     * The amount of error/tolerance in the Mean Squared Error where the
    //     * forecast is still considered "correct" by the test.
    //     */
    //    private static double MSE_TOLERANCE = 0.1;

    /**
     * Constructor required by the JUnit framework.
     */
    public TripleExponentialSmoothingTest( String name )
    {
        super(name);
    }

    /**
     * A simple test of triple exponential smoothing where the observed data is
     * constant. This should result in the same constant forecast for any
     * value of the smoothing constants.
     */
    public void testConstantTripleExponentialSmoothing()
    {
        final int NUMBER_OF_OBSERVATIONS = 20;

        // Set up some constant observed values
        DataSet observedData = new DataSet();
        DataPoint dp;

        for ( int t=0; t<NUMBER_OF_OBSERVATIONS; t++ )
        {
            dp = new Observation( 5.0 );
            dp.setIndependentValue( "t",  t+1 );
            observedData.add( dp );
        }
        observedData.setPeriodsPerYear( 4 );

        // These are the expected results
        double expectedResult[] = { 5.0, 5.0 };

        // Try the forecasting model with smoothing constants ranging
        //  from 0.1 to 0.9 (in 0.1 increments)
        double beta = 0.1;
        double gamma = 0.1;
        double alpha = 0.1;
        for ( ; alpha<0.95; alpha+=0.1 )
        {
            ForecastingModel model
                    = new TripleExponentialSmoothingModel(alpha,
                    gamma,
                    beta);

            // Initialize the model
            model.init( observedData );

            // Create a data set for forecasting
            DataSet fcValues = new DataSet();

            dp = new Observation( 0.0 );
            dp.setIndependentValue( "t", NUMBER_OF_OBSERVATIONS-1.0 );
            fcValues.add( dp );

            dp = new Observation( 0.0 );
            dp.setIndependentValue( "t", NUMBER_OF_OBSERVATIONS );
            fcValues.add( dp );

            // Get forecast values
            DataSet results = model.forecast( fcValues );

            checkResults( results, expectedResult );
        }
    }

    /**
     * An example taken from Wayne Winston's book. Sales of air conditioners.
     * This example uses only the first two years of data, then tests the
     * extrapolation out to a third year.
     */
    public void testAirConditioning2YearExample()
    {
        DataSet observedData = new DataSet();
        DataPoint dp;

        // Two years of sales data for air conditioning units
        // - for periods -23 ... 0
        double[] observations = { 4, 3,10,14,25,26,38,40,28,17,16,13,
                9, 6,18,27,48,50,75,77,52,33,31,24 };

        for ( int t=0; t<observations.length; t++ )
        {
            dp = new Observation( observations[t] );
            dp.setIndependentValue( "t",  t-23);
            observedData.add( dp );
        }
        observedData.setPeriodsPerYear( 12 );

        ForecastingModel model
                = new TripleExponentialSmoothingModel( 0.5, 0.4, 0.6 );

        // Initialize the model
        model.init( observedData );

        //assertEquals( "Checking the accuracy of the model",
        //              3.36563849, model.getMSE(), MSE_TOLERANCE );

        // Create a data set for forecasting
        DataSet fcValues = new DataSet();

        for ( int t=0; t<12; t ++ )
        {
            dp = new Observation( 0.0 );
            dp.setIndependentValue( "t", t+1 );
            fcValues.add( dp );
        }

        // Get forecast values
        DataSet results = model.forecast( fcValues );

        // These are the expected results
        double expectedResult[]
                = { 10.516154,  7.650000,  /* t =  1, 2 */
                24.944615, 37.206923,  /* t =  3, 4 */
                68.214615, 73.000000,  /* t =  5, 6 */
                111.057692,118.520769,  /* t =  7, 8 */
                83.618462, 53.210769,  /* t =  9,10 */
                51.268462, 41.650000 };/* t = 11,12 */

        checkResults( results, expectedResult, TOLERANCE );
    }

    /**
     * An example taken from Wayne Winston's book. Sales of air conditioners.
     * This example uses three years of data, then tests the "forecasts" -
     * using triple exponential smoothing - for the third year. It therefore
     * tests the updating of the triple exponential smoothing terms as
     * additional data becomes available.
     */
    public void testAirConditioning3YearExample()
    {
        DataSet observedData = new DataSet();
        DataPoint dp;

        // Two years of sales data for air conditioning units
        // - for periods -23 ... 12
        double[] observations = { 4, 3,10,14,25,26,38,40,28,17,16,13,
                9, 6,18,27,48,50,75,77,52,33,31,24,
                13, 7,23,32,58,60,90,93,63,39,37,29 };

        for ( int t=0; t<observations.length; t++ )
        {
            dp = new Observation( observations[t] );
            dp.setIndependentValue( "t",  t-23);
            observedData.add( dp );
        }
        observedData.setPeriodsPerYear( 12 );

        ForecastingModel model
                = new TripleExponentialSmoothingModel( 0.5, 0.4, 0.6 );

        // Initialize the model
        model.init( observedData );

        //assertEquals( "Checking the accuracy of the model",
        //              3.36563849, model.getMSE(), MSE_TOLERANCE );

        // Create a data set for forecasting
        DataSet fcValues = new DataSet();

        for ( int t=0; t<12; t ++ )
        {
            dp = new Observation( 0.0 );
            dp.setIndependentValue( "t", t+1 );
            fcValues.add( dp );
        }

        // Get forecast values
        DataSet results = model.forecast( fcValues );

        // These are the expected results
        double expectedResult[]
                = { 10.516154,  8.875898,  /* t =  1, 2 */
                25.776672, 35.482731,  /* t =  3, 4 */
                59.162286, 59.736113,  /* t =  5, 6 */
                86.897135, 90.762761,  /* t =  7, 8 */
                62.680566, 38.729109,  /* t =  9,10 */
                36.338738, 29.031300 };/* t = 11,12 */

        checkResults( results, expectedResult, TOLERANCE );
    }

    protected void checkResults( DataSet actualResults,
                                 double[] expectedResults )
    {
        checkResults( actualResults, expectedResults, TOLERANCE );
    }

    /**
     * A helper function that validates the actual results obtaining for
     * a DataSet match the expected results. This is the same as the other
     * checkResults method except that with this method, the caller can
     * specify an acceptance tolerance when comparing actual with expected
     * results.
     * @param actualResults the DataSet returned from the forecast method
     *        that contains the data points for which forecasts were done.
     * @param expectedResults an array of expected values for the forecast
     *        data points. The order should match the order of the results
     *        as defined by the DataSet iterator.
     * @param tolerance the tolerance to accept when comparing the actual
     *        results (obtained from a forecasting model) with the expected
     *        results.
     */
    protected void checkResults( DataSet actualResults,
                                 double[] expectedResults,
                                 double tolerance )
    {
        // This is just to safeguard against a bug in the test case!  :-)
        assertNotNull( "Checking expected results is not null",
                expectedResults );
        assertTrue( "Checking there are some expected results",
                expectedResults.length > 0 );

        assertEquals( "Checking the correct number of results returned",
                expectedResults.length, actualResults.size() );

        // Iterate through the results, checking each one in turn
        Iterator<DataPoint> it = actualResults.iterator();
        int i=0;
        while ( it.hasNext() )
        {
            // Check that the results are within specified tolerance
            //  of the expected values
            DataPoint fc = (DataPoint)it.next();
            double fcValue = fc.getDependentValue();

            assertEquals( "Checking result",
                    expectedResults[i], fcValue, tolerance );
            i++;
        }
    }
}

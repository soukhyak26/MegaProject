package domains;

import com.intuit.karate.cucumber.CucumberRunner;
import com.intuit.karate.cucumber.KarateStats;
import cucumber.api.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;

import static junit.framework.TestCase.assertTrue;


@CucumberOptions(tags = {"~@ignore"})
public class DemoTest extends TestBase {
    @BeforeClass
    public static void beforeClass() {
        // skip 'callSingle' in karate-config.js
        System.setProperty("karate.env", "web");
    }
/*
    @AfterClass
    public static void afterClass(){
        MongoReader.drop();
    }
*/
}

package bdd;



import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import uti.ConfigFileReader;
import com.cucumber.listener.Reporter;
import java.io.File;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/bdd/features",
        plugin = {"html:Report/cucumber-reports.html"},
        monochrome = true
)

public class CucumberTestRunner {
//    @AfterClass
//    public static void writeExtentReport() {
//        ConfigFileReader path= new ConfigFileReader();
//        Reporter.loadXMLConfig(new File(path.getReportConfigPath()));
//    }
}

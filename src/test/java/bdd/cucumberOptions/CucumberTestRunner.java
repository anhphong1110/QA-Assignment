package bdd.cucumberOptions;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/bdd/features/TestFeatures.feature"
        ,glue= {"scripts/ui"},
        plugin = { "pretty", "html:target/cucumber-reports.html" },
        monochrome = true
)

public class CucumberTestRunner {
}

package cucumber;

@CucumberOptions(features="/src/test/java/cucumber", glue="mamtaranipkg.stepDefinitions", monochrome=true, plugin= {"html:target/cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests{

}

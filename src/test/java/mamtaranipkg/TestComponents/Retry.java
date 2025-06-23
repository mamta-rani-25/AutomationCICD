package mamtaranipkg.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer { // this is for flaky tests 

	int count = 0;
	int maxTry = 1;

	@Override
	public boolean retry(ITestResult result) {
		if (count < maxTry) {
			count++;
			return true;
		}

		return false;
	}

}

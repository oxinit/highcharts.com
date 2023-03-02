package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomTestListener implements ITestListener {
    final static Logger logger = LoggerFactory.getLogger(String.valueOf(CustomTestListener.class));

    @Override
    public void onFinish(ITestContext contextFinish) {
        logger.info("onFinish method finished ");

    }

    @Override
    public void onStart(ITestContext contextStart) {
        logger.info("onStart method started ");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        logger.info("Method failed with certain success percentage "+ result.getName());

    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.info("Method failed "+ result.getName());

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.info("Method skipped "+ result.getName());

    }

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("Method started "+ result.getName());

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Method passed "+ result.getName());
    }
}

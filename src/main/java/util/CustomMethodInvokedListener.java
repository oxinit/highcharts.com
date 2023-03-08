package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

public class CustomMethodInvokedListener implements IInvokedMethodListener {
    final static Logger logger = LoggerFactory.getLogger(String.valueOf(CustomMethodInvokedListener.class));

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult result) {
        logger.info("This method is invoked after- " + method.getTestMethod().getMethodName());

    }

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult result) {
        logger.info("This method is invoked before- " + method.getTestMethod().getMethodName());
    }

}


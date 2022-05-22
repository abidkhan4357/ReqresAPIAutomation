package org.reqres.listeners;

import org.reqres.apiclient.APIClient;
import org.reqres.reports.ExtentLogger;
import org.reqres.reports.ExtentReport;
import org.testng.*;

public class ExtentReportListener implements ITestListener, ISuiteListener {

    APIClient apiClient = new APIClient();
    @Override
    public void onStart(ISuite suite) {
        ExtentReport.initializeReport();
    }

    @Override
    public void onFinish(ISuite suite) {
        ExtentReport.flushReport();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentReport.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentLogger.pass(result.getMethod().getDescription());
        logRequestResponseToExtentReport();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentLogger.fail(result.getMethod().getDescription());
        ExtentLogger.info(result.getThrowable().toString());
        logRequestResponseToExtentReport();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentLogger.skip(result.getMethod().getDescription());
    }

    private void logRequestResponseToExtentReport() {
        String apiRequestResponse = apiClient.getRequestResponseLogs().get();
        ExtentLogger.info("<pre>" + apiRequestResponse + "</pre>");
    }
}

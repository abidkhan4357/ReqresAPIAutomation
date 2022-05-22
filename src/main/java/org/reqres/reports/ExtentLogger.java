package org.reqres.reports;

import com.aventstack.extentreports.Status;

public final class ExtentLogger {

    private ExtentLogger() {

    }

    public static void pass(String message) {
        ExtentManager.getExtentTest().log(Status.PASS, message);
    }

    public static void fail(String message) {
        ExtentManager.getExtentTest().log(Status.FAIL, message);
    }

    public static void skip(String message) {
        ExtentManager.getExtentTest().log(Status.SKIP, message);
    }

    public static void info(String message)
    {
        ExtentManager.getExtentTest().log(Status.INFO, message);
    }

}

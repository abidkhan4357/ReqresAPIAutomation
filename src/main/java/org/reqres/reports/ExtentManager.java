package org.reqres.reports;

import com.aventstack.extentreports.ExtentTest;

public final class ExtentManager {

    private ExtentManager() {

    }
    private static final ThreadLocal<ExtentTest> exttest = new ThreadLocal<>();

    static ExtentTest getExtentTest() {
        return exttest.get();
    }

    static void setExtentTest(ExtentTest test) {
        exttest.set(test);
    }

    static void unload() {
        exttest.remove();
    }


}


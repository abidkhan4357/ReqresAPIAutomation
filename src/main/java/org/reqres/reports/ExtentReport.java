package org.reqres.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.reqres.constants.FilePathConstants;

import java.util.Objects;

public final class ExtentReport {

    private ExtentReport() {

    }

    private static ExtentReports extent;

    public static void initializeReport() {
        if(Objects.isNull(extent)) {
            extent = new ExtentReports();
            ExtentSparkReporter spark = new ExtentSparkReporter(FilePathConstants.EXTENTREPORT_FILEPATH);
            extent.attachReporter(spark);
            spark.config().setTheme(Theme.STANDARD);
            spark.config().setReportName("Reqres Automation Report");
        }
    }

    public static void flushReport() {
        if(Objects.nonNull(extent)) {
            extent.flush();
        }

    }

    public static void createTest(String testcasename) {
        ExtentTest test = extent.createTest(testcasename);
        ExtentManager.setExtentTest(test);
    }


}

package com.example.framework.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    private static final Path screenshotDir = Path.of("target", "screenshots");

    public static String capture(WebDriver driver, String testName) {
        try {
            // Ensure target/screenshots directory exists
            Files.createDirectories(screenshotDir);

            // Unique timestamp for filename
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date());

            // Capture screenshot as file
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Build destination path
            File dest = screenshotDir.resolve(testName + "_" + timestamp + ".png").toFile();

            // Copy captured screenshot to destination
            FileUtils.copyFile(src, dest);
            System.out.println("Path: "+dest.getAbsolutePath()+"\n"+dest.getPath()+"\n"+dest.getCanonicalPath());
            return dest.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
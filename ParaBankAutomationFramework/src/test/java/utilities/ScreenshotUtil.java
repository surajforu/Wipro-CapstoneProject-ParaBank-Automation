package utilities;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class ScreenshotUtil {

    public static String captureScreenshot(
            WebDriver driver,
            String testName) {

        if (driver == null) {
            return null;
        }

        File source = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);

        String relativePath =
                "screenshots"
                + File.separator
                + testName
                + "_"
                + System.currentTimeMillis()
                + ".png";

        String completePath =
                System.getProperty("user.dir")
                + File.separator
                + relativePath;

        File destination = new File(completePath);

        File parentFolder = destination.getParentFile();

        if (!parentFolder.exists()) {
            parentFolder.mkdirs();
        }

        try {

            FileHandler.copy(source, destination);

            return relativePath.replace("\\", "/");

        } catch (IOException e) {

            throw new RuntimeException(
                    "Unable to save screenshot", e);
        }
    }
}
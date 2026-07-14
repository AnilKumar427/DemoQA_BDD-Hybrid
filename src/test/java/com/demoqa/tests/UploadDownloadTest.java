package com.demoqa.tests;

import com.demoqa.pages.UploadDownloadPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.File;

// Imported from your QA_Utils JAR
import utils.ConfigReader;

public class UploadDownloadTest extends BaseTest {
    @Test
    public void testFileTransferModules() throws Exception {
        driver.get(ConfigReader.getProperty("baseUrl") + "/upload-download");
        UploadDownloadPage page = new UploadDownloadPage(driver);

        page.triggerDownload();
        Thread.sleep(1000);

        File dummyFile = new File(System.getProperty("user.dir") + File.separator + "frameworkSample.txt");
        dummyFile.createNewFile();
        page.performUpload(dummyFile.getAbsolutePath());

        Assert.assertTrue(page.getUploadConfirmationText().contains("frameworkSample.txt"));
        dummyFile.delete();
    }
}
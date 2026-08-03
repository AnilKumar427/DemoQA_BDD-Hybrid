package com.demoqa.steps;

import com.demoqa.context.DriverManager;
import com.demoqa.pages.UploadDownloadPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import java.io.File;

public class UploadDownloadSteps {
    private UploadDownloadPage uploadPage = new UploadDownloadPage(DriverManager.getDriver());

    @When("I trigger the file download")
    public void i_trigger_the_file_download() throws InterruptedException {
        uploadPage.triggerDownload();
        Thread.sleep(1000);
    }

    @And("I upload a sample file named {string}")
    public void i_upload_a_sample_file_named(String fileName) throws Exception {
        File dummyFile = new File(System.getProperty("user.dir") + File.separator + fileName);
        dummyFile.createNewFile();
        uploadPage.performUpload(dummyFile.getAbsolutePath());
        dummyFile.deleteOnExit();
    }

    @Then("the upload confirmation should contain {string}")
    public void the_upload_confirmation_should_contain(String expectedName) {
        Assert.assertTrue(uploadPage.getUploadConfirmationText().contains(expectedName));
    }
}
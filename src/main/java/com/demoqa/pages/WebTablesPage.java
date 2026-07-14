package com.demoqa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.ArrayList;
import java.util.List;

// Imported from your QA_Utils JAR
import pages.BaseEnginePage;

public class WebTablesPage extends BaseEnginePage {
    @FindBy(id = "addNewRecordButton") private WebElement addBtn;
    @FindBy(id = "firstName") private WebElement fName;
    @FindBy(id = "lastName") private WebElement lName;
    @FindBy(id = "userEmail") private WebElement email;
    @FindBy(id = "age") private WebElement userAge;
    @FindBy(id = "salary") private WebElement userSalary;
    @FindBy(id = "department") private WebElement dept;
    @FindBy(id = "submit") private WebElement submitBtn;
    @FindBy(id = "searchBox") private WebElement searchInput;
    @FindBy(xpath = "//div[@class='rt-tbody']//div[@class='rt-tr' and not(contains(@class, '-padRow'))]//div[@class='rt-td']")
    private List<WebElement> tableActiveCells;

    public WebTablesPage(WebDriver driver) {
        super(driver);
    }

    public void addNewRecord(String first, String last, String mail, String age, String salary, String department) {
        addBtn.click();
        fName.sendKeys(first);
        lName.sendKeys(last);
        email.sendKeys(mail);
        userAge.sendKeys(age);
        userSalary.sendKeys(salary);
        dept.sendKeys(department);
        submitBtn.click();
    }

    public void searchForRecord(String query) {
        searchInput.clear();
        searchInput.sendKeys(query);
    }

    public List<String> getRowTextData() {
        List<String> output = new ArrayList<>();
        for (WebElement cell : tableActiveCells) {
            String val = cell.getText().trim();
            if (!val.isEmpty()) output.add(val);
        }
        return output;
    }
}
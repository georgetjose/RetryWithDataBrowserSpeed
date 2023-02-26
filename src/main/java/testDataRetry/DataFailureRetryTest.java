package testDataRetry;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.time.Duration;

public class DataFailureRetryTest
{
    ChromeDriver driver;

    @BeforeMethod
    public void launchBrowser()
    {
        driver = new ChromeDriver();
        driver.get("http://leaftaps.com/opentaps");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @DataProvider(name= "testDataRetry")
    public Object[][] TestDataFeed()
    {
        return new DataProviderInitializerClass().getTestData();
    }


    @Test(dataProvider="testData", retryAnalyzer = RetryAnalyzer.class)
    public void createLead(String userName,String password, String firstName, String lastName)
    {
        driver.findElement(By.xpath("//input[@id='username']")).sendKeys(userName);
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@class='decorativeSubmit']")).click();
        driver.findElement(By.xpath("//a[contains(text(),'CRM/SFA')]")).click();
        driver.findElement(By.xpath("//a[text()='Leads']")).click();
        driver.findElement(By.xpath("//a[text()='Create Lead']")).click();
        driver.findElement(By.xpath("//input[@id='createLeadForm_companyName']")).sendKeys("Automation Company");
        driver.findElement(By.xpath("//input[@id='createLeadForm_firstName']")).sendKeys(firstName);
        driver.findElement(By.xpath("//input[@id='createLeadForm_lastName']")).sendKeys(lastName);
        driver.findElement(By.xpath("//*[@name='submitButton']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//*[@id='viewLead_firstName_sp']")).getText(),firstName);
    }

    @AfterMethod
    public void tearDown()
    {
        driver.quit();
    }
}

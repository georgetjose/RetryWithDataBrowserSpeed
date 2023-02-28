# TestNGDataProvider

Assignment 1

Create a plain Selenium Test case with invalid Test Data. It should fail. Next it should retry with valid Test Data and it should pass.
Data should not be hardcoded but should be taken from TestNG DataProvider. 
Use faker library to create various test data.

Create another plain Selenium Test Case when the Test fails in one browser it should retry the same Test case in another browser.


Assignment 2

Create a Config file with 3 speed levels (eg. retry_high, retry_medium, retry_low).
Start the execution of plain Selenium Test case in the highest speed setting. If the exception is of instanceOf(timeOut exception) retry using slower speed settings.
Use Owner library to control the same.
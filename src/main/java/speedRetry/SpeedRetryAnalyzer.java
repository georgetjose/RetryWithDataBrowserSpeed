package speedRetry;

import org.aeonbits.owner.ConfigFactory;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class SpeedRetryAnalyzer implements IRetryAnalyzer {

    int counter = 0;
    int retryLimit = 2;
    static Long waitingTime = null;

    WaitTime waitTime = ConfigFactory.create(WaitTime.class);

    public Long getWaitingTime()
    {
        return waitingTime;
    }

    public void setWaitingTime(Long waitingTime) {
        this.waitingTime = waitingTime;
    }

    @Override
    public boolean retry(ITestResult result) {

        if(counter < retryLimit)
        {
            counter++;
            if (counter == 1) {
                setWaitingTime(waitTime.retry_medium());
            } else {
                setWaitingTime(waitTime.retry_low());
            }
            return true;
        }
        return false;
    }

}

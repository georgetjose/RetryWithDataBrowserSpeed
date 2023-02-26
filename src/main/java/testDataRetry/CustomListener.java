
package testDataRetry;


import com.github.javafaker.Faker;
import org.testng.IAnnotationTransformer;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;

public class CustomListener implements IHookable, IAnnotationTransformer
{
    public static ThreadLocal<String> firstname = new ThreadLocal<String>();
    public static ThreadLocal<String> lastname = new ThreadLocal<String>();
    int runCounter = 0;

    @Override
    public void run(IHookCallBack iHookCallBack, ITestResult iTestResult)
    {
        Object[] parameterValues = iHookCallBack.getParameters();
        if (runCounter>0) {
            setFirstName(new Faker().name().firstName());
            setLastName(new Faker().name().lastName());
            parameterValues[2] = getFirstName();
            parameterValues[3] = getLastName();
            iHookCallBack.runTestMethod(iTestResult);
        } else {
            iHookCallBack.runTestMethod(iTestResult);
            runCounter++;
        }
        System.out.println(parameterValues[0]);
        System.out.println(parameterValues[1]);
        System.out.println(parameterValues[2]);
        System.out.println(parameterValues[3]);
    }

    public static void setFirstName(String firstName) {
        firstname.set(firstName);
    }

    public static void setLastName(String lastName) {
        lastname.set(lastName);
    }

    public static String getFirstName() {
        return firstname.get(); // Get the value for the current thread
    }

    public static String getLastName() {
        return lastname.get(); // Get the value for the current thread
    }
}




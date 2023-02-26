package testDataRetry;

import com.github.javafaker.Faker;

public class DataProviderInitializerClass
{
    public Object [][] userCredentials= new Object[1][4];
    public Object[][] getTestData() {

        userCredentials[0][0]="demosalesmanager";
        userCredentials[0][1]="crmsfa";
        userCredentials[0][2]= new Faker().name().firstName();
        userCredentials[0][3]= "";
        return userCredentials;
    }
}

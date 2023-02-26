package browserRetry;

public class BrowserFlow
{
    public static BrowserDriver testBrowser(BrowserTypes type)
    {
        System.out.println("WebDriver getting triggered!!");
        switch(type)
        {
            case CHROME: return new ChromeBrowser();
            case FIREFOX: return new FirefoxBrowser();
            case EDGE: return new EdgeBrowser();
            default:
                System.out.println("Wrong Browser selection!!!");
        }
        return null;
    }

}

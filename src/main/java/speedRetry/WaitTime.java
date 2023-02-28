package speedRetry;

import org.aeonbits.owner.Config;

@Config.Sources(value="file:./src/main/resources/WaitTime.properties")
public interface WaitTime extends Config
{
    long retry_high();
    long retry_medium();
    long retry_low();

}

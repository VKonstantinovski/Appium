import com.brainster.appiumsetup.AppiumSetup;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AppiuumTest {

    @BeforeClass
    public void beforeClass(){ AppiumSetup.setup();

    }
    @Test
    public void test1(){
        Assert.assertEquals(AppiumSetup.createAlarm(),"12:00 PM");

    }

    @Test
    public void test2 (){
        Assert.assertTrue(AppiumSetup.alarmScheduled());

    }
    @Test
    public void test3(){
        Assert.assertEquals(AppiumSetup.selectRingtone(),"Rooster Alarm");
    }

    @AfterClass
    public void afterClass(){
        AppiumSetup.end();
    }

}

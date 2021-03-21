package com.brainster.appiumsetup;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AppiumSetup {

    private static AndroidDriver driver;
    private static WebDriverWait wait;


    public static void setup (){

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName","Android");
        caps.setCapability("deviceName","emulator-5554");
        caps.setCapability("app","C:\\Users\\vkons\\Desktop\\stopcovid.apk");

        try {
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),caps);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,60);

    }
    public static void clickOn(String locatorType){
        wait.until(ExpectedConditions.visibilityOf(driver.findElementByAndroidUIAutomator("new UiSelector()."+locatorType))).click();
    }
    //public static String clickOn(String locatorType){
       // wait.until(ExpectedConditions.visibilityOf(driver.findElementByAndroidUIAutomator("new UiSelector()."+locatorType))).click();
   // }

    public static String getText(String locatorType){

       return  wait.until(ExpectedConditions.visibilityOf(driver.findElementByAndroidUIAutomator("new UiSelector()."+locatorType))).getText();

    }

    public static void scrollAndClick(String visibleText) {
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\""+visibleText+"\").instance(0))").click();

    }


    public static String createAlarm(){

        driver.pressKey(new KeyEvent(AndroidKey.HOME));

        clickOn("text(\"Clock\")");



        //wait.until(ExpectedConditions.visibilityOf(driver.findElementByAndroidUIAutomator("new UiSelector().description(\"Alarm\")"))).click();
        clickOn("description(\"Alarm\")");

        //wait.until(ExpectedConditions.visibilityOf(driver.findElementByAndroidUIAutomator("new UiSelector().description(\"Add alarm\")"))).click();
        clickOn("description(\"Add alarm\")");

        //wait.until(ExpectedConditions.visibilityOf(driver.findElementByAndroidUIAutomator("new UiSelector().description(\"12\")"))).click();
        clickOn("description(\"12\")");

        //wait.until(ExpectedConditions.visibilityOf(driver.findElementByAndroidUIAutomator("new UiSelector().description(\"0\")"))).click();
        clickOn("description(\"0\")");

        //wait.until(ExpectedConditions.visibilityOf(driver.findElementByAndroidUIAutomator("new UiSelector().text(\"PM\")"))).click();
        clickOn("text(\"PM\")");

        //wait.until(ExpectedConditions.visibilityOf(driver.findElementByAndroidUIAutomator("new UiSelector().text(\"OK\")"))).click();
        clickOn("text(\"OK\")");

       return getText("resourceId(\"com.google.android.deskclock:id/digital_clock\")");
               //wait.until(ExpectedConditions.visibilityOf(driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.google.android.deskclock:id/digital_clock\")"))).getText();

    }


    public static boolean alarmScheduled(){
        if (getText("resourceId(\"com.google.android.deskclock:id/onoff\")").equals("ON")){
            return true;
        }else
            {return false;}

    }
    public static String selectRingtone(){

        clickOn("resourceId(\"com.google.android.deskclock:id/choose_ringtone\")");

        scrollAndClick("Rooster Alarm");  //Uspeav na net da go najdam da ne si pomislite deka go napraiv sam :D 

        driver.pressKey(new KeyEvent(AndroidKey.BACK));

        return getText("resourceId(\"com.google.android.deskclock:id/choose_ringtone\")");
    }


    public static void end(){
        driver.quit();
    }
}

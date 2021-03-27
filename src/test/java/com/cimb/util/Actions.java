package com.cimb.util;

import com.cimb.base.TestBase;
import org.openqa.selenium.Alert;

public class Actions extends TestBase {

    public static void dismissDialog() {
        if (driver.switchTo().alert() != null) {
            Alert alert = driver.switchTo().alert();
            alert.dismiss();
        }
    }
}

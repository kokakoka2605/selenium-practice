package org.example.utils;

import io.qameta.allure.Allure;
import org.example.base.BaseTest;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;

/** Если тест упал — делает скриншот и прикрепляет его к Allure-отчёту. */
public class ScreenshotExtension implements AfterTestExecutionCallback {

    @Override
    public void afterTestExecution(ExtensionContext context){
        if(context.getExecutionException().isEmpty()){
            return;
        }
        Object test = context.getRequiredTestInstance();
        if(test instanceof BaseTest baseTest && baseTest.driver != null){
            byte[] png = ((TakesScreenshot) baseTest.driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Скриншот при падении", "image/png", new ByteArrayInputStream(png), "png");
        }
    }
}

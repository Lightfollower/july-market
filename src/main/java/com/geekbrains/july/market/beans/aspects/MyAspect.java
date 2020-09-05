package com.geekbrains.july.market.beans.aspects;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

@Aspect
@Component
public class MyAspect {

    @AfterReturning(pointcut = "execution(public * com.geekbrains.july.market.controllers.Rest*Controller.saveNew*(*))",
            returning = "result")
    public void savingNewSomething(Object result) throws IOException {
        log("Saving new ", result);
    }

    @AfterReturning(pointcut = "execution(public * com.geekbrains.july.market.controllers.Rest*Controller.modify*(*))",
            returning = "result")
    public void modifyingSomething(ResponseEntity result) throws IOException {
        log("Modifying ", result.getBody());
    }

    @AfterReturning(pointcut = "execution(public * com.geekbrains.july.market.controllers.Rest*Controller.deleteOne*(*))",
            returning = "result")
    public void deletingSomething(Object result) throws IOException {
        log("Deleting ", result);
    }

    public void log(String operation, Object result) throws IOException {
        FileWriter fileWriter = new FileWriter("log.txt", true);
        Date date = new Date();
        fileWriter.write(operation + result.toString() + " " + date + System.lineSeparator());
        fileWriter.close();
    }
}

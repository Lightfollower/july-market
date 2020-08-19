package com.geekbrains.july.market.beans.aspects;

import com.geekbrains.july.market.entities.Category;
import com.geekbrains.july.market.entities.Product;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class MyAspect {
    @AfterReturning(pointcut = "execution(public * com.geekbrains.july.market.controllers.Rest*Controller.saveNew*(*))",
            returning = "result")
    public void savingNewSomething(Object result) {
        Date date = new Date();
        System.out.println("Saving  " + result + date);
    }

    @AfterReturning(pointcut = "execution(public * com.geekbrains.july.market.controllers.Rest*Controller.modify*(*))",
            returning = "result")
    public void modifyingSomething(ResponseEntity result) {
        Date date = new Date();
        System.out.println("Modifying " + result.getBody() + date);
    }

    @AfterReturning(pointcut = "execution(public * com.geekbrains.july.market.controllers.Rest*Controller.deleteOne*(*))",
            returning = "result")
    public void deletingSomething(Object result) {
        Date date = new Date();
        System.out.println("Deleting " + result + date);
    }
}

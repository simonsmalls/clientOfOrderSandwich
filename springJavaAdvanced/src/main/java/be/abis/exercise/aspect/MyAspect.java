package be.abis.exercise.aspect;

import be.abis.exercise.exception.ZipCodeNotCorrectException;
import be.abis.exercise.model.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAspect {
    Logger log = LogManager.getLogger("Console");

    @AfterReturning(pointcut="Mypointcut.findMethods()")
    public void noErr(JoinPoint jp){


        log.info("does this to console");

    }

    @AfterThrowing(pointcut="Mypointcut.except()",throwing="exc")
    public void err(Exception exc){
        System.out.println("exception thrown");
        System.out.println(exc.getMessage());
        Logger log= LogManager.getLogger("exceptionLogger");
        log.error(exc.getMessage());
    }



}

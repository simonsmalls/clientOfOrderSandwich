package be.abis.exercise.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class Mypointcut {

    @Pointcut("execution( * be.abis.exercise.repository.*.find*(..))")
    public static void findMethods(){


    }

    @Pointcut("execution( * be.abis.exercise.*.*.*(..))")
    public static void except(){


    }
    @Pointcut("execution( * be.abis.exercise.*.*.*())")
    public static void except0(){


    }


}

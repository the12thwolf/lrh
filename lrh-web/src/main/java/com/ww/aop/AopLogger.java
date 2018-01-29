package com.ww.aop;
import com.ww.util.DataUtil;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/10/30.
 */
@Aspect
@Component
public class AopLogger implements Ordered {
    @Autowired
    protected HttpSession session;
    @Autowired
    private HttpServletRequest request;

    private final Logger logger = Logger.getLogger(AopLogger.class);
    @Pointcut("execution (* com.ww..*.*(..))")
    public void myMethod(){}

    @Before("myMethod()")
    public void before(JoinPoint jp) {
        logger.info("调用方法:"+jp.getTarget().toString().split("@")[0]+"."+jp.getSignature().getName()+"");
        Object[] args = jp.getArgs();
        if(DataUtil.isNotEmpty(args)){
            for(int i=0;i<args.length;i++){
                logger.info(""+"参数"+i+":"+args[i].toString()+"");
            }

        }
    }

    @After("myMethod()")
    public void after() {
        //System.out.println("method after");
    }
    @AfterReturning(pointcut ="myMethod()",returning="obj")
    public void AfterReturning(JoinPoint jp,Object obj) {
        logger.info("调用方法:"+jp.getTarget().toString().split("@")[0]+"."+jp.getSignature().getName()+"");
        if(obj!=null){
            logger.info("返回值:"+obj.toString());
        }else{
            logger.info("没有返回值");
        }

    }
    @AfterThrowing(pointcut ="myMethod()",throwing="ex")
    public void AfterThrowing(JoinPoint jp,Throwable ex) {
        logger.error("调用方法:"+jp.getTarget().toString().split("@")[0]+"."+jp.getSignature().getName()+",发生异常:"+ex);
    }

    /**
     * Spring中的事务是通过aop来实现的，当我们自己写aop拦截的时候，会遇到跟spring的事务aop执行的先后顺序问题，
     * 比如说动态切换数据源的问题，如果事务在前，数据源切换在后，会导致数据源切换失效，所以就用到了Order（排序）这个关键字.
     * 我们可以通过在@AspectJ的方法中实现org.springframework.core.Ordered 这个接口来定义order的顺序，order 的值越小，
     * 说明越先被执行。
     当实现Ordered 接口之后，我们自己写的aop在事务介入之前就执行了！
     * @return
     */
    public int getOrder() {
        return 0;
    }
}

package com.security.controller.async;

import com.security.service.AsyncService;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.codegen.CompilerConstants;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/order")
public class AsyncController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    DeferredResultHolder deferredResultHolder;

    @Autowired
    private AsyncService asyncService;

    @GetMapping
    public DeferredResult<String> order() throws Exception{
        logger.info("main thread start");

        String orderNumber = RandomStringUtils.randomNumeric(8);
        mockQueue.setPlaceOrder(orderNumber);

        DeferredResult<String> result = new DeferredResult<>();
        deferredResultHolder.getMap().put(orderNumber, result);
        logger.info("main thread return");
        return result;
    }

    @GetMapping("/callable")
    @ApiOperation(value = "Callable example API")
    public Callable<String> callableTest(){
        logger.info("callable outter start");

        Callable<String> callable = () -> {
            logger.info("callable start");
            Thread.sleep(1000);
            logger.info("callable ends");
            return "callable";
        };
        logger.info("callable outter ends");

        return callable;
    }

    @GetMapping("/async")
    public DeferredResult<String> asyncTest(){
        logger.info("async outter start");
        DeferredResult<String> deferredResult = new DeferredResult<>();
        asyncService.asyncService(deferredResult);
        logger.info("async outter ends");

        return deferredResult;
    }
}

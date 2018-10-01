package com.security.service.impl;

import com.security.service.AsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Future;


@Service
public class AsyncServiceImpl implements AsyncService {

    private Logger logger = LoggerFactory.getLogger(getClass());



    @Override
    @Async
    public void asyncService(DeferredResult<String> deferredResult) {
        logger.info("async start");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("async end");
        deferredResult.setResult("async");
    }
}

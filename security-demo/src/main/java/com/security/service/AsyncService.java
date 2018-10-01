package com.security.service;

import org.springframework.web.context.request.async.DeferredResult;


public interface AsyncService {

    void asyncService(DeferredResult<String> deferredResult);
}

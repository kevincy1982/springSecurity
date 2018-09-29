package com.security.service.impl;

import com.security.service.HelloService;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public void greeting(String name) {
        System.out.println("Hellp " + name);
    }
}

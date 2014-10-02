package com.dream.scheduling.demo;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HelloDemoImpl implements Serializable {
    
    private static Logger log=LoggerFactory.getLogger(HelloDemoImpl.class);

    public void testSayHello() {

        this.log.info("this is a test for tesing schedule!");
        
    }

}

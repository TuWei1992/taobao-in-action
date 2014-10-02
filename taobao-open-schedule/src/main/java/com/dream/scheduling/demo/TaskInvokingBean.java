package com.dream.scheduling.demo;

import com.dream.scheduling.core.AppsMethodInvokingJobDetailFactoryBean;


public abstract class TaskInvokingBean extends AppsMethodInvokingJobDetailFactoryBean {
    
    private Object targetObject;

    
    public Object getTargetObject() {
    
        return targetObject;
    }

    
    public void setTargetObject(Object targetObject) {
    
        this.targetObject = targetObject;
    }
    
    

}

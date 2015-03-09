package com.dream.auth.controller;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;

import com.dream.auth.model.Auth;

public class AuthControllerTest  {
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());
//    @Autowired
//    private AuthController authController = null;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

//    @Test
//    public void testCreate() throws Exception {
//        log.debug("testing add new user...");
//        request = newGet("/userform.html");
//        request.addParameter("method", "Add");
//		ModelMap model = null;
//		Auth auth = new Auth();
//		BindingResult errors = new BindException(auth,"auth");
//		authController.create(model, auth, errors, request, response);
//    }
    
    
    @Test
    public void testDelete() throws Exception {
    	Object o1 = null;
    	Object o2 = null;
    	boolean result = o1 == null ? true:false && o2 == null ? false : true;
    	logger.debug("*********************"+result);
    	
    	boolean result1 = (o1 == null ? true:false) && (o2 == null ? false : true);
    	logger.debug("*********************"+result1);
    	
    	boolean result2 = o1 == null ? true:false && (o2 == null ? false : true);
    	
    	logger.debug("*********************"+result2);
    }
    
    
    @Test
    public void testGet() throws Exception {
    	String id = "123";
    	String name = "auth";
    	com.dream.auth.controller.Auth a = new com.dream.auth.controller.Auth();
    	a.setId("123");
    	a.setName("auth");
    	
//    	com.dream.auth.controller.Auth b = new com.dream.auth.controller.Auth();
//    	b.setId(id);
//    	b.setName(name);
    	
    	com.dream.auth.controller.Auth c = new com.dream.auth.controller.Auth();
    	c.setId("234");
    	c.setName("auth");
    	logger.debug("*********************"+a.equals(c));
    }


}

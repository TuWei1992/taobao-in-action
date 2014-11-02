package com.dream.rapid.jsp.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.commons.lang.StringUtils;


/**
 * 根据随机数生成Token,避免用户重复提交
 * 
 * @author Frank
 * 
 */
public class RandomTokenValueTag extends RandomTokenTag {
    
    private String target;
    
    protected int doStartTagInternal() throws Exception {
            String tokenString = generateRandomTokenString();
            if(StringUtils.isNotEmpty(target)){
                this.pageContext.setAttribute(target, tokenString);
            }else{
                try {
                    JspWriter out = pageContext.getOut();
                    out.write(tokenString);
                } catch (IOException io) {
                    throw new JspException(io);
                }
            }
            return (SKIP_BODY);
     }
    
    
    protected String generateRandomTokenString(){
        String RandomGUIDString = randomString();
        StringBuffer sb = new StringBuffer();
        sb.append("?");
//        sb.append(Constants.RANDOM_TOKEN_NAME);
        sb.append("=");
        sb.append(tokenName);
        sb.append("&");
        sb.append(tokenName);
        sb.append("=");
        sb.append(RandomGUIDString);
        // 自定义的BackURL跳转错误页面
        if (StringUtils.isNotEmpty(backurl)) {
            sb.append("&");
//            sb.append(Constants.RANDOM_TOKEN_FAILED_URL);
            sb.append("=");
            sb.append(backurl);
         
        }
        return sb.toString();
    }


    /**
     * @param target the target to set
     */
    public void setTarget(String target) {
        this.target = target;
    }


}

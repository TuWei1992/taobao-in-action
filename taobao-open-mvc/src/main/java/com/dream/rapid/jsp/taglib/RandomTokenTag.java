package com.dream.rapid.jsp.taglib;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.springframework.web.servlet.tags.RequestContextAwareTag;


/**
 * 根据随机数生成Token,避免用户重复提交
 * 
 * @author Frank
 * 
 */
public class RandomTokenTag extends RequestContextAwareTag {
    
    protected String tokenName;
    protected String backurl;

	private static final long serialVersionUID = -1692181824618342993L;
	
	protected String randomString(){
	    String RandomGUIDString = RandomGUID.getRandomGUID(true);
	    HttpSession session = pageContext.getSession();
	    logger.debug("===========>Going to put a token "+RandomGUIDString +" with name " +tokenName+ " for session "+session.getId());
	    session.setAttribute(tokenName, RandomGUIDString);
	    logger.debug("===========>Have put a token "+RandomGUIDString +" with name " +tokenName+ " for session "+session.getId());
        return RandomGUIDString;
	}
	
	public void setBackurl(String backurl){
	   this.backurl =  backurl ;
	}
	
	public void setTokenName(String tokenName){
	       this.tokenName =  tokenName ;
	}

    /* (non-Javadoc)
     * @see org.springframework.web.servlet.tags.RequestContextAwareTag#doStartTagInternal()
     */
    @Override
    protected int doStartTagInternal() throws Exception {
       try {
            JspWriter out = pageContext.getOut();
            out.write(generateRandomTokenString());
        } catch (IOException io) {
            throw new JspException(io);
        }
     // Continue processing this page
        return (SKIP_BODY);
    }

    
    
    protected String generateRandomTokenString(){
        String RandomGUIDString = randomString();
        StringBuffer sb = new StringBuffer();
//        sb.append("<input type=\"hidden\" name=\"" + Constants.RANDOM_TOKEN_NAME + "\" id=\"" + Constants.RANDOM_TOKEN_NAME + "\" value=\"" + tokenName + "\" />\n");
//        sb.append("<input type=\"hidden\" name=\"" + tokenName + "\" id=\"" + tokenName + "\" value=\"" + RandomGUIDString + "\" />\n");
//        // 自定义的BackURL跳转错误页面
//        if (StringUtils.isNotEmpty(backurl)) {
//            sb.append("<input type=\"hidden\" name=\"" + Constants.RANDOM_TOKEN_FAILED_URL + "\" id=\"" + Constants.RANDOM_TOKEN_FAILED_URL + "\" value=\"" + backurl + "\" />\n");
//        }
        return sb.toString();
    }

}

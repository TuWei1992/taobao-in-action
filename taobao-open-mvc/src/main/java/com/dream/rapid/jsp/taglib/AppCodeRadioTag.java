package com.dream.rapid.jsp.taglib;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.servlet.jsp.JspException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.dream.common.model.AppCode;


/**
 * 生成指定类型的应用参数的单选框的JSPTAG实现类，可将参数列表以radio的形式显示或者将参数列表保存在指定的作用域中
 * 
 * @version 1.0 2008-07-08
 * @author Frank
 * @since 1.4
 */
public class AppCodeRadioTag extends BasicSelectTagSupport {

	private static final long serialVersionUID = 3367760645555418777L;
	private final Log log = LogFactory.getLog(AppCodeRadioTag.class);

	/**
	 * 指定保存当前应用参数列表的作用域范围，可选"request","session","application"
	 */
	private String scope;

	/**
	 * 指定应用参数的类型
	 */
	private String groupId;

	/**
	 * Process the start of this taglib.
	 * 
	 * @return int status
	 * @exception JspException
	 *                if a JSP exception has occurred
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTagInternal() throws JspException {
//		ExpressionEvaluator eval = new ExpressionEvaluator(this, pageContext);
//
//		if (selected != null) {
//			selected = eval.evalString("default", selected);
//		}

		Locale userLocale = pageContext.getRequest().getLocale();
		List<AppCode> appCodeList = null;

		try {
			appCodeList = getAppCodeList(groupId);
        } catch (Exception e) {
			log.error("Error when get appcode. " + e.getMessage());

			StringBuffer sb = new StringBuffer();
			sb.append("<select name=\"" + name + "\" id=\"" + name
					+ "\" class=\"select\">\n");
			sb.append("<option value=\"\">Get app code error!</option>");
			sb.append("</select>");

			try {
				write(sb.toString());
			} catch (IOException io) {
				throw new JspException(io);
			}

			return super.doStartTag();
		}

		// 如果指定要保存作用域则进行保存，否则输出下拉框代码
		if (scope != null) {

			if (null == name || "".equals(name)) {
				throw new JspException(
						"Attribute 'name' can not be null or '' when attribute 'toScope' is not null");
			}

			if (scope.equals("page")) {
				pageContext.setAttribute(name, appCodeList);
			} else if (scope.equals("request")) {
				pageContext.getRequest().setAttribute(name, appCodeList);
			} else if (scope.equals("session")) {
				pageContext.getSession().setAttribute(name, appCodeList);
			} else if (scope.equals("application")) {
				pageContext.getServletContext().setAttribute(name, appCodeList);
			} else {
				throw new JspException(
						"Attribute 'scope' must be: page, request, session or application");
			}
		} else {
			StringBuffer sb = new StringBuffer();

			for (int i=0;i<appCodeList.size();i++) {
				AppCode appCode = appCodeList.get(i);
				sb.append("    <input type=\"radio\" name=\"" + name
						+ "\" id=\"" + name + "\" value=\""
						+ appCode.getCodeValue()
						+ "\" style=\"border:none\" ");

				if ((selected != null)
						&& selected.equals(appCode.getCodeValue())) {
					sb.append(" checked");
				}

//				sb.append(">" + appCode.getCodeName() + "\n");
			}

			try {
				write(sb.toString());
			} catch (IOException io) {
				throw new JspException(io);
			}
		}

		return super.doStartTag();
	}

	/**
	 * @param scope
	 *            The scope to set
	 * @jsp.attribute required="false" rtexprvalue="true"
	 */
	public void setToScope(String scope) {
		this.scope = scope;
	}

	/**
	 * @param codeType
	 *            The codeType to set
	 * @jsp.attribute required="true" rtexprvalue="true"
	 */
	public void setCodeType(String codeType) {
		this.groupId = codeType;
	}

}

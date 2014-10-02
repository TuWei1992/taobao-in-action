<%@page import="com.dream.auth.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<input type="hidden" id="userId" name="userId" value="${auth.userId}"/>

	<tr>	
		<td class="tdLabel">
			<%=Auth.ALIAS_TB_USER_NICK%>:
		</td>		
		<td>
		<form:input path="tbUserNick" id="tbUserNick" cssClass="" maxlength="60" />
		<font color='red'><form:errors path="tbUserNick"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			<span class="required">*</span><%=Auth.ALIAS_IS_LOCK%>:
		</td>		
		<td>
		<form:input path="isLock" id="isLock" cssClass="required " maxlength="1" />
		<font color='red'><form:errors path="isLock"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			<span class="required">*</span><%=Auth.ALIAS_IS_ENABLE%>:
		</td>		
		<td>
		<form:input path="isEnable" id="isEnable" cssClass="required " maxlength="1" />
		<font color='red'><form:errors path="isEnable"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			<%=Auth.ALIAS_TAOBAO_USER_NICK%>:
		</td>		
		<td>
		<form:input path="taobaoUserNick" id="taobaoUserNick" cssClass="" maxlength="20" />
		<font color='red'><form:errors path="taobaoUserNick"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			<%=Auth.ALIAS_TAOBAO_USER_ID%>:
		</td>		
		<td>
		<form:input path="taobaoUserId" id="taobaoUserId" cssClass="" maxlength="30" />
		<font color='red'><form:errors path="taobaoUserId"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			<%=Auth.ALIAS_SUB_TAOBAO_USER_ID%>:
		</td>		
		<td>
		<form:input path="subTaobaoUserId" id="subTaobaoUserId" cssClass="" maxlength="20" />
		<font color='red'><form:errors path="subTaobaoUserId"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			<%=Auth.ALIAS_SUB_TAOBAO_USER_NICK%>:
		</td>		
		<td>
		<form:input path="subTaobaoUserNick" id="subTaobaoUserNick" cssClass="" maxlength="30" />
		<font color='red'><form:errors path="subTaobaoUserNick"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			<%=Auth.ALIAS_REFRESHED_TIME%>:
		</td>		
		<td>
		<input value="${auth.refreshedTimeString}" onclick="WdatePicker({dateFmt:'<%=Auth.FORMAT_REFRESHED_TIME%>'})" id="refreshedTimeString" name="refreshedTimeString"  maxlength="0" class="" />
		<font color='red'><form:errors path="refreshedTime"/></font>
		</td>
	</tr>	
	
		
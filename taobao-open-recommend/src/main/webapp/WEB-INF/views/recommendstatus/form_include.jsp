<%@page import="com.dream.recommend.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<input type="hidden" id="shopId" name="shopId" value="${recommendStatus.shopId}"/>

	<tr>	
		<td class="tdLabel">
			<%=RecommendStatus.ALIAS_STATUS%>:
		</td>		
		<td>
		<form:input path="status" id="status" cssClass="" maxlength="1" />
		<font color='red'><form:errors path="status"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			<%=RecommendStatus.ALIAS_LAST_MODIFIED_TIME%>:
		</td>		
		<td>
		<input value="${recommendStatus.lastModifiedTimeString}" onclick="WdatePicker({dateFmt:'<%=RecommendStatus.FORMAT_LAST_MODIFIED_TIME%>'})" id="lastModifiedTimeString" name="lastModifiedTimeString"  maxlength="0" class="" />
		<font color='red'><form:errors path="lastModifiedTime"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			<%=RecommendStatus.ALIAS_LAST_MODIFIED_BY%>:
		</td>		
		<td>
		<form:input path="lastModifiedBy" id="lastModifiedBy" cssClass="validate-integer " maxlength="19" />
		<font color='red'><form:errors path="lastModifiedBy"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			<%=RecommendStatus.ALIAS_UPDATED_TIME%>:
		</td>		
		<td>
		<input value="${recommendStatus.updatedTimeString}" onclick="WdatePicker({dateFmt:'<%=RecommendStatus.FORMAT_UPDATED_TIME%>'})" id="updatedTimeString" name="updatedTimeString"  maxlength="0" class="" />
		<font color='red'><form:errors path="updatedTime"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			<%=RecommendStatus.ALIAS_UPDATED_USER_ID%>:
		</td>		
		<td>
		<form:input path="updatedUserId" id="updatedUserId" cssClass="validate-integer " maxlength="19" />
		<font color='red'><form:errors path="updatedUserId"/></font>
		</td>
	</tr>	
	
		
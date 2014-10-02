<%@page import="com.dream.shop.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<input type="hidden" id="sid" name="sid" value="${shop.sid}"/>

	<tr>	
		<td class="tdLabel">
			<span class="required">*</span><%=Shop.ALIAS_CID%>:
		</td>		
		<td>
		<form:input path="cid" id="cid" cssClass="required validate-integer " maxlength="19" />
		<font color='red'><form:errors path="cid"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			<span class="required">*</span><%=Shop.ALIAS_NICK%>:
		</td>		
		<td>
		<form:input path="nick" id="nick" cssClass="required " maxlength="20" />
		<font color='red'><form:errors path="nick"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			<%=Shop.ALIAS_TITLE%>:
		</td>		
		<td>
		<form:input path="title" id="title" cssClass="" maxlength="20" />
		<font color='red'><form:errors path="title"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			<%=Shop.ALIAS_DESC%>:
		</td>		
		<td>
		<form:input path="desc" id="desc" cssClass="" maxlength="60" />
		<font color='red'><form:errors path="desc"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			<%=Shop.ALIAS_BULLETIN%>:
		</td>		
		<td>
		<form:input path="bulletin" id="bulletin" cssClass="" maxlength="100" />
		<font color='red'><form:errors path="bulletin"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			<%=Shop.ALIAS_PIC_PATH%>:
		</td>		
		<td>
		<form:input path="picPath" id="picPath" cssClass="" maxlength="100" />
		<font color='red'><form:errors path="picPath"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			<%=Shop.ALIAS_CREATED%>:
		</td>		
		<td>
		<input value="${shop.createdString}" onclick="WdatePicker({dateFmt:'<%=Shop.FORMAT_CREATED%>'})" id="createdString" name="createdString"  maxlength="0" class="" />
		<font color='red'><form:errors path="created"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			<%=Shop.ALIAS_MODIFIED%>:
		</td>		
		<td>
		<input value="${shop.modifiedString}" onclick="WdatePicker({dateFmt:'<%=Shop.FORMAT_MODIFIED%>'})" id="modifiedString" name="modifiedString"  maxlength="0" class="" />
		<font color='red'><form:errors path="modified"/></font>
		</td>
	</tr>	
	
		
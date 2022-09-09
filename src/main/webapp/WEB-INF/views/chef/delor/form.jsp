<%--
- form.jsp
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<jstl:choose>
<jstl:when test="${isNew == true}">	
<acme:form>
<acme:input-textbox code="chef.delor.form.label.keylet" path="keylet" placeholder="XXXXXX:YYMMdd"/>
	<acme:input-textbox code="chef.delor.form.label.subject" path="subject" />
	<acme:input-textbox code="chef.delor.form.label.explanation" path="explanation" />
	<acme:input-textbox code="chef.delor.form.label.startPeriod" path="startPeriod" />
	<acme:input-textbox code="chef.delor.form.label.finishPeriod" path="finishPeriod" />
	<acme:input-money code="chef.delor.form.label.income" path="income" />
	<acme:input-textbox code="chef.delor.form.label.moreInfo" path="moreInfo" />
	<acme:input-select code="chef.delor.form.label.select.ingredient" path="ingredients">
				<jstl:forEach items="${ingredients}" var="ingredient">
					<acme:input-option code="${ingredient.getCode()}" value="${ingredient.getId()}" />
				</jstl:forEach>
			</acme:input-select>
	
	
	<acme:submit code="chef.delor.form.button.create" action="/chef/delor/create"/>
</acme:form>



</jstl:when>


<jstl:otherwise>	
<acme:form >
	<acme:input-textbox code="chef.delor.form.label.keylet" path="keylet" readonly="true"/>
	<acme:input-textbox code="chef.delor.form.label.instantiationMoment" path="instantiationMoment" readonly="true"/>
	<acme:input-textbox code="chef.delor.form.label.subject" path="subject" />
	<acme:input-textbox code="chef.delor.form.label.explanation" path="explanation" />
	<acme:input-textbox code="chef.delor.form.label.startPeriod" path="startPeriod" />
	<acme:input-textbox code="chef.delor.form.label.finishPeriod" path="finishPeriod" />
	<acme:input-money code="chef.delor.form.label.income" path="income" />
	<acme:input-textbox code="chef.delor.form.label.moreInfo" path="moreInfo" />
	<acme:button code="chef.delor.form.label.ingredient" action="/any/artifact/show?id=${ingredienttId}" />

<jstl:if test="${ingredientPublish == false}">
<acme:submit code="chef.delor.form.button.update" action="/chef/delor/update"/>
<acme:submit code="chef.delor.form.button.delete" action="/chef/delor/delete"/>
</jstl:if>

</acme:form>
	</jstl:otherwise>
</jstl:choose>
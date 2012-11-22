package com.springsource.insight.plugin.jsf;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;

import org.aspectj.lang.JoinPoint;

import com.springsource.insight.collection.method.MethodOperationCollectionAspect;
import com.springsource.insight.intercept.operation.Operation;
import com.springsource.insight.intercept.operation.OperationType;

public aspect NavigationHandlerOperationCollectionAspect extends
		MethodOperationCollectionAspect {

	static final OperationType TYPE = OperationType.valueOf("jsf_navigation_operation");

	public pointcut collectionPoint()
        : execution(public void NavigationHandler.handleNavigation(FacesContext, String, String));

	@Override
	protected Operation createOperation(JoinPoint jp) {
		String outcome = jp.getArgs()[2].toString();

		StringBuilder label = new StringBuilder("JSF Navigation [");
		label.append(outcome);
		label.append("]");
		return super.createOperation(jp).type(TYPE).label(label.toString())
				.put("fromAction", jp.getArgs()[1].toString())
				.put("outcome", outcome);
	}

}

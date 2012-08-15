package com.springsource.insight.plugin.jsf;

import javax.faces.context.FacesContext;
import javax.faces.lifecycle.Lifecycle;

import org.aspectj.lang.JoinPoint;

import com.springsource.insight.collection.method.MethodOperationCollectionAspect;
import com.springsource.insight.intercept.operation.Operation;
import com.springsource.insight.intercept.operation.OperationType;

public aspect LifecycleRenderOperationCollectionAspect extends
		MethodOperationCollectionAspect {

	static final OperationType TYPE = OperationType
			.valueOf("jsf_lifecycle_render");

	public pointcut collectionPoint()
        : execution(void Lifecycle.render(FacesContext));

	@Override
	protected Operation createOperation(JoinPoint jp) {
		return super.createOperation(jp).type(TYPE)
				.label("JSF Lifecycle Render");
	}
}

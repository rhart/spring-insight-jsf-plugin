package com.springsource.insight.plugin.jsf.myfaces;

import javax.faces.context.FacesContext;

import org.aspectj.lang.JoinPoint;

import com.springsource.insight.intercept.operation.Operation;
import com.springsource.insight.intercept.operation.OperationType;
import com.springsource.insight.plugin.jsf.AbstractPhaseOperationCollectionAspect;

public aspect ApplyRequestValuesPhaseOperationCollectionAspect extends
		AbstractPhaseOperationCollectionAspect {

	private static final OperationType TYPE = OperationType
			.valueOf("apply_request_values_phase");

	@Override
	protected OperationType getOperationType() {
		return TYPE;
	}

	public pointcut collectionPoint()
        : execution(boolean org.apache.myfaces.lifecycle.ApplyRequestValuesExecutor.execute(FacesContext));

	@Override
	protected Operation createOperation(JoinPoint jp) {
		return super.createOperation(jp)
				.label("JSF Apply Request Values Phase");
	}
}

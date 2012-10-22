package com.springsource.insight.plugin.jsf;

import org.aspectj.lang.JoinPoint;

import com.springsource.insight.collection.method.MethodOperationCollectionAspect;
import com.springsource.insight.intercept.operation.Operation;
import com.springsource.insight.intercept.operation.OperationType;

public abstract aspect AbstractPhaseOperationCollectionAspect extends
		MethodOperationCollectionAspect {

	protected abstract OperationType getOperationType();

	@Override
	protected Operation createOperation(JoinPoint jp) {
		return super
				.createOperation(jp)
				.type(getOperationType())
				.put("implementationClass", jp.getTarget().getClass().getName())
				.put("implementationClassMethod",
						jp.getSignature().toLongString());
	}
}

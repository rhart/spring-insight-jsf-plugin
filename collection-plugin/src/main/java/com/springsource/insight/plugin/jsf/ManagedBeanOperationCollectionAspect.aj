package com.springsource.insight.plugin.jsf;

import javax.faces.bean.ManagedBean;

import org.aspectj.lang.JoinPoint;

import com.springsource.insight.collection.method.MethodOperationCollectionAspect;
import com.springsource.insight.intercept.operation.Operation;
import com.springsource.insight.intercept.operation.OperationType;

public aspect ManagedBeanOperationCollectionAspect extends
		MethodOperationCollectionAspect {

	static final OperationType TYPE = OperationType.valueOf("jsf_managed_bean");

	public pointcut collectionPoint()
        : execution(public * (@ManagedBean *).*(..));

	@Override
	protected Operation createOperation(JoinPoint jp) {
		StringBuilder label = new StringBuilder("JSF Managed Bean (");
		label.append(jp.getTarget().getClass().getSimpleName());
		label.append("#");
		label.append(jp.getSignature().getName());
		label.append(")");
		return super.createOperation(jp).type(TYPE).label(label.toString());
	}

}

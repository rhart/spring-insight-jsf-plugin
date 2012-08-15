package com.springsource.insight.plugin.jsf;

import org.aspectj.lang.JoinPoint;

import com.springsource.insight.collection.method.MethodOperationCollectionAspect;
import com.springsource.insight.intercept.operation.Operation;
import com.springsource.insight.intercept.operation.OperationType;

public aspect ManagedBeanOperationCollectionAspect extends MethodOperationCollectionAspect {

    static final OperationType TYPE = OperationType.valueOf("jsf_managed_bean");

    public pointcut collectionPoint()
        : execution(public * (@javax.faces.bean.ManagedBean *).*(..));

    @Override
    protected Operation createOperation(JoinPoint jp) {
    	StringBuilder label = new StringBuilder("JSF Managed Bean (");
    	label.append(jp.getTarget().getClass().getSimpleName());
    	label.append(")");
        return super.createOperation(jp).type(TYPE).label(label.toString());
    }

}

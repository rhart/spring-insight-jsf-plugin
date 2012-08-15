package com.springsource.insight.plugin.jsf;

import org.aspectj.lang.JoinPoint;

import com.springsource.insight.collection.method.MethodOperationCollectionAspect;
import com.springsource.insight.intercept.operation.Operation;
import com.springsource.insight.intercept.operation.OperationType;

public aspect FacesValidatorOperationCollectionAspect extends MethodOperationCollectionAspect {

    static final OperationType TYPE = OperationType.valueOf("jsf_validator");

    public pointcut collectionPoint()
        : execution(public * (@javax.faces.validator.FacesValidator *).*(..));

    @Override
    protected Operation createOperation(JoinPoint jp) {
    	StringBuilder label = new StringBuilder("JSF Validator (");
    	label.append(jp.getTarget().getClass().getSimpleName());
    	label.append(")");
        return super.createOperation(jp).type(TYPE).label(label.toString());
    }
}

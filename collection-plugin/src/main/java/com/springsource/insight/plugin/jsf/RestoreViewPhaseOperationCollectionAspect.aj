package com.springsource.insight.plugin.jsf;

import javax.faces.context.FacesContext;

import org.apache.myfaces.lifecycle.DefaultRestoreViewSupport;
import org.apache.myfaces.lifecycle.RestoreViewSupport;
import org.aspectj.lang.JoinPoint;

import com.springsource.insight.collection.method.MethodOperationCollectionAspect;
import com.springsource.insight.intercept.operation.Operation;
import com.springsource.insight.intercept.operation.OperationType;

public aspect RestoreViewPhaseOperationCollectionAspect extends
		MethodOperationCollectionAspect {

	static final OperationType TYPE = OperationType
			.valueOf("restore_view_phase_operation");

	public pointcut collectionPoint()
        : execution(boolean org.apache.myfaces.lifecycle.RestoreViewExecutor.execute(FacesContext))
        	|| execution (void com.sun.faces.lifecycle.RestoreViewPhase.execute(FacesContext));

	@Override
	protected Operation createOperation(JoinPoint jp) {
		FacesContext facesContext = (FacesContext) jp.getArgs()[0];
		RestoreViewSupport restoreViewSupport = new DefaultRestoreViewSupport();
		String viewId = restoreViewSupport.calculateViewId(facesContext);

		StringBuilder label = new StringBuilder("JSF Restore View Phase [");
		label.append(viewId);
		label.append("]");
		return super.createOperation(jp).type(TYPE).label(label.toString())
				.put("viewId", viewId)
				.put("isPostBack", restoreViewSupport.isPostback(facesContext));
	}
}

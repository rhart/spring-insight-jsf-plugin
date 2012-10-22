package com.springsource.insight.plugin.jsf.myfaces;

import javax.faces.context.FacesContext;

import org.apache.myfaces.lifecycle.DefaultRestoreViewSupport;
import org.apache.myfaces.lifecycle.RestoreViewSupport;
import org.aspectj.lang.JoinPoint;

import com.springsource.insight.intercept.operation.Operation;
import com.springsource.insight.intercept.operation.OperationType;
import com.springsource.insight.plugin.jsf.AbstractPhaseOperationCollectionAspect;

public aspect RestoreViewPhaseOperationCollectionAspect extends
		AbstractPhaseOperationCollectionAspect {

	private static final OperationType TYPE = OperationType
			.valueOf("restore_view_phase");

	@Override
	protected OperationType getOperationType() {
		return TYPE;
	}

	public pointcut collectionPoint()
        : execution(boolean org.apache.myfaces.lifecycle.RestoreViewExecutor.execute(FacesContext));

	@Override
	protected Operation createOperation(JoinPoint jp) {
		FacesContext facesContext = (FacesContext) jp.getArgs()[0];
		RestoreViewSupport restoreViewSupport = new DefaultRestoreViewSupport();
		String viewId = restoreViewSupport.calculateViewId(facesContext);
		return super.createOperation(jp)
				.label("JSF Restore View Phase [" + viewId + "]")
				.put("viewId", viewId)
				.put("isPostBack", restoreViewSupport.isPostback(facesContext));
	}
}

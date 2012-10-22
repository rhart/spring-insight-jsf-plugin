package com.springsource.insight.plugin.jsf.myfaces;

import javax.faces.context.FacesContext;

import org.aspectj.lang.JoinPoint;

import com.springsource.insight.intercept.operation.Operation;
import com.springsource.insight.intercept.operation.OperationType;
import com.springsource.insight.plugin.jsf.AbstractPhaseOperationCollectionAspect;

public aspect RenderResponsePhaseOperationCollectionAspect extends
		AbstractPhaseOperationCollectionAspect {

	private static final OperationType TYPE = OperationType
			.valueOf("render_response_phase");

	@Override
	protected OperationType getOperationType() {
		return TYPE;
	}

	public pointcut collectionPoint()
        : execution(boolean org.apache.myfaces.lifecycle.RenderResponseExecutor.execute(FacesContext));

	@Override
	protected Operation createOperation(JoinPoint jp) {
		FacesContext facesContext = (FacesContext) jp.getArgs()[0];

		String viewId = facesContext.getViewRoot().getViewId();
		return super.createOperation(jp)
				.label("JSF Render Response Phase [" + viewId + "]")
				.put("viewId", viewId);
	}
}

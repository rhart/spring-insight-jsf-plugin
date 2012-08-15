package com.springsource.insight.plugin.jsf.myfaces;

import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.aspectj.lang.JoinPoint;

import com.springsource.insight.collection.method.MethodOperationCollectionAspect;
import com.springsource.insight.intercept.operation.Operation;
import com.springsource.insight.intercept.operation.OperationType;

public aspect PhaseExecutorOperationCollectionAspect extends
		MethodOperationCollectionAspect {

	static final OperationType TYPE = OperationType
			.valueOf("jsf_phase_executor");

	static final Map<String, String> phaseDescriptions;

	static {
		phaseDescriptions = new HashMap<String, String>();
		phaseDescriptions.put(
				"org.apache.myfaces.lifecycle.ApplyRequestValuesExecutor",
				"JSF Apply Request Values Phase");
		phaseDescriptions.put(
				"org.apache.myfaces.lifecycle.InvokeApplicationExecutor",
				"JSF Invoke Application Phase");
		phaseDescriptions.put(
				"org.apache.myfaces.lifecycle.ProcessValidationsExecutor",
				"JSF Process Validations Phase");
		phaseDescriptions.put(
				"org.apache.myfaces.lifecycle.RenderResponseExecutor",
				"JSF Render Response Phase");
		phaseDescriptions.put(
				"org.apache.myfaces.lifecycle.RestoreViewExecutor",
				"JSF Restore View Phase");
		phaseDescriptions.put(
				"org.apache.myfaces.lifecycle.UpdateModelValuesExecutor",
				"JSF Update Model Values");
	}

	public pointcut collectionPoint()
        : execution(boolean org.apache.myfaces.lifecycle.PhaseExecutor.execute(FacesContext));

	@Override
	protected Operation createOperation(JoinPoint jp) {
		return super
				.createOperation(jp)
				.type(TYPE)
				.label(phaseDescriptions.get(jp.getTarget().getClass()
						.getName()));
	}
}

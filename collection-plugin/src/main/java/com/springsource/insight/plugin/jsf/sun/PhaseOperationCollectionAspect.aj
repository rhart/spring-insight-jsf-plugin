package com.springsource.insight.plugin.jsf.sun;

import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.aspectj.lang.JoinPoint;

import com.springsource.insight.collection.method.MethodOperationCollectionAspect;
import com.springsource.insight.intercept.operation.Operation;
import com.springsource.insight.intercept.operation.OperationType;

public aspect PhaseOperationCollectionAspect extends
		MethodOperationCollectionAspect {

	static final OperationType TYPE = OperationType.valueOf("jsf_phase");

	static final Map<String, String> phaseDescriptions;

	static {
		phaseDescriptions = new HashMap<String, String>();
		phaseDescriptions.put(
				"com.sun.faces.lifecycle.ApplyRequestValuesPhase",
				"JSF Apply Request Values Phase");
		phaseDescriptions.put("com.sun.faces.lifecycle.InvokeApplicationPhase",
				"JSF Invoke Application Phase");
		phaseDescriptions.put(
				"com.sun.faces.lifecycle.ProcessValidationsPhase",
				"JSF Process Validations Phase");
		phaseDescriptions.put("com.sun.faces.lifecycle.RenderResponsePhase",
				"JSF Render Response Phase");
		phaseDescriptions.put("com.sun.faces.lifecycle.RestoreViewPhase",
				"JSF Restore View Phase");
		phaseDescriptions.put("com.sun.faces.lifecycle.UpdateModelValuesPhase",
				"JSF Update Model Values");
	}

	public pointcut collectionPoint()
    	: execution(void com.sun.faces.lifecycle.Phase.execute(FacesContext));

	@Override
	protected Operation createOperation(JoinPoint jp) {
		String targetClassName = jp.getTarget().getClass().getName();
		if (targetClassName.equals("com.sun.faces.lifecycle.RenderResponsePhase")) {
			FacesContext facesContext = (FacesContext) jp.getArgs()[0];
			return super
					.createOperation(jp)
					.type(TYPE)
					.label(phaseDescriptions.get(targetClassName) + " [" + facesContext.getViewRoot().getViewId() + "]");
		}
		
		return super
				.createOperation(jp)
				.type(TYPE)
				.label(phaseDescriptions.get(targetClassName));
	}
}

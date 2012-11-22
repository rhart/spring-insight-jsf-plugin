package com.springsource.insight.plugin.jsf;

import com.springsource.insight.intercept.endpoint.EndPointAnalysis;
import com.springsource.insight.intercept.endpoint.EndPointAnalyzer;
import com.springsource.insight.intercept.endpoint.EndPointName;
import com.springsource.insight.intercept.operation.Operation;
import com.springsource.insight.intercept.operation.OperationType;
import com.springsource.insight.intercept.trace.Frame;
import com.springsource.insight.intercept.trace.FrameUtil;
import com.springsource.insight.intercept.trace.Trace;

public class JSFActionEndPointAnalyzer implements EndPointAnalyzer {

	public EndPointAnalysis locateEndPoint(Trace trace) {
		Frame lifecycleExecuteFrame = trace
				.getFirstFrameOfType(OperationType.valueOf("jsf_action_listener_operation"));
		if (lifecycleExecuteFrame == null) {
			return null;
		}

		Frame httpFrame = trace.getFirstFrameOfType(OperationType.HTTP);
		if (httpFrame == null
				|| !FrameUtil.frameIsAncestor(httpFrame, lifecycleExecuteFrame)) {
			return null;
		}

		Operation operation = lifecycleExecuteFrame.getOperation();

		String resourceKey = operation.get("implementationClass") + "."
				+ operation.get("implementationClassMethod");
		String resourceLabel = operation.get("implementationClass") + "#"
				+ operation.get("implementationClassMethod");

		Operation httpOperation = httpFrame.getOperation();
		String exampleRequest = httpOperation.getLabel();
		int score = FrameUtil.getDepth(lifecycleExecuteFrame);
		return new EndPointAnalysis(trace.getRange(),
				EndPointName.valueOf(resourceKey), resourceLabel,
				exampleRequest, score);
	}
}

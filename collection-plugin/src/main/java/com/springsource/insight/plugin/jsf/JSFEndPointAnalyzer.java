package com.springsource.insight.plugin.jsf;

import com.springsource.insight.intercept.endpoint.EndPointAnalysis;
import com.springsource.insight.intercept.endpoint.EndPointAnalyzer;
import com.springsource.insight.intercept.endpoint.EndPointName;
import com.springsource.insight.intercept.operation.Operation;
import com.springsource.insight.intercept.operation.OperationType;
import com.springsource.insight.intercept.operation.SourceCodeLocation;
import com.springsource.insight.intercept.trace.Frame;
import com.springsource.insight.intercept.trace.FrameUtil;
import com.springsource.insight.intercept.trace.Trace;

public class JSFEndPointAnalyzer implements EndPointAnalyzer {
    
    public EndPointAnalysis locateEndPoint(Trace trace) {
        Frame lifecycleExecuteFrame = trace.getFirstFrameOfType(LifecycleExecuteOperationCollectionAspect.TYPE);
        if (lifecycleExecuteFrame == null) {
            return null;
        }
        
        Frame httpFrame = trace.getFirstFrameOfType(OperationType.HTTP);
        if (httpFrame == null || !FrameUtil.frameIsAncestor(httpFrame, lifecycleExecuteFrame)) {
            return null;
        }
        
        Operation operation = lifecycleExecuteFrame.getOperation();
        SourceCodeLocation actionLocation = operation.getSourceCodeLocation();
        
        String resourceKey = actionLocation.getClassName() + "." + actionLocation.getMethodName();
        String resourceLabel = "JSF Endpoint";
        
        Operation httpOperation = httpFrame.getOperation();
        String exampleRequest = httpOperation.getLabel();
		int score = FrameUtil.getDepth(lifecycleExecuteFrame);        
        return new EndPointAnalysis(trace.getRange(), 
                                    EndPointName.valueOf(resourceKey),
                                    resourceLabel,
                                    exampleRequest, score); 
    }
}

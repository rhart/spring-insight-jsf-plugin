package com.springsource.insight.plugin.jsf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import com.springsource.insight.intercept.application.ApplicationName;
import com.springsource.insight.intercept.endpoint.EndPointAnalysis;
import com.springsource.insight.intercept.operation.Operation;
import com.springsource.insight.intercept.operation.OperationType;
import com.springsource.insight.intercept.operation.SourceCodeLocation;
import com.springsource.insight.intercept.trace.Frame;
import com.springsource.insight.intercept.trace.FrameBuilder;
import com.springsource.insight.intercept.trace.SimpleFrameBuilder;
import com.springsource.insight.intercept.trace.Trace;
import com.springsource.insight.intercept.trace.TraceId;

public class JSFEndPointAnalyzerTest {

	private ApplicationName app = ApplicationName.valueOf("app");
	private JSFEndPointAnalyzer endPointAnalyzer;
	private Operation lifecycleExecuteOp;

	@Before
	public void setUp() {
		/*endPointAnalyzer = new JSFEndPointAnalyzer();
		lifecycleExecuteOp = new Operation()
				.type(LifecycleExecuteOperationCollectionAspect.TYPE);*/
	}

	@Test
	public void locateEndPoint_noHttp() {
		/*FrameBuilder b = new SimpleFrameBuilder();
		b.enter(new Operation());
		b.enter(lifecycleExecuteOp);
		b.exit();
		Frame rootFrame = b.exit();
		Trace trace = Trace.newInstance(app, TraceId.valueOf("0"), rootFrame);
		assertNull(endPointAnalyzer.locateEndPoint(trace));*/
	}

	@Test
	public void locateEndPoint_noJSF() {
		/*FrameBuilder b = new SimpleFrameBuilder();
		Operation httpOp = new Operation().type(OperationType.HTTP);
		b.enter(httpOp);
		b.enter(new Operation());
		b.exit();
		Frame httpFrame = b.exit();
		Trace trace = Trace.newInstance(app, TraceId.valueOf("0"), httpFrame);
		assertEquals(null, endPointAnalyzer.locateEndPoint(trace));*/
	}

	@Test
	public void locateEndPoint_httpMustComeBeforeAccount() {
		/*FrameBuilder b = new SimpleFrameBuilder();
		b.enter(lifecycleExecuteOp);
		Operation httpOp = new Operation().type(OperationType.HTTP);
		b.enter(httpOp);
		b.exit();
		Frame jsfFrame = b.exit();
		Trace trace = Trace.newInstance(app, TraceId.valueOf("0"), jsfFrame);
		assertEquals(null, endPointAnalyzer.locateEndPoint(trace));*/
	}

	@Test
	public void locateEndPoint() {
		/*lifecycleExecuteOp.label("com.sun.faces.lifecycle.LifecycleImpl")
				.sourceCodeLocation(
						new SourceCodeLocation(
								"com.sun.faces.lifecycle.LifecycleImpl",
								"execute", 111));
		FrameBuilder b = new SimpleFrameBuilder();
		Operation httpOp = new Operation().type(OperationType.HTTP);
		b.enter(httpOp);
		b.enter(lifecycleExecuteOp);
		b.exit();
		Frame httpFrame = b.exit();
		Trace trace = Trace.newInstance(app, TraceId.valueOf("0"), httpFrame);
		EndPointAnalysis endPoint = endPointAnalyzer.locateEndPoint(trace);
		assertEquals("com.sun.faces.lifecycle.LifecycleImpl.execute", endPoint
				.getEndPointName().getName());
		assertEquals("JSF Endpoint", endPoint.getResourceLabel());*/
	}

}

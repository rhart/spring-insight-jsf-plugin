package com.springsource.insight.plugin.jsf.myfaces;

import static org.junit.Assert.assertEquals;

import org.apache.myfaces.lifecycle.MockExecutor;
import org.junit.Test;

import com.springsource.insight.collection.OperationCollectionAspectSupport;
import com.springsource.insight.collection.OperationCollectionAspectTestSupport;
import com.springsource.insight.intercept.operation.Operation;

public class PhaseExecutorOperationCollectionAspectTest extends
	OperationCollectionAspectTestSupport {	
	
	@Test
	public void testExecuteMethodCollected() {
		/**
		 * First step: Execute whatever method is matched by our pointcut in
		 * {@link PhaseExecutorOperationCollectionAspect}
		 * 
		 */
		MockExecutor phase = new MockExecutor();
		phase.execute(null);

		/**
		 * Second step: Snatch the operation that was just created
		 */
		Operation op = getLastEntered(Operation.class);

		/**
		 * Third step: Validate that our operation has been created as we expect
		 */
		assertEquals(MockExecutor.class.getName(), op.getSourceCodeLocation()
				.getClassName());
		assertEquals("execute", op.getSourceCodeLocation().getMethodName());
	}

	@Override
	public OperationCollectionAspectSupport getAspect() {
		return PhaseExecutorOperationCollectionAspect.aspectOf();
	}
}

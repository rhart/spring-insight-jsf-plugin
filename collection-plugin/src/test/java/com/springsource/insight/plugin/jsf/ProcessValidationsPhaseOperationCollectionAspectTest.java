package com.springsource.insight.plugin.jsf;

import static org.junit.Assert.assertEquals;

import javax.faces.context.FacesContext;

import org.junit.Test;

import com.springsource.insight.collection.OperationCollectionAspectSupport;
import com.springsource.insight.collection.OperationCollectionAspectTestSupport;
import com.springsource.insight.intercept.operation.Operation;
import com.sun.faces.lifecycle.ProcessValidationsPhase;

/**
 * This test verifies that JSF validators are correctly captured by the aspect,
 * {@link ProcessValidationsPhaseOperationCollectionAspect}.
 */
public class ProcessValidationsPhaseOperationCollectionAspectTest extends
		OperationCollectionAspectTestSupport {

	@Test
	public void myOperationCollected() {
		/**
		 * First step: Execute whatever method is matched by our pointcut in
		 * {@link ProcessValidationsPhaseOperationCollectionAspect}
		 * 
		 */
		MockPhase bean = new MockPhase();
		bean.execute(null);

		/**
		 * Second step: Snatch the operation that was just created
		 */
		Operation op = getLastEntered(Operation.class);

		/**
		 * Third step: Validate that our operation has been created as we expect
		 */
		assertEquals(MockPhase.class.getName(), op.getSourceCodeLocation()
				.getClassName());
		assertEquals("execute", op.getSourceCodeLocation().getMethodName());
	}

	private static class MockPhase extends ProcessValidationsPhase {

		@Override
		public void execute(FacesContext facesContext) {
		}

	}

	@Override
	public OperationCollectionAspectSupport getAspect() {
		return ProcessValidationsPhaseOperationCollectionAspect.aspectOf();
	}
}

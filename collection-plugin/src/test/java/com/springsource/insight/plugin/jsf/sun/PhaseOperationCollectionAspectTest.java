package com.springsource.insight.plugin.jsf.sun;

import static org.junit.Assert.assertEquals;

import javax.faces.FacesException;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.junit.Test;

import com.springsource.insight.collection.OperationCollectionAspectSupport;
import com.springsource.insight.collection.OperationCollectionAspectTestSupport;
import com.springsource.insight.intercept.operation.Operation;
import com.sun.faces.lifecycle.Phase;

/**
 * This test verifies that {@link ApplyRequestValuesPhaseOperation} is correctly
 * captured by the aspect,
 * {@link ApplyRequestValuesPhaseOperationCollectionAspect}.
 */
public class PhaseOperationCollectionAspectTest extends
		OperationCollectionAspectTestSupport {
	@Test
	public void myOperationCollected() {
		/**
		 * First step: Execute whatever method is matched by our pointcut in
		 * {@link ApplyRequestValuesPhaseOperationCollectionAspect}
		 * 
		 */
		MockPhase phase = new MockPhase();
		phase.execute(null);

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

	private static class MockPhase extends Phase {

		@Override
		public void execute(FacesContext facesContext) throws FacesException {

		}

		@Override
		public PhaseId getId() {
			return PhaseId.ANY_PHASE;
		}
	}

	@Override
	public OperationCollectionAspectSupport getAspect() {
		return PhaseOperationCollectionAspect.aspectOf();
	}
}

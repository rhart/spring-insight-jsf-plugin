package com.springsource.insight.plugin.jsf;

import static org.junit.Assert.assertEquals;

import javax.faces.FacesException;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseListener;
import javax.faces.lifecycle.Lifecycle;

import org.junit.Test;

import com.springsource.insight.collection.OperationCollectionAspectSupport;
import com.springsource.insight.collection.OperationCollectionAspectTestSupport;
import com.springsource.insight.intercept.operation.Operation;

/**
 * This test verifies that {@link LifecycleExecuteOperation} is correctly
 * captured by the aspect,
 * {@link LifecycleExecuteOperationCollectionAspect}.
 */
public class LifecycleExecuteOperationCollectionAspectTest extends
		OperationCollectionAspectTestSupport {
	@Test
	public void myOperationCollected() {
		/**
		 * First step: Execute whatever method is matched by our pointcut in
		 * {@link LifecycleExecuteOperationCollectionAspect}
		 * 
		 */
		MockLifecycle lifecycle = new MockLifecycle();
		lifecycle.execute(null);

		/**
		 * Second step: Snatch the operation that was just created
		 */
		Operation op = getLastEntered(Operation.class);

		/**
		 * Third step: Validate that our operation has been created as we expect
		 */
		assertEquals(MockLifecycle.class.getName(), op.getSourceCodeLocation()
				.getClassName());
		assertEquals("execute", op.getSourceCodeLocation().getMethodName());
	}

	private static class MockLifecycle extends Lifecycle {

		@Override
		public void execute(FacesContext facesContext) throws FacesException {

		}

		@Override
		public void addPhaseListener(PhaseListener listener) {
			
		}

		@Override
		public PhaseListener[] getPhaseListeners() {
			return null;
		}

		@Override
		public void removePhaseListener(PhaseListener listener) {
			
		}

		@Override
		public void render(FacesContext context) throws FacesException {
			
		}

	}

	@Override
	public OperationCollectionAspectSupport getAspect() {
		return LifecycleExecuteOperationCollectionAspect.aspectOf();
	}
}

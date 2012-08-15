package com.springsource.insight.plugin.jsf;

import static org.junit.Assert.assertEquals;

import javax.faces.bean.ManagedBean;

import org.junit.Test;

import com.springsource.insight.collection.OperationCollectionAspectSupport;
import com.springsource.insight.collection.OperationCollectionAspectTestSupport;
import com.springsource.insight.intercept.operation.Operation;

/**
 * This test verifies that {@link ManagedBean} is correctly captured by the
 * aspect, {@link ManagedBeanOperationCollectionAspect}.
 */
public class ManagedBeanOperationCollectionAspectTest extends
		OperationCollectionAspectTestSupport {

	@Test
	public void myOperationCollected() {
		/**
		 * First step: Execute whatever method is matched by our pointcut in
		 * {@link ManagedBeanOperationCollectionAspect}
		 * 
		 */
		MockManagedBean bean = new MockManagedBean();
		bean.doSomething();

		/**
		 * Second step: Snatch the operation that was just created
		 */
		Operation op = getLastEntered(Operation.class);

		/**
		 * Third step: Validate that our operation has been created as we expect
		 */
		assertEquals(MockManagedBean.class.getName(), op
				.getSourceCodeLocation().getClassName());
		assertEquals("doSomething", op.getSourceCodeLocation().getMethodName());
	}

	@Test
	public void myOtherOperationCollected() {
		/**
		 * First step: Execute whatever method is matched by our pointcut in
		 * {@link ManagedBeanOperationCollectionAspect}
		 * 
		 */
		MockManagedBean bean = new MockManagedBean();
		bean.doSomethingElse();

		/**
		 * Second step: Snatch the operation that was just created
		 */
		Operation op = getLastEntered(Operation.class);

		/**
		 * Third step: Validate that our operation has been created as we expect
		 */
		assertEquals(MockManagedBean.class.getName(), op
				.getSourceCodeLocation().getClassName());
		assertEquals("doSomethingElse", op.getSourceCodeLocation()
				.getMethodName());
	}

	@ManagedBean
	private static class MockManagedBean {

		public String doSomething() {
			return "didSomething";
		}

		public String doSomethingElse() {
			return "didSomethingElse";
		}
	}

	@Override
	public OperationCollectionAspectSupport getAspect() {
		return ManagedBeanOperationCollectionAspect.aspectOf();
	}
}

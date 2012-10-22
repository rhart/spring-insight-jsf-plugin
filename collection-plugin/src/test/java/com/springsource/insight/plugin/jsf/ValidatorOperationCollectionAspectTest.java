package com.springsource.insight.plugin.jsf;

import static org.junit.Assert.assertEquals;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.junit.Ignore;
import org.junit.Test;

import com.springsource.insight.collection.OperationCollectionAspectSupport;
import com.springsource.insight.collection.OperationCollectionAspectTestSupport;
import com.springsource.insight.intercept.operation.Operation;

/**
 * This test verifies that {@link FacesValidator} is correctly captured by the
 * aspect, {@link FacesValidatorOperationCollectionAspect}.
 */
public class ValidatorOperationCollectionAspectTest extends
		OperationCollectionAspectTestSupport {

	@Test
	@Ignore
	public void myOperationCollected() {
		/**
		 * First step: Execute whatever method is matched by our pointcut in
		 * {@link ManagedBeanOperationCollectionAspect}
		 * 
		 */
		MockValidator bean = new MockValidator();
		bean.validate(null, null, null);

		/**
		 * Second step: Snatch the operation that was just created
		 */
		Operation op = getLastEntered(Operation.class);

		/**
		 * Third step: Validate that our operation has been created as we expect
		 */
		assertEquals(MockValidator.class.getName(), op.getSourceCodeLocation()
				.getClassName());
		assertEquals("validate", op.getSourceCodeLocation().getMethodName());
	}

	private static class MockValidator implements Validator {

		public void validate(FacesContext context, UIComponent component,
				Object value) throws ValidatorException {
			// Does nothing
		}
	}

	@Override
	public OperationCollectionAspectSupport getAspect() {
		return ValidatorOperationCollectionAspect.aspectOf();
	}
}

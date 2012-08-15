package com.springsource.insight.plugin.jsf;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.junit.Test;

import com.springsource.insight.collection.OperationCollectionAspectSupport;
import com.springsource.insight.collection.OperationCollectionAspectTestSupport;
import com.springsource.insight.intercept.operation.Operation;

/**
 * This test verifies that {@link FacesValidator} is correctly captured by the
 * aspect, {@link FacesValidatorOperationCollectionAspect}.
 */
public class FacesValidatorOperationCollectionAspectTest extends
		OperationCollectionAspectTestSupport {

	@Test
	public void nonValidateOperationCollected() {
		/**
		 * First step: Execute whatever method is matched by our pointcut in
		 * {@link FacesValiatorOperationCollectionAspect}
		 * 
		 */
		MockValidator bean = new MockValidator();
		bean.doSomethingOtherThanValidate();

		/**
		 * Second step: Snatch the operation that was just created
		 */
		Operation op = getLastEntered(Operation.class);

		/**
		 * Third step: Validate that our operation has been created as we expect
		 */
		assertEquals(MockValidator.class.getName(), op.getSourceCodeLocation()
				.getClassName());
		assertEquals("doSomethingOtherThanValidate", op.getSourceCodeLocation()
				.getMethodName());
	}

	@Test
	public void validateOperationNotCollected() {
		/**
		 * First step: Set up new spiedOperationCollector and set as collector
		 * for aspect.
		 */
		spiedOperationCollector = spy(originalOperationCollector);
		getAspect().setCollector(spiedOperationCollector);

		/**
		 * Second step: Execute the method we do *not* want to be called.
		 */
		MockValidator bean = new MockValidator();
		bean.validate(null, null, null);

		/**
		 * Third step: Verify the collector was not called.
		 */
		verifyNoMoreInteractions(spiedOperationCollector);
		getAspect().setCollector(originalOperationCollector);
	}

	@FacesValidator(value = "MockValidator")
	private static class MockValidator implements Validator {

		public void doSomethingOtherThanValidate() {

		}

		public void validate(FacesContext context, UIComponent component,
				Object value) throws ValidatorException {
			// Does nothing
		}
	}

	@Override
	public OperationCollectionAspectSupport getAspect() {
		return FacesValidatorOperationCollectionAspect.aspectOf();
	}
}

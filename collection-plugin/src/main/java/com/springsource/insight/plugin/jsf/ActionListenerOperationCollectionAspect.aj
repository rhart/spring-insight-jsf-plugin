package com.springsource.insight.plugin.jsf;

import javax.el.MethodExpression;
import javax.faces.component.ActionSource;
import javax.faces.component.ActionSource2;
import javax.faces.component.UIComponent;
import javax.faces.el.MethodBinding;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.faces.event.MethodExpressionActionListener;

import org.aspectj.lang.JoinPoint;

import com.springsource.insight.collection.method.MethodOperationCollectionAspect;
import com.springsource.insight.intercept.operation.Operation;
import com.springsource.insight.intercept.operation.OperationType;

public aspect ActionListenerOperationCollectionAspect extends
		MethodOperationCollectionAspect {

	static final OperationType TYPE = OperationType
			.valueOf("jsf_action_listener");

	public pointcut collectionPoint()
        : execution(public void ActionListener.processAction(ActionEvent));

	@Override
	protected Operation createOperation(JoinPoint jp) {
		String fromAction = null;
		
		if (jp.getTarget() instanceof MethodExpressionActionListener) {
			MethodExpressionActionListener listener = (MethodExpressionActionListener) jp.getTarget();
			fromAction = "XX";
		} else {
		
			ActionEvent actionEvent = (ActionEvent) jp.getArgs()[0];
			UIComponent component = actionEvent.getComponent();
	
			MethodExpression methodExpression = null;
			MethodBinding methodBinding = null;
	
	
			if (component instanceof ActionSource2) {
				// Must be an instance of ActionSource2, so don't look on action if
				// the actionExpression is set
				methodExpression = ((ActionSource2) component)
						.getActionExpression();
			}
			if (methodExpression == null && component instanceof ActionSource) {
				// Backwards compatibility for pre-1.2.
				methodBinding = ((ActionSource) component).getAction();
			}
	
			if (methodExpression != null) {
				fromAction = methodExpression.getExpressionString();
			}
	
			else if (methodBinding != null) {
				fromAction = methodBinding.getExpressionString();
			}
		}

		StringBuilder label = new StringBuilder("JSF Action (");
		label.append(fromAction);
		label.append(")");
		return super.createOperation(jp).type(TYPE).label(label.toString());
	}
}

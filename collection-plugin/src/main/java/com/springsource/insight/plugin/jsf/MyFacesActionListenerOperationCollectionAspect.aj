package com.springsource.insight.plugin.jsf;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.faces.event.MethodExpressionActionListener;

import org.apache.myfaces.view.facelets.tag.jsf.PartialMethodExpressionActionListener;

import com.springsource.insight.intercept.operation.OperationType;

public aspect MyFacesActionListenerOperationCollectionAspect extends
		AbstractActionListenerOperationCollectionAspect {

	static final OperationType TYPE = OperationType
			.valueOf("jsf_action_listener_operation");

	public pointcut collectionPoint()
        : execution(public void ActionListener.processAction(ActionEvent))
            && !(within(org.apache.myfaces.view.facelets.tag.jsf.core.ActionListenerHandler) || within(com.sun.faces.**));

	@Override
	protected Object loadState(FacesContext ctx,
			MethodExpressionActionListener listener) {
		Object state = null;
		if (listener instanceof PartialMethodExpressionActionListener) {
			PartialMethodExpressionActionListener partialListener = (PartialMethodExpressionActionListener) listener;
			partialListener.clearInitialState();
			state = partialListener.saveState(ctx);
			partialListener.markInitialState();
		}
		return state;
	}
}

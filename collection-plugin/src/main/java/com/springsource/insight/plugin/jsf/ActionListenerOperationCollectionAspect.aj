package com.springsource.insight.plugin.jsf;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.faces.event.MethodExpressionActionListener;

import com.springsource.insight.intercept.operation.OperationType;

@SuppressWarnings("deprecation")
public aspect ActionListenerOperationCollectionAspect extends AbstractActionListenerOperationCollectionAspect {

    static final OperationType TYPE = OperationType.valueOf("jsf_action_listener_operation");

    public pointcut collectionPoint()
        : execution(public void ActionListener.processAction(ActionEvent))
            && !(within(com.sun.faces.facelets.tag.jsf.core.ActionListenerHandler)
                    || within(org.apache.myfaces.view.facelets.tag.jsf.core.ActionListenerHandler)
                    || within(org.apache.myfaces.view.facelets.tag.jsf.PartialMethodExpressionActionListener));

    protected Object loadState(FacesContext ctx, MethodExpressionActionListener listener) {
        return null;
    }
}

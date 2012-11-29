package com.springsource.insight.plugin.jsf.test.bean;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class ExamplePhaseListener implements PhaseListener {

    private static final long serialVersionUID = 20121125L;

    public void beforePhase(PhaseEvent event) {
        System.out.println("In before phase");
    }

    public void afterPhase(PhaseEvent event) {
        System.out.println("In after phase");
    }

    public PhaseId getPhaseId() {
        return PhaseId.RENDER_RESPONSE;
    }
}

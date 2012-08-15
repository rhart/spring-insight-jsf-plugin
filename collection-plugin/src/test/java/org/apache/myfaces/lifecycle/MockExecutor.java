package org.apache.myfaces.lifecycle;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

public class MockExecutor extends PhaseExecutor {

	@Override
	public boolean execute(FacesContext facesContext) {
		return false;
	}

	@Override
	public PhaseId getPhase() {
		return PhaseId.ANY_PHASE;
	}
}

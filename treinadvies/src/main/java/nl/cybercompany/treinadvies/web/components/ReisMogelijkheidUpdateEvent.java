package nl.cybercompany.treinadvies.web.components;

import nl.cybercompany.treinadvies.domain.ReisMogelijkheid;

import org.apache.wicket.ajax.AjaxRequestTarget;

public class ReisMogelijkheidUpdateEvent {

	private final AjaxRequestTarget target;

	private final ReisMogelijkheid reisMogelijkheid;

	public ReisMogelijkheidUpdateEvent(AjaxRequestTarget target, ReisMogelijkheid reisMogelijkheid) {
		this.target = target;
		this.reisMogelijkheid = reisMogelijkheid;
	}

	public AjaxRequestTarget getTarget() {
		return target;
	}

	public ReisMogelijkheid getReisMogelijkheid() {
		return reisMogelijkheid;
	}
}

/**
 *
 */
package nl.cybercompany.treinadvies.web.components;

import java.util.Iterator;
import java.util.List;

import nl.cybercompany.treinadvies.domain.ReisMogelijkheid;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;

/**
 * @author haiko
 *
 */
public class ReisMogelijkhedenPanel extends Panel {

	private ReisMogelijkheid currentSelected;

	private WebMarkupContainer container;

	public ReisMogelijkhedenPanel(String id, IModel<List<ReisMogelijkheid>> model, IModel<ReisMogelijkheid> selectedReisMogelijkheidModel) {
		super(id, model);

		currentSelected = selectedReisMogelijkheidModel.getObject();

		List<ReisMogelijkheid> reisMogelijkheden = model.getObject();

		container = new WebMarkupContainer("timeTable");
		container.setOutputMarkupId(Boolean.TRUE);

		add(container.add(getTimeTable(reisMogelijkheden)));
	}


	private Component getTimeTable(final List<ReisMogelijkheid> reisMogelijkheden) {
		RepeatingView reisMogelijkhedenView = new RepeatingView("reisMogelijkhedenView");
		reisMogelijkhedenView.setOutputMarkupId(Boolean.TRUE);

		for (Iterator iterator = reisMogelijkheden.iterator(); iterator
				.hasNext();) {
			final ReisMogelijkheid reisMogelijkheid = (ReisMogelijkheid) iterator
					.next();
			final AbstractItem item = new AbstractItem(reisMogelijkhedenView.newChildId());

			item.add(new Label("vertrektijd",reisMogelijkheid.getVertrekTijdInUurEnMinuten()));
			item.add(new Label("aankomsttijd",reisMogelijkheid.getAankomstTijdInUurEnMinuten()));
			item.add(new Label("overstap",Integer.toString(reisMogelijkheid.getDelen().size() -1)));
			item.add(new Label("reistijd",reisMogelijkheid.getReisTijd()));

			if(reisMogelijkheid.equals(currentSelected)){
				item.add(AttributeModifier.append("class", "selected"));
			}

			item.add(new AjaxEventBehavior("onclick"){

				@Override
				protected void onEvent(AjaxRequestTarget target) {
					send(getPage(), Broadcast.BREADTH, new ReisMogelijkheidUpdateEvent(target, reisMogelijkheid));
					currentSelected = reisMogelijkheid;
					container.replace(getTimeTable(reisMogelijkheden));
					target.add(container);

				}

			});
			reisMogelijkhedenView.add(item);

		}
		return reisMogelijkhedenView.setOutputMarkupId(Boolean.TRUE);
	}

}

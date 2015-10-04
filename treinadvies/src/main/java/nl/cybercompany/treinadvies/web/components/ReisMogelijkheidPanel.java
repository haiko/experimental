/**
 *
 */
package nl.cybercompany.treinadvies.web.components;

import static nl.cybercompany.treinadvies.util.Checks.*;

import java.util.Iterator;
import java.util.List;

import nl.cybercompany.treinadvies.domain.Punt;
import nl.cybercompany.treinadvies.domain.ReisDeel;
import nl.cybercompany.treinadvies.domain.ReisMogelijkheid;
import nl.cybercompany.treinadvies.domain.StationTransferPunt;
import nl.cybercompany.treinadvies.nsapi.Melding;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * @author haiko
 *
 */
public class ReisMogelijkheidPanel extends Panel {

	private WebMarkupContainer container;

	public ReisMogelijkheidPanel(String id, IModel<ReisMogelijkheid> model) {
		super(id);


		container = new WebMarkupContainer("reisMogelijkheidContainer");
		container.setOutputMarkupId(Boolean.TRUE);
		ReisInfoView view  = new ReisInfoView("reisDelenView", model);

		if(!model.getObject().getMeldingen().isEmpty()){

			Fragment frag1 = new Fragment("placeholder", "alertsFragment", container);
			RepeatingView alerts = new RepeatingView("alerts");

			List<Melding> meldingen = model.getObject().getMeldingen();

			for (Iterator iterator = meldingen.iterator(); iterator.hasNext();) {
				Melding melding = (Melding) iterator.next();
				AbstractItem item = new AbstractItem(alerts.newChildId());
				item.add(new Label("text", melding.getId()));
			}

			frag1.add(alerts);
			container.add(frag1);
		}
		else {
			Fragment frag2 = new Fragment("placeholder", "noalertsFragment", container);
			container.add(frag2);
		}
		container.add(view.setOutputMarkupId(Boolean.TRUE));
		add(container);

	}

	@Override
	public void onEvent(IEvent<?> event) {
		super.onEvent(event);

		if(event.getPayload() instanceof ReisMogelijkheidUpdateEvent){
			ReisMogelijkheidUpdateEvent reisMogelijkheidUpdateEvent = (ReisMogelijkheidUpdateEvent) event.getPayload();
			AjaxRequestTarget target = reisMogelijkheidUpdateEvent.getTarget();
			ReisMogelijkheid mogelijkheid = reisMogelijkheidUpdateEvent.getReisMogelijkheid();

			ReisInfoView infoView = new ReisInfoView("reisDelenView", Model.of(mogelijkheid));
			container.addOrReplace(infoView.setOutputMarkupId(Boolean.TRUE));
			target.add(container);
		}
	}

	private class ReisInfoView  extends RepeatingView {

		ReisInfoView(String id, IModel<ReisMogelijkheid> model){
			super(id);

			List<ReisDeel> delen = model.getObject().getDelen();

			DateTimeFormatter format = DateTimeFormat.forPattern("HH:mm");

			for (Iterator iterator = delen.iterator(); iterator.hasNext();) {
				ReisDeel reisDeel = (ReisDeel) iterator.next();

				List<Punt> punten =  reisDeel.getReisPunten();

				for (Iterator iterator2 = punten.iterator(); iterator2.hasNext();) {
					Punt punt = (Punt) iterator2.next();
					AbstractItem item = new AbstractItem(this.newChildId());


					item.add(new Label("tijd",punt.getVertrekTijd().toString(format)));
					item.add(new Label("station", punt.getStation().getName()));

					if(punt instanceof StationTransferPunt){
						StationTransferPunt transfer = (StationTransferPunt) punt;
						item.add((new Label("spoor", transfer.getSpoor())));
					}
					else {
						item.add((new Label("spoor", "")));
						item.add(AttributeModifier.append("class", Model.of("tussenstation")));
					}


					if(punten.indexOf(punt) == 0 && notNull(reisDeel.getVervoer())){
						String reisDetails = reisDeel.getVervoer().getType().concat(" (").concat(reisDeel.getVervoer().getVervoerder()).concat(")");
						item.add(new Label("reisDetails", reisDetails));
					}
					else {
						item.add(new Label("reisDetails", ""));
					}

					if(delen.indexOf(reisDeel)%2==0){
						item.add(new AttributeAppender("class", Model.of(" odd")));
					}


					this.add(item);
				}

			}
		}
	}

}

/**
 *
 */
package nl.cybercompany.treinadvies.web.pages;

import static nl.cybercompany.treinadvies.util.CollectionUtil.last;
import static nl.cybercompany.treinadvies.web.util.WicketUtil.prefix;

import java.io.Serializable;
import java.util.List;

import nl.cybercompany.treinadvies.domain.NoResultException;
import nl.cybercompany.treinadvies.domain.ReisAdvies;
import nl.cybercompany.treinadvies.domain.ReisMogelijkheid;
import nl.cybercompany.treinadvies.domain.ReisVraag;
import nl.cybercompany.treinadvies.web.application.TreinAdviesApplication;
import nl.cybercompany.treinadvies.web.components.ReisMogelijkhedenPanel;
import nl.cybercompany.treinadvies.web.components.ReisMogelijkheidPanel;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ReisAdvies pagina
 *
 * @author haiko
 *
 */
public class ReisAdviesPage extends BasePage {

	private static final Logger logger = LoggerFactory.getLogger(ReisAdviesPage.class);

	private ReisVraag reisVraag;

	public ReisAdviesPage(PageParameters parameters){
		String vertrekStation = parameters.get(0).toString();
		String aankomstStation = parameters.get(1).toString();
		String tijdAsString = parameters.get(HomePage.TIJD_PARAM).toString();

		DateTime tijd;
		if(tijdAsString != null){
			tijd = DateTimeFormat.forPattern(HomePage.TIJD_PATTERN).parseDateTime(tijdAsString);
		}
		else {
			tijd = new DateTime(DateTimeZone.forID("Europe/Amsterdam"));
		}

		reisVraag = ReisVraag.createSimpleReisVraag(vertrekStation, aankomstStation, tijd);
		DateTimeFormatter format = DateTimeFormat.forPattern("HH:mm EEEE d MMMM").withLocale(getLocale());
		String prettyTijd = format.print(tijd);
		add(new Label("treinreis", new StringResourceModel("reisadviespage.label.treinreis", null, new Object[] {reisVraag.getVertrekStation(), reisVraag.getAankomstStation(), prettyTijd})));

		WebMarkupContainer container = new WebMarkupContainer("container");

		ReisAdvies reisAdvies;
		try {

			reisAdvies = ((TreinAdviesApplication)getApplication()).getReisplannerService().planReis(reisVraag);
			ReisMogelijkheidPanel reisMogelijkheidPanel = new ReisMogelijkheidPanel("reisMogelijkheidPanel", new Model(selectReisMogelijkheid(reisAdvies.getReisMogelijkheden(), tijd)));
			reisMogelijkheidPanel.setOutputMarkupId(Boolean.TRUE);

			Fragment frag = new Fragment("placeholder", "resultaatFragment", container);
			frag.add(new ReisMogelijkhedenPanel("reisMogelijkhedenPanel", new Model((Serializable) reisAdvies.getReisMogelijkheden()), Model.of(reisAdvies.getReisMogelijkheden().get(3))));
			frag.add(reisMogelijkheidPanel);
			container.add(frag);

		} catch (NoResultException e) {
			logger.error("geen resultaten voor " + reisVraag, e);
			Fragment frag = new Fragment("placeholder", "noresultaatFragment", container);
			frag.add(new Label("noresultsLabel", new StringResourceModel("reisadviespage.noresults", null, null)));
			container.add(frag);
		}

		add(container);
	}



	@Override
	protected void onBeforeRender() {
		// add the <title> tag
		String title =  (String) getPageTitle().getObject();
		addOrReplace(new Label("title", getPageTitle()));

		Label desc = new Label("description", "");
		desc.add(new AttributeAppender("CONTENT", getDescription(), " "));
		addOrReplace(desc);

		Label keywords = new Label("keywords", "");
		keywords.add(new AttributeAppender("CONTENT", getKeywords(), " "));
		addOrReplace(keywords);

		super.onBeforeRender();
	}

	@Override
	public IModel getPageTitle() {
		return new StringResourceModel(prefix(this).concat(TITLE),  new Model(reisVraag));
	}

	@Override
	public IModel getDescription() {
		return new StringResourceModel(prefix(this).concat(METADESCRIPTION), new Model(reisVraag));

	}

	@Override
	public IModel getKeywords() {
		return new StringResourceModel(prefix(this).concat(METAKEYWORDS), this, new Model(reisVraag));
	}

	/**
	 * Selecteer reismogelijkheid net na opgegeven tijd.
	 *
	 * @param reisMogelijkheden
	 * @param tijd
	 * @return
	 */
	private ReisMogelijkheid selectReisMogelijkheid(
			List<ReisMogelijkheid> reisMogelijkheden, DateTime tijd) {
		for (ReisMogelijkheid reisMogelijkheid : reisMogelijkheden) {
			if(reisMogelijkheid.getVertrekTijd().isAfter(tijd)){
				logger.debug("selecteer reismogelijkheid:" + reisMogelijkheid.toString());
				return reisMogelijkheid;
			}
		}
		return last(reisMogelijkheden);
	}
}

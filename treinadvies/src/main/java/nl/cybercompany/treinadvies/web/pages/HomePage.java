package nl.cybercompany.treinadvies.web.pages;

import static nl.cybercompany.treinadvies.web.util.WicketUtil.prefix;
import nl.cybercompany.treinadvies.domain.ReisVraag;
import nl.cybercompany.treinadvies.web.components.StationAutoCompleteTextField;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.wicket.jquery.ui.form.button.Button;

public class HomePage extends BasePage {

	// TODO validation
	// TODO via station
	static String TIJD_PATTERN = "HH:mm dd-MM-yyyy" ;

	static String TIJD_PARAM = "tijd" ;


	private static Logger logger = LoggerFactory.getLogger(HomePage.class);

	private static final long serialVersionUID = 1L;

	public HomePage() {

		final ReisVraag planReis = new ReisVraag();
		Form<Void> planReisForm = new Form("planReisForm");
		planReisForm.add(new StationAutoCompleteTextField("vertrekStation",
				new PropertyModel<ReisVraag>(planReis, "vertrekStation"))
				.setRequired(true));
		planReisForm.add(new StationAutoCompleteTextField("aankomstStation",
				new PropertyModel<ReisVraag>(planReis, "aankomstStation"))
				.setRequired(true));
		Button submitButton = new Button("submit") {

			@Override
			public void onSubmit() {

				PageParameters pageParameters = new PageParameters();
				pageParameters.set(0, planReis.getVertrekStation());
				pageParameters.set(1, planReis.getAankomstStation());
				pageParameters.add(TIJD_PARAM, new DateTime().toString(DateTimeFormat.forPattern(TIJD_PATTERN)));

				setResponsePage(ReisAdviesPage.class, pageParameters);

			}

		};
		planReisForm.add(submitButton);
		add(planReisForm);

	}

	@Override
	protected void onBeforeRender() {
		// add the <title> tag
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
		return new ResourceModel(prefix(this).concat(TITLE));
	}

	@Override
	public IModel getDescription() {
		return new ResourceModel(prefix(this).concat(METADESCRIPTION));
	}

	@Override
	public IModel getKeywords() {
		return new ResourceModel(prefix(this).concat(METAKEYWORDS));
	}
}

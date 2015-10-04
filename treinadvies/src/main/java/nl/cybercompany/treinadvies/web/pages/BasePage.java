/**
 *
 */
package nl.cybercompany.treinadvies.web.pages;

import java.util.Properties;

import nl.cybercompany.treinadvies.web.application.TreinAdviesApplication;
import nl.cybercompany.treinadvies.web.components.Footer;
import nl.cybercompany.treinadvies.web.style.FixBootstrapStylesCssResourceReference;

import org.apache.wicket.Component;
import org.apache.wicket.devutils.debugbar.DebugBar;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.GenericWebPage;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.util.string.StringValue;

import de.agilecoders.wicket.Bootstrap;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.button.dropdown.DropDownButton;
import de.agilecoders.wicket.markup.html.bootstrap.button.dropdown.MenuDivider;
import de.agilecoders.wicket.markup.html.bootstrap.button.dropdown.MenuHeader;
import de.agilecoders.wicket.markup.html.bootstrap.button.dropdown.MenuPageButton;
import de.agilecoders.wicket.markup.html.bootstrap.html.ChromeFrameMetaTag;
import de.agilecoders.wicket.markup.html.bootstrap.html.HtmlTag;
import de.agilecoders.wicket.markup.html.bootstrap.html.OptimizedMobileViewportMetaTag;
import de.agilecoders.wicket.markup.html.bootstrap.image.Icon;
import de.agilecoders.wicket.markup.html.bootstrap.image.IconType;
import de.agilecoders.wicket.markup.html.bootstrap.navbar.AffixBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.navbar.Navbar;
import de.agilecoders.wicket.markup.html.bootstrap.navbar.NavbarButton;
import de.agilecoders.wicket.markup.html.bootstrap.navbar.NavbarComponents;
import de.agilecoders.wicket.markup.html.bootstrap.navbar.NavbarDropDownButton;
import de.agilecoders.wicket.settings.IBootstrapSettings;

/**
 * Basis pagina voor deze Applicatie
 *
 *
 * @author haiko
 *
 */
public abstract class BasePage extends GenericWebPage {

	protected static String METADESCRIPTION = "meta.description";

	protected static String METAKEYWORDS = "meta.keywords";

	protected static String TITLE = "title";

	/**
	 * Deliver a title for the Page
	 *
	 * @return an {@link IModel} with a title.
	 */
	public abstract IModel getPageTitle();

	/**
	 * Deliver a meta description for the page
	 *
	 * @return an {@link IModel} with a description.
	 */
	public abstract IModel getDescription();

	/**
	 * Deliver meta keywords for the page
	 *
	 * @return an {@link IModel} with keywords.
	 */
	public abstract IModel getKeywords();

	/**
	 * Construct.
	 */
	public BasePage() {
		super();

		commonInit(new PageParameters());
	}

	/**
	 * Construct.
	 *
	 * @param model
	 *            The model to use for this page
	 */
	public BasePage(IModel model) {
		super(model);

		commonInit(new PageParameters());
	}

	/**
	 * Construct.
	 *
	 * @param parameters
	 *            current page parameters
	 */
	public BasePage(PageParameters parameters) {
		super(parameters);

		commonInit(parameters);
	}

	/**
	 * @return application properties
	 */
	public Properties getProperties() {
		return TreinAdviesApplication.get().getProperties();
	}

	/**
	 * common initializer
	 *
	 * @param pageParameters
	 *            current page parameters
	 */
	private void commonInit(PageParameters pageParameters) {
		add(new HtmlTag("html"));

		add(new OptimizedMobileViewportMetaTag("viewport"));
		add(new ChromeFrameMetaTag("chrome-frame"));
		add(newNavbar("navbar"));
		add(newNavigation("navigation"));
		add(new Footer("footer"));

		add(new BootstrapBaseBehavior());

		if (getApplication().getDebugSettings().isDevelopmentUtilitiesEnabled()) {
		    add(new DebugBar("dev"));
		} else {
		    add(new EmptyPanel("dev").setVisible(false));
		}
	}

	/**
	 * creates a new {@link Navbar} instance
	 *
	 * @param markupId
	 *            The components markup id.
	 * @return a new {@link Navbar} instance
	 */
	protected Navbar newNavbar(String markupId) {
		Navbar navbar = new Navbar(markupId);

		navbar.setPosition(Navbar.Position.STATIC_TOP);
		// show brand name and logo
		navbar.brandName(Model.of("Trein Advies"));
		navbar.setBrandImage(new PackageResourceReference(BasePage.class,
				"trein-logo-small.png"), Model.of("Trein Advies logo"));

		// show dark navbar
		navbar.invert(false);

		navbar.addComponents(NavbarComponents.transform(
				Navbar.ComponentPosition.LEFT, new NavbarButton<HomePage>(
						HomePage.class, Model.of("Overview")).setIcon(new Icon(
						IconType.Home))));

		DropDownButton dropdown = new NavbarDropDownButton(Model.of("More..."))
				.addButton(
						new MenuPageButton<HomePage>(HomePage.class, Model
								.of("Overview"))
								.setIcon(new Icon(IconType.Home)))
				.addButton(new MenuDivider())
				.addButton(new MenuHeader(Model.of("Themes")))
				.setIcon(IconType.AlignJustify);

		IBootstrapSettings settings = Bootstrap.getSettings(getApplication());
		// List<ITheme> themes = settings.getThemeProvider().available();
		//
		// for (ITheme theme : themes) {
		// PageParameters params = new PageParameters();
		// params.set("theme", theme.name());
		//
		// dropdown.addButton(new MenuPageButton<Page>(getPageClass(), params,
		// Model.of(theme.name())));
		// }
		//
		// navbar.addButton(Navbar.ButtonPosition.RIGHT, dropdown);

		return navbar;
	}

	/**
	 * sets the theme for the current user.
	 *
	 * @param pageParameters
	 *            current page parameters
	 */
	private void configureTheme(PageParameters pageParameters) {
		StringValue theme = pageParameters.get("theme");

		if (!theme.isEmpty()) {
			IBootstrapSettings settings = Bootstrap
					.getSettings(getApplication());
			settings.getActiveThemeProvider()
					.setActiveTheme(theme.toString(""));
		}
	}

	@Override
	protected void onConfigure() {
		super.onConfigure();

		configureTheme(getPageParameters());
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);

		response.render(CssHeaderItem
				.forReference(FixBootstrapStylesCssResourceReference.INSTANCE));
	}

	protected boolean hasNavigation() {
		return false;
	}

	/**
	 * creates a new navigation component.
	 *
	 * @param markupId
	 *            The component's markup id
	 * @return a new navigation component.
	 */
	private Component newNavigation(String markupId) {
		WebMarkupContainer navigation = new WebMarkupContainer(markupId);
		navigation.add(new AffixBehavior("200"));
		navigation.setVisible(hasNavigation());

		return navigation;
	}

}

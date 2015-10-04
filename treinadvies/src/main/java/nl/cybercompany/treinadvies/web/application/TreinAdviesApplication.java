package nl.cybercompany.treinadvies.web.application;

import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

import nl.cybercompany.treinadvies.business.ReisplannerService;
import nl.cybercompany.treinadvies.domain.StationList;
import nl.cybercompany.treinadvies.web.pages.HomePage;
import nl.cybercompany.treinadvies.web.pages.ReisAdviesPage;

import org.apache.wicket.Application;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.Session;
import org.apache.wicket.core.request.mapper.MountedMapper;
import org.apache.wicket.devutils.DevelopmentUtilitiesNotEnabledException;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.mapper.parameter.PageParametersEncoder;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

import de.agilecoders.wicket.Bootstrap;
import de.agilecoders.wicket.settings.BootstrapSettings;
import de.agilecoders.wicket.settings.BootswatchThemeProvider;
import de.agilecoders.wicket.settings.ThemeProvider;

/**
 * Application object for your web application. If you want to run this
 * application without deploying, run the Start class.
 *
 * @see nl.cybercompany.treinadvies.web.pages.application.Start#main(String[])
 */
public class TreinAdviesApplication extends WebApplication {

	private Properties properties;

	@SpringBean
	private ReisplannerService reisplannerService;

	private StationList stationList;

	/**
	 * Get Application for current thread.
	 *
	 * @return The current thread's Application
	 */
	public static TreinAdviesApplication get() {
		return (TreinAdviesApplication) Application.get();
	}

	public TreinAdviesApplication() {
		super();
		properties = loadProperties();
		setConfigurationType(RuntimeConfigurationType.valueOf(properties
				.getProperty("configuration.type")));
	}

	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<HomePage> getHomePage() {
		return HomePage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init() {
		super.init();
		getComponentInstantiationListeners().add(
				new SpringComponentInjector(this));
		Injector.get().inject(this);

		getRootRequestMapperAsCompound().add(
				new MountedMapper("advies", ReisAdviesPage.class,
						new PageParametersEncoder()));

		this.getApplicationSettings().setPageExpiredErrorPage(getHomePage());
		this.getApplicationSettings().setInternalErrorPage(getHomePage());
		this.getApplicationSettings().setAccessDeniedPage(getHomePage());

		BootstrapSettings settings = new BootstrapSettings();
		settings.minify(true); // use minimized version of all bootstrap
								// references

		Bootstrap.install(this, settings);

		if(RuntimeConfigurationType.DEVELOPMENT.equals(this.getConfigurationType())){
			getDebugSettings().setDevelopmentUtilitiesEnabled(true);
			getMarkupSettings().setCompressWhitespace(true);


		}

		if(RuntimeConfigurationType.DEPLOYMENT.equals(this.getConfigurationType())){
			getMarkupSettings().setCompressWhitespace(true);
			getMarkupSettings().setStripComments(true);
			getMarkupSettings().setStripWicketTags(true);
		}

	}

	/**
	 * Get {@link StationList}
	 *
	 * @return
	 */
	public StationList getStationList() {

		if (stationList == null) {
			// initialize stationlist.
			this.stationList = reisplannerService.getStationList();
		}

		return stationList;
	}



	@Override
	public Session newSession(Request request, Response response) {
		Session session = super.newSession(request, response);

		// fix on dutch for now
		session.setLocale(new Locale("nl"));
		return session;
	}

	public ReisplannerService getReisplannerService() {
		return reisplannerService;
	}

	public void setReisplannerService(ReisplannerService reisplannerService) {
		this.reisplannerService = reisplannerService;
	}

	public Properties getProperties() {
		return properties;
	}

	private Properties loadProperties() {
		Properties properties = new Properties();
		try {
			properties.load(getClass()
					.getResourceAsStream("/config.properties"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return properties;
	}

	private void configureBootstrap() {
        BootstrapSettings settings = new BootstrapSettings();
        settings.minify(true) // use minimized version of all bootstrap references
            .useJqueryPP(true)
            .useModernizr(true)
            .useResponsiveCss(true)
            .getBootstrapLessCompilerSettings().setUseLessCompiler(true);

        ThemeProvider themeProvider = new BootswatchThemeProvider() {{
                defaultTheme("bootstrap");
        }};
        settings.setThemeProvider(themeProvider);

        Bootstrap.install(this, settings);
    }
}

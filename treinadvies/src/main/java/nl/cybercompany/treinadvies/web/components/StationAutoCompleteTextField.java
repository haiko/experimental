/**
 * 
 */
package nl.cybercompany.treinadvies.web.components;

import static nl.cybercompany.treinadvies.util.StringUtil.length;
import static nl.cybercompany.treinadvies.util.StringUtil.startsWithIgnoreCase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import nl.cybercompany.treinadvies.domain.Station;
import nl.cybercompany.treinadvies.domain.StationList;
import nl.cybercompany.treinadvies.web.application.TreinAdviesApplication;

import org.apache.wicket.model.IModel;

import com.googlecode.wicket.jquery.ui.form.autocomplete.AutoCompleteTextField;

/**
 * Autocompletes input with station names.
 * 
 * @author haiko
 *
 */
public class StationAutoCompleteTextField extends AutoCompleteTextField {
	
	private StationList stationList;

	public StationAutoCompleteTextField(String id, IModel model) {
		super(id, model);
		stationList = ((TreinAdviesApplication)getApplication()).getStationList();
		
	}

	/* (non-Javadoc)
	 * @see org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteTextField#getChoices(java.lang.String)
	 */
	@Override
	protected List getChoices(String input) {
		
		// begin autocomplete when 2 characters are given
		if(length(input) < 2){
			return Collections.EMPTY_LIST;
		}
		
		List<String> choices = new ArrayList<String>();
		List<Station> stations = stationList.getStations();
		
		for (Station station : stations) {
			if(startsWithIgnoreCase(station.getName(),(input))) {
				choices.add(station.getName());
			}
		}		
		return choices;
	}	
}

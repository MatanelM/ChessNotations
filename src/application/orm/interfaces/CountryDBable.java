package application.orm.interfaces;

import java.util.ArrayList;

import application.orm.entities.Country;

public interface CountryDBable {

	public ArrayList<Country> getAllCountries();

}

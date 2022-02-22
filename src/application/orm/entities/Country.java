package application.orm.entities;

public class Country {
	
	private String name;
	private String Alpha2Code;
	private String Alpha3Code;
	private int CountryId;
	public Country(String name, String alpha2Code, String alpha3Code, int countryId) {
		super();
		this.name = name;
		Alpha2Code = alpha2Code;
		Alpha3Code = alpha3Code;
		CountryId = countryId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAlpha2Code() {
		return Alpha2Code;
	}
	public void setAlpha2Code(String alpha2Code) {
		Alpha2Code = alpha2Code;
	}
	public String getAlpha3Code() {
		return Alpha3Code;
	}
	public void setAlpha3Code(String alpha3Code) {
		Alpha3Code = alpha3Code;
	}
	public int getCountryId() {
		return CountryId;
	}
	public void setCountryId(int countryId) {
		CountryId = countryId;
	}
	
	
	
}

package application.util;

import application.orm.entities.CountryEnum;

public class Util {

	
	public static final String MainFont = "Verdana";
	public static String dbUrl = "jdbc:mysql://localhost:3306/chess";
	public static String sqlUser = "david";
	public static String sqlPass = "my-secret-pwd";

	public static CountryEnum getCountryEnumByAlpha3(String name) {
		
		for (int i = 0; i < CountryEnum.values().length; i++) {
			if ( CountryEnum.values()[i].getAlpha3Code().equals(name))
				return CountryEnum.values()[i];
			
		}
		return null;
		
	}
	
	public static String findExtension(String fileName) {
	    int lastIndex = fileName.lastIndexOf('.');
	    if (lastIndex == -1) {
	        return "";
	    }
	    return fileName.substring(lastIndex + 1);
	}
}

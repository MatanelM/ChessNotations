package application.orm.entities;

public enum CountryEnum {

	AFGHANISTAN("Afghanistan", "AFG", 4), ALBANIA("Albania", "ALB", 8), ANTARCTICA("Antarctica", "ATA", 10),
	ALGERIA("Algeria", "DZA", 12), AMERICAN_SAMOA("American Samoa", "ASM", 16), ANDORRA("Andorra", "AND", 20),
	ANGOLA("Angola", "AGO", 24), ANTIGUA_AND_BARBUDA("Antigua and Barbuda", "ATG", 28),
	AZERBAIJAN("Azerbaijan", "AZE", 31), ARGENTINA("Argentina", "ARG", 32), AUSTRALIA("Australia", "AUS", 36),
	AUSTRIA("Austria", "AUT", 40), BAHAMAS("Bahamas (the)", "BHS", 44), BAHRAIN("Bahrain", "BHR", 48),
	BANGLADESH("Bangladesh", "BGD", 50), ARMENIA("Armenia", "ARM", 51), BARBADOS("Barbados", "BRB", 52),
	BELGIUM("Belgium", "BEL", 56), BERMUDA("Bermuda", "BMU", 60), BHUTAN("Bhutan", "BTN", 64),
	BOLIVIA("Bolivia (Plurinational State of)", "BOL", 68), BOSNIA_AND_HERZEGOVINA("Bosnia and Herzegovina", "BIH", 70),
	BOTSWANA("Botswana", "BWA", 72), BOUVET_ISLAND("Bouvet Island", "BVT", 74), BRAZIL("Brazil", "BRA", 76),
	BELIZE("Belize", "BLZ", 84), BRITISH_INDIAN_OCEAN_TERRITORY("British Indian Ocean Territory (the)", "IOT", 86),
	SOLOMON_ISLANDS("Solomon Islands", "SLB", 90), BRUNEI_DARUSSALAM("Brunei Darussalam", "BRN", 96),
	BULGARIA("Bulgaria", "BGR", 100), MYANMAR("Myanmar", "MMR", 104), BURUNDI("Burundi", "BDI", 108),
	BELARUS("Belarus", "BLR", 112), CAMBODIA("Cambodia", "KHM", 116), CAMEROON("Cameroon", "CMR", 120),
	CANADA("Canada", "CAN", 124), CABO_VERDE("Cabo Verde", "CPV", 132),
	CAYMAN_ISLANDS("Cayman Islands (the)", "CYM", 136),
	CENTRAL_AFRICAN_REPUBLIC("Central African Republic (the)", "CAF", 140), SRI_LANKA("Sri Lanka", "LKA", 144),
	CHAD("Chad", "TCD", 148), CHILE("Chile", "CHL", 152), CHINA("China", "CHN", 156),
	TAIWAN("Taiwan (Province of China)", "TWN", 158), CHRISTMAS_ISLAND("Christmas Island", "CXR", 162),
	COLOMBIA("Colombia", "COL", 170), COMOROS("Comoros (the)", "COM", 174), MAYOTTE("Mayotte", "MYT", 175),
	CONGO("Congo (the)", "COG", 178), COOK_ISLANDS("Cook Islands (the)", "COK", 184),
	COSTA_RICA("Costa Rica", "CRI", 188), CROATIA("Croatia", "HRV", 191), CUBA("Cuba", "CUB", 192),
	CYPRUS("Cyprus", "CYP", 196), CZECHIA("Czechia", "CZE", 203), BENIN("Benin", "BEN", 204),
	DENMARK("Denmark", "DNK", 208), DOMINICA("Dominica", "DMA", 212),
	DOMINICAN_REPUBLIC("Dominican Republic (the)", "DOM", 214), ECUADOR("Ecuador", "ECU", 218),
	EL_SALVADOR("El Salvador", "SLV", 222), EQUATORIAL_GUINEA("Equatorial Guinea", "GNQ", 226),
	ETHIOPIA("Ethiopia", "ETH", 231), ERITREA("Eritrea", "ERI", 232), ESTONIA("Estonia", "EST", 233),
	FAROE_ISLANDS("Faroe Islands (the)", "FRO", 234), FALKLAND_ISLANDS("Falkland Islands (the) [Malvinas]", "FLK", 238),
	SOUTH_GEORGIA_AND_THE_SOUTH_SANDWICH_ISLANDS("South Georgia and the South Sandwich Islands", "SGS", 239),
	FIJI("Fiji", "FJI", 242), FINLAND("Finland", "FIN", 246), LAND_ISLANDS("land Islands", "ALA", 248),
	FRANCE("France", "FRA", 250), FRENCH_GUIANA("French Guiana", "GUF", 254),
	FRENCH_POLYNESIA("French Polynesia", "PYF", 258),
	FRENCH_SOUTHERN_TERRITORIES("French Southern Territories (the)", "ATF", 260), DJIBOUTI("Djibouti", "DJI", 262),
	GABON("Gabon", "GAB", 266), GEORGIA("Georgia", "GEO", 268), GAMBIA("Gambia (the)", "GMB", 270),
	PALESTINE_STATE_OF("Palestine, State of", "PSE", 275), GERMANY("Germany", "DEU", 276), GHANA("Ghana", "GHA", 288),
	GIBRALTAR("Gibraltar", "GIB", 292), KIRIBATI("Kiribati", "KIR", 296), GREECE("Greece", "GRC", 300),
	GREENLAND("Greenland", "GRL", 304), GRENADA("Grenada", "GRD", 308), GUADELOUPE("Guadeloupe", "GLP", 312),
	GUAM("Guam", "GUM", 316), GUATEMALA("Guatemala", "GTM", 320), GUINEA("Guinea", "GIN", 324),
	GUYANA("Guyana", "GUY", 328), HAITI("Haiti", "HTI", 332),
	HEARD_ISLAND_AND_MCDONALD_ISLANDS("Heard Island and McDonald Islands", "HMD", 334),
	HOLY_SEE("Holy See (the)", "VAT", 336), HONDURAS("Honduras", "HND", 340), HONG_KONG("Hong Kong", "HKG", 344),
	HUNGARY("Hungary", "HUN", 348), ICELAND("Iceland", "ISL", 352), INDIA("India", "IND", 356),
	INDONESIA("Indonesia", "IDN", 360), ISLAMIC_REPUBLIC_OF_IRAN("Iran (Islamic Republic of)", "IRN", 364),
	IRAQ("Iraq", "IRQ", 368), IRELAND("Ireland", "IRL", 372), ISRAEL("Israel", "ISR", 376), ITALY("Italy", "ITA", 380),
	CTE_DIVOIRE("Cte d`Ivoire", "CIV", 384), JAMAICA("Jamaica", "JAM", 388), JAPAN("Japan", "JPN", 392),
	KAZAKHSTAN("Kazakhstan", "KAZ", 398), JORDAN("Jordan", "JOR", 400), KENYA("Kenya", "KEN", 404),
	THE_DEMOCRATIC_PEOPLES_REPUBLIC_OF_KOREA_("Korea (the Democratic Peoples Republic of)", "PRK", 408),
	THE_REPUBLIC_OF_KOREA("Korea (the Republic of)", "KOR", 410), KUWAIT("Kuwait", "KWT", 414),
	KYRGYZSTAN("Kyrgyzstan", "KGZ", 417),
	LAO_PEOPLES_DEMOCRATIC_REPUBLIC("Lao Peoples Democratic Republic (the)", "LAO", 418),
	LEBANON("Lebanon", "LBN", 422), LESOTHO("Lesotho", "LSO", 426), LATVIA("Latvia", "LVA", 428),
	LIBERIA("Liberia", "LBR", 430), LIBYA("Libya", "LBY", 434), LIECHTENSTEIN("Liechtenstein", "LIE", 438),
	LITHUANIA("Lithuania", "LTU", 440), LUXEMBOURG("Luxembourg", "LUX", 442), MACAO("Macao", "MAC", 446),
	MADAGASCAR("Madagascar", "MDG", 450), MALAWI("Malawi", "MWI", 454), MALAYSIA("Malaysia", "MYS", 458),
	MALDIVES("Maldives", "MDV", 462), MALI("Mali", "MLI", 466), MALTA("Malta", "MLT", 470),
	MARTINIQUE("Martinique", "MTQ", 474), MAURITANIA("Mauritania", "MRT", 478), MAURITIUS("Mauritius", "MUS", 480),
	MEXICO("Mexico", "MEX", 484), MONACO("Monaco", "MCO", 492), MONGOLIA("Mongolia", "MNG", 496),
	MOLDOVA("Moldova (the Republic of)", "MDA", 498), MONTENEGRO("Montenegro", "MNE", 499),
	MONTSERRAT("Montserrat", "MSR", 500), MOROCCO("Morocco", "MAR", 504), MOZAMBIQUE("Mozambique", "MOZ", 508),
	OMAN("Oman", "OMN", 512), NAMIBIA("Namibia", "NAM", 516), NAURU("Nauru", "NRU", 520), NEPAL("Nepal", "NPL", 524),
	NETHERLANDS("Netherlands", "NLD", 528), CURAAO("Curaao", "CUW", 531), ARUBA("Aruba", "ABW", 533),
	SINT_MAARTEN("Sint Maarten (Dutch part)", "SXM", 534),
	BONAIRE_SINT_EUSTATIUS_AND_SABA("Bonaire, Sint Eustatius and Saba", "BES", 535),
	NEW_CALEDONIA("New Caledonia", "NCL", 540), VANUATU("Vanuatu", "VUT", 548), NEW_ZEALAND("New Zealand", "NZL", 554),
	NICARAGUA("Nicaragua", "NIC", 558), NIGER("Niger (the)", "NER", 562), NIGERIA("Nigeria", "NGA", 566),
	NIUE("Niue", "NIU", 570), NORFOLK_ISLAND("Norfolk Island", "NFK", 574), NORWAY("Norway", "NOR", 578),
	NORTHERN_MARIANA_ISLANDS("Northern Mariana Islands (the)", "MNP", 580),
	UNITED_STATES_MINOR_OUTLYING_ISLANDS("United States Minor Outlying Islands (the)", "UMI", 581),
	MICRONESIA("Micronesia (Federated States of)", "FSM", 583), MARSHALL_ISLANDS("Marshall Islands (the)", "MHL", 584),
	PALAU("Palau", "PLW", 585), PAKISTAN("Pakistan", "PAK", 586), PANAMA("Panama", "PAN", 591),
	PAPUA_NEW_GUINEA("Papua New Guinea", "PNG", 598), PARAGUAY("Paraguay", "PRY", 600), PERU("Peru", "PER", 604),
	PHILIPPINES("Philippines (the)", "PHL", 608), PITCAIRN("Pitcairn", "PCN", 612), POLAND("Poland", "POL", 616),
	PORTUGAL("Portugal", "PRT", 620), GUINEA_BISSAU("Guinea-Bissau", "GNB", 624),
	TIMOR_LESTE("Timor-Leste", "TLS", 626), PUERTO_RICO("Puerto Rico", "PRI", 630), QATAR("Qatar", "QAT", 634),
	RUNION("Runion", "REU", 638), ROMANIA("Romania", "ROU", 642),
	RUSSIAN_FEDERATION("Russian Federation (the)", "RUS", 643), RWANDA("Rwanda", "RWA", 646),
	SAINT_BARTHLEMY("Saint Barthlemy", "BLM", 652),
	SAINT_HELENA_ASCENSION_AND_TRISTAN_DA_CUNHA("Saint Helena, Ascension and Tristan da Cunha", "SHN", 654),
	SAINT_KITTS_AND_NEVIS("Saint Kitts and Nevis", "KNA", 659), ANGUILLA("Anguilla", "AIA", 660),
	SAINT_LUCIA("Saint Lucia", "LCA", 662), SAINT_MARTIN("Saint Martin (French part)", "MAF", 663),
	SAINT_PIERRE_AND_MIQUELON("Saint Pierre and Miquelon", "SPM", 666),
	SAINT_VINCENT_AND_THE_GRENADINES("Saint Vincent and the Grenadines", "VCT", 670),
	SAN_MARINO("San Marino", "SMR", 674), SAO_TOME_AND_PRINCIPE("Sao Tome and Principe", "STP", 678),
	SAUDI_ARABIA("Saudi Arabia", "SAU", 682), SENEGAL("Senegal", "SEN", 686), SERBIA("Serbia", "SRB", 688),
	SEYCHELLES("Seychelles", "SYC", 690), SIERRA_LEONE("Sierra Leone", "SLE", 694), SINGAPORE("Singapore", "SGP", 702),
	SLOVAKIA("Slovakia", "SVK", 703), VIET_NAM("Viet Nam", "VNM", 704), SLOVENIA("Slovenia", "SVN", 705),
	SOMALIA("Somalia", "SOM", 706), SOUTH_AFRICA("South Africa", "ZAF", 710), ZIMBABWE("Zimbabwe", "ZWE", 716),
	SPAIN("Spain", "ESP", 724), SOUTH_SUDAN("South Sudan", "SSD", 728), SUDAN("Sudan (the)", "SDN", 729),
	WESTERN_SAHARA("Western Sahara", "ESH", 732), SURINAME("Suriname", "SUR", 740),
	SVALBARD_AND_JAN_MAYEN("Svalbard and Jan Mayen", "SJM", 744), ESWATINI("Eswatini", "SWZ", 748),
	SWEDEN("Sweden", "SWE", 752), SWITZERLAND("Switzerland", "CHE", 756),
	SYRIAN_ARAB_REPUBLIC("Syrian Arab Republic", "SYR", 760), TAJIKISTAN("Tajikistan", "TJK", 762),
	THAILAND("Thailand", "THA", 764), TOGO("Togo", "TGO", 768), TOKELAU("Tokelau", "TKL", 772),
	TONGA("Tonga", "TON", 776), TRINIDAD_AND_TOBAGO("Trinidad and Tobago", "TTO", 780),
	UNITED_ARAB_EMIRATES("United Arab Emirates (the)", "ARE", 784), TUNISIA("Tunisia", "TUN", 788),
	TURKEY("Turkey", "TUR", 792), TURKMENISTAN("Turkmenistan", "TKM", 795),
	TURKS_AND_CAICOS_ISLANDS("Turks and Caicos Islands (the)", "TCA", 796), TUVALU("Tuvalu", "TUV", 798),
	UGANDA("Uganda", "UGA", 800), UKRAINE("Ukraine", "UKR", 804),
	REPUBLIC_OF_NORTH_MACEDONIA("Republic of North Macedonia", "MKD", 807), EGYPT("Egypt", "EGY", 818),
	UNITED_KINGDOM_OF_GREAT_BRITAIN_AND_NORTHERN_IRELAND("United Kingdom of Great Britain and Northern Ireland (the)",
			"GBR", 826),
	GUERNSEY("Guernsey", "GGY", 831), JERSEY("Jersey", "JEY", 832), ISLE_OF_MAN("Isle of Man", "IMN", 833),
	TANZANIA_UNITED_REPUBLIC_OF("Tanzania, United Republic of", "TZA", 834),
	UNITED_STATES_OF_AMERICA("United States of America (the)", "USA", 840),
	VIRGIN_ISLANDS("Virgin Islands (U.S.)", "VIR", 850), BURKINA_FASO("Burkina Faso", "BFA", 854),
	URUGUAY("Uruguay", "URY", 858), UZBEKISTAN("Uzbekistan", "UZB", 860),NETHERLANDS_1("Netherlands","NED",1000),
	VENEZUELA("Venezuela (Bolivarian Republic of)", "VEN", 862), WALLIS_AND_FUTUNA("Wallis and Futuna", "WLF", 876),
	SAMOA("Samoa", "WSM", 882), YEMEN("Yemen", "YEM", 887), ZAMBIA("Zambia", "ZMB", 894), UNITED_ARAB_EMIRATES_("United Arab Emirates", "UAE", 1009);

	private String countryName;
	private String Alpha3Code;
	private int CountryID;

	CountryEnum(String countryName, String Alpha3Code, int CountryID) {
		this.CountryID = CountryID;
		this.countryName = countryName;
		this.Alpha3Code = Alpha3Code;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getAlpha3Code() {
		return Alpha3Code;
	}

	public void setAlpha3Code(String alpha3Code) {
		Alpha3Code = alpha3Code;
	}

	public int getCountryID() {
		return CountryID;
	}

	public void setCountryID(int countryID) {
		CountryID = countryID;
	}

}

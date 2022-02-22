CREATE schema chess;

use chess;

/* CREATE TABLE */
CREATE TABLE Country(
`Country` VARCHAR(100),
`Alpha2_code` VARCHAR(100),
`Alpha3_code` VARCHAR(100) PRIMARY KEY,
`CountryId` INT  
);

CREATE TABLE Player (
    PlayerID varchar(255) not null,
    LastName varchar(255),
    FirstName varchar(255) not null,
	CountryCode varchar(4),
    Elo int not null,
    BestElo int,
    Birth smallint,
	PRIMARY KEY (PlayerID),
	FOREIGN KEY (CountryCode) REFERENCES Country(Alpha3_code)
);


CREATE TABLE Game (
    GameID int  auto_increment,
    GameDate Date not null,
    BlackResultID float not null,
    WhiteResultID float not null,
	PRIMARY KEY (GameID)
);

CREATE TABLE Notation (
	GameID int  not null,
    MoveNumber int auto_increment ,
    DescriptionMove varchar(255) not null,
    FOREIGN KEY (GameID) REFERENCES Game(GameID) ON DELETE CASCADE,
    PRIMARY KEY (MoveNumber,GameID) 
);
select * from game;
CREATE TABLE Championship (
    Championshipid int auto_increment,
    BlackPlayer varchar(255) not null,
    WhitePlayer varchar(255) not null,
   Country varchar(255),
	ChampionshipYear smallint not null,
    PRIMARY KEY (Championshipid),
	FOREIGN KEY (BlackPlayer) REFERENCES Player(PlayerID),
    FOREIGN KEY (WhitePlayer) REFERENCES Player(PlayerID),
    FOREIGN KEY (Country) REFERENCES Country(Alpha3_code)
);


CREATE TABLE ChampionshipGame (  
    GameNumber int not null,
    Championshipid int not null,
	GameID int not null,
    PRIMARY KEY (GameNumber,Championshipid),
	FOREIGN KEY (Championshipid) REFERENCES Championship(Championshipid),
    FOREIGN KEY (GameID) REFERENCES Game(GameID)  ON DELETE CASCADE
);

CREATE TABLE Tournament (
    Tournamentid int auto_increment,
    TournamentName varchar(255) not null,
    CountryCode varchar(4),
	TournamentYear smallint not null,
    PRIMARY KEY (Tournamentid),
    FOREIGN KEY (CountryCode) REFERENCES Country(Alpha3_code)
);


CREATE TABLE TournamentGame (
    Tournamentid int auto_increment,
    GameID int  not null,
    BlackPlayerID varchar(255) not null,
    WhitePlayerID varchar(255) not null,
    PRIMARY KEY (GameID),
    FOREIGN KEY (Tournamentid) REFERENCES Tournament(Tournamentid),
    FOREIGN KEY (GameID) REFERENCES Game(GameID)  ON DELETE CASCADE,
    FOREIGN KEY (BlackPlayerID) REFERENCES Player(PlayerID),
    FOREIGN KEY (WhitePlayerID) REFERENCES Player(PlayerID)
);

delimiter //
create trigger update_best_elo
BEFORE update
on player
FOR EACH ROW
BEGIN
IF (new.Elo > old.BestElo) Then
set New.BestElo = New.Elo;
End if;
END;//
delimiter ;	

/* INSERT QUERY NO: 1 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Afghanistan', 'AF', 'AFG', 004);

/* INSERT QUERY NO: 2 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Albania', 'AL', 'ALB', 008);

/* INSERT QUERY NO: 3 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Algeria', 'DZ', 'DZA', 012);

/* INSERT QUERY NO: 4 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('American Samoa', 'AS', 'ASM', 016);

/* INSERT QUERY NO: 5 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Andorra', 'AD', 'AND', 020);

/* INSERT QUERY NO: 6 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Angola', 'AO', 'AGO', 024);

/* INSERT QUERY NO: 7 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Anguilla', 'AI', 'AIA', 660);

/* INSERT QUERY NO: 8 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Antarctica', 'AQ', 'ATA', 010);

/* INSERT QUERY NO: 9 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Antigua and Barbuda', 'AG', 'ATG', 028);

/* INSERT QUERY NO: 10 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Argentina', 'AR', 'ARG', 032);

/* INSERT QUERY NO: 11 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Armenia', 'AM', 'ARM', 051);

/* INSERT QUERY NO: 12 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Aruba', 'AW', 'ABW', 533);

/* INSERT QUERY NO: 13 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Australia', 'AU', 'AUS', 036);

/* INSERT QUERY NO: 14 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Austria', 'AT', 'AUT', 040);

/* INSERT QUERY NO: 15 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Azerbaijan', 'AZ', 'AZE', 031);

/* INSERT QUERY NO: 16 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Bahamas (the)', 'BS', 'BHS', 044);

/* INSERT QUERY NO: 17 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Bahrain', 'BH', 'BHR', 048);

/* INSERT QUERY NO: 18 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Bangladesh', 'BD', 'BGD', 050);

/* INSERT QUERY NO: 19 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Barbados', 'BB', 'BRB', 052);

/* INSERT QUERY NO: 20 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Belarus', 'BY', 'BLR', 112);

/* INSERT QUERY NO: 21 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Belgium', 'BE', 'BEL', 056);

/* INSERT QUERY NO: 22 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Belize', 'BZ', 'BLZ', 084);

/* INSERT QUERY NO: 23 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Benin', 'BJ', 'BEN', 204);

/* INSERT QUERY NO: 24 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Bermuda', 'BM', 'BMU', 060);

/* INSERT QUERY NO: 25 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Bhutan', 'BT', 'BTN', 064);

/* INSERT QUERY NO: 26 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Bolivia (Plurinational State of)', 'BO', 'BOL', 068);

/* INSERT QUERY NO: 27 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('"Bonaire, Sint Eustatius and Saba"', 'BQ', 'BES', 535);

/* INSERT QUERY NO: 28 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Bosnia and Herzegovina', 'BA', 'BIH', 070);

/* INSERT QUERY NO: 29 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Botswana', 'BW', 'BWA', 072);

/* INSERT QUERY NO: 30 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Bouvet Island', 'BV', 'BVT', 074);

/* INSERT QUERY NO: 31 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Brazil', 'BR', 'BRA', 076);

/* INSERT QUERY NO: 32 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('British Indian Ocean Territory (the)', 'IO', 'IOT', 086);

/* INSERT QUERY NO: 33 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Brunei Darussalam', 'BN', 'BRN', 096);

/* INSERT QUERY NO: 34 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Bulgaria', 'BG', 'BGR', 100);

/* INSERT QUERY NO: 35 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Burkina Faso', 'BF', 'BFA', 854);

/* INSERT QUERY NO: 36 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Burundi', 'BI', 'BDI', 108);

/* INSERT QUERY NO: 37 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Cabo Verde', 'CV', 'CPV', 132);

/* INSERT QUERY NO: 38 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Cambodia', 'KH', 'KHM', 116);

/* INSERT QUERY NO: 39 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Cameroon', 'CM', 'CMR', 120);

/* INSERT QUERY NO: 40 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Canada', 'CA', 'CAN', 124);

/* INSERT QUERY NO: 41 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Cayman Islands (the)', 'KY', 'CYM', 136);

/* INSERT QUERY NO: 42 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Central African Republic (the)', 'CF', 'CAF', 140);

/* INSERT QUERY NO: 43 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Chad', 'TD', 'TCD', 148);

/* INSERT QUERY NO: 44 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Chile', 'CL', 'CHL', 152);

/* INSERT QUERY NO: 45 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('China', 'CN', 'CHN', 156);

/* INSERT QUERY NO: 46 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Christmas Island', 'CX', 'CXR', 162);

/* INSERT QUERY NO: 47 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Cocos (Keeling) Islands (the)', 'CC', 'CCK', 166);

/* INSERT QUERY NO: 48 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Colombia', 'CO', 'COL', 170);

/* INSERT QUERY NO: 49 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Comoros (the)', 'KM', 'COM', 174);

/* INSERT QUERY NO: 50 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Congo (the Democratic Republic of the)', 'CD', 'COD', 180);

/* INSERT QUERY NO: 51 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Congo (the)', 'CG', 'COG', 178);

/* INSERT QUERY NO: 52 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Cook Islands (the)', 'CK', 'COK', 184);

/* INSERT QUERY NO: 53 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Costa Rica', 'CR', 'CRI', 188);

/* INSERT QUERY NO: 54 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Croatia', 'HR', 'HRV', 191);

/* INSERT QUERY NO: 55 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Cuba', 'CU', 'CUB', 192);

/* INSERT QUERY NO: 56 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Curaçao', 'CW', 'CUW', 531);

/* INSERT QUERY NO: 57 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Cyprus', 'CY', 'CYP', 196);

/* INSERT QUERY NO: 58 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Czechia', 'CZ', 'CZE', 203);

/* INSERT QUERY NO: 59 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Côte d`Ivoire', 'CI', 'CIV', 384);

/* INSERT QUERY NO: 60 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Denmark', 'DK', 'DNK', 208);

/* INSERT QUERY NO: 61 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Djibouti', 'DJ', 'DJI', 262);

/* INSERT QUERY NO: 62 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Dominica', 'DM', 'DMA', 212);

/* INSERT QUERY NO: 63 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Dominican Republic (the)', 'DO', 'DOM', 214);

/* INSERT QUERY NO: 64 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Ecuador', 'EC', 'ECU', 218);

/* INSERT QUERY NO: 65 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Egypt', 'EG', 'EGY', 818);

/* INSERT QUERY NO: 66 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('El Salvador', 'SV', 'SLV', 222);

/* INSERT QUERY NO: 67 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Equatorial Guinea', 'GQ', 'GNQ', 226);

/* INSERT QUERY NO: 68 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Eritrea', 'ER', 'ERI', 232);

/* INSERT QUERY NO: 69 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Estonia', 'EE', 'EST', 233);

/* INSERT QUERY NO: 70 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Eswatini', 'SZ', 'SWZ', 748);

/* INSERT QUERY NO: 71 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Ethiopia', 'ET', 'ETH', 231);

/* INSERT QUERY NO: 72 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Falkland Islands (the) [Malvinas]', 'FK', 'FLK', 238);

/* INSERT QUERY NO: 73 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Faroe Islands (the)', 'FO', 'FRO', 234);

/* INSERT QUERY NO: 74 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Fiji', 'FJ', 'FJI', 242);

/* INSERT QUERY NO: 75 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Finland', 'FI', 'FIN', 246);

/* INSERT QUERY NO: 76 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('France', 'FR', 'FRA', 250);

/* INSERT QUERY NO: 77 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('French Guiana', 'GF', 'GUF', 254);

/* INSERT QUERY NO: 78 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('French Polynesia', 'PF', 'PYF', 258);

/* INSERT QUERY NO: 79 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('French Southern Territories (the)', 'TF', 'ATF', 260);

/* INSERT QUERY NO: 80 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Gabon', 'GA', 'GAB', 266);

/* INSERT QUERY NO: 81 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Gambia (the)', 'GM', 'GMB', 270);

/* INSERT QUERY NO: 82 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Georgia', 'GE', 'GEO', 268);

/* INSERT QUERY NO: 83 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Germany', 'DE', 'DEU', 276);

/* INSERT QUERY NO: 84 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Ghana', 'GH', 'GHA', 288);

/* INSERT QUERY NO: 85 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Gibraltar', 'GI', 'GIB', 292);

/* INSERT QUERY NO: 86 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Greece', 'GR', 'GRC', 300);

/* INSERT QUERY NO: 87 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Greenland', 'GL', 'GRL', 304);

/* INSERT QUERY NO: 88 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Grenada', 'GD', 'GRD', 308);

/* INSERT QUERY NO: 89 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Guadeloupe', 'GP', 'GLP', 312);

/* INSERT QUERY NO: 90 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Guam', 'GU', 'GUM', 316);

/* INSERT QUERY NO: 91 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Guatemala', 'GT', 'GTM', 320);

/* INSERT QUERY NO: 92 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Guernsey', 'GG', 'GGY', 831);

/* INSERT QUERY NO: 93 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Guinea', 'GN', 'GIN', 324);

/* INSERT QUERY NO: 94 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Guinea-Bissau', 'GW', 'GNB', 624);

/* INSERT QUERY NO: 95 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Guyana', 'GY', 'GUY', 328);

/* INSERT QUERY NO: 96 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Haiti', 'HT', 'HTI', 332);

/* INSERT QUERY NO: 97 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Heard Island and McDonald Islands', 'HM', 'HMD', 334);

/* INSERT QUERY NO: 98 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Holy See (the)', 'VA', 'VAT', 336);

/* INSERT QUERY NO: 99 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Honduras', 'HN', 'HND', 340);

/* INSERT QUERY NO: 100 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Hong Kong', 'HK', 'HKG', 344);

/* INSERT QUERY NO: 101 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Hungary', 'HU', 'HUN', 348);

/* INSERT QUERY NO: 102 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Iceland', 'IS', 'ISL', 352);

/* INSERT QUERY NO: 103 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('India', 'IN', 'IND', 356);

/* INSERT QUERY NO: 104 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Indonesia', 'ID', 'IDN', 360);

/* INSERT QUERY NO: 105 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Iran (Islamic Republic of)', 'IR', 'IRN', 364);

/* INSERT QUERY NO: 106 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Iraq', 'IQ', 'IRQ', 368);

/* INSERT QUERY NO: 107 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Ireland', 'IE', 'IRL', 372);

/* INSERT QUERY NO: 108 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Isle of Man', 'IM', 'IMN', 833);

/* INSERT QUERY NO: 109 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Israel', 'IL', 'ISR', 376);

/* INSERT QUERY NO: 110 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Italy', 'IT', 'ITA', 380);

/* INSERT QUERY NO: 111 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Jamaica', 'JM', 'JAM', 388);

/* INSERT QUERY NO: 112 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Japan', 'JP', 'JPN', 392);

/* INSERT QUERY NO: 113 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Jersey', 'JE', 'JEY', 832);

/* INSERT QUERY NO: 114 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Jordan', 'JO', 'JOR', 400);

/* INSERT QUERY NO: 115 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Kazakhstan', 'KZ', 'KAZ', 398);

/* INSERT QUERY NO: 116 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Kenya', 'KE', 'KEN', 404);

/* INSERT QUERY NO: 117 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Kiribati', 'KI', 'KIR', 296);

/* INSERT QUERY NO: 118 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Korea (the Democratic Peoples Republic of)', 'KP', 'PRK', 408);

/* INSERT QUERY NO: 119 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Korea (the Republic of)', 'KR', 'KOR', 410);

/* INSERT QUERY NO: 120 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Kuwait', 'KW', 'KWT', 414);

/* INSERT QUERY NO: 121 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Kyrgyzstan', 'KG', 'KGZ', 417);

/* INSERT QUERY NO: 122 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Lao Peoples Democratic Republic (the)', 'LA', 'LAO', 418);

/* INSERT QUERY NO: 123 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Latvia', 'LV', 'LVA', 428);

/* INSERT QUERY NO: 124 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Lebanon', 'LB', 'LBN', 422);

/* INSERT QUERY NO: 125 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Lesotho', 'LS', 'LSO', 426);

/* INSERT QUERY NO: 126 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Liberia', 'LR', 'LBR', 430);

/* INSERT QUERY NO: 127 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Libya', 'LY', 'LBY', 434);

/* INSERT QUERY NO: 128 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Liechtenstein', 'LI', 'LIE', 438);

/* INSERT QUERY NO: 129 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Lithuania', 'LT', 'LTU', 440);

/* INSERT QUERY NO: 130 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Luxembourg', 'LU', 'LUX', 442);

/* INSERT QUERY NO: 131 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Macao', 'MO', 'MAC', 446);

/* INSERT QUERY NO: 132 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Madagascar', 'MG', 'MDG', 450);

/* INSERT QUERY NO: 133 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Malawi', 'MW', 'MWI', 454);

/* INSERT QUERY NO: 134 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Malaysia', 'MY', 'MYS', 458);

/* INSERT QUERY NO: 135 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Maldives', 'MV', 'MDV', 462);

/* INSERT QUERY NO: 136 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Mali', 'ML', 'MLI', 466);

/* INSERT QUERY NO: 137 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Malta', 'MT', 'MLT', 470);

/* INSERT QUERY NO: 138 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Marshall Islands (the)', 'MH', 'MHL', 584);

/* INSERT QUERY NO: 139 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Martinique', 'MQ', 'MTQ', 474);

/* INSERT QUERY NO: 140 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Mauritania', 'MR', 'MRT', 478);

/* INSERT QUERY NO: 141 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Mauritius', 'MU', 'MUS', 480);

/* INSERT QUERY NO: 142 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Mayotte', 'YT', 'MYT', 175);

/* INSERT QUERY NO: 143 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Mexico', 'MX', 'MEX', 484);

/* INSERT QUERY NO: 144 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Micronesia (Federated States of)', 'FM', 'FSM', 583);

/* INSERT QUERY NO: 145 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Moldova (the Republic of)', 'MD', 'MDA', 498);

/* INSERT QUERY NO: 146 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Monaco', 'MC', 'MCO', 492);

/* INSERT QUERY NO: 147 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Mongolia', 'MN', 'MNG', 496);

/* INSERT QUERY NO: 148 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Montenegro', 'ME', 'MNE', 499);

/* INSERT QUERY NO: 149 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Montserrat', 'MS', 'MSR', 500);

/* INSERT QUERY NO: 150 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Morocco', 'MA', 'MAR', 504);

/* INSERT QUERY NO: 151 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Mozambique', 'MZ', 'MOZ', 508);

/* INSERT QUERY NO: 152 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Myanmar', 'MM', 'MMR', 104);

/* INSERT QUERY NO: 153 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Namibia', 'NA', 'NAM', 516);

/* INSERT QUERY NO: 154 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Nauru', 'NR', 'NRU', 520);

/* INSERT QUERY NO: 155 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Nepal', 'NP', 'NPL', 524);

/* INSERT QUERY NO: 156 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Netherlands (the)', 'NL', 'NLD', 528);

/* INSERT QUERY NO: 157 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('New Caledonia', 'NC', 'NCL', 540);

/* INSERT QUERY NO: 158 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('New Zealand', 'NZ', 'NZL', 554);

/* INSERT QUERY NO: 159 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Nicaragua', 'NI', 'NIC', 558);

/* INSERT QUERY NO: 160 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Niger (the)', 'NE', 'NER', 562);

/* INSERT QUERY NO: 161 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Nigeria', 'NG', 'NGA', 566);

/* INSERT QUERY NO: 162 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Niue', 'NU', 'NIU', 570);

/* INSERT QUERY NO: 163 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Norfolk Island', 'NF', 'NFK', 574);

/* INSERT QUERY NO: 164 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Northern Mariana Islands (the)', 'MP', 'MNP', 580);

/* INSERT QUERY NO: 165 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Norway', 'NO', 'NOR', 578);

/* INSERT QUERY NO: 166 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Oman', 'OM', 'OMN', 512);

/* INSERT QUERY NO: 167 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Pakistan', 'PK', 'PAK', 586);

/* INSERT QUERY NO: 168 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Palau', 'PW', 'PLW', 585);

/* INSERT QUERY NO: 169 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('"Palestine, State of"', 'PS', 'PSE', 275);

/* INSERT QUERY NO: 170 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Panama', 'PA', 'PAN', 591);

/* INSERT QUERY NO: 171 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Papua New Guinea', 'PG', 'PNG', 598);

/* INSERT QUERY NO: 172 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Paraguay', 'PY', 'PRY', 600);

/* INSERT QUERY NO: 173 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Peru', 'PE', 'PER', 604);

/* INSERT QUERY NO: 174 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Philippines (the)', 'PH', 'PHL', 608);

/* INSERT QUERY NO: 175 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Pitcairn', 'PN', 'PCN', 612);

/* INSERT QUERY NO: 176 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Poland', 'PL', 'POL', 616);

/* INSERT QUERY NO: 177 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Portugal', 'PT', 'PRT', 620);

/* INSERT QUERY NO: 178 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Puerto Rico', 'PR', 'PRI', 630);

/* INSERT QUERY NO: 179 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Qatar', 'QA', 'QAT', 634);

/* INSERT QUERY NO: 180 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Republic of North Macedonia', 'MK', 'MKD', 807);

/* INSERT QUERY NO: 181 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Romania', 'RO', 'ROU', 642);

/* INSERT QUERY NO: 182 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Russian Federation (the)', 'RU', 'RUS', 643);

/* INSERT QUERY NO: 183 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Rwanda', 'RW', 'RWA', 646);

/* INSERT QUERY NO: 184 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Réunion', 'RE', 'REU', 638);

/* INSERT QUERY NO: 185 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Saint Barthélemy', 'BL', 'BLM', 652);

/* INSERT QUERY NO: 186 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('"Saint Helena, Ascension and Tristan da Cunha"', 'SH', 'SHN', 654);

/* INSERT QUERY NO: 187 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Saint Kitts and Nevis', 'KN', 'KNA', 659);

/* INSERT QUERY NO: 188 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Saint Lucia', 'LC', 'LCA', 662);

/* INSERT QUERY NO: 189 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Saint Martin (French part)', 'MF', 'MAF', 663);

/* INSERT QUERY NO: 190 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Saint Pierre and Miquelon', 'PM', 'SPM', 666);

/* INSERT QUERY NO: 191 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Saint Vincent and the Grenadines', 'VC', 'VCT', 670);

/* INSERT QUERY NO: 192 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Samoa', 'WS', 'WSM', 882);

/* INSERT QUERY NO: 193 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('San Marino', 'SM', 'SMR', 674);

/* INSERT QUERY NO: 194 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Sao Tome and Principe', 'ST', 'STP', 678);

/* INSERT QUERY NO: 195 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Saudi Arabia', 'SA', 'SAU', 682);

/* INSERT QUERY NO: 196 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Senegal', 'SN', 'SEN', 686);

/* INSERT QUERY NO: 197 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Serbia', 'RS', 'SRB', 688);

/* INSERT QUERY NO: 198 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Seychelles', 'SC', 'SYC', 690);

/* INSERT QUERY NO: 199 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Sierra Leone', 'SL', 'SLE', 694);

/* INSERT QUERY NO: 200 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Singapore', 'SG', 'SGP', 702);

/* INSERT QUERY NO: 201 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Sint Maarten (Dutch part)', 'SX', 'SXM', 534);

/* INSERT QUERY NO: 202 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Slovakia', 'SK', 'SVK', 703);

/* INSERT QUERY NO: 203 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Slovenia', 'SI', 'SVN', 705);

/* INSERT QUERY NO: 204 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Solomon Islands', 'SB', 'SLB', 090);

/* INSERT QUERY NO: 205 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Somalia', 'SO', 'SOM', 706);

/* INSERT QUERY NO: 206 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('South Africa', 'ZA', 'ZAF', 710);

/* INSERT QUERY NO: 207 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('South Georgia and the South Sandwich Islands', 'GS', 'SGS', 239);

/* INSERT QUERY NO: 208 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('South Sudan', 'SS', 'SSD', 728);

/* INSERT QUERY NO: 209 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Spain', 'ES', 'ESP', 724);

/* INSERT QUERY NO: 210 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Sri Lanka', 'LK', 'LKA', 144);

/* INSERT QUERY NO: 211 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Sudan (the)', 'SD', 'SDN', 729);

/* INSERT QUERY NO: 212 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Suriname', 'SR', 'SUR', 740);

/* INSERT QUERY NO: 213 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Svalbard and Jan Mayen', 'SJ', 'SJM', 744);

/* INSERT QUERY NO: 214 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Sweden', 'SE', 'SWE', 752);

/* INSERT QUERY NO: 215 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Switzerland', 'CH', 'CHE', 756);

/* INSERT QUERY NO: 216 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Syrian Arab Republic', 'SY', 'SYR', 760);

/* INSERT QUERY NO: 217 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Taiwan (Province of China)', 'TW', 'TWN', 158);

/* INSERT QUERY NO: 218 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Tajikistan', 'TJ', 'TJK', 762);

/* INSERT QUERY NO: 219 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('"Tanzania, United Republic of"', 'TZ', 'TZA', 834);

/* INSERT QUERY NO: 220 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Thailand', 'TH', 'THA', 764);

/* INSERT QUERY NO: 221 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Timor-Leste', 'TL', 'TLS', 626);

/* INSERT QUERY NO: 222 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Togo', 'TG', 'TGO', 768);

/* INSERT QUERY NO: 223 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Tokelau', 'TK', 'TKL', 772);

/* INSERT QUERY NO: 224 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Tonga', 'TO', 'TON', 776);

/* INSERT QUERY NO: 225 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Trinidad and Tobago', 'TT', 'TTO', 780);

/* INSERT QUERY NO: 226 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Tunisia', 'TN', 'TUN', 788);

/* INSERT QUERY NO: 227 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Turkey', 'TR', 'TUR', 792);

/* INSERT QUERY NO: 228 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Turkmenistan', 'TM', 'TKM', 795);

/* INSERT QUERY NO: 229 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Turks and Caicos Islands (the)', 'TC', 'TCA', 796);

/* INSERT QUERY NO: 230 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Tuvalu', 'TV', 'TUV', 798);

/* INSERT QUERY NO: 231 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Uganda', 'UG', 'UGA', 800);

/* INSERT QUERY NO: 232 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Ukraine', 'UA', 'UKR', 804);

/* INSERT QUERY NO: 233 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('United Arab Emirates (the)', 'AE', 'ARE', 784);

/* INSERT QUERY NO: 234 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('United Kingdom of Great Britain and Northern Ireland (the)', 'GB', 'GBR', 826);

/* INSERT QUERY NO: 235 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('United States Minor Outlying Islands (the)', 'UM', 'UMI', 581);

/* INSERT QUERY NO: 236 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('United States of America (the)', 'US', 'USA', 840);

/* INSERT QUERY NO: 237 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Uruguay', 'UY', 'URY', 858);

/* INSERT QUERY NO: 238 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Uzbekistan', 'UZ', 'UZB', 860);

/* INSERT QUERY NO: 239 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Vanuatu', 'VU', 'VUT', 548);

/* INSERT QUERY NO: 240 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Venezuela (Bolivarian Republic of)', 'VE', 'VEN', 862);

/* INSERT QUERY NO: 241 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Viet Nam', 'VN', 'VNM', 704);

/* INSERT QUERY NO: 242 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Virgin Islands (British)', 'VG', 'VGB', 092);

/* INSERT QUERY NO: 243 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Virgin Islands (U.S.)', 'VI', 'VIR', 850);

/* INSERT QUERY NO: 244 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Wallis and Futuna', 'WF', 'WLF', 876);

/* INSERT QUERY NO: 245 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Western Sahara', 'EH', 'ESH', 732);

/* INSERT QUERY NO: 246 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Yemen', 'YE', 'YEM', 887);

/* INSERT QUERY NO: 247 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Zambia', 'ZM', 'ZMB', 894);

/* INSERT QUERY NO: 248 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Zimbabwe', 'ZW', 'ZWE', 716);

/* INSERT QUERY NO: 249 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Åland Islands', 'AX', 'ALA', 248);


/* INSERT QUERY NO: 249 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Iran', null, 'IRI', 1004);

/* INSERT QUERY NO: 249 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('United Arab Emirates', null, 'UAE', 1005);

/* INSERT QUERY NO: 249 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Croatia', null, 'CRO', 1006);

/* INSERT QUERY NO: 249 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Germany', null, 'GER', 1007);

/* INSERT QUERY NO: 249 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Netherlands', null, 'NED', 1009);

INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Bulgaria', null, 'BUL', 1010);

INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('Vietnam', null, 'VIE', 1012);

INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('England', null, 'ENG', 1013);

/* INSERT QUERY NO: 249 */
INSERT INTO Country(`Country`, `Alpha2_code`, `Alpha3_code`, `CountryId`)
VALUES ('N/a', null, 'OOO', 1008);

INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('1','Carlsen','Magnus','NOR',2865,2865,1990);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('2','Firouzja','Alireza','FRA',2804,2804,2003);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('3','Ding','Liren','CHN',2799,2799,1992);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('4','Caruana','Fabiano','USA',2792,2792,1992);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('5','Nepomniachtchi','Ian','RUS',2773,2773,1990);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('6','Aronian','Levon','USA',2772,2772,1982);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('7','Giri','Anish','NED',2772,2772,1994);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('8','So','Wesley','USA',2772,2772,1993);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('9','Mamedyarov','Shakhriyar','AZE',2767,2767,1985);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('10','Grischuk','Alexander','RUS',2764,2764,1983);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('11','Rapport','Richard','HUN',2763,2763,1996);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('12','Vachier-Lagrave','Maxime','FRA',2761,2761,1990);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('13','Duda','Jan-Krzysztof','POL',2760,2760,1998);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('14','Radjabov','Teimour','AZE',2753,2753,1987);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('15','Dominguez Perez','Leinier','USA',2752,2752,1983);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('16','Anand','Viswanathan','IND',2751,2751,1969);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('17','Wang','Hao','CHN',2744,2744,1989);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('18','Karjakin','Sergey','RUS',2743,2743,1990);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('19','Vitiugov','Nikita','RUS',2736,2736,1987);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('20','Topalov','Veselin','BUL',2730,2730,1975);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('21','Wei','Yi','CHN',2729,2729,1999);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('22','Vidit','Santosh Gujrathi','IND',2727,2727,1994);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('23','Andreikin','Dmitry','RUS',2724,2724,1990);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('24','Dubov','Daniil','RUS',2720,2720,1996);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('25','Harikrishna','Pentala','IND',2717,2717,1986);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('26','Esipenko','Andrey','RUS',2714,2714,2002);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('27','Yu','Yangyi','CHN',2713,2713,1994);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('28','Le','Quang Liem','VIE',2709,2709,1991);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('29','Shankland','Sam','USA',2708,2708,1991);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('30','Tomashevsky','Evgeny','RUS',2708,2708,1987);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('31','Fedoseev','Vladimir','RUS',2704,2704,1995);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('32','Shirov','Alexei','ESP',2704,2704,1972);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('33','Vallejo Pons','Francisco','ESP',2704,2704,1982);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('34','Alekseenko','Kirill','RUS',2702,2702,1997);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('35','Van Foreest','Jorden','NED',2702,2702,1999);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('36','Maghsoodloo','Parham','IRI',2701,2701,2000);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('37','Artemiev','Vladislav','RUS',2700,2700,1998);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('38','Bu','Xiangzhi','CHN',2700,2700,1985);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('39','Korobov','Anton','UKR',2699,2699,1985);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('40','Adams','Michael','ENG',2698,2698,1971);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('41','Navara','David','CZE',2697,2697,1985);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('42','Sadler','Matthew D','ENG',2694,2694,1974);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('43','Anton Guijarro','David','ESP',2691,2691,1995);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('44','Salem','A.R. Saleh','UAE',2690,2690,1993);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('45','Wojtaszek','Radoslaw','POL',2686,2686,1987);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('46','Xiong','Jeffery','USA',2686,2686,2000);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('47','Sarana','Alexey','RUS',2685,2685,2000);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('48','Sevian','Samuel','USA',2684,2684,2000);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('49','Kryvoruchko','Yuriy','UKR',2683,2683,1986);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('50','Eljanov','Pavel','UKR',2683,2683,1983);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('51','Svidler','Peter','RUS',2683,2683,1976);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('52','Jakovenko','Dmitry','RUS',2682,2682,1983);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('53','Predke','Alexandr','RUS',2682,2682,1994);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('54','Volokitin','Andrei','UKR',2681,2681,1986);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('55','Amin','Bassem','EGY',2681,2681,1988);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('56','Oparin','Grigoriy','RUS',2681,2681,1997);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('57','Sargissian','Gabriel','ARM',2681,2681,1983);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('58','Sjugirov','Sanan','RUS',2680,2680,1993);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('59','Deac','Bogdan-Daniel','ROU',2679,2679,2001);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('60','Li','Chao b','CHN',2679,2679,1989);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('61','Almasi','Zoltan','HUN',2678,2678,1976);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('62','Ivanchuk','Vasyl','UKR',2678,2678,1969);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('63','Robson','Ray','USA',2676,2676,1994);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('64','Saric','Ivan','CRO',2674,2674,1990);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('65','Matlakov','Maxim','RUS',2674,2674,1991);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('66','Nyzhnyk','Illya','UKR',2674,2674,1996);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('67','Grandelius','Nils','SWE',2672,2672,1993);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('68','Jones','Gawain C B','ENG',2671,2671,1987);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('69','Areshchenko','Alexander','UKR',2670,2670,1986);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('70','Gelfand','Boris','ISR',2669,2669,1968);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('71','Kovalenko','Igor','UKR',2668,2668,1988);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('72','Ni','Hua','CHN',2668,2668,1983);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('73','Cheparinov','Ivan','BUL',2664,2664,1986);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('74','Keymer','Vincent','GER',2664,2664,2004);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('75','Leko','Peter','HUN',2663,2663,1979);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('76','Nihal','Sarin','IND',2662,2662,2004);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('77','Guseinov','Gadir','AZE',2661,2661,1986);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('78','Najer','Evgeniy','RUS',2661,2661,1977);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('79','Berkes','Ferenc','HUN',2660,2660,1985);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('80','Tari','Aryan','NOR',2660,2660,1999);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('81','Inarkiev','Ernesto','RUS',2659,2659,1985);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('82','Kasimdzhanov','Rustam','UZB',2659,2659,1979);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('83','Hou','Yifan','CHN',2658,2658,1994);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('85','Kamsky','Gata','USA',2657,2657,1974);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('86','Ragger','Markus','AUT',2656,2656,1988);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('87','Mamedov','Rauf','AZE',2656,2656,1988);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('88','Swiercz','Dariusz','USA',2656,2656,1994);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('89','Demchenko','Anton','RUS',2654,2654,1987);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('90','Shevchenko','Kirill','UKR',2652,2652,2002);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('91','Gledura','Benjamin','HUN',2652,2652,1999);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('92','Malakhov','Vladimir','RUS',2652,2652,1980);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('93','Nisipeanu','Liviu-Dieter','GER',2652,2652,1976);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('94','Cori','Jorge','PER',2650,2650,1995);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('95','Sasikiran','Krishnan','IND',2650,2650,1981);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('96','Melkumyan','Hrant','ARM',2649,2649,1989);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('97','Naiditsch','Arkadij','AZE',2648,2648,1985);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('98','Adhiban','B.','IND',2648,2648,1992);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('99','McShane','Luke J','ENG',2647,2647,1984);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('100','Grigoryan','Karen H.','ARM',2646,2646,1995);
INSERT INTO Player(PlayerID,LastName,FirstName,CountryCode,Elo,BestElo,Birth) VALUES ('101','Howell','David W L','ENG',2646,2646,1990);

CREATE USER 'david'@'localhost' IDENTIFIED BY 'my-secret-pwd';

GRANT ALL ON chess.* TO 'david'@'localhost' WITH GRANT OPTION;




#Connection con = DriverManager.getConnection(String.format("jdbc:mysql://localhost:3306/%s?user=%s&password=%s", Util.DATABASE_NAME, Util.USERNAME, Util.PASSWORD));
#
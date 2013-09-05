CREATE TABLE rankedcountry(
	id int8 PRIMARY KEY,
	rank int,
	countryname varchar,
	countryid int,
	land int,
	networth int,
	color int,
	clan varchar,
	gov int,
	gdi boolean,
	dead boolean,
	protection boolean,
	dao_created int8,
	dao_updated int8
);

GRANT ALL ON rankedcountry TO mars ; 

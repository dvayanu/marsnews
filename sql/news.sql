CREATE TABLE news (
    news_id integer PRIMARY KEY,
    attacker_id integer,
	attacker_name varchar(100),
	attacker_clan varchar(100),
    defender_id integer,
	defender_name varchar(100),
	defender_clan varchar(100),
	
	kill boolean,
	result1 integer,
	result2 integer,
	timestamp bigint,
	type integer
);

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

GRANT ALL ON news TO marsuser;
GRANT ALL ON rankedcountry TO marsuser;

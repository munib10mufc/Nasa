 
--drop table if exists sources;
--create table sources(
--id serial,
--source_id varchar(30) not null,


--title varchar(50),
--source varchar(100),
--UNIQUE KEY sources_unique (source_id));
--CREATE INDEX "sources_index" ON sources(source_id);
--
--
drop table if exists external_apis;
create table external_apis(
id serial,
host_name varchar(20),
url_category varchar(60) not null,
url varchar(200),
api_key varchar(100),
n_retries int default 2,
last_used_on date,
UNIQUE KEY external_apis_unique (url_category));
CREATE INDEX "external_apis_index" ON external_apis(url_category);

insert into external_apis(host_name,url_category,url) values ('Nasa', 'Event Categories', 'https://eonet.sci.gsfc.nasa.gov/api/v3/categories');
insert into external_apis(host_name,url_category,url) values ('Nasa', 'Sources', 'https://eonet.sci.gsfc.nasa.gov/api/v3/sources');
insert into external_apis(host_name,url_category,url,api_key) values ('LocationIQ', 'forwardGeocoding', 'https://us1.locationiq.com/v1/search.php?format=json&limit=1','pk.dcfd4bf0a19ef7c8d1e514d150deea05');
insert into external_apis(host_name,url_category,url) values ('Nasa', 'DisasterInfo', 'https://eonet.sci.gsfc.nasa.gov/api/v3/events');

--
--
--drop table if exists locations_info;
--create table locations_info(
--id serial,
--location_name varchar(20),
--min_lat varchar(9),
--max_lat varchar(9),
--min_lon varchar(9),
--max_lon varchar(9),
--UNIQUE KEY locations_info_unique (location_name));
--CREATE INDEX "locations_info_index" ON locations_info(location_name);

--drop table if exists users;
--create table users(
--id serial,
--name varchar(20),
--password varchar(100),
--role varchar(9),
--UNIQUE KEY users_unique (name));
--CREATE INDEX "users_index" ON users(name);

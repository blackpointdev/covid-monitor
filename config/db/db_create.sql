-- public.covid_data definition

-- Drop table

-- DROP TABLE public.covid_data;

CREATE TABLE public.covid_data (
	id int8 NOT NULL GENERATED ALWAYS AS IDENTITY,
	x_date date NOT NULL,
	country varchar NOT NULL,
	newly_infected int8 NOT NULL,
	all_infected int8 NOT NULL,
	newly_deceased int8 NOT NULL,
	all_deceased int8 NOT NULL,
	CONSTRAINT covid_data_pk PRIMARY KEY (id)
);

INSERT INTO public.covid_data (x_date,country,newly_infected,all_infected,newly_deceased,all_deceased) VALUES
	 ('2021-01-01','Poland',123,123124,234,234234),
	 ('2021-08-24','Poland',3434,4343,3434,3434);
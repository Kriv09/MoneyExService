CREATE DATABASE currencies;
USE currencies;
 
 -- Creating tables: 

CREATE TABLE Currency
 (
 id bigint primary key,
 code varchar(4) unique not null,
 name varchar(20) unique not null,
 sign varchar(1) unique not null
 );

CREATE TABLE Exchanges 
( 
SourceCurrencyId bigint,
 DestCurrencyId bigint, 
Conversion decimal(8,2), 
Foreign Key (SourceCurrencyId) references currency(id),
 Foreign Key (DestCurrencyId) references currency(id) 
);

 CREATE TABLE userexchanges
( 
currencyPair varchar(10) not null,
SourceCurrencyAmount bigint not null,
ResultExchanging bigint not null 
);

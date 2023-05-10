
DROP DATABASE IF EXISTS elitesneakers;
Create schema elitesneakers;
Use elitesneakers;

Create Table Cliente(

	email varchar(50) not null,
	nome varchar(20) NOT NULL,
    cognome varchar(20) NOT NULL,
    passwd varchar(500),
    age int not null,
    indirizzo varchar(100) not null,
    indirizzo_spedizione varchar(100) not null,
    isAdmin boolean,
    
    primary key(Email)
    
);

Create table Prodotto(

	codice_prod int not null AUTO_INCREMENT,
	brand varchar(20) not null,
    modello varchar(20) not null,
    quantita int not null default 1,
    photo mediumblob DEFAULT NULL,
    prezzo int not null,

	primary key(codice_prod)
) auto_increment = 1;

Create table Ordine(
	numero_ord int not null,
    email varchar(50) not null,
    dettagli varchar(300) not null,
    
    primary key(numero_ord),
    foreign key(Email) references Cliente(Email)  on update cascade
    
);




DROP DATABASE IF EXISTS elitesneakers;
Create schema elitesneakers;
Use elitesneakers;

Create Table Cliente(

	email varchar(60) not null,
	nome varchar(30) NOT NULL,
    cognome varchar(30) NOT NULL,
    passwd varchar(500),
    data_nascita date not null,
    indirizzo varchar(200) not null,
    indirizzo_spedizione varchar(200) not null,
    isAdmin boolean,
    
    primary key(Email)
    
);

Create table Prodotto(

	codice_prod int not null AUTO_INCREMENT,
	brand varchar(50) not null,
    modello varchar(50) not null,
    quantita int not null default 1,
    photo mediumblob DEFAULT NULL,
    descrizione varchar(300) not null,
    prezzo double not null,
    taglia int not null,

	primary key(codice_prod)
) auto_increment = 1;

Create table Ordine(
	numero_ord int not null,
    email varchar(50) not null,
    data date not null,
    
    primary key(numero_ord),
    foreign key(Email) references Cliente(Email)  on update cascade
    
);

Create table DettaglioOrdine(
	numero_ord int not null,
    codice_prod int not null,
    quantita int not null,
    prezzo double not null,
    
    primary key(numero_ord, codice_prod),
    foreign key(numero_ord) references Ordine(numero_ord),
    foreign key(codice_prod) references Prodotto(codice_prod)

);



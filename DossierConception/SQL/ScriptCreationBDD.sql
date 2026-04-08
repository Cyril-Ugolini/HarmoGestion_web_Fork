CREATE DATABASE harmogestion;

USE harmogestion;

CREATE TABLE Membre(
   id_membre INT AUTO_INCREMENT,
   nom_membre VARCHAR(30) NOT NULL,
   prenom_membre VARCHAR(30) NOT NULL,
   date_inscription_membre DATE NOT NULL,
   PRIMARY KEY(id_membre)
)ENGINE=InnoDB;

CREATE TABLE Instrument(
   id_instrument INT AUTO_INCREMENT,
   libelle_instrument VARCHAR(50) NOT NULL,
   PRIMARY KEY(id_instrument),
   UNIQUE(libelle_instrument)
)ENGINE=InnoDB;

CREATE TABLE Cours(
   id_cours INT AUTO_INCREMENT,
   date_cours DATETIME NOT NULL,
   duree_cours TINYINT NOT NULL,
   id_instrument INT NOT NULL,
   id_membre_enseignant INT NOT NULL,
   PRIMARY KEY(id_cours),
   FOREIGN KEY(id_instrument) REFERENCES Instrument(id_instrument),
   FOREIGN KEY(id_membre_enseignant) REFERENCES Membre(id_membre)
)ENGINE=InnoDB;

CREATE TABLE Representation(
   id_representation INT AUTO_INCREMENT,
   nom_representation VARCHAR(80) NOT NULL,
   date_representation DATETIME NOT NULL,
   lieu_representation VARCHAR(100) NOT NULL,
   PRIMARY KEY(id_representation)
)ENGINE=InnoDB;

CREATE TABLE Pratiquer(
   id_membre INT,
   id_instrument INT,
   apprentissage_en_cours BOOLEAN NOT NULL,
   PRIMARY KEY(id_membre, id_instrument),
   FOREIGN KEY(id_membre) REFERENCES Membre(id_membre),
   FOREIGN KEY(id_instrument) REFERENCES Instrument(id_instrument)
)ENGINE=InnoDB;

CREATE TABLE ParticiperCours(
   id_membre_apprenant INT,
   id_cours INT,
   PRIMARY KEY(id_membre_apprenant, id_cours),
   FOREIGN KEY(id_membre_apprenant) REFERENCES Membre(id_membre),
   FOREIGN KEY(id_cours) REFERENCES Cours(id_cours)
)ENGINE=InnoDB;

CREATE TABLE ParticiperRepresentation(
   id_membre INT,
   id_representation INT,
   PRIMARY KEY(id_membre, id_representation),
   FOREIGN KEY(id_membre) REFERENCES Membre(id_membre),
   FOREIGN KEY(id_representation) REFERENCES Representation(id_representation)
)ENGINE=InnoDB;

CREATE TABLE InstrumentsRepresentation(
   id_instrument INT,
   id_representation INT,
   PRIMARY KEY(id_instrument, id_representation),
   FOREIGN KEY(id_instrument) REFERENCES Instrument(id_instrument),
   FOREIGN KEY(id_representation) REFERENCES Representation(id_representation)
)ENGINE=InnoDB;

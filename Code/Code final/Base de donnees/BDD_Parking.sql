CREATE DATABASE Projet_Parking;
use Projet_Parking;
CREATE TABLE parking(`Id` INT(2) NOT NULL PRIMARY KEY AUTO_INCREMENT, `Nom` VARCHAR(30),`Position` TEXT,`Disponibilite` INT(2));
INSERT INTO parking(`Id`,`Nom`,`Position`,`Disponibilite` ) 
VALUES (NULL,'SITE VINCI','Situé aux abords de Polytech Orléans site VINCI', 30),
(NULL, 'RESIDENCE DES CHARMES','Parking gratuit devant la résidence des charmes', 30),
(NULL, 'RESIDENCE ARISTOTE','Parking situé à 200 mètres de la faculté de lettre', 30),
(NULL, 'RUE DE CHARTRES','Situé devant le parc floral et la Faculté de Sciences', 30),
(NULL, 'UNIVERSITE CHATEAU','A 100 mètre du terrain omnisports', 30),
(NULL, 'SITE GALILEE PUBLIC','Situé aux abords de Polytech Orléans site GALILEE', 30),
(NULL, 'SITE GALILEE PRIVEE','Accès réservé aux personnels de Polytech Orléans', 30),
(NULL, 'IUT','Situé aux abords de l''IUT', 30);
SELECT * FROM parking;

<?php

require 'vendor/autoload.php';



$client = new MongoDB\Client;
$imsemeilenstein=$client->imsemeilenstein;
$Fahrzeug=$imsemeilenstein->Fahrzeug;

if(isset($_GET['del1'])){
	$marke=$_GET['del1'];

$deleteResult = $Fahrzeug->deleteOne(["marke" => "$marke"]);

		printf("Deleted %d documents", $deleteResult->getDeletedCount());

    header('location: index.php');
}
/*


$Gebaude=$imsemeilenstein->Gebaude;

if(isset($_GET['del2'])){
	$name=$_GET['del2'];

$deleteResult = $Gebaude->deleteOne(["name" => "$name "]);


		printf("Deleted %d documents", $deleteResult->getDeletedCount());

    header('location: index.php');




*/


$Gebaude=$imsemeilenstein->Gebaude;

if(isset($_GET['del2'])){
	$name=$_GET['del2'];

$deleteResult = $Gebaude->deleteOne(["name" => "$name"]);


		printf("Deleted %d documents", $deleteResult->getDeletedCount());

    header('location: index.php');
}
	

$Mitarbeiter=$imsemeilenstein->Mitarbeiter;

if(isset($_GET['del3'])){
	$nachname=$_GET['del3'];

$deleteResult = $Mitarbeiter->deleteOne(["nachname" => "$nachname"]);


		printf("Deleted %d documents", $deleteResult->getDeletedCount());

    header('location: index.php');
}
	

	




?>
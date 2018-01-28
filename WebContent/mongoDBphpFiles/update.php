<?php

require 'vendor/autoload.php';



$client = new MongoDB\Client;
$imsemeilenstein=$client->imsemeilenstein;
$fahrzeug=$imsemeilenstein->fahrzeug;

if(isset($_GET['edit1'])){
	
$baujahr=$_GET['edit1'];
$updateResult = $fahrzeug->updateOne(
	['baujahr' => '$baujahr'],
	['$set' => ['$baujahr' => '2007']]);

printf("modified %d documents", $updateResult->getModifiedCount());
printf("matched %d documents", $updateResult->getMatchedCount());
  // header('location: index.php');
}





?>
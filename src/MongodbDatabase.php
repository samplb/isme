

<?php

	require_once( './vendor/autoload.php');

	class MongodbDatabase{

		function __construct()
		{

		$this->db = ( new MongoDB\Client )-> imsemeilenstein-> Fahrzeug; 
		}	




		public function insertnewItem($iteminfo=[])
		{

			if(empty($iteminfo)){

				return false;
			}
				//wenns Data gibt, insert alles.

			$insertable = $this->db->insertOne([
				'marke' => $iteminfo ['marke'],
				'baujahr' => $iteminfo ['baujahr'],
				'kennzeichen'=> $iteminfo ['kennzeichen'],
				'mNr_mechaniker' =>$iteminfo['mNr_mechaniker'],
				'mNr_bueromitarbeiter' => $iteminfo['mNr_bueromitarbeiter']
			]);

			//gib züruck das was reingegeben ist

			return $insertable->getInsertedId();
		} 


		public function fetchFahrzeug() 
		{
			return $this->db->find();

		}

}


		class MongodbDatabase1{ 





		function __construct()
		{

		$this->db1 = ( new MongoDB\Client )-> imsemeilenstein-> Gebaude;

		}	

		public function insertnewItem1($iteminfo=[])
		{

			if(empty($iteminfo)){

				return false;
			}
				//wenns Data gibt, insert alles.

			$insertable1 = $this->db1->insertOne([
				'name' => $iteminfo ['name'],
				'strasse' => $iteminfo ['strasse'],
				'strassenNr'=> $iteminfo ['strassenNr'],
				'plz' => $iteminfo ['plz'],
				'ort'=> $iteminfo ['ort'],
				'unternehmen'=>$iteminfo['unternehmen'],
				'buero'=> $iteminfo['buero'],
				'garage'=>$iteminfo['garage'],
				'mitarbeiter'=>$iteminfo['mitarbeiter']

			]);

			//gib züruck das was reingegeben ist

			return $insertable1->getInsertedId();
		}


		public function fetchGebaude()
		{
			return $this->db1->find();

}
}





		class MongodbDatabase2{ 





		function __construct()
		{

		$this->db2 = ( new MongoDB\Client )-> imsemeilenstein-> Mitarbeiter;

		}	

		public function insertnewItem2($iteminfo=[])
		{

			if(empty($iteminfo)){

				return false;
			}
				//wenns Data gibt, insert alles.

			$insertable2 = $this->db2->insertOne([
			'mitname'=>$iteminfo['mitname'],
            'nachname'=>$iteminfo['nachname'],
            'unternehmenname'=>$iteminfo['unternehmenname'],
            'chef'=>$iteminfo['chef'],
            'anstellung'=>$iteminfo['anstellung'],
            'svNr'=>$iteminfo['svNr'],
            'gehalt'=>$iteminfo['gehalt'],
            'telefonnummer'=>$iteminfo['telefonnummer']

			]);

			//gib züruck das was reingegeben ist

			return $insertable2->getInsertedId();
		}


		public function fetchMitarbeiter()
		{
			return $this->db2->find();

}
}






?>



	
<?php

	require_once( './MongodbDatabase.php' );

	$db= new MongodbDatabase;
	//$db1= new MongodbDatabase1;
    //$db2= new MongodbDatabase2;


	
		//exit();
?>


<!DOCTYPE html>
<html lang="en">
<head>
	
	<meta charset="UTF-8">
	<title>MongoDB</title>
	<link rel='stylesheet' type='text/css' href='//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css'>

</head>


<body>


	<?php
//Check if we have data to insert Information about cars to mongodb database.

	if(isset($_POST['marke'])){
		if (! empty($_POST['marke'])){
			$insertable=$db->insertNewItem([
			'marke'			=> 		$_POST['marke'],
			'baujahr' 		=> 		$_POST['baujahr'],
			'kennzeichen'	=> 		$_POST['kennzeichen'],
			'mNr_mechaniker' =>     $_POST['mNr_mechaniker'],
			'mNr_bueromitarbeiter' => $_POST['mNr_bueromitarbeiter']

		]);
			if(insertable){
				?>

				<div class="container">

					<div class ="panel">

							<h3> Neues Fahrzeug hinzugefügt.</h3>

					</div>
				</div>
				<?php

			}
		}
	}

	?>
	
		<div class="container">
				<div class="col-sm-6">

					<div class="panel panel-dafault">
					
						<div class="panel-heading">
							
								<b>Fahrzeug hinzufügen</b>
					</div>

		<div>
			<div class="panel-body">
				<form action="" method="POST">

							<div class="form-group">
						<input type="text" name="marke" id="" class="form-control" placeholder="Marke">	</div>



							<div class="form-group">
						<input type="number" name="baujahr" id="" class="form-control" placeholder="Baujahr">		</div>



							<div class="form-group">
						<input type="text" name="kennzeichen" id="" class="form-control" placeholder="Kennzeichen">		
							</div>

								<div class="form-group">
						<input type="text" name="mNr_mechaniker" id="" class="form-control" placeholder="Mechaniker ID">		
							</div>

							<div class="form-group">
						<input type="text" name="mNr_bueromitarbeiter" id="" class="form-control" placeholder="Büroangestellter ID">		
							</div>



							<div class="form-group">
						<input type="submit" value="Save"  class="btn btn-danger" >		
							</div>


						</form>
					</div>
				</div>
			</div>
			<div class="col-sm-6">
				
				<h2> Fahrzeuge</h2>

				<?php



				foreach($db->fetchFahrzeug() as $item)
				{


						?>


					<ul class='list-group'>
							<li class="list-group-item"><?php echo $item->marke ?> </li>
							<li class="list-group-item"><?php echo $item->baujahr ?> </li>
							<li class="list-group-item"><?php echo $item->kennzeichen ?> </li>
							<li class="list-group-item"><?php echo $item->mNr_mechaniker ?> </li>
							<li class="list-group-item"><?php echo $item->mNr_bueromitarbeiter ?> </li>
							<td> 


							 <a href="delete.php? del1=<?php echo $item ["marke"]; ?>" class="btn btn-danger" > delete </a>
                             <a href="update.php? edit1=<?php echo $item ['baujahr']; ?>" class="btn btn-danger" >Edit</a>

							</td>  

						</ul>											



						<?php

						
		

				}


				?>


			</div>
		</div>















<?php

	require_once( './MongodbDatabase.php' );

	
	$db1= new MongodbDatabase1;


	
		//exit();
?>

	<?php
//Check if we have data to insert Information about cars to mongodb database.

	if(isset($_POST['strasse'])){
		if (! empty($_POST['strasse'])){
			$insertable1=$db1->insertNewItem1([
			'name' 				=>	 		$_POST['name'],
			'strasse' 			=> 			$_POST['strasse'],
			'strassenNr'	=>			$_POST['strassenNr'],
			'plz' 				=>			$_POST['plz'],
			'ort'				=> 			$_POST['ort'],
			'unternehmen'       =>          $_POST['unternehmen'],
			'buero'				=> 			$_POST['buero'],
			'garage'			=>			$_POST['garage'],
			'mitarbeiter'		=>			$_POST['mitarbeiter']


		]);

			//inserting Data - look at mongodbdatabase file
			if(insertable1){
				?>

				<div class="container">

					<div class ="panel">
							<h3> Neues Gebaude hinzugefügt.</h3>

					</div>
				</div>
				<?php

			}
		}
	}

	?>
	


		<div class="container">
				<div class="col-sm-6">

					<div class="panel panel-dafault">
					<div class="panel-heading">
							<b>Gebaude hinzufügen</b>
					</div>

		<div>
			<div class="panel-body">
				<form action="" method="POST">

							<div class="form-group">
						<input type="text" name="name" id="" class="form-control" placeholder="Name">	</div>



							<div class="form-group">
						<input type="text" name="strasse" id="" class="form-control" placeholder="Strasse">		</div>



							<div class="form-group">
						<input type="text" name="strassenNr" id="" class="form-control" placeholder="Strassennummer">		
							</div>



							<div class="form-group">
						<input type="number" name="plz" id="" class="form-control" placeholder="PLZ">		</div>



							<div class="form-group">
						<input type="text" name="ort" id="" class="form-control" placeholder="Ort">		
							</div>


							<div class="form-group">
						<input type="text" name="unternehmen" id="" class="form-control" placeholder="Unternehmen" value="Amstec">		
							</div>


							<div class="form-group">
						<input type="text" name="garage" id="" class="form-control" placeholder="Garage">		
							</div>


							<div class="form-group">
						<input type="text" name="buero" id="" class="form-control" placeholder="Büro">	
						
							</div>


							<div class="form-group">
						<input type="text" name="mitarbeiter" id="" class="form-control" placeholder="Mitarbeiter">		
							</div>





							<div class="form-group">
						<input type="submit" value="Save"  class="btn btn-danger" >		
							</div>


						</form>
					</div>
				</div>
			</div>
			<div class="col-sm-6">
				
				<h2> Gebaeude</h2>

				<?php



				foreach($db1->fetchGebaude() as $item)
				{
						?>


					<ul class='list-group'>
							<li class="list-group-item"><?php echo $item->name ?> </li>
							<li class="list-group-item"><?php echo $item->strasse ?> </li>
							<li class="list-group-item"><?php echo $item->strassenNr ?> </li>
							<li class="list-group-item"><?php echo $item->plz ?> </li>
							<li class="list-group-item"><?php echo $item->ort ?> </li>
							<li class="list-group-item"><?php echo $item->unternehmen ?> </li>
							<li class="list-group-item"><?php echo $item->garage ?> </li>
							<li class="list-group-item"><?php echo $item->buero ?> </li>
							<li class="list-group-item"><?php echo $item->mitarbeiter ?> </li>





							
							 <a href="delete.php? del2=<?php echo $item ["name"]; ?>" class="btn btn-danger" > Delete </a>

						</ul>

				




                        <?php

                        
        

                }


                ?>


            </div>
        </div>







        <?php

    require_once( './MongodbDatabase.php' );

    
    $db2= new MongodbDatabase2;


    
?>

    <?php
//Check if we have data to insert Information about cars to mongodb database.

    if(isset($_POST['mitname'])){
        if (! empty($_POST['mitname'])){
            $insertable2=$db2->insertNewItem2([
            'mitname'              =>          $_POST['mitname'],
            'nachname'           =>          $_POST['nachname'],
            'unternehmenname'    =>          $_POST['unternehmenname'],
            'chef'               =>          $_POST['chef'],
            'anstellung'               =>          $_POST['anstellung'],
            'svNr'               =>          $_POST['svNr'],
            'gehalt'               =>          $_POST['gehalt'],
            'telefonnummer'               =>          $_POST['telefonnummer']


        ]);

            //inserting Data - look at mongodbdatabase file
            if(insertable2){
                ?>

                <div class="container">

                    <div class ="panel">
                            <h3> Neuer Mitarbeiter hinzugefügt.</h3>

                    </div>
                </div>
                <?php

            }
        }
    }

    ?>
    


        <div class="container">
                <div class="col-sm-6">

                    <div class="panel panel-dafault">
                    <div class="panel-heading">
                            <b>Mitarbeiter hinzufügen</b>
                    </div>

        <div>
            <div class="panel-body">
                <form action="" method="POST">
   <div class="form-group">
                        <input type="text" name="mitname" id="" class="form-control" placeholder="Name">   </div>



                            <div class="form-group">
                        <input type="text" name="nachname" id="" class="form-control" placeholder="Nachname">     </div>



                            <div class="form-group">
                        <input type="text" name="unternehmenname" id="" class="form-control" placeholder="Unternehmen" value="Amstec">       
                            </div>



                            <div class="form-group">
                        <input type="number" name="chef" id="" class="form-control" placeholder="Chef ID" value='1'>       </div>



                            <div class="form-group">
                        <input type="text" name="anstellung" id="" class="form-control" placeholder="Anstellung">                              </div>
					

                            


                               <div class="form-group">
                        <input type="text" name="svNr" id="" class="form-control" placeholder="SVNR">     
                            </div>

                               <div class="form-group">
                        <input type="text" name="gehalt" id="" class="form-control" placeholder="Gehalt">     
                            </div>

                               <div class="form-group">
                        <input type="text" name="telefonnummer" id="" class="form-control" placeholder="Telefonnummer">     
                            </div>



                            <div class="form-group">
                        <input type="submit" value="Save"  class="btn btn-danger" >     
                            </div>

                        </form>
                    </div>
                </div>
            </div>
            <div class="col-sm-6">
                
                <h2> Mitarbeiter</h2>

                <?php



            foreach($db2->fetchMitarbeiter() as $item)
                {
                        
?>
                            <ul class='list-group'>
                            <li class="list-group-item"><?php echo $item->mitname ?> </li>
                            <li class="list-group-item"><?php echo $item->nachname ?> </li>
                            <li class="list-group-item"><?php echo $item->unternehmenname ?> </li>
                            <li class="list-group-item"><?php echo $item->chef ?> </li>
                            <li class="list-group-item"><?php echo $item->anstellung ?> </li>
                            <li class="list-group-item"><?php echo $item->svNr ?> </li>
                            <li class="list-group-item"><?php echo $item->gehalt ?> </li>
                            <li class="list-group-item"><?php echo $item->telefonnummer ?> </li>
                            
							 <a href="delete.php? del3=<?php echo $item ["nachname"]; ?>" class="btn btn-danger" > Delete </a>

                        </ul>

                  
                   




                       
<?php
                        } 
        

             


                ?>


            </div>
        </div>





















</body>

</html>
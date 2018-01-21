<?php 
		session_start();
		//incijalizacija variabli
		$name="";
		$nachname="";
		$mnr=0;
		$update=false;
		$unr=123;
		$edit_state=false;
		
		$name="";
		$gnr=0;
		$strasse="";
		$snr="";
		$plz="";
		$ort="";
		
		$marke="";
		$baujahr="";
		$kennzeichen="14564";
		$mnr_buero="";
		$mnr_mechaniker="";
		
		
		$edit_state1=false;
		$edit_state2=false;

	$db= mysqli_connect('localhost', 'root','','amstec');
	
	
	
	
	//MITARBEITER
	
	// ako je pritisnut save button
	
	
	if(isset($_POST['save'])) {
		$name= $_POST ['name'];
		$nachname= $_POST ['nachname'];
		$unr=$_POST ['unr'];
		
		$query ="INSERT INTO mitarbeiter (name,nachname,unr) VALUES ('$name', '$nachname',$unr)";
		
		mysqli_query($db,$query);
		$_SESSION['msg'] = "Saved";
		header ('location: index.php');  
	}

	
	//update records
	
	if(isset($_POST['update'])){
		
		$name=$_POST['name'];
		$nachname=$_POST['nachname'];
		
		$mnr=$_POST['mnr'];
		$unr=$_POST['unr'];
		
		$query="UPDATE mitarbeiter SET name='$name', nachname='$nachname' , unr='$unr' WHERE mnr='$mnr'" ;
		
		mysqli_query($db, $query);
		$_SESSION['msg'] = "Updated";
		header('location: index.php');
	}
	
	//delete records
	
	if(isset($_GET['del'])){
		$mnr= $_GET['del'];
		mysqli_query($db,"DELETE FROM mitarbeiter WHERE mnr=$mnr");
			$_SESSION['msg'] = "Deleted";
		header('location: index.php');
		
		
	}
	
	
	// prikazuje insert
	
	$results= mysqli_query($db, "SELECT * FROM mitarbeiter");
	
// GEBAUDE


// ako je pritisnut save button
	
	if(isset($_POST['save1'])) {
		$name=$_POST['name'];
		$strasse=$_POST['strasse'];
		$snr=$_POST['snr'];
		$plz=$_POST['plz'];
		$ort=$_POST['ort'];
		$unr=$_POST['unr'];
		
		$query ="INSERT INTO gebaeude (name,strasse,snr,plz,ort,unr) VALUES ('$name', '$strasse','$snr','$plz','$ort','$unr')";
		
		mysqli_query($db,$query);
		$_SESSION['msg'] = "Saved";
		header ('location: index.php'); 

		
	}

	
	
	//update records
	
	if(isset($_POST['update1'])){
		$name=mysqli_real_escape_string($_POST['name']);
		$gnr=mysqli_real_escape_string($_POST['gnr']);
		$strasse=mysqli_real_escape_string($_POST['strasse']);
		$snr=mysqli_real_escape_string($_POST['snr']);
		$plz=mysqli_real_escape_string($_POST['plz']);
		$ort=mysqli_real_escape_string($_POST['ort']);
		$unr=mysqli_real_escape_string($_POST['unr']);
		
		
		mysqli_query($db,"UPDATE gebaeude SET name='$name', strasse='$strasse', snr='$snr', plz='$plz', ort='$ort', unr='$unr'  WHERE gnr='$gnr'" );
		$_SESSION['msg'] = "Updated";
		
	header('location: index.php');
	}
	
	//delete records
	
	if(isset($_GET['del1'])){
		$gnr= $_GET['del1'];
		mysqli_query($db,"DELETE FROM gebaeude WHERE gnr=$gnr");
			$_SESSION['msg'] = "Deleted";
		header('location: index.php');
		
		
	}
	
	
	// prikazuje insert
	
	$results= mysqli_query($db, "SELECT * FROM gebaeude");
	
	
	
	
	
	//FAHRZEUG
	
	
		// ako je pritisnut save button
	
	
	if(isset($_POST['save2'])) {
		$marke= $_POST ['marke'];
		$baujahr= $_POST ['baujahr'];
		$kennzeichen= $_POST ['kennzeichen'];
		$mnr_mechaniker=$_POST['mnr_mechaniker'];
		$mnr_buero=$_POST['mnr_buero'];
		
		$query ="INSERT INTO fahrzeug (marke,baujahr,mnr_mechaniker,mnr_buero) VALUES ('$marke', '$baujahr','$mnr_mechaniker','$mnr_buero')";
		
		mysqli_query($db,$query);
		$_SESSION['msg'] = "Saved";
		header ('location: index.php');  
	}

	
	//update records
	
	if(isset($_POST['update2'])){
		
		
		mysqli_query($db,"UPDATE fahrzeug SET marke='$marke', baujahr='$baujahr' WHERE kennzeichen=$kennzeichen" );
		$_SESSION['msg'] = "Updated";
		
	header('location: index.php');
	}
	
	//delete records
	
	if(isset($_GET['del2'])){
		$kennzeichen= $_GET['del2'];
		mysqli_query($db,"DELETE FROM fahrzeug WHERE kennzeichen=$kennzeichen");
			$_SESSION['msg'] = "Deleted";
		header('location: index.php');
		
		
	}
	
	
	// prikazuje insert
	
	$results= mysqli_query($db, "SELECT * FROM fahrzeug");
	
	
	
	
	
	
	
	

	
	
	
	

	
?>
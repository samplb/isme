<?php  include('server.php'); ?>


<?php 
	if(isset($_GET['edit'])){
	$mnr= $_GET ['edit'];
	$edit_state=true;
	$rec= mysqli_query($db,"SELECT * FROM mitarbeiter WHERE mnr=$mnr");
	$record= mysqli_fetch_array($rec);
	$name=$record['name'];
	$nachname=$record['nachname'];
	$unr=$record['unr'];
	$mnr=$record['mnr'];
}


if(isset($_GET['edit1'])){
	$gnr= $_GET ['edit1'];
	$edit_state1=true;
	$rec= mysqli_query($db,"SELECT * FROM gebaeude WHERE gnr=$gnr");
	$record= mysqli_fetch_array($rec);
	$name=$record['name'];
	$strasse=$record['strasse'];
	$snr=$record['snr'];
	$plz=$record['plz'];
	$ort=$record['ort'];
	$unr=$record['unr'];
}


if(isset($_GET['edit2'])){
	$kennzeichen= $_GET ['edit2'];
	$edit_state2=true;
	$rec= mysqli_query($db,"SELECT * FROM fahrzeug WHERE kennzeichen=$kennzeichen");
	$record= mysqli_fetch_array($rec);
	$marke=$record['marke'];
	$baujahr=$record['baujahr'];
	$mnr_buero=$record['mnr_buero'];
	$mnr_mechaniker=$record['mnr_mechaniker'];
	
}

if(isset($_POST['update'])){
		
		$name=mysqli_real_escape_string($_POST['name']);
		$nachname=mysqli_real_escape_string($_POST['nachname']);
		
		$mnr=mysqli_real_escape_string($_POST['mnr']);
		$unr=mysqli_real_escape_string($_POST['unr']);
		
		$query="UPDATE mitarbeiter SET name='$name', nachname='$nachname' , unr='$unr' WHERE mnr='$mnr'" ;
		mysqli_query($db, $query);
		$_SESSION['msg'] = "Updated";
	}

?>


<!DOCTYPE html>
<html>
<head>
	<title>AMSTEC_Verwaltung</title>
	<link rel="stylesheet" type="text/css" href="style.css">
	
</head>



<body>

	<?php if (isset($_SESSION['msq'])): ?>
	<div class="msg">
		<?php 
			echo $_SESSION['msq']; 
			unset($_SESSION['msq']);
		?>
	</div>
<?php endif ?>

		
		<center>
		<b>
		Mitarbeiter MANAGEMENT
		</b>
		</center>

	<?php $results = mysqli_query($db, "SELECT * FROM mitarbeiter"); ?>

<table>
	<thead>
		<tr>
			<th>Name</th>
			<th>Nachname</th>
			<th>ID</th>
			<th>Unternehmensnummer<th>
			<th colspan="2">Action</th>
		</tr>
	</thead>
	
	<?php while ($row = mysqli_fetch_array($results)) { ?>
		<tr>
			<td><?php echo $row['name']; ?></td>
			<td><?php echo $row['nachname']; ?></td>
			<td><?php echo $row['mnr']; ?></td>
			<td><?php echo $row['unr']; ?></td>
			<td>
				<a href="index.php? edit=<?php echo $row['mnr']; ?>" class="edit_btn" >Edit</a>
			</td>
			<td>
				<a href="server.php? del=<?php echo $row['mnr']; ?>" class="del_btn">Delete</a>
			</td>
		</tr>
	<?php } ?>
</table>




	<form method="post" action="server.php" >
	<input type="hidden" name="mnr" value="<?php echo $mnr; ?>">
	
		<div class="input-group">
			<label>Name</label>
			<input type="text" name="name" value="<?php echo $name; ?>">
		</div>
		<div class="input-group">
			<label>Nachname</label>
			
			<input type="text" name="nachname" value="<?php echo $nachname; ?>">
		</div>
		<div class="input-group">
			<label>Unternehmensnummer</label>
			
			<input type="text" name="unr" value="<?php echo $unr; ?>">
		</div>
		
		<div class="input-group">
		
		
				<?php if ($edit_state == true): ?>
		<button class="btn" type="submit" name="update" style="background: #556B2F;" >update</button>
				<?php else: ?>
		<button class="btn" type="submit" name="save" >Save</button>
				<?php endif ?>
		

		</div>
	
		
		
		
	</form>
	
	

	
		<center>
		<b>
		Gebaude MANAGEMENT
		</b>
		</center>

	<?php $results = mysqli_query($db, "SELECT * FROM gebaeude"); ?>

<table>
	<thead>
		<tr>
			<th>Name</th>
			<th>GNr</th>
			<th>Strasse</th>
			<th>StrNr<th>
			<th>PLZ<th>
			<th>ORT<th>
			<th>Unternehmensnummer<th>
			<th colspan="2">Action</th>
		</tr>
	</thead>
	
	<?php while ($row = mysqli_fetch_array($results)) { ?>
		<tr>
			<td><?php echo $row['name']; ?></td>
			<td><?php echo $row['gnr']; ?></td>
			<td><?php echo $row['strasse']; ?></td>
			<td><?php echo $row['snr']; ?></td>
			<td><?php echo $row['plz']; ?></td>
			<td><?php echo $row['ort']; ?></td>
			<td><?php echo $row['unr']; ?></td>
			<td>
				<a href="index.php? edit1=<?php echo $row['gnr']; ?>" class="edit_btn" >Edit</a>
			</td>
			<td>
				<a href="server.php? del1=<?php echo $row['gnr']; ?>" class="del_btn">Delete</a>
			</td>
		</tr>
	<?php } ?>
</table>




	<form method="post" action="server.php" >
	
			<input type="hidden" name="gnr" value="<?php echo $gnr; ?>">
	
		<div class="input-group">
			<label>Name</label>
			<input type="text" name="name" value="<?php echo $name; ?>">
		</div>
		
		<div class="input-group">
			<label>Strasse</label>
			
			<input type="text" name="strasse" value="<?php echo $strasse; ?>">
		</div>
		
		<div class="input-group">
			<label>Strassennummer</label>
			
			<input type="text" name="snr" value="<?php echo $snr; ?>">
		</div>
		
		
		<div class="input-group">
			<label>PLZ</label>
	
			<input type="text" name="plz" value="<?php echo $plz; ?>">
		</div>
		
		
		<div class="input-group">
			<label>Ort</label>
						<input type="text" name="ort" value="<?php echo $ort; ?>">
		</div>
		
		<div class="input-group">
			<label>Unternehmensnummer</label>
						<input type="text" name="unr" value="<?php echo $unr; ?>">
		</div>
		
		<div class="input-group">
		
		
				<?php if ($edit_state1 == true): ?>
		<button class="btn" type="submit" name="update1" style="background: #556B2F;" >Update</button>
				<?php else: ?>
		<button class="btn" type="submit" name="save1" >Save</button>
				<?php endif ?>
				
		</div>	


			</form>
	

	
	<center>
		<b>
		Fahrzeug MANAGEMENT
		</b>
		</center>
	
	<?php $results = mysqli_query($db, "SELECT * FROM fahrzeug"); ?>

<table>
	<thead>
		<tr>
			<th>Marke</th>
			<th>Baujahr</th>
			<th>Kennzeichen</th>
			<th>GMit</th>
			<th>BMit</th>
			<th colspan="2">Action</th>
		</tr>
	</thead>
	
	<?php while ($row = mysqli_fetch_array($results)) { ?>
		<tr>
			<td><?php echo $row['marke']; ?></td>
			<td><?php echo $row['baujahr']; ?></td>
			<td><?php echo $row['kennzeichen']; ?></td>
			<td><?php echo $row['mnr_mechaniker']; ?></td>
			<td><?php echo $row['mnr_buero']; ?></td>
		
			<td>
				<a href="index.php? edit2=<?php echo $row['kennzeichen']; ?>" class="edit_btn" >Edit</a>
			</td>
			<td>
				<a href="server.php? del2=<?php echo $row['kennzeichen']; ?>" class="del_btn">Delete</a>
			</td>
		</tr>
	<?php } ?>
</table>




	<form method="post" action="server.php" >
	
			<input type="hidden" name="mnr" value="<?php echo $mnr; ?>">
	
		<div class="input-group">
			<label>Marke</label>
			<input type="text" name="marke" value="<?php echo $marke; ?>">
		</div>
		
		<div class="input-group">
			<label>Baujahr</label>
			<input type="text" name="baujahr" value="<?php echo $baujahr; ?>">
		</div>
		
		<div class="input-group">
			<label>GMit</label>
			<input type="text" name="mnr_mechaniker" value="<?php echo $mnr_mechaniker; ?>">
		</div>
		
		<div class="input-group">
			<label>BMit</label>
			<input type="text" name="mnr_buero" value="<?php echo $mnr_buero; ?>">
		</div>

		
		<div class="input-group">
		
		
				<?php if ($edit_state2 == true): ?>
		<button class="btn" type="submit" name="update2" style="background: #556B2F;" >update</button>
				<?php else: ?>
		<button class="btn" type="submit" name="save2" >Save</button>
				<?php endif ?>
		

		</div>
	
		
		
		
	</form>
	
	
	
	
	
</body>
</html>




<?php                                                  //Connection a la base de donnee
$host='localhost';
$user='root';
$password='aventures25';
$db='Projet_Parking';

$connect=mysqli_connect($host,$user,$password,$db);


if(!$connect){
	echo "Connexion failed!";
	exit;
}


?>




<?php   
                                                  //Fichier Qui envoie les donnees JSON #Affichage_Place_De_Parking
												  
//Header indispensable pour passer du json au javascript

header('Content-Type: application/json; charset utf-8');
												  
//Conection a la base de donnees
include("Connection_BDD.php");



//Requetes pour selectionner les donnees a afficher
$sql="SELECT Disponibilite FROM parking";
$result=mysqli_query($connect,$sql);


//On compte le nombre de ligne
$num_rows=mysqli_num_rows($result);



//tableau qui contient les donnees JSON
$server_response=array();
if($num_rows>0){
	//assoc ici et non array car c'est plus rapide, array renvoie la numerotation du tableau et on pourrat utliser $var pour afficher mais on en pas besoin
	while($row=mysqli_fetch_assoc($result)){
	$server_response[]=$row;
	}
}
else {
	echo "Le tableau est vide";
	}
	
	
	
//On envoie les donnees en json tel que si []==> json array et {}==> json objet
echo json_encode($server_response);


//On ferme la base de donnees
mysqli_close($connect);
?>

<?php
/*
																					//TEST 1 AVEC BOUTON ET METHOD AJOUT DANS BDD
header("Content-type: application/json; charset=utf-8");
$host="localhost";
$user="root";
$password="aventures25";
$db="testtutoparking";

$sql="INSERT INTO tblville (Nom_ville) VALUES ('POLYTECH');";
$con=mysqli_connect($host,$user,$password,$db);
$result=mysqli_query($con,$sql);
mysqli_close($con);


// inutile juste pour afficher directement
$sql="SELECT * FROM tblville;";
$con=mysqli_connect($host,$user,$password,$db);
$result=mysqli_query($con,$sql);
$server_response=array();
while($row=mysqli_fetch_array($result))
{
	$server_response[]=$row;
}
echo json_encode(array("server_response"=>$server_response));
mysqli_close($con);
*/





																		//TEST 2 AVEC RECEPTION DE VARIABLE ANDROID (POST/PUT)
//header("Content-type: application/json; charset=utf-8");

//choix du nom qui recevra le contenu
$post= json_decode(file_get_contents("php://input"), true);
$value=intval($post['param1']);
//$value2=intval($post['param2']);



$host="localhost";
$user="root";
$password="aventures25";
$db="testtutoparking";
$con=mysqli_connect($host,$user,$password,$db);

/*
$sql="UPDATE tblville SET Num_ville=28 WHERE ID_ville=$value";
$result=mysqli_query($con,$sql);
mysqli_close($con);
*/

//$value=2;
//si c'est pair ou impair
$Id=$value;




switch($Id){
																//Parking vinci

case 0:
			//sortie (pair)
			$Id=1;
			$sql="SELECT Num_ville FROM tblville WHERE ID_ville=$Id "; //ici Id
			$result=mysqli_query($con,$sql);
			if(!$result){
				echo "failed 1";
				exit;
			} 
			else{
			$row=mysqli_fetch_array($result);
			//Mis a jour de la base de donnee 
			$Num_ville=$row['Num_ville'] + 1;
			//connection a la base de donnee pour modifier le nombre de place disponible
		
			$sql="UPDATE tblville SET Num_ville=$Num_ville WHERE ID_ville=$Id";//ici id et table
			$result=mysqli_query($con,$sql);//ici le result
			if(!$result){
				echo "failed 2";
				exit;
			} 
			else{
				
				mysqli_close($con);
			echo "Nombre de ville= ".$Num_ville;
			break;//on ferme le case
			}
			}
			
	case 1:
			//reservation (impair)
			$Id=1;
				$sql="SELECT Num_ville FROM tblville WHERE ID_ville=$Id "; //ici Id
			$result=mysqli_query($con,$sql);
			if(!$result){
				echo "failed 1";
				exit;
			} 
			else{
			$row=mysqli_fetch_array($result);
			//Mis a jour de la base de donnee 
			$Num_ville=$row['Num_ville'] - 1;
			//connection a la base de donnee pour modifier le nombre de place disponible

			$sql="UPDATE tblville SET Num_ville=$Num_ville WHERE ID_ville=$Id";//ici id et table
			$result=mysqli_query($con,$sql);//ici le result
			if(!$result){
				echo "failed 2";
				exit;
			} 
			else{
				
				mysqli_close($con);
			echo "Nombre de ville= ".$Num_ville;
			break;//on ferme le case
			}
			}	
			
			
			//mysqli_free_result($result) ???
		
}


?>
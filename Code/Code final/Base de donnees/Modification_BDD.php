



<?php                                                  //Fichier Qui modifie les donnees de la base #Modification_Place_De_Parking

//Reception de l'identifiant provenant d'android
$POST= json_decode(file_get_contents("php://input"), true);
//conversion de l'identifiant en entier
$Id=intval($POST['ID_Parking']);


//Conection a la base de donnees
include("Connection_BDD.php");


//Choix selon l'identifiant du parking
switch($Id){
	
															//Le parking selectionner pour sortir, ajout de +1 dans la base de donnee: include("Sortie_Ajout_BDD.php");
	
	
																	//SORTIE PARKING===Ajout de +1 dans la case de parking, les chiffres envoyé par android sont PAIRS
																	//Cf.Testxi2
			case 0:
			//PARKING VINCI
			$Id=1;//Identifiant du parking
			$sql="SELECT disponibilite FROM parking WHERE Id=$Id ";
			$result=mysqli_query($connect,$sql);
			if(!$result){
				echo "failed connection";
				exit;
			} 
			else{
			$row=mysqli_fetch_array($result);
			//Mis a jour de la base de donnee 
			$disponibilite=$row['disponibilite'] + 1;
			//connection a la base de donnee pour modifier le nombre de place disponible
			
			$sql="UPDATE parking SET disponibilite=$disponibilite WHERE Id=$Id";
			
			$result=mysqli_query($connect,$sql);
			if(!$result){
				echo "failed request";
				exit;
			} 
			else{
				
				mysqli_close($connect);
			echo "Nombre de place de parking a VINCI= ".$disponibilite;
			break;//on ferme le case
			}
			}
			
			case 10:
			//PARKING CHARMES
			$Id=2;
			$sql="SELECT disponibilite FROM parking WHERE Id=$Id ";
			$result=mysqli_query($connect,$sql);
			if(!$result){
				echo "failed connection";
				exit;
			} 
			else{
			$row=mysqli_fetch_array($result);
			//Mis a jour de la base de donnee 
			$disponibilite=$row['disponibilite'] + 1;
			//connection a la base de donnee pour modifier le nombre de place disponible
			
			$sql="UPDATE parking SET disponibilite=$disponibilite WHERE Id=$Id";
			
			$result=mysqli_query($connect,$sql);
			if(!$result){
				echo "failed request";
				exit;
			} 
			else{
				
				mysqli_close($connect);
			echo "Nombre de place de parking a VINCI= ".$disponibilite;
			break;//on ferme le case
			}
			}
			
			case 20:
			//PARKING ARISTOTE
			$Id=3;
			$sql="SELECT disponibilite FROM parking WHERE Id=$Id ";
			$result=mysqli_query($connect,$sql);
			if(!$result){
				echo "failed connection";
				exit;
			} 
			else{
			$row=mysqli_fetch_array($result);
			//Mis a jour de la base de donnee 
			$disponibilite=$row['disponibilite'] + 1;
			//connection a la base de donnee pour modifier le nombre de place disponible
			
			$sql="UPDATE parking SET disponibilite=$disponibilite WHERE Id=$Id";
			
			$result=mysqli_query($connect,$sql);
			if(!$result){
				echo "failed request";
				exit;
			} 
			else{
				
				mysqli_close($connect);
			echo "Nombre de place de parking a VINCI= ".$disponibilite;
			break;//on ferme le case
			}
			}
			
			case 30:
			//PARKING CHARTRES
			$Id=4;
			$sql="SELECT disponibilite FROM parking WHERE Id=$Id ";
			$result=mysqli_query($connect,$sql);
			if(!$result){
				echo "failed connection";
				exit;
			} 
			else{
			$row=mysqli_fetch_array($result);
			//Mis a jour de la base de donnee 
			$disponibilite=$row['disponibilite'] + 1;
			//connection a la base de donnee pour modifier le nombre de place disponible
			
			$sql="UPDATE parking SET disponibilite=$disponibilite WHERE Id=$Id";
			
			$result=mysqli_query($connect,$sql);
			if(!$result){
				echo "failed request";
				exit;
			} 
			else{
				
				mysqli_close($connect);
			echo "Nombre de place de parking a VINCI= ".$disponibilite;
			break;//on ferme le case
			}
			}
			
		case 40:
			//PARKING CHATEAU
			$Id=5;
			$sql="SELECT disponibilite FROM parking WHERE Id=$Id ";
			$result=mysqli_query($connect,$sql);
			if(!$result){
				echo "failed connection";
				exit;
			} 
			else{
			$row=mysqli_fetch_array($result);
			//Mis a jour de la base de donnee 
			$disponibilite=$row['disponibilite'] + 1;
			//connection a la base de donnee pour modifier le nombre de place disponible
			
			$sql="UPDATE parking SET disponibilite=$disponibilite WHERE Id=$Id";
			
			$result=mysqli_query($connect,$sql);
			if(!$result){
				echo "failed request";
				exit;
			} 
			else{
				
				mysqli_close($connect);
			echo "Nombre de place de parking a VINCI= ".$disponibilite;
			break;//on ferme le case
			}
			}
			
			
			case 50:
			//PARKING GALILEE PUBLIC
			$Id=6;
			$sql="SELECT disponibilite FROM parking WHERE Id=$Id ";
			$result=mysqli_query($connect,$sql);
			if(!$result){
				echo "failed connection";
				exit;
			} 
			else{
			$row=mysqli_fetch_array($result);
			//Mis a jour de la base de donnee 
			$disponibilite=$row['disponibilite'] + 1;
			//connection a la base de donnee pour modifier le nombre de place disponible
			
			$sql="UPDATE parking SET disponibilite=$disponibilite WHERE Id=$Id";
			
			$result=mysqli_query($connect,$sql);
			if(!$result){
				echo "failed request";
				exit;
			} 
			else{
				
				mysqli_close($connect);
			echo "Nombre de place de parking a VINCI= ".$disponibilite;
			break;//on ferme le case
			}
			}
			
			
			case 60:
			//PARKING GALILEE PRIVEE
			$Id=7;
			$sql="SELECT disponibilite FROM parking WHERE Id=$Id ";
			$result=mysqli_query($connect,$sql);
			if(!$result){
				echo "failed connection";
				exit;
			} 
			else{
			$row=mysqli_fetch_array($result);
			//Mis a jour de la base de donnee 
			$disponibilite=$row['disponibilite'] + 1;
			//connection a la base de donnee pour modifier le nombre de place disponible
			
			$sql="UPDATE parking SET disponibilite=$disponibilite WHERE Id=$Id";
			
			$result=mysqli_query($connect,$sql);
			if(!$result){
				echo "failed request";
				exit;
			} 
			else{
				
				mysqli_close($connect);
			echo "Nombre de place de parking a VINCI= ".$disponibilite;
			break;//on ferme le case
			}
			}
			
			
			
		case 70:
			//PARKING IUT
			$Id=8;
			$sql="SELECT disponibilite FROM parking WHERE Id=$Id ";
			$result=mysqli_query($connect,$sql);
			if(!$result){
				echo "failed connection";
				exit;
			} 
			else{
			$row=mysqli_fetch_array($result);
			//Mis a jour de la base de donnee 
			$disponibilite=$row['disponibilite'] + 1;
			//connection a la base de donnee pour modifier le nombre de place disponible
			
			$sql="UPDATE parking SET disponibilite=$disponibilite WHERE Id=$Id";
			
			$result=mysqli_query($connect,$sql);
			if(!$result){
				echo "failed request";
				exit;
			} 
			else{
				
				mysqli_close($connect);
			echo "Nombre de place de parking a VINCI= ".$disponibilite;
			break;//on ferme le case
			}
			}
													//Le parking selectionner pour reserver termine par 1, retrait dans la base de donnee
													//include("Reserve_Retrait_BDD.php");
													
													
		
																	//?1=Reservation PARKING===retrait car place occupee
			case 1:
			//PARKING VINCI
			$Id=1;//Identifiant du parking dans la base de donnee
			$sql="SELECT disponibilite FROM parking WHERE Id=$Id ";
			$result=mysqli_query($connect,$sql);
			if(!$result){
				echo "failed connexion";
				exit;
			} 
			else{
			$row=mysqli_fetch_array($result);
			//Mis a jour de la base de donnee, on peut mettre aussi la condition de calcul sur $disponibilite
			$disponibilite=$row['disponibilite'] - 1;
			//connection a la base de donnee pour modifier le nombre de place disponible
			
			$sql="UPDATE parking SET disponibilite=$disponibilite WHERE Id=$Id";
			$result=mysqli_query($connect,$sql);
			if(!$result){
				echo "failed request";
				exit;
			} 
			else{
				
				mysqli_close($connect);
			echo "Nombre de place de parking a VINCI= ".$disponibilite;
			break;//on ferme le case
			}
			}
			
			case 11:
			//PARKING CHARMES
			$Id=2;
			$sql="SELECT disponibilite FROM parking WHERE Id=$Id ";
			$result=mysqli_query($connect,$sql);
			if(!$result){
				echo "failed connexion";
				exit;
			} 
			else{
			$row=mysqli_fetch_array($result);
			//Mis a jour de la base de donnee 
			$disponibilite=$row['disponibilite'] - 1;
			//connection a la base de donnee pour modifier le nombre de place disponible
			
			$sql="UPDATE parking SET disponibilite=$disponibilite WHERE Id=$Id";
			$result=mysqli_query($connect,$sql);
			if(!$result){
				echo "failed request";
				exit;
			} 
			else{
				
				mysqli_close($connect);
			echo "Nombre de place de parking a VINCI= ".$disponibilite;
			break;//on ferme le case
			}
			}
			
			case 21:
			//PARKING ARISTOTE
			$Id=3;
			$sql="SELECT disponibilite FROM parking WHERE Id=$Id ";
			$result=mysqli_query($connect,$sql);
			if(!$result){
				echo "failed connexion";
				exit;
			} 
			else{
			$row=mysqli_fetch_array($result);
			//Mis a jour de la base de donnee 
			$disponibilite=$row['disponibilite'] - 1;
			//connection a la base de donnee pour modifier le nombre de place disponible
			
			$sql="UPDATE parking SET disponibilite=$disponibilite WHERE Id=$Id";
			$result=mysqli_query($connect,$sql);
			if(!$result){
				echo "failed request";
				exit;
			} 
			else{
				
				mysqli_close($connect);
			echo "Nombre de place de parking a VINCI= ".$disponibilite;
			break;//on ferme le case
			}
			}
			
			case 31:
			//PARKING CHARTRES
			$Id=4;
			$sql="SELECT disponibilite FROM parking WHERE Id=$Id ";
			$result=mysqli_query($connect,$sql);
			if(!$result){
				echo "failed connexion";
				exit;
			} 
			else{
			$row=mysqli_fetch_array($result);
			//Mis a jour de la base de donnee 
			$disponibilite=$row['disponibilite'] - 1;
			//connection a la base de donnee pour modifier le nombre de place disponible
			
			$sql="UPDATE parking SET disponibilite=$disponibilite WHERE Id=$Id";
			$result=mysqli_query($connect,$sql);
			if(!$result){
				echo "failed request";
				exit;
			} 
			else{
				
				mysqli_close($connect);
			echo "Nombre de place de parking a VINCI= ".$disponibilite;
			break;//on ferme le case
			}
			}
			
		case 41:
			//PARKING CHATEAU
			$Id=5;
			$sql="SELECT disponibilite FROM parking WHERE Id=$Id ";
			$result=mysqli_query($connect,$sql);
			if(!$result){
				echo "failed connexion";
				exit;
			} 
			else{
			$row=mysqli_fetch_array($result);
			//Mis a jour de la base de donnee 
			$disponibilite=$row['disponibilite'] - 1;
			//connection a la base de donnee pour modifier le nombre de place disponible
			
			$sql="UPDATE parking SET disponibilite=$disponibilite WHERE Id=$Id";
			$result=mysqli_query($connect,$sql);
			if(!$result){
				echo "failed request";
				exit;
			} 
			else{
				
				mysqli_close($connect);
			echo "Nombre de place de parking a VINCI= ".$disponibilite;
			break;//on ferme le case
			}
			}
			
			
			case 51:
			//PARKING GALILEE PUBLIC
			$Id=6;
			$sql="SELECT disponibilite FROM parking WHERE Id=$Id ";
			$result=mysqli_query($connect,$sql);
			if(!$result){
				echo "failed connexion";
				exit;
			} 
			else{
			$row=mysqli_fetch_array($result);
			//Mis a jour de la base de donnee 
			$disponibilite=$row['disponibilite'] - 1;
			//connection a la base de donnee pour modifier le nombre de place disponible
			
			$sql="UPDATE parking SET disponibilite=$disponibilite WHERE Id=$Id";
			$result=mysqli_query($connect,$sql);
			if(!$result){
				echo "failed request";
				exit;
			} 
			else{
				
				mysqli_close($connect);
			echo "Nombre de place de parking a VINCI= ".$disponibilite;
			break;//on ferme le case
			}
			}
			
			
			case 61:
			//PARKING GALILEE PRIVEE
			$Id=7;
			$sql="SELECT disponibilite FROM parking WHERE Id=$Id ";
			$result=mysqli_query($connect,$sql);
			if(!$result){
				echo "failed connexion";
				exit;
			} 
			else{
			$row=mysqli_fetch_array($result);
			//Mis a jour de la base de donnee 
			$disponibilite=$row['disponibilite'] - 1;
			//connection a la base de donnee pour modifier le nombre de place disponible
			
			$sql="UPDATE parking SET disponibilite=$disponibilite WHERE Id=$Id";
			$result=mysqli_query($connect,$sql);
			if(!$result){
				echo "failed request";
				exit;
			} 
			else{
				
				mysqli_close($connect);
			echo "Nombre de place de parking a VINCI= ".$disponibilite;
			break;//on ferme le case
			}
			}
			
			
			
		case 71:
			//PARKING IUT
			$Id=8;
			$sql="SELECT disponibilite FROM parking WHERE Id=$Id ";
			$result=mysqli_query($connect,$sql);
			if(!$result){
				echo "failed connexion";
				exit;
			} 
			else{
			$row=mysqli_fetch_array($result);
			//Mis a jour de la base de donnee 
			$disponibilite=$row['disponibilite'] - 1;
			//connection a la base de donnee pour modifier le nombre de place disponible
			
			$sql="UPDATE parking SET disponibilite=$disponibilite WHERE Id=$Id";
			$result=mysqli_query($connect,$sql);
			if(!$result){
				echo "failed request";
				exit;
			} 
			else{
				
				mysqli_close($connect);
			echo "Nombre de place de parking a VINCI= ".$disponibilite;
			break;//on ferme le case
			}
			}											
													
													
}

?>
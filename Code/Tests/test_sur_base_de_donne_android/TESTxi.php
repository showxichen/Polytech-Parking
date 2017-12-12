<?php
header("Content-type: application/json; charset=utf-8");
$host="localhost";
$user="root";
$password="aventures25";
$db="testtutoparking";

$sql="SELECT * FROM tblville";
$con=mysqli_connect($host,$user,$password,$db);
$result=mysqli_query($con,$sql);
$number_of_rows= mysqli_num_rows($result);
$server_response=array();

if($number_of_rows>0){
while($row=mysqli_fetch_assoc($result))
{
	$server_response[]=$row;

}
}

echo json_encode($server_response);
mysqli_close($con);
?>



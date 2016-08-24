<style type="text/css">
<!--
body {
	background-color: #CCFFFF;
}
-->
</style>
<script language="JavaScript" type="text/JavaScript">
<!--
function MM_openBrWindow(theURL,winName,features) { //v2.0
  window.open(theURL,winName,features);
}
//-->
</script>
<title>FyQ DE PAR EN PAR. SUBIDA DE FICHEROS DE PREGUNTAS</title><div align="center">
  <h1><strong><img src="atomo.png" width="30" height="25">&nbsp;&nbsp;&nbsp;FyQ DE PAR EN PAR. SUBIDA DE FICHEROS DE PREGUNTAS&nbsp;&nbsp; <img src="atomo.png" width="30" height="25"></strong></h1>
</div>
<BR>
<center><input type="button" value="VOLVER A LA PÁGINA DE SUBIDA DE FICHEROS" onclick="history.go(-1)"></center>
<div align="center">
  <p>      <?
/* filtramos el tipo de archivos recibidos
de forma que solo se permitan imagenes en formato
jpg ó gif. Si el fichero transferido tuviera formato
distinto, la función exit() acabaría la ejecución del script */
$nombre=$_FILES['archivo']['name'];
if(file_exists("ficheros/".$nombre)) {
    print "<center><h2> El nombre del archivo ".$nombre." ya existe, debes elegir otro nombre</h2></center>";
	 exit();
 }

if($_FILES['archivo']['type']<>"text/plain") {
    print "<center><h2> El formato ".$_FILES['archivo']['type']." no está permitido </h2></center>";
	 exit();
 }else{
	            # anidamos este segundo condicional
	            # para guardar en una variable
	            # la extensión real del fichero
	            # mas adelante la utilizaremos
	if ($_FILES['archivo']['type']=="text/plain"){
		$extension = explode(".",$nombre);
		$num = count($extension)-1;
		
		if($extension[$num]<>"txt")
		{
         echo "<h2><center>el formato de archivo no es valido, solo .txt </h2></center>";
		 exit;
            }
			}
 /* filtremos ahora el tamaño de modo que no supere
 el máximo establecido en el hidden del formulario
 (logicamente ese valor no puede superar el valor máximo
 de la configuración de php, pero si puede ser menor)
 y también evitaremos archivos sin contenido, 
 es decir con tamaño CERO */
if($_FILES['archivo']['size']>$_POST['lim_tamano'] 
	                             OR $_FILES['archivo']['size']==0){
 print "<center><h2> El tamaño ".$_FILES['archivo']['size']." excede el límite o está vacío</h2></center>";
 exit();
 }

# asignemos un nombre a la imagen transferida
# de modo que se guarde en el servidor 
# con un nombre distinto, asignado por nosotros
# con ello, podemos evitar duplicidades de nombres
# ya que si existiera un fichero con el mismo nombre
# que el enviado por el cliente, se sobreescribiría


# aceptemos la transferencia siempre que el archivo tenga nombre
if ($_FILES['archivo']['tmp_name'] != "none" ){
/* con la función copy
pasaremos el archivo que está en el directorio temporal
al subdirectorio que contiene el script que estamos
ejecutando. Podríamos incluir un path y copiarlo
a otro directorio */
           if (copy($_FILES['archivo']['tmp_name'], "ficheros/".$nombre)) {
	             echo "<h2><center> Se ha transferido el archivo </center></h2>"; 
		   }
    }else{
    echo "<h2><center>No ha podido transferirse el fichero</center></h2>";  
}
} 
?>
  </p>
  <p>
    <input name="Submit" type="submit" onClick="MM_openBrWindow('listado.php','listado','scrollbars=yes,width=350,height=650')" value="LISTADO DE FICHEROS YA CREADOS"> 
  </p>
</div>

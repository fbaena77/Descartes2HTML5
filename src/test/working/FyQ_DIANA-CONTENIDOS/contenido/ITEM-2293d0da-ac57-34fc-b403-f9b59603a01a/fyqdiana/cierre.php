<?
session_start();
session_name();

$FICHERO2=$_SESSION['F2'];
if(file_exists($FICHERO2))
{
unlink($FICHERO2);
}
 
session_destroy();
?>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>CIERRE DE SESI&Oacute;N</title>
<style type="text/css">
<!--
body {
	background-color: #CCFFFF;
}
-->
</style>
</head>

<body>
<div align="center">
    <h1><strong>  <img src="atomo.png" width="30" height="25">&nbsp;&nbsp;&nbsp;SESI&Oacute;N CERRADA &nbsp;&nbsp; <img src="atomo.png" width="30" height="25"> </strong></h1>
    <p>
    La sesi&oacute;n se ha cerrado correctamente. </p>
    <p>Gracias por utilizar el genedaror de contenidos para juegos del Proyecto Newton
  </p>
  <BR>
</div>

<div align="center">
  <table width="80%" border="0">
    <tr>
      <td><div align="center"><a href="javascript:self.close()"> 
        <input type=button value="Cerrar Ventana" onClick="window.close();">
      </a></div>
        <div align="center">
      </div></td>
    </tr>
  </table>
</div>
</body>
</html>

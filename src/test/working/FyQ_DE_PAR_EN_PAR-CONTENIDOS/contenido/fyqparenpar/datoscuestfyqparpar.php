<?
session_start();
session_name();
?>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>FyQ DE PAR EN PAR. CUESTIONARIO ELABORADO</title>
<style type="text/css">
<!--
body {
	background-color: #CCFFFF;
}
-->
</style>
<script language="JavaScript" type="text/JavaScript">
<!--

function MM_goToURL() { //v3.0
  var i, args=MM_goToURL.arguments; document.MM_returnValue = false;
  for (i=0; i<(args.length-1); i+=2) eval(args[i]+".location='"+args[i+1]+"'");
}
//-->
</script>
</head>

<body>
<div align="center">
    <h1><strong>  <img src="atomo.png" width="30" height="25">&nbsp;&nbsp;&nbsp;FyQ DE PAR EN PAR. CUESTIONARIO ELABORADO&nbsp;&nbsp; <img src="atomo.png" width="30" height="25"> </strong></h1>
    <?

$nomfic=$_POST['nomfic'];
$extension = explode(".",$nomfic);
$num = count($extension)-1;
$existe=file_exists($nomfic);
if($existe == 1)
{
$tam=filesize($nomfic);
}
$_SESSION['F1']=$nomfic;
$_SESSION['F2']=$nomfic;

if($extension[0] == null || $extension[0] == " " || $extension[0] == "  " || $extension[0] == "   " || $extension[0] == "    " || $extension[0] == "     " || $extension[0] == "      " || $extension[0] == "       "|| $extension[0] == "        "|| $extension[0] == "          ")
{
echo "HAY QUE INTRODUCIR UN NOMBRE VÁLIDO DEL FICHERO";
?>
<BR>
<BR>
<input type="button" value="VOLVER AL FORMULARIO" onclick="history.go(-1)"> 
<?
exit;
}


if($extension[$num] <> "txt")
{
echo "LA EXTESIÓN DEL FICHERO NO ES VÁLIDA. ESTA DEBE DE SER Y ESCRITO EN MINÚSCULAS .txt";
?>
<BR>
<BR>
<input type="button" value="VOLVER AL FORMULARIO" onclick="history.go(-1)"> 
<?
exit;
}


if($tam>0)
{
echo "ESTE FICHERO YA HA SIDO ESCRITO, HAY QUE PONERLE OTRO NOMBRE AL FICHERO <Br>" ;
echo "NO ESTÁ PERMITIDO ACTUALIZAR ESTA PÁGINA <Br>";
?>
<BR>
<BR>
<input type="button" value="VOLVER AL FORMULARIO" onclick="history.go(-1)"> 
<?
exit;
}


$fp = fopen($_POST['nomfic'],"a");
fwrite($fp, $_POST['T']."\r\n");
fwrite($fp,"'".$_POST['p1']."'"."\r\n");
fwrite($fp,"'".$_POST['p2']."'"."\r\n");
fwrite($fp,"'".$_POST['p3']."'"."\r\n");
fwrite($fp,"'".$_POST['p4']."'"."\r\n");
fwrite($fp,"'".$_POST['p5']."'"."\r\n");
fwrite($fp,"'".$_POST['p6']."'"."\r\n");
fwrite($fp,"'".$_POST['p7']."'"."\r\n");
fwrite($fp,"'".$_POST['p8']."'"."\r\n");
fwrite($fp,"'".$_POST['p9']."'"."\r\n");
fwrite($fp,$_POST['num1']."\r\n");
fwrite($fp,"'".$_POST['pE1']."'"."\r\n");
fwrite($fp,"'".$_POST['pE2']."'"."\r\n");
fwrite($fp,"'".$_POST['pE3']."'"."\r\n");
fwrite($fp,"'".$_POST['pE4']."'"."\r\n");
fwrite($fp,"'".$_POST['pE5']."'"."\r\n");
fwrite($fp,"'".$_POST['pE6']."'"."\r\n");
fwrite($fp,"'".$_POST['pE7']."'"."\r\n");
fwrite($fp,"'".$_POST['pE8']."'"."\r\n");
fwrite($fp,"'".$_POST['pE9']."'"."\r\n");
fwrite($fp, "'AUTOR/A: ".$_POST['nombautor']."'"."\r\n");
fwrite($fp, "'TEMA: ".$_POST['nombbateria']."'"."\r\n");
fwrite($fp, "FyQ DE PAR EN PAR");
fclose($fp);
?>

<table width="100%"  border="1">
  <tr>
        <td colspan="5"><div align="center">
          <h2>DATOS GENERALES </h2>
        </div></td>
    </tr>
      <tr>
        <td colspan="5"><div align="center"><strong>Nombre del fichero: <? echo  $_POST['nomfic'] ?>
        </strong></div></td>
      </tr>
      <tr>
        <td colspan="5"><div align="center"><strong>Nombre y apellido del autor de la bater&iacute;a de preguntas: 
              <? echo  $_POST['nombautor'] ?>
        </strong></div></td>
      </tr>
      <tr>
        <td colspan="5"><div align="center"><strong>TIEMPO: <? echo  $_POST['T'] ?>          
        </strong></div></td>
      </tr>
      <tr>
        <td colspan="5">&nbsp;</td>
      </tr>
      <tr>
        <td colspan="5"><div align="center">
          <h2>            <strong>PAREJAS <? echo  $_POST['num1'] ?></strong></h2>
        </div></td>
      </tr>
      <tr>
        <td width="14%" colspan="-1"><div align="center"></div></td>
        <td width="13%"><div align="center"><strong>1&ordf;</strong></div></td>
        <td width="22%"><div align="center"><strong><? echo  $_POST['p1'] ?></strong></div></td>
        <td width="21%"><div align="center"><strong><? echo  $_POST['pE1'] ?></strong></div></td>
        <td width="30%">&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td><div align="center"><strong>2&ordf;</strong></div></td>
        <td><div align="center"><strong><? echo  $_POST['p2'] ?></strong></div></td>
        <td><div align="center"><strong><? echo  $_POST['pE2'] ?></strong></div></td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td><div align="center"><strong>3&ordf;</strong></div></td>
        <td><div align="center"><strong><? echo  $_POST['p3'] ?></strong></div></td>
        <td><div align="center"><strong><? echo  $_POST['pE3'] ?></strong></div></td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td><div align="center"><strong>4&ordf;</strong></div></td>
        <td><div align="center"><strong><? echo  $_POST['p4'] ?></strong></div></td>
        <td><div align="center"><strong><? echo  $_POST['pE4'] ?></strong></div></td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td><div align="center"><strong>5&ordf;</strong></div></td>
        <td><div align="center"><strong><? echo  $_POST['p5'] ?></strong></div></td>
        <td><div align="center"><strong><? echo  $_POST['pE5'] ?></strong></div></td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td><div align="center"><strong>6&ordf;</strong></div></td>
        <td><div align="center"><strong><? echo  $_POST['p6'] ?></strong></div></td>
        <td><div align="center"><strong><? echo  $_POST['pE6'] ?></strong></div></td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td><div align="center"><strong>7&ordf;</strong></div></td>
        <td><div align="center"><strong><? echo  $_POST['p7'] ?></strong></div></td>
        <td><div align="center"><strong><? echo  $_POST['pE7'] ?></strong></div></td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td><div align="center"><strong>8&ordf;</strong></div></td>
        <td><div align="center"><strong><? echo  $_POST['p8'] ?></strong></div></td>
        <td><div align="center"><strong><? echo  $_POST['pE8'] ?></strong></div></td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td><div align="center"><strong>9&ordf;</strong></div></td>
        <td><div align="center"><strong><? echo  $_POST['p9'] ?></strong></div></td>
        <td><div align="center"><strong><? echo  $_POST['pE9'] ?></strong></div></td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td colspan="5"><div align="center"></div></td>
      </tr>
      <tr>
        <td colspan="5"><div align="center"></div></td>
      </tr>
  </table>

</div>

<CENTER>




<p align="center"><BR>
SI DESEAS MODIFICAR EL FORMULARIO, PINCHAR EL BOTÓN VOLVER AL FORMULARIO, PERO LOS DATOS INTRODUCIDOS SE PERDER&Aacute;N. TAMBIÉN SE PUEDE GENERAR EL FICHERO Y MODIFICAR EN ÉL LOS ERRORES DETECTADOS<BR>
  <BR>
  <input type="button" value="VOLVER AL FORMULARIO" onclick="history.go(-1)"> 
    
  <?
# unlink($nomfic);
?>
</p>
<p align="center">
  <input type="button" onclick="MM_goToURL('parent','descargarfich.php');return document.MM_returnValue" value="DESCARGAR EL FICHERO GENERADO"> 
</p>
<div align="center">
<A Href="descargarfich.php?<?echo session_name()."=".session_id()?>">  </A>
</div>
</body>
</html>

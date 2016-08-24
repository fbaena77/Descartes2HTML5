<?
session_start();
session_name();
?>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>FyQ EL DUELO. CUESTIONARIO ELABORADO</title>
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
    <h1><strong>  <img src="atomo.png" width="30" height="25">&nbsp;&nbsp;&nbsp;FyQ CADA SABIO CON SU TEMA&nbsp;&nbsp; <img src="atomo.png" width="30" height="25"> </strong></h1>
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
fwrite($fp,"'".$_POST['pregT1']."'"."\r\n");
fwrite($fp,"'".$_POST['pregT1A1']."'"."\r\n");
fwrite($fp,"'".$_POST['pregT1A2']."'"."\r\n");
fwrite($fp,"'".$_POST['pregT1A3']."'"."\r\n");
fwrite($fp,"'".$_POST['pregT1A4']."'"."\r\n");
fwrite($fp,"'".$_POST['pregT1A5']."'"."\r\n");
fwrite($fp,"'".$_POST['pregT1A6']."'"."\r\n");
fwrite($fp,"'".$_POST['resT1A1']."'"."\r\n");
fwrite($fp,"'".$_POST['resT1A2']."'"."\r\n");
fwrite($fp,"'".$_POST['resT1A3']."'"."\r\n");
fwrite($fp,$_POST['RESPT1']."\r\n");
fwrite($fp,"'".$_POST['pregT2']."'"."\r\n");
fwrite($fp,"'".$_POST['pregT2A1']."'"."\r\n");
fwrite($fp,"'".$_POST['pregT2A2']."'"."\r\n");
fwrite($fp,"'".$_POST['pregT2A3']."'"."\r\n");
fwrite($fp,"'".$_POST['pregT2A4']."'"."\r\n");
fwrite($fp,"'".$_POST['pregT2A5']."'"."\r\n");
fwrite($fp,"'".$_POST['pregT2A6']."'"."\r\n");
fwrite($fp,"'".$_POST['resT2A1']."'"."\r\n");
fwrite($fp,"'".$_POST['resT2A2']."'"."\r\n");
fwrite($fp,"'".$_POST['resT2A3']."'"."\r\n");
fwrite($fp,$_POST['RESPT2']."\r\n");
fwrite($fp,"'".$_POST['pregT3']."'"."\r\n");
fwrite($fp,"'".$_POST['pregT3A1']."'"."\r\n");
fwrite($fp,"'".$_POST['pregT3A2']."'"."\r\n");
fwrite($fp,"'".$_POST['pregT3A3']."'"."\r\n");
fwrite($fp,"'".$_POST['pregT3A4']."'"."\r\n");
fwrite($fp,"'".$_POST['pregT3A5']."'"."\r\n");
fwrite($fp,"'".$_POST['pregT3A6']."'"."\r\n");
fwrite($fp,"'".$_POST['resT3A1']."'"."\r\n");
fwrite($fp,"'".$_POST['resT3A2']."'"."\r\n");
fwrite($fp,"'".$_POST['resT3A3']."'"."\r\n");
fwrite($fp,$_POST['RESPT3']."\r\n");
fwrite($fp,"'".$_POST['pregT4']."'"."\r\n");
fwrite($fp,"'".$_POST['pregT4A1']."'"."\r\n");
fwrite($fp,"'".$_POST['pregT4A2']."'"."\r\n");
fwrite($fp,"'".$_POST['pregT4A3']."'"."\r\n");
fwrite($fp,"'".$_POST['pregT4A4']."'"."\r\n");
fwrite($fp,"'".$_POST['pregT4A5']."'"."\r\n");
fwrite($fp,"'".$_POST['pregT4A6']."'"."\r\n");
fwrite($fp,"'".$_POST['resT4A1']."'"."\r\n");
fwrite($fp,"'".$_POST['resT4A2']."'"."\r\n");
fwrite($fp,"'".$_POST['resT4A3']."'"."\r\n");
fwrite($fp,$_POST['RESPT4']."\r\n");
fwrite($fp, "'AUTOR/A: ".$_POST['nombautor']."'"."\r\n");
fwrite($fp, "'TEMA: ".$_POST['nombbateria']."'"."\r\n");
fwrite($fp, "FyQ EL DUELO");
fclose($fp);
?>

<table width="100%"  border="1">
  <tr>
        <td colspan="2"><div align="center">
          <h2>DATOS GENERALES </h2>
        </div></td>
    </tr>
      <tr>
        <td colspan="2"><div align="center"><strong>Nombre del fichero: <? echo  $_POST['nomfic'] ?>
        </strong></div></td>
      </tr>
      <tr>
        <td colspan="2"><div align="center"><strong>Nombre y apellido del autor de la bater&iacute;a de preguntas: 
              <? echo  $_POST['nombautor'] ?>
        </strong></div></td>
      </tr>
      <tr>
        <td colspan="2"><div align="center"><strong>T&iacute;tulo de la bater&iacute;a de preguntas: <? echo  $_POST['nombbateria'] ?>          
        </strong></div></td>
      </tr>
      <tr>
        <td colspan="2">&nbsp;</td>
      </tr>
      <tr>
        <td colspan="2"><div align="center"><strong> PRIMER TEMA: <? echo  $_POST['pregT1']
				   ?> </strong></div></td>
      </tr>
      <tr>
        <td width="16%" rowspan="2"><div align="center"> <strong> PREGUNTA <? echo  $_POST['num1T1'] ?> </strong></div>
            <div align="center"></div></td>
        <td><div align="center"><strong><? echo  $_POST['pregT1A1']
				   ?>&nbsp; <? echo  $_POST['pregT1A2']
				   ?> &nbsp;<? echo  $_POST['pregT1A3']

				   ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregT1A4']
				   ?>&nbsp; <? echo  $_POST['pregT1A5']

				   ?>&nbsp; <? echo  $_POST['pregT1A6']

				   ?></strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>OPCIONES RESPUESTAS </strong></div></td>
        <td><div align="center"><strong>1.) <? echo  $_POST['resT1A1']
				   ?>&nbsp; 2.) <? echo  $_POST['resT1A2']

				   ?>  &nbsp;3.)&nbsp;<? echo  $_POST['resT1A3']

				   ?> &nbsp;&nbsp;&nbsp;&nbsp;RESP. CORRECTA:
        <? echo  $_POST['RESPT1']

				   ?></strong></div></td>
      </tr>
      <tr>
        <td colspan="2">&nbsp;</td>
      </tr>
      <tr>
        <td colspan="2"><div align="center"><strong> SEGUNDO TEMA: <? echo  $_POST['pregT2']
				   ?> </strong></div></td>
      </tr>
      <tr>
        <td width="16%" rowspan="2"><div align="center"> <strong> PREGUNTA <? echo  $_POST['num1T2'] ?> </strong></div>
            <div align="center"></div></td>
        <td><div align="center"><strong><? echo  $_POST['pregT2A1']
				   ?>&nbsp; <? echo  $_POST['pregT2A2']
				   ?> &nbsp;<? echo  $_POST['pregT2A3']

				   ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregT2A4']
				   ?>&nbsp; <? echo  $_POST['pregT2A5']

				   ?>&nbsp; <? echo  $_POST['pregT2A6']

				   ?></strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>OPCIONES RESPUESTAS </strong></div></td>
        <td><div align="center"><strong>1.) <? echo  $_POST['resT2A1']
				   ?> &nbsp;2.)  <? echo  $_POST['resT2A2']

				   ?> &nbsp;3.)&nbsp;<? echo  $_POST['resT2A3']

				   ?>  &nbsp;&nbsp;&nbsp;&nbsp;RESP. CORRECTA: <? echo  $_POST['RESPT2']

				   ?></strong></div></td>
      </tr>
      <tr>
        <td colspan="2">&nbsp;</td>
      </tr>
      <tr>
        <td colspan="2"><div align="center"><strong> TERCER TEMA: <? echo  $_POST['pregT3']
				   ?> </strong></div></td>
      </tr>
      <tr>
        <td width="16%" rowspan="2"><div align="center"> <strong> PREGUNTA <? echo  $_POST['num1T3'] ?> </strong></div>
            <div align="center"></div></td>
        <td><div align="center"><strong><? echo  $_POST['pregT3A1']
				   ?>&nbsp; <? echo  $_POST['pregT3A2']
				   ?> &nbsp;<? echo  $_POST['pregT3A3']

				   ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregT3A4']
				   ?>&nbsp; <? echo  $_POST['pregT3A5']

				   ?>&nbsp; <? echo  $_POST['pregT3A6']

				   ?></strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>OPCIONES RESPUESTAS </strong></div></td>
        <td><div align="center"><strong>1.) <? echo  $_POST['resT3A1']
				   ?>&nbsp; 2.) <? echo  $_POST['resT3A2']

				   ?> &nbsp;3.)&nbsp;<? echo  $_POST['resT3A3']

				   ?>  &nbsp;&nbsp;&nbsp;&nbsp;RESP. CORRECTA: <? echo  $_POST['RESPT3']

				   ?></strong></div></td>
      </tr>
      <tr>
        <td colspan="2">&nbsp;</td>
      </tr>
      <tr>
        <td colspan="2"><div align="center"><strong> CUARTO TEMA: <? echo  $_POST['pregT4']
				   ?> </strong></div></td>
      </tr>
      <tr>
        <td width="16%" rowspan="2"><div align="center"> <strong> PREGUNTA <? echo  $_POST['num1T4'] ?> </strong></div>
            <div align="center"></div></td>
        <td><div align="center"><strong><? echo  $_POST['pregT4A1']
				   ?>&nbsp; <? echo  $_POST['pregT4A2']
				   ?> &nbsp;<? echo  $_POST['pregT4A3']

				   ?></strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregT4A4']
				   ?>&nbsp; <? echo  $_POST['pregT4A5']

				   ?>&nbsp; <? echo  $_POST['pregT4A6']

				   ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>OPCIONES RESPUESTAS </strong></div></td>
        <td><div align="center"><strong>1.) <? echo  $_POST['resT4A1']
				   ?>&nbsp; 2.) <? echo  $_POST['resT4A2']

				   ?> &nbsp;3.)&nbsp;<? echo  $_POST['resT4A3']

				   ?>   &nbsp;&nbsp;&nbsp;&nbsp;RESP. CORRECTA: <? echo  $_POST['RESPT4']

				   ?></strong></div></td>
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

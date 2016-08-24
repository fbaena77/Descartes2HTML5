<?
session_start();
session_name();
$nombre=session_id();
$extension=".txt";
$nomfic=$nombre . $extension;
$_SESSION['F1']=$nomfic;
$_SESSION['F2']=$nomfic;
?>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>FyQ DIANA. CUESTIONARIO ELABORADO</title>
<style type="text/css">
<!--
body {
	background-color: #CCFFFF;
}
.Estilo1 {
	font-size: 18px;
	font-weight: bold;
}
-->
</style>
<script language="JavaScript" type="text/JavaScript">
<!--
function MM_goToURL() { //v3.0
  var i, args=MM_goToURL.arguments; document.MM_returnValue = false;
  for (i=0; i<(args.length-1); i+=2) eval(args[i]+".location='"+args[i+1]+"'");
}

function MM_openBrWindow(theURL,winName,features) { //v2.0
  window.open(theURL,winName,features);
}
//-->
</script>
</head>

<body>
<div align="center">
    <h1><strong>  <img src="atomo.png" width="30" height="25">&nbsp;&nbsp;&nbsp;FyQ DIANA. CUESTIONARIO ELABORADO&nbsp;&nbsp; <img src="atomo.png" width="30" height="25"> </strong></h1>
    <?
$fp = fopen($nomfic,"a");
fwrite($fp, "'AUTOR/A: ".$_POST['nombautor']."'"."\r\n");
fwrite($fp, "'TEMA: ".$_POST['nombbateria']."'"."\r\n\r\n");
fwrite($fp,"'".$_POST['num1']."'"."\r\n");
fwrite($fp,"'".$_POST['pregA1']."'"."\r\n");
fwrite($fp,"'".$_POST['pregB1']."'"."\r\n");
fwrite($fp,"'".$_POST['pregC1']."'"."\r\n");
fwrite($fp,$_POST['A1']."\r\n");
fwrite($fp,"'".$_POST['num2']."'"."\r\n");
fwrite($fp,"'".$_POST['pregA2']."'"."\r\n");
fwrite($fp,"'".$_POST['pregB2']."'"."\r\n");
fwrite($fp,"'".$_POST['pregC2']."'"."\r\n");
fwrite($fp,$_POST['A2']."\r\n");
fwrite($fp,"'".$_POST['num3']."'"."\r\n");
fwrite($fp,"'".$_POST['pregA3']."'"."\r\n");
fwrite($fp,"'".$_POST['pregB3']."'"."\r\n");
fwrite($fp,"'".$_POST['pregC3']."'"."\r\n");
fwrite($fp,$_POST['A3']."\r\n");
fwrite($fp,"'".$_POST['num4']."'"."\r\n");
fwrite($fp,"'".$_POST['pregA4']."'"."\r\n");
fwrite($fp,"'".$_POST['pregB4']."'"."\r\n");
fwrite($fp,"'".$_POST['pregC4']."'"."\r\n");
fwrite($fp,$_POST['A4']."\r\n");
fwrite($fp,"'".$_POST['num5']."'"."\r\n");
fwrite($fp,"'".$_POST['pregA5']."'"."\r\n");
fwrite($fp,"'".$_POST['pregB5']."'"."\r\n");
fwrite($fp,"'".$_POST['pregC5']."'"."\r\n");
fwrite($fp,$_POST['A5']."\r\n");
fwrite($fp, "'FyQ DIANA'");
fclose($fp);
?>

<table width="100%"  border="1">
  <tr>
        <td colspan="2"><div align="center">
          <h2>DATOS GENERALES </h2>
        </div></td>
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
        <td colspan="2"><div align="center" class="Estilo1">             <? echo  $_POST['num1'] ?></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong> Enunciado de la pregunta: </strong></div></td>
        <td><div align="center"><strong>Respuestas: </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregA1'],"<br>";
				   ?></strong></div></td>
        <td rowspan="3"><div align="center"><strong><? echo  $_POST['A1'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregB1'],"<br>";
				   ?></strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregC1'],"<br>";
				   ?></strong></div></td>
      </tr>
      <tr>
        <td colspan="2"><div align="center" class="Estilo1">  <? echo  $_POST['num2'] ?></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong> Enunciado de la pregunta: </strong></div></td>
        <td><div align="center"><strong>Respuestas: </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregA2'],"<br>"; ?></strong></div></td>
        <td rowspan="3"><div align="center"><strong><? echo  $_POST['A2'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregB2'],"<br>";
 ?></strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregC2'],"<br>"; ?></strong></div></td>
      </tr>
      <tr>
        <td colspan="2"><div align="center" class="Estilo1">  <? echo  $_POST['num3'] ?></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong> Enunciado de la pregunta: </strong></div></td>
        <td><div align="center"><strong>Respuestas: </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregA3'],"<br>"; ?></strong></div></td>
        <td rowspan="3"><div align="center"><strong><? echo  $_POST['A3'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregB3'],"<br>";
 ?></strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregC3'],"<br>"; ?></strong></div></td>
      </tr>
      <tr>
        <td colspan="2"><div align="center" class="Estilo1">  <? echo  $_POST['num4'] ?></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong> Enunciado de la pregunta: </strong></div></td>
        <td><div align="center"><strong>Respuestas: </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregA4'],"<br>"; ?></strong></div></td>
        <td rowspan="3"><div align="center"><strong><? echo  $_POST['A4'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregB4'],"<br>";
 ?></strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregC4'],"<br>"; ?></strong></div></td>
      </tr>
      <tr>
        <td colspan="2"><div align="center" class="Estilo1">  <? echo  $_POST['num5'] ?></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong> Enunciado de la pregunta: </strong></div></td>
        <td><div align="center"><strong>Respuestas: </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregA5'],"<br>"; ?></strong></div></td>
        <td rowspan="3"><div align="center"><strong><? echo  $_POST['A5'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregB5'],"<br>";
 ?></strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregC5'],"<br>"; ?></strong></div></td>
      </tr>
  </table>

</div>

<CENTER>




<p align="center" ondeactivate="MM_openBrWindow('<? echo $nomfic; ?>','','')">SI DESEAS MODIFICAR EL FORMULARIO, PINCHAR EL BOT&Oacute;N VOLVER AL FORMULARIO, PERO LOS DATOS INTRODUCIDOS SE PERDER&Aacute;N. TAMBI&Eacute;N SE PUEDE GENERAR EL FICHERO Y MODIFICAR EN &Eacute;L LOS ERRORES DETECTADOS</p>
<p align="center" ondeactivate="MM_openBrWindow('<? echo $nomfic; ?>','','')">AL FINALIZAR SE DEBE DE CERRAR LA SESI&Oacute;N PARA QUE TODO FUNCIONE CORRECTAMENTE<BR>
</p>
<div align="center">
  <table width="80%" border="0">
    <tr>
      <td><div align="center">
          <input name="button" type="button" onClick="MM_openBrWindow('<? echo $nomfic; ?>','','menubar=yes,scrollbars=yes')" value="ABRIR FICHERO EN EL NAVEGADOR">
      </div></td>
      <td><div align="center">
          <input name="button3" type="button" onClick="MM_goToURL('parent','descargarfich.php');return document.MM_returnValue" value="DESCARGAR EL FICHERO GENERADO">
      </div></td>
    </tr>
    <tr>
      <td><div align="center">
          <input name="button2" type="button" onClick="history.go(-1)" value="VOLVER AL FORMULARIO">
      </div></td>
      <td><div align="center">
          <input name="button32" type="button" onClick="MM_goToURL('parent','cierre.php');return document.MM_returnValue" value="CERRAR SESI&Oacute;N">
      </div></td>
    </tr>
  </table>
</div>
</body>
</html>

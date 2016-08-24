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
<title>FyQ DEL TIRÓN. CUESTIONARIO ELABORADO</title>
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

function MM_goToURL() { //v3.0
  var i, args=MM_goToURL.arguments; document.MM_returnValue = false;
  for (i=0; i<(args.length-1); i+=2) eval(args[i]+".location='"+args[i+1]+"'");
}
//-->
</script>
</head>

<body>
<div align="center">
    <h1><strong>  <img src="atomo.png" width="30" height="25">&nbsp;&nbsp;&nbsp;FyQ DEL TIRÓN. CUESTIONARIO ELABORADO&nbsp;&nbsp; <img src="atomo.png" width="30" height="25"> </strong></h1>
    <?
$fp = fopen($nomfic,"a");
fwrite($fp, "'AUTOR/A: ".$_POST['nombautor']."'"."\r\n");
fwrite($fp, "'TEMA: ".$_POST['nombbateria']."'"."\r\n");
fwrite($fp,"'".$_POST['P1']."'"."\r\n");
fwrite($fp,"'".$_POST['R1']."'"."\r\n");
fwrite($fp,"'".$_POST['P2']."'"."\r\n");
fwrite($fp,"'".$_POST['R2']."'"."\r\n");
fwrite($fp,"'".$_POST['P3']."'"."\r\n");
fwrite($fp,"'".$_POST['R3']."'"."\r\n");
fwrite($fp,"'".$_POST['P4']."'"."\r\n");
fwrite($fp,"'".$_POST['R4']."'"."\r\n");
fwrite($fp,"'".$_POST['P5']."'"."\r\n");
fwrite($fp,"'".$_POST['R5']."'"."\r\n");
fwrite($fp,"'".$_POST['P6']."'"."\r\n");
fwrite($fp,"'".$_POST['R6']."'"."\r\n");
fwrite($fp,"'".$_POST['P7']."'"."\r\n");
fwrite($fp,"'".$_POST['R7']."'"."\r\n");
fwrite($fp,"'".$_POST['P8']."'"."\r\n");
fwrite($fp,"'".$_POST['R8']."'"."\r\n");
fwrite($fp,"'".$_POST['P9']."'"."\r\n");
fwrite($fp,"'".$_POST['R9']."'"."\r\n");
fwrite($fp,"'".$_POST['P10']."'"."\r\n");
fwrite($fp,"'".$_POST['R10']."'"."\r\n");
fwrite($fp, "'FyQ DEL TIRÓN'"."\r\n");
fclose($fp);
?>

<table width="100%"  border="1">
  <tr>
        <td colspan="3"><div align="center">
          <h2>DATOS GENERALES </h2>
        </div></td>
    </tr>
      <tr>
        <td colspan="3"><div align="center"><strong>Nombre y apellido del autor de la bater&iacute;a de preguntas: 
              <? echo  $_POST['nombautor'] ?>
        </strong></div></td>
      </tr>
      <tr>
        <td colspan="3"><div align="center"><strong>T&iacute;tulo de la bater&iacute;a de preguntas: <? echo  $_POST['nombbateria'] ?> </strong></div></td>
      </tr>
      <tr>
        <td colspan="3">&nbsp;</td>
      </tr>
      <tr>
        <td width="79" height="28"><div align="center"><strong>N&uacute;mero </strong></div></td>
        <td width="442" height="28"><div align="center"><strong>Pregunta</strong></div></td>
        <td width="477" height="28"><div align="center"><strong>Respuesta</strong></div></td>
      </tr>
      <tr>
        <td height="28"><div align="center"><strong>1</strong></div></td>
        <td><div align="center"><strong>
          <? echo  $_POST['P1'] ?>        </strong></div></td>
        <td><div align="center"><strong>
          <? echo  $_POST['R1'] ?>        </strong></div></td>
      </tr>
      <tr>
        <td height="28"><div align="center"><strong>2</strong></div></td>
        <td><div align="center"><strong><? echo  $_POST['P2'] ?></strong></div></td>
        <td><div align="center"><strong><? echo  $_POST['R2'] ?></strong></div></td>
      </tr>
      <tr>
        <td height="28"><div align="center"><strong>3</strong></div></td>
        <td><div align="center"><strong><? echo  $_POST['P3'] ?></strong></div></td>
        <td><div align="center"><strong><? echo  $_POST['R3'] ?></strong></div></td>
      </tr>
      <tr>
        <td height="28"><div align="center"><strong>4 </strong></div></td>
        <td><div align="center"><strong><? echo  $_POST['P4'] ?></strong></div></td>
        <td><div align="center"><strong><? echo  $_POST['R4'] ?></strong></div></td>
      </tr>
      <tr>
        <td height="28"><div align="center"><strong>5</strong></div></td>
        <td><div align="center"><strong><? echo  $_POST['P5'] ?></strong></div></td>
        <td><div align="center"><strong><? echo  $_POST['R5'] ?></strong></div></td>
      </tr>
      <tr>
        <td height="28"><div align="center"><strong>6 </strong></div></td>
        <td><div align="center"><strong><? echo  $_POST['P6'] ?></strong></div></td>
        <td><div align="center"><strong><? echo  $_POST['R6'] ?></strong></div></td>
      </tr>
      <tr>
        <td height="28"><div align="center"><strong>7 </strong></div></td>
        <td><div align="center"><strong><? echo  $_POST['P7'] ?></strong></div></td>
        <td><div align="center"><strong><? echo  $_POST['R7'] ?></strong></div></td>
      </tr>
      <tr>
        <td height="28"><div align="center"><strong>8 </strong></div></td>
        <td><div align="center"><strong><? echo  $_POST['P8'] ?></strong></div></td>
        <td><div align="center"><strong><? echo  $_POST['R8'] ?></strong></div></td>
      </tr>
      <tr>
        <td height="28"><div align="center"><strong>9 </strong></div></td>
        <td><div align="center"><strong><? echo  $_POST['P9'] ?></strong></div></td>
        <td><div align="center"><strong><? echo  $_POST['R9'] ?></strong></div></td>
      </tr>
      <tr>
        <td height="28"><div align="center"><strong>10 </strong></div></td>
        <td><div align="center"><strong><? echo  $_POST['P10'] ?></strong></div></td>
        <td><div align="center"><strong><? echo  $_POST['R10'] ?></strong></div></td>
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

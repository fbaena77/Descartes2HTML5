<?
session_start();
session_name();
?>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>FyQ EL PARTIDO. CUESTIONARIO ELABORADO</title>
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
//-->
</script>
</head>

<body>
<div align="center">
    <h1><strong>  <img src="atomo.png" width="30" height="25">&nbsp;&nbsp;&nbsp;FyQ EL PARTIDO. CUESTIONARIO ELABORADO&nbsp;&nbsp; <img src="atomo.png" width="30" height="25"> </strong></h1>
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
fwrite($fp, "'AUTOR/A: ".$_POST['nombautor']."'"."\r\n");
fwrite($fp, "'TEMA: ".$_POST['nombbateria']."'"."\r\n\r\n");
fwrite($fp,"'".$_POST['num1']."'"."\r\n");
fwrite($fp,"'".$_POST['pregA1']."'"."\r\n");
fwrite($fp,"'".$_POST['pregB1']."'"."\r\n");
fwrite($fp,"'".$_POST['pregC1']."'"."\r\n");
fwrite($fp,"'".$_POST['opcA1']."'"."\r\n");
fwrite($fp,"'".$_POST['opcB1']."'"."\r\n");
fwrite($fp,"'".$_POST['opcC1']."'"."\r\n");
fwrite($fp,"'".$_POST['opcD1']."'"."\r\n");
fwrite($fp,"'".$_POST['opcorrect1']."'"."\r\n");
fwrite($fp,"'".$_POST['num2']."'"."\r\n");
fwrite($fp,"'".$_POST['pregA2']."'"."\r\n");
fwrite($fp,"'".$_POST['pregB2']."'"."\r\n");
fwrite($fp,"'".$_POST['pregC2']."'"."\r\n");
fwrite($fp,"'".$_POST['opcA2']."'"."\r\n");
fwrite($fp,"'".$_POST['opcB2']."'"."\r\n");
fwrite($fp,"'".$_POST['opcC2']."'"."\r\n");
fwrite($fp,"'".$_POST['opcD2']."'"."\r\n");
fwrite($fp,"'".$_POST['opcorrect2']."'"."\r\n");
fwrite($fp,"'".$_POST['num3']."'"."\r\n");
fwrite($fp,"'".$_POST['pregA3']."'"."\r\n");
fwrite($fp,"'".$_POST['pregB3']."'"."\r\n");
fwrite($fp,"'".$_POST['pregC3']."'"."\r\n");
fwrite($fp,"'".$_POST['opcA3']."'"."\r\n");
fwrite($fp,"'".$_POST['opcB3']."'"."\r\n");
fwrite($fp,"'".$_POST['opcC3']."'"."\r\n");
fwrite($fp,"'".$_POST['opcD3']."'"."\r\n");
fwrite($fp,"'".$_POST['opcorrect3']."'"."\r\n");
fwrite($fp,"'".$_POST['num4']."'"."\r\n");
fwrite($fp,"'".$_POST['pregA4']."'"."\r\n");
fwrite($fp,"'".$_POST['pregB4']."'"."\r\n");
fwrite($fp,"'".$_POST['pregC4']."'"."\r\n");
fwrite($fp,"'".$_POST['opcA4']."'"."\r\n");
fwrite($fp,"'".$_POST['opcB4']."'"."\r\n");
fwrite($fp,"'".$_POST['opcC4']."'"."\r\n");
fwrite($fp,"'".$_POST['opcD4']."'"."\r\n");
fwrite($fp,"'".$_POST['opcorrect4']."'"."\r\n");
fwrite($fp,"'".$_POST['num5']."'"."\r\n");
fwrite($fp,"'".$_POST['pregA5']."'"."\r\n");
fwrite($fp,"'".$_POST['pregB5']."'"."\r\n");
fwrite($fp,"'".$_POST['pregC5']."'"."\r\n");
fwrite($fp,"'".$_POST['opcA5']."'"."\r\n");
fwrite($fp,"'".$_POST['opcB5']."'"."\r\n");
fwrite($fp,"'".$_POST['opcC5']."'"."\r\n");
fwrite($fp,"'".$_POST['opcD5']."'"."\r\n");
fwrite($fp,"'".$_POST['opcorrect5']."'"."\r\n");
fwrite($fp,"'".$_POST['num6']."'"."\r\n");
fwrite($fp,"'".$_POST['pregA6']."'"."\r\n");
fwrite($fp,"'".$_POST['pregB6']."'"."\r\n");
fwrite($fp,"'".$_POST['pregC6']."'"."\r\n");
fwrite($fp,"'".$_POST['opcA6']."'"."\r\n");
fwrite($fp,"'".$_POST['opcB6']."'"."\r\n");
fwrite($fp,"'".$_POST['opcC6']."'"."\r\n");
fwrite($fp,"'".$_POST['opcD6']."'"."\r\n");
fwrite($fp,"'".$_POST['opcorrect6']."'"."\r\n");
fwrite($fp,"'".$_POST['num7']."'"."\r\n");
fwrite($fp,"'".$_POST['pregA7']."'"."\r\n");
fwrite($fp,"'".$_POST['pregB7']."'"."\r\n");
fwrite($fp,"'".$_POST['pregC7']."'"."\r\n");
fwrite($fp,"'".$_POST['opcA7']."'"."\r\n");
fwrite($fp,"'".$_POST['opcB7']."'"."\r\n");
fwrite($fp,"'".$_POST['opcC7']."'"."\r\n");
fwrite($fp,"'".$_POST['opcD7']."'"."\r\n");
fwrite($fp,"'".$_POST['opcorrect7']."'"."\r\n");
fwrite($fp,"'".$_POST['num8']."'"."\r\n");
fwrite($fp,"'".$_POST['pregA8']."'"."\r\n");
fwrite($fp,"'".$_POST['pregB8']."'"."\r\n");
fwrite($fp,"'".$_POST['pregC8']."'"."\r\n");
fwrite($fp,"'".$_POST['opcA8']."'"."\r\n");
fwrite($fp,"'".$_POST['opcB8']."'"."\r\n");
fwrite($fp,"'".$_POST['opcC8']."'"."\r\n");
fwrite($fp,"'".$_POST['opcD8']."'"."\r\n");
fwrite($fp,"'".$_POST['opcorrect8']."'"."\r\n");
fwrite($fp,"'".$_POST['num9']."'"."\r\n");
fwrite($fp,"'".$_POST['pregA9']."'"."\r\n");
fwrite($fp,"'".$_POST['pregB9']."'"."\r\n");
fwrite($fp,"'".$_POST['pregC9']."'"."\r\n");
fwrite($fp,"'".$_POST['opcA9']."'"."\r\n");
fwrite($fp,"'".$_POST['opcB9']."'"."\r\n");
fwrite($fp,"'".$_POST['opcC9']."'"."\r\n");
fwrite($fp,"'".$_POST['opcD9']."'"."\r\n");
fwrite($fp,"'".$_POST['opcorrect9']."'"."\r\n");
fwrite($fp,"'".$_POST['num10']."'"."\r\n");
fwrite($fp,"'".$_POST['pregA10']."'"."\r\n");
fwrite($fp,"'".$_POST['pregB10']."'"."\r\n");
fwrite($fp,"'".$_POST['pregC10']."'"."\r\n");
fwrite($fp,"'".$_POST['opcA10']."'"."\r\n");
fwrite($fp,"'".$_POST['opcB10']."'"."\r\n");
fwrite($fp,"'".$_POST['opcC10']."'"."\r\n");
fwrite($fp,"'".$_POST['opcD10']."'"."\r\n");
fwrite($fp,"'".$_POST['opcorrect10']."'"."\r\n");
fwrite($fp,"'".$_POST['num11']."'"."\r\n");
fwrite($fp,"'".$_POST['pregA11']."'"."\r\n");
fwrite($fp,"'".$_POST['pregB11']."'"."\r\n");
fwrite($fp,"'".$_POST['pregC11']."'"."\r\n");
fwrite($fp,"'".$_POST['opcA11']."'"."\r\n");
fwrite($fp,"'".$_POST['opcB11']."'"."\r\n");
fwrite($fp,"'".$_POST['opcC11']."'"."\r\n");
fwrite($fp,"'".$_POST['opcD11']."'"."\r\n");
fwrite($fp,"'".$_POST['opcorrect11']."'"."\r\n");
fwrite($fp,"'".$_POST['num12']."'"."\r\n");
fwrite($fp,"'".$_POST['pregA12']."'"."\r\n");
fwrite($fp,"'".$_POST['pregB12']."'"."\r\n");
fwrite($fp,"'".$_POST['pregC12']."'"."\r\n");
fwrite($fp,"'".$_POST['opcA12']."'"."\r\n");
fwrite($fp,"'".$_POST['opcB12']."'"."\r\n");
fwrite($fp,"'".$_POST['opcC12']."'"."\r\n");
fwrite($fp,"'".$_POST['opcD12']."'"."\r\n");
fwrite($fp,"'".$_POST['opcorrect12']."'"."\r\n");
fwrite($fp,"'".$_POST['num13']."'"."\r\n");
fwrite($fp,"'".$_POST['pregA13']."'"."\r\n");
fwrite($fp,"'".$_POST['pregB13']."'"."\r\n");
fwrite($fp,"'".$_POST['pregC13']."'"."\r\n");
fwrite($fp,"'".$_POST['opcA13']."'"."\r\n");
fwrite($fp,"'".$_POST['opcB13']."'"."\r\n");
fwrite($fp,"'".$_POST['opcC13']."'"."\r\n");
fwrite($fp,"'".$_POST['opcD13']."'"."\r\n");
fwrite($fp,"'".$_POST['opcorrect13']."'"."\r\n");
fwrite($fp,"'".$_POST['num14']."'"."\r\n");
fwrite($fp,"'".$_POST['pregA14']."'"."\r\n");
fwrite($fp,"'".$_POST['pregB14']."'"."\r\n");
fwrite($fp,"'".$_POST['pregC14']."'"."\r\n");
fwrite($fp,"'".$_POST['opcA14']."'"."\r\n");
fwrite($fp,"'".$_POST['opcB14']."'"."\r\n");
fwrite($fp,"'".$_POST['opcC14']."'"."\r\n");
fwrite($fp,"'".$_POST['opcD14']."'"."\r\n");
fwrite($fp,"'".$_POST['opcorrect14']."'"."\r\n");
fwrite($fp,"'".$_POST['num15']."'"."\r\n");
fwrite($fp,"'".$_POST['pregA15']."'"."\r\n");
fwrite($fp,"'".$_POST['pregB15']."'"."\r\n");
fwrite($fp,"'".$_POST['pregC15']."'"."\r\n");
fwrite($fp,"'".$_POST['opcA15']."'"."\r\n");
fwrite($fp,"'".$_POST['opcB15']."'"."\r\n");
fwrite($fp,"'".$_POST['opcC15']."'"."\r\n");
fwrite($fp,"'".$_POST['opcD15']."'"."\r\n");
fwrite($fp,"'".$_POST['opcorrect15']."'"."\r\n");
fwrite($fp,"'".$_POST['num16']."'"."\r\n");
fwrite($fp,"'".$_POST['pregA16']."'"."\r\n");
fwrite($fp,"'".$_POST['pregB16']."'"."\r\n");
fwrite($fp,"'".$_POST['pregC16']."'"."\r\n");
fwrite($fp,"'".$_POST['opcA16']."'"."\r\n");
fwrite($fp,"'".$_POST['opcB16']."'"."\r\n");
fwrite($fp,"'".$_POST['opcC16']."'"."\r\n");
fwrite($fp,"'".$_POST['opcD16']."'"."\r\n");
fwrite($fp,"'".$_POST['opcorrect16']."'"."\r\n");
fwrite($fp,"'".$_POST['num17']."'"."\r\n");
fwrite($fp,"'".$_POST['pregA17']."'"."\r\n");
fwrite($fp,"'".$_POST['pregB17']."'"."\r\n");
fwrite($fp,"'".$_POST['pregC17']."'"."\r\n");
fwrite($fp,"'".$_POST['opcA17']."'"."\r\n");
fwrite($fp,"'".$_POST['opcB17']."'"."\r\n");
fwrite($fp,"'".$_POST['opcC17']."'"."\r\n");
fwrite($fp,"'".$_POST['opcD17']."'"."\r\n");
fwrite($fp,"'".$_POST['opcorrect17']."'"."\r\n");
fwrite($fp,"'".$_POST['num18']."'"."\r\n");
fwrite($fp,"'".$_POST['pregA18']."'"."\r\n");
fwrite($fp,"'".$_POST['pregB18']."'"."\r\n");
fwrite($fp,"'".$_POST['pregC18']."'"."\r\n");
fwrite($fp,"'".$_POST['opcA18']."'"."\r\n");
fwrite($fp,"'".$_POST['opcB18']."'"."\r\n");
fwrite($fp,"'".$_POST['opcC18']."'"."\r\n");
fwrite($fp,"'".$_POST['opcD18']."'"."\r\n");
fwrite($fp,"'".$_POST['opcorrect18']."'"."\r\n");
fwrite($fp,"'".$_POST['num19']."'"."\r\n");
fwrite($fp,"'".$_POST['pregA19']."'"."\r\n");
fwrite($fp,"'".$_POST['pregB19']."'"."\r\n");
fwrite($fp,"'".$_POST['pregC19']."'"."\r\n");
fwrite($fp,"'".$_POST['opcA19']."'"."\r\n");
fwrite($fp,"'".$_POST['opcB19']."'"."\r\n");
fwrite($fp,"'".$_POST['opcC19']."'"."\r\n");
fwrite($fp,"'".$_POST['opcD19']."'"."\r\n");
fwrite($fp,"'".$_POST['opcorrect19']."'"."\r\n");
fwrite($fp,"'".$_POST['num20']."'"."\r\n");
fwrite($fp,"'".$_POST['pregA20']."'"."\r\n");
fwrite($fp,"'".$_POST['pregB20']."'"."\r\n");
fwrite($fp,"'".$_POST['pregC20']."'"."\r\n");
fwrite($fp,"'".$_POST['opcA20']."'"."\r\n");
fwrite($fp,"'".$_POST['opcB20']."'"."\r\n");
fwrite($fp,"'".$_POST['opcC20']."'"."\r\n");
fwrite($fp,"'".$_POST['opcD20']."'"."\r\n");
fwrite($fp,"'".$_POST['opcorrect20']."'"."\r\n");
fwrite($fp, "FyQ EL PARTIDO");
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
        <td colspan="2"><div align="center" class="Estilo1">             <? echo  $_POST['num1'] ?></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong> Enunciado de la pregunta: </strong></div></td>
        <td><div align="center"><strong>Respuestas: </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregA1'],"<br>";
				   ?></strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n A: <? echo  $_POST['opcA1'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregB1'],"<br>";
				   ?></strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n B: <? echo  $_POST['opcB1'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregC1'],"<br>";
				   ?></strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n C: <? echo  $_POST['opcC1'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>Opci&oacute;n correcta: <? echo  $_POST['opcorrect1'] ?> </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n D: <? echo  $_POST['opcD1'] ?>        </strong></div></td>
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
        <td><div align="center"><strong>Opci&oacute;n A: <? echo  $_POST['opcA2'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregB2'],"<br>";
 ?></strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n B: <? echo  $_POST['opcB2'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregC2'],"<br>"; ?></strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n C: <? echo  $_POST['opcC2'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>Opci&oacute;n correcta: <? echo  $_POST['opcorrect2'] ?> </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n D: <? echo  $_POST['opcD2'] ?> </strong></div></td>
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
        <td><div align="center"><strong>Opci&oacute;n A: <? echo  $_POST['opcA3'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregB3'],"<br>";
 ?></strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n B: <? echo  $_POST['opcB3'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregC3'],"<br>"; ?></strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n C: <? echo  $_POST['opcC3'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>Opci&oacute;n correcta: <? echo  $_POST['opcorrect3'] ?> </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n D: <? echo  $_POST['opcD3'] ?> </strong></div></td>
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
        <td><div align="center"><strong>Opci&oacute;n A: <? echo  $_POST['opcA4'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregB4'],"<br>";
 ?></strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n B: <? echo  $_POST['opcB4'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregC4'],"<br>"; ?></strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n C: <? echo  $_POST['opcC4'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>Opci&oacute;n correcta: <? echo  $_POST['opcorrect4'] ?> </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n D: <? echo  $_POST['opcD4'] ?> </strong></div></td>
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
        <td><div align="center"><strong>Opci&oacute;n A: <? echo  $_POST['opcA5'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregB5'],"<br>";
 ?></strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n B: <? echo  $_POST['opcB5'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregC5'],"<br>"; ?></strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n C: <? echo  $_POST['opcC5'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>Opci&oacute;n correcta: <? echo  $_POST['opcorrect5'] ?> </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n D: <? echo  $_POST['opcD5'] ?> </strong></div></td>
      </tr>
      <tr>
        <td colspan="2"><div align="center" class="Estilo1">  <? echo  $_POST['num6'] ?></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong> Enunciado de la pregunta: </strong></div></td>
        <td><div align="center"><strong>Respuestas: </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregA6'],"<br>"; ?></strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n A: <? echo  $_POST['opcA6'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregB6'],"<br>";
 ?></strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n B: <? echo  $_POST['opcB6'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregC6'],"<br>"; ?></strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n C: <? echo  $_POST['opcC6'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>Opci&oacute;n correcta: <? echo  $_POST['opcorrect6'] ?> </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n D: <? echo  $_POST['opcD6'] ?> </strong></div></td>
      </tr>
      <tr>
        <td colspan="2"><div align="center" class="Estilo1">  <? echo  $_POST['num7'] ?></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong> Enunciado de la pregunta: </strong></div></td>
        <td><div align="center"><strong>Respuestas: </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregA7'],"<br>"; ?></strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n A: <? echo  $_POST['opcA7'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregB7'],"<br>";
 ?></strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n B: <? echo  $_POST['opcB7'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregC7'],"<br>"; ?></strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n C: <? echo  $_POST['opcC7'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>Opci&oacute;n correcta: <? echo  $_POST['opcorrect7'] ?> </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n D: <? echo  $_POST['opcD7'] ?> </strong></div></td>
      </tr>
      <tr>
        <td colspan="2"><div align="center" class="Estilo1">  <? echo  $_POST['num8'] ?></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong> Enunciado de la pregunta: </strong></div></td>
        <td><div align="center"><strong>Respuestas: </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregA8'],"<br>"; ?></strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n A: <? echo  $_POST['opcA8'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregB8'],"<br>";
 ?></strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n B: <? echo  $_POST['opcB8'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregC8'],"<br>"; ?></strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n C: <? echo  $_POST['opcC8'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>Opci&oacute;n correcta: <? echo  $_POST['opcorrect8'] ?> </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n D: <? echo  $_POST['opcD8'] ?> </strong></div></td>
      </tr>
      <tr>
        <td colspan="2"><div align="center" class="Estilo1">  <? echo  $_POST['num9'] ?></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong> Enunciado de la pregunta: </strong></div></td>
        <td><div align="center"><strong>Respuestas: </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregA9'],"<br>"; ?></strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n A: <? echo  $_POST['opcA9'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregB9'],"<br>";
 ?></strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n B: <? echo  $_POST['opcB9'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregC9'],"<br>"; ?></strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n C: <? echo  $_POST['opcC9'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>Opci&oacute;n correcta: <? echo  $_POST['opcorrect9'] ?> </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n D: <? echo  $_POST['opcD9'] ?> </strong></div></td>
      </tr>
      <tr>
        <td colspan="2"><div align="center" class="Estilo1">  <? echo  $_POST['num10'] ?></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong> Enunciado de la pregunta: </strong></div></td>
        <td><div align="center"><strong>Respuestas: </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregA10'],"<br>"; ?></strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n A: <? echo  $_POST['opcA10'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregB10'],"<br>";
 ?></strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n B: <? echo  $_POST['opcB10'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregC10'],"<br>"; ?></strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n C: <? echo  $_POST['opcC10'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>Opci&oacute;n correcta: <? echo  $_POST['opcorrect10'] ?> </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n D: <? echo  $_POST['opcD10'] ?> </strong></div></td>
      </tr>
      <tr>
        <td colspan="2"><div align="center" class="Estilo1">  <? echo  $_POST['num11'] ?></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong> Enunciado de la pregunta: </strong></div></td>
        <td><div align="center"><strong>Respuestas: </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregA11'],"<br>"; ?></strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n A: <? echo  $_POST['opcA11'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregB11'],"<br>";
 ?></strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n B: <? echo  $_POST['opcB11'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregC11'],"<br>"; ?></strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n C: <? echo  $_POST['opcC11'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>Opci&oacute;n correcta: <? echo  $_POST['opcorrect11'] ?> </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n D: <? echo  $_POST['opcD11'] ?> </strong></div></td>
      </tr>
      <tr>
        <td colspan="2"><div align="center" class="Estilo1">  <? echo  $_POST['num12'] ?></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong> Enunciado de la pregunta: </strong></div></td>
        <td><div align="center"><strong>Respuestas: </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregA12'],"<br>"; ?></strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n A: <? echo  $_POST['opcA12'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregB12'],"<br>";
 ?></strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n B: <? echo  $_POST['opcB12'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregC12'],"<br>"; ?></strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n C: <? echo  $_POST['opcC12'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>Opci&oacute;n correcta: <? echo  $_POST['opcorrect12'] ?> </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n D: <? echo  $_POST['opcD12'] ?> </strong></div></td>
      </tr>
      <tr>
        <td colspan="2"><div align="center" class="Estilo1">  <? echo  $_POST['num13'] ?></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong> Enunciado de la pregunta: </strong></div></td>
        <td><div align="center"><strong>Respuestas: </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregA13'],"<br>"; ?></strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n A: <? echo  $_POST['opcA13'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregB13'],"<br>";
 ?></strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n B: <? echo  $_POST['opcB13'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregC13'],"<br>"; ?></strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n C: <? echo  $_POST['opcC13'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>Opci&oacute;n correcta: <? echo  $_POST['opcorrect13'] ?> </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n D: <? echo  $_POST['opcD13'] ?> </strong></div></td>
      </tr>
      <tr>
        <td colspan="2"><div align="center" class="Estilo1">  <? echo  $_POST['num14'] ?></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong> Enunciado de la pregunta: </strong></div></td>
        <td><div align="center"><strong>Respuestas: </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregA14'],"<br>"; ?></strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n A: <? echo  $_POST['opcA14'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregB14'],"<br>";
 ?></strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n B: <? echo  $_POST['opcB14'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregC14'],"<br>"; ?></strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n C: <? echo  $_POST['opcC14'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>Opci&oacute;n correcta: <? echo  $_POST['opcorrect14'] ?> </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n D: <? echo  $_POST['opcD14'] ?> </strong></div></td>
      </tr>
      <tr>
        <td colspan="2"><div align="center" class="Estilo1">  <? echo  $_POST['num15'] ?></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong> Enunciado de la pregunta: </strong></div></td>
        <td><div align="center"><strong>Respuestas: </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregA15'],"<br>"; ?></strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n A: <? echo  $_POST['opcA15'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregB15'],"<br>";
 ?></strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n B: <? echo  $_POST['opcB15'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregC15'],"<br>"; ?></strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n C: <? echo  $_POST['opcC15'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>Opci&oacute;n correcta: <? echo  $_POST['opcorrect15'] ?> </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n D: <? echo  $_POST['opcD15'] ?> </strong></div></td>
      </tr>
      <tr>
        <td colspan="2"><div align="center" class="Estilo1">  <? echo  $_POST['num16'] ?></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong> Enunciado de la pregunta: </strong></div></td>
        <td><div align="center"><strong>Respuestas: </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregA16'],"<br>"; ?></strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n A: <? echo  $_POST['opcA16'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregB16'],"<br>";
 ?></strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n B: <? echo  $_POST['opcB16'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregC16'],"<br>"; ?></strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n C: <? echo  $_POST['opcC16'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>Opci&oacute;n correcta: <? echo  $_POST['opcorrect16'] ?> </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n D: <? echo  $_POST['opcD16'] ?> </strong></div></td>
      </tr>
      <tr>
        <td colspan="2"><div align="center" class="Estilo1">  <? echo  $_POST['num17'] ?></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong> Enunciado de la pregunta: </strong></div></td>
        <td><div align="center"><strong>Respuestas: </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregA17'],"<br>"; ?></strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n A: <? echo  $_POST['opcA17'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregB17'],"<br>";
 ?></strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n B: <? echo  $_POST['opcB17'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregC17'],"<br>"; ?></strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n C: <? echo  $_POST['opcC17'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>Opci&oacute;n correcta: <? echo  $_POST['opcorrect17'] ?> </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n D: <? echo  $_POST['opcD17'] ?> </strong></div></td>
      </tr>
      <tr>
        <td colspan="2"><div align="center" class="Estilo1">  <? echo  $_POST['num18'] ?></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong> Enunciado de la pregunta: </strong></div></td>
        <td><div align="center"><strong>Respuestas: </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregA18'],"<br>"; ?></strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n A: <? echo  $_POST['opcA18'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregB18'],"<br>";
 ?></strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n B: <? echo  $_POST['opcB18'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregC18'],"<br>"; ?></strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n C: <? echo  $_POST['opcC18'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>Opci&oacute;n correcta: <? echo  $_POST['opcorrect18'] ?> </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n D: <? echo  $_POST['opcD18'] ?> </strong></div></td>
      </tr>
      <tr>
        <td colspan="2"><div align="center" class="Estilo1">  <? echo  $_POST['num19'] ?></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong> Enunciado de la pregunta: </strong></div></td>
        <td><div align="center"><strong>Respuestas: </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregA19'],"<br>"; ?></strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n A: <? echo  $_POST['opcA19'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregB19'],"<br>";
 ?></strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n B: <? echo  $_POST['opcB19'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregC19'],"<br>"; ?></strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n C: <? echo  $_POST['opcC19'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>Opci&oacute;n correcta: <? echo  $_POST['opcorrect19'] ?> </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n D: <? echo  $_POST['opcD19'] ?> </strong></div></td>
      </tr>
      <tr>
        <td colspan="2"><div align="center" class="Estilo1">  <? echo  $_POST['num20'] ?></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong> Enunciado de la pregunta: </strong></div></td>
        <td><div align="center"><strong>Respuestas: </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregA20'],"<br>"; ?></strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n A: <? echo  $_POST['opcA20'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregB20'],"<br>";
 ?></strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n B: <? echo  $_POST['opcB20'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong><? echo  $_POST['pregC20'],"<br>"; ?></strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n C: <? echo  $_POST['opcC20'] ?> </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>Opci&oacute;n correcta: <? echo  $_POST['opcorrect20'] ?> </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n D: <? echo  $_POST['opcD20'] ?> </strong></div></td>
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

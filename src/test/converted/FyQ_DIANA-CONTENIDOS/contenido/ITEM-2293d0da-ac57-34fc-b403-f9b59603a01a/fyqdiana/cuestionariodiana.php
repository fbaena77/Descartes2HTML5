<?
 session_name();
 session_start();
 $FICHERO2=$_SESSION['F2'];

if(file_exists($FICHERO2))
{
unlink($FICHERO2);
}
?>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>FyQ DIANA. DISE&Ntilde;O DE BATER&Iacute;A DE PREGUNTAS</title>
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
function MM_openBrWindow(theURL,winName,features) { //v2.0
  window.open(theURL,winName,features);
}
//-->
</script>
</head>

<body>


<form action="datosdiana.php" method="post" name="formulario2">
  <div align="center">
    <h1><strong>  <img src="atomo.png" width="30" height="25">&nbsp;&nbsp;&nbsp;FyQ DIANA. DISE&Ntilde;O DE BATER&Iacute;A DE PREGUNTAS&nbsp;&nbsp; <img src="atomo.png" width="30" height="25"></strong></h1>
    <table width="100%"  border="1">
      <tr>
        <td colspan="2"><div align="center">
          <h2>DATOS GENERALES </h2>
        </div></td>
      </tr>
      <tr>
        <td colspan="2"><div align="center"><strong>Nombre y apellido del autor de la bater&iacute;a de preguntas:
              <input name="nombautor" type="text" id="nombautor" value="Nombre y apellido del autor" size="52" maxlength="50">
        </strong></div></td>
      </tr>
      <tr>
        <td colspan="2"><div align="center"><strong>T&iacute;tulo de la bater&iacute;a de preguntas:
              <input name="nombbateria" type="text" id="nombbateria" value="T&iacute;tulo" size="52" maxlength="50">
        </strong></div></td>
      </tr>
      <tr>
        <td colspan="2"><div align="center"><strong>
          <input name="Submit" type="button" onClick="MM_openBrWindow('instru.html','','scrollbars=yes,width=500,height=500')" value="INSTRUCCIONES">
        </strong></div></td>
      </tr>
      <tr align="center">
        <td colspan="2"><div align="center" class="Estilo1">          PREGUNTA 1 
            <input name="num1" type="hidden" id="num1" value="PREGUNTA 1">
        (Escribir en min&uacute;sculas) </div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            Enunciado de la pregunta: 
                
            
            
        </strong></div></td>
        <td><div align="center"><strong>Respuestas: </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregA1" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
        <td rowspan="3"><div align="center"><strong>
          <input name="A1" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregB1" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregC1" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
      </tr>
      <tr>
        <td colspan="2"><div align="center" class="Estilo1">            PREGUNTA 2 
            <input name="num2" type="hidden" id="num2" value="PREGUNTA 2">
        (Escribir en min&uacute;sculas) </div></td>
      </tr>
      <tr>
        <td><div align="center"><strong> Enunciado de la pregunta: </strong></div></td>
        <td><div align="center"><strong>Respuestas: </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregA2" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
        <td rowspan="3"><div align="center"><strong>
          <input name="A2" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregB2" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregC2" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
      </tr>
      <tr>
        <td colspan="2"><div align="center" class="Estilo1"> PREGUNTA 3
            <input name="num3" type="hidden" id="num3" value="PREGUNTA 3">
      (Escribir en min&uacute;sculas) </div></td>
      </tr>
      <tr>
        <td><div align="center"><strong> Enunciado de la pregunta: </strong></div></td>
        <td><div align="center"><strong>Respuestas: </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregA3" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
        <td rowspan="3"><div align="center"><strong>
          <input name="A3" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregB3" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregC3" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
      </tr>
      <tr>
        <td colspan="2"><div align="center" class="Estilo1"> PREGUNTA 4 
            <input name="num4" type="hidden" id="num4" value="PREGUNTA 4">
      (Escribir en min&uacute;sculas) </div></td>
      </tr>
      <tr>
        <td><div align="center"><strong> Enunciado de la pregunta: </strong></div></td>
        <td><div align="center"><strong>Respuestas: </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregA4" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
        <td rowspan="3"><div align="center"><strong>
          <input name="A4" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregB4" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregC4" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
      </tr>
      <tr>
        <td colspan="2"><div align="center" class="Estilo1"> PREGUNTA 5 
            <input name="num5" type="hidden" id="num5" value="PREGUNTA 5">
      (Escribir en min&uacute;sculas) </div></td>
      </tr>
      <tr>
        <td><div align="center"><strong> Enunciado de la pregunta: </strong></div></td>
        <td><div align="center"><strong>Respuestas: </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregA5" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
        <td rowspan="3"><div align="center"><strong>
          <input name="A5" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregB5" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregC5" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
      </tr>
      <tr>
        <td colspan="2">&nbsp;</td>
      </tr>
      <tr>
        <td><div align="center">
            <input name="Enviar" type="submit" id="Enviar" value="Enviar">
        </div></td>
        <td><div align="center">
            <input name="borrar" type="reset" id="borrar" value="Borrar">
        </div></td>
      </tr>
    </table>
    <p><strong>Nota: Repasar bien los datos antes de enviar el formulario, ya que se si vuelve a la p&aacute;gina los datos introducidos se perder&aacute;n </strong></p>
    <p><strong>Para que este formulario genere el fichero de texto, debe estar siendo utilizado bajo un servidor que utilice php. </strong></p>
    <p><br>
    </p>
  </div>
</form>
</body>
</html>

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
<title>FyQ DEL TIRÓN. DISE&Ntilde;O DE BATER&Iacute;A DE PREGUNTAS</title>
<style type="text/css">
<!--
body {
	background-color: #CCFFFF;
}
-->
</style>
</head>

<body>


<form action="datosdeltiron.php" method="post" name="formulario2">
  <div align="center">
    <h1><strong>  <img src="atomo.png" width="30" height="25">&nbsp;&nbsp;&nbsp;FyQ DEL TIRÓN. DISE&Ntilde;O DE BATER&Iacute;A DE PREGUNTAS&nbsp;&nbsp; <img src="atomo.png" width="30" height="25"></strong></h1>
    <table width="100%"  border="1">
      <tr>
        <td width="40%" colspan="3"><div align="center">
            <h2>DATOS GENERALES </h2>
        </div></td>
      </tr>
      <tr>
        <td colspan="3"><div align="center"><strong>Nombre y apellido del autor de la bater&iacute;a de preguntas:
                  <input name="nombautor" type="text" id="nombautor" value="Nombre y apellido del autor" size="52" maxlength="50">
        </strong></div></td>
      </tr>
      <tr>
        <td height="28" colspan="3"><div align="center"><strong>T&iacute;tulo de la bater&iacute;a de preguntas:
              <input name="nombbateria" type="text" id="nombbateria" value="T&iacute;tulo" size="52" maxlength="50">
        </strong></div></td>
      </tr>
      <tr>
        <td height="28" colspan="3">&nbsp;</td>
      </tr>
      <tr>
        <td height="28"><div align="center"><strong>N&uacute;mero </strong></div></td>
        <td height="28"><div align="center"><strong>Pregunta</strong></div></td>
        <td height="28"><div align="center"><strong>Respuesta</strong></div></td>
      </tr>
      <tr>
        <td height="28"><div align="center"><strong>1</strong></div></td>
        <td><div align="center"><strong>
            <input name="P1" type="text" id="P1" size="90" maxlength="80">
        </strong></div></td>
        <td><div align="center"><strong>
            <input name="R1" type="text" id="R1" size="26" maxlength="22">
        </strong></div></td>
      </tr>
      <tr>
        <td height="28"><div align="center"><strong>2</strong></div></td>
        <td><div align="center"><strong>
            <input name="P2" type="text" id="P2" size="90" maxlength="80">
        </strong></div></td>
        <td><div align="center"><strong>
            <input name="R2" type="text" id="R2" size="26" maxlength="22">
        </strong></div></td>
      </tr>
      <tr>
        <td height="28"><div align="center"><strong>3</strong></div></td>
        <td><div align="center"><strong>
            <input name="P3" type="text" id="P3" size="90" maxlength="80">
        </strong></div></td>
        <td><div align="center"><strong>
            <input name="R3" type="text" id="R3" size="26" maxlength="22">
        </strong></div></td>
      </tr>
      <tr>
        <td height="28"><div align="center"><strong>4 </strong></div></td>
        <td><div align="center"><strong>
            <input name="P4" type="text" id="P4" size="90" maxlength="80">
        </strong></div></td>
        <td><div align="center"><strong>
            <input name="R4" type="text" id="R4" size="26" maxlength="22">
        </strong></div></td>
      </tr>
      <tr>
        <td height="28"><div align="center"><strong>5</strong></div></td>
        <td><div align="center"><strong>
            <input name="P5" type="text" id="P5" size="90" maxlength="80">
        </strong></div></td>
        <td><div align="center"><strong>
            <input name="R5" type="text" id="R5" size="26" maxlength="22">
        </strong></div></td>
      </tr>
      <tr>
        <td height="28"><div align="center"><strong>6 </strong></div></td>
        <td><div align="center"><strong>
            <input name="P6" type="text" id="P6" size="90" maxlength="80">
        </strong></div></td>
        <td><div align="center"><strong>
            <input name="R6" type="text" id="R6" size="26" maxlength="22">
        </strong></div></td>
      </tr>
      <tr>
        <td height="28"><div align="center"><strong>7 </strong></div></td>
        <td><div align="center"><strong>
            <input name="P7" type="text" id="P7" size="90" maxlength="80">
        </strong></div></td>
        <td><div align="center"><strong>
            <input name="R7" type="text" id="R7" size="26" maxlength="22">
        </strong></div></td>
      </tr>
      <tr>
        <td height="28"><div align="center"><strong>8 </strong></div></td>
        <td><div align="center"><strong>
            <input name="P8" type="text" id="P8" size="90" maxlength="80">
        </strong></div></td>
        <td><div align="center"><strong>
            <input name="R8" type="text" id="R8" size="26" maxlength="22">
        </strong></div></td>
      </tr>
      <tr>
        <td height="28"><div align="center"><strong>9 </strong></div></td>
        <td><div align="center"><strong>
            <input name="P9" type="text" id="P9" size="90" maxlength="80">
        </strong></div></td>
        <td><div align="center"><strong>
            <input name="R9" type="text" id="R9" size="26" maxlength="22">
        </strong></div></td>
      </tr>
      <tr>
        <td height="28"><div align="center"><strong>10 </strong></div></td>
        <td><div align="center"><strong>
            <input name="P10" type="text" id="P10" size="90" maxlength="80">
        </strong></div></td>
        <td><div align="center"><strong>
            <input name="R10" type="text" id="R10" size="26" maxlength="22">
        </strong></div></td>
      </tr>
      <tr>
        <td height="28" colspan="3"><div align="center">
            <h2><strong>
            </strong> </h2>
        </div></td>
      </tr>
      <tr>
        <td colspan="3"><div align="center">
  <input name="Enviar" type="submit" id="Enviar" value="Enviar">
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <input name="borrar" type="reset" id="borrar" value="Borrar">
        </div></td>
      </tr>
    </table>
    <p><strong>Nota: Repasar bien los datos antes de enviar el formulario, ya que se si vuelve a la p&aacute;gina los datos introducidos se perder&aacute;n.</strong></p>
    <p><strong>Para que este formulario genere el fichero de texto, debe estar siendo utilizado bajo un servidor que utilice php. </strong><br>
    </p>
  </div>
</form>
</body>
</html>

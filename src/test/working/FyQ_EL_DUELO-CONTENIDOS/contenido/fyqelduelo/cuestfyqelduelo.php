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
<title>FyQ EL DUELO. DISE&Ntilde;O DE BATER&Iacute;A DE PREGUNTAS</title>
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
</head>

<body>


<form action="datoselduelo.php" method="post" name="formulario2">
  <div align="center">
    <h1><strong>  <img src="atomo.png" width="30" height="25">&nbsp;&nbsp;&nbsp;FyQ EL DUELO. DISE&Ntilde;O.&nbsp; <img src="atomo.png" width="30" height="25"></strong></h1>
    <table width="100%"  border="1">
      <tr>
        <td colspan="2"><div align="center">
          <h2>DATOS GENERALES </h2>
        </div></td>
      </tr>
      <tr>
        <td colspan="2"><div align="center"><strong>Nombre y extensi&oacute;n del fichero (la extensi&oacute;n debe ser .txt y en min&uacute;sculas):
              <input name="nomfic" type="text" id="nomfic" value="nombre.txt" size="62" maxlength="60">
        </strong></div></td>
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
        <td height="46" colspan="2"><div align="center">
          <input name="Submit" type="button" onClick="MM_openBrWindow('instru.html','','scrollbars=yes,width=500,height=500')" value="INSTRUCCIONES">
        </div></td>
      </tr>
      <tr>
        <td colspan="2"><div align="center"><strong>
          PRIMER TEMA:
                <input name="pregT1" type="text" id="nombbateria" size="40" maxlength="30">
        </strong></div></td>
      </tr>
      <tr>
        <td width="16%" rowspan="2"><div align="center"> <strong>
            PREGUNTA 
                  <input name="num1T1" type="hidden" id="num1T1" value="TEMA 1.">
        </strong></div>          <div align="center"></div></td>
        <td width="84%"><div align="center"><strong>
            <input name="pregT1A1" type="text" id="nombbateria" size="40" maxlength="36">
            <input name="pregT1A2" type="text" id="nombbateria" size="40" maxlength="36">
            <input name="pregT1A3" type="text" id="nombbateria" size="40" maxlength="36">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregT1A4" type="text" id="nombbateria" size="40" maxlength="36">
            <input name="pregT1A5" type="text" id="nombbateria" size="40" maxlength="36">
            <input name="pregT1A6" type="text" id="nombbateria" size="40" maxlength="36">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>OPCIONES RESPUESTAS </strong></div></td>
        <td><div align="center"><strong>
          1.
          <input name="resT1A1" type="text" id="nombbateria" size="20" maxlength="13"> 
&nbsp;&nbsp;&nbsp;&nbsp;2.           
<input name="resT1A2" type="text" id="nombbateria" size="20" maxlength="13">
&nbsp;&nbsp;&nbsp;&nbsp;3.
<input name="resT1A3" type="text" id="nombbateria" size="20" maxlength="13"> 
&nbsp;&nbsp;&nbsp;RESP. CORRECTA 
<select name="RESPT1" id="RESPT1">
  <option selected> </option>
  <option>1</option>
  <option>2</option>
  <option>3</option>
</select>
</strong></div></td>
      </tr>
      <tr>
        <td colspan="2">&nbsp;</td>
      </tr>
      <tr>
        <td colspan="2"><div align="center"><strong> SEGUNDO TEMA:
                  <input name="pregT2" type="text" id="nombbateria" size="40" maxlength="30">
        </strong></div></td>
      </tr>
      <tr>
        <td rowspan="2"><div align="center"> <strong> PREGUNTA 
          <input name="num1T2" type="hidden" id="num1T2" value="TEMA 2.">
        </strong></div>          <div align="center"></div></td>
        <td><div align="center"><strong>
            <input name="pregT2A1" type="text" id="nombbateria" size="40" maxlength="36">
            <input name="pregT2A2" type="text" id="nombbateria" size="40" maxlength="36">
            <input name="pregT2A3" type="text" id="nombbateria" size="40" maxlength="36">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregT2A4" type="text" id="nombbateria" size="40" maxlength="36">
            <input name="pregT2A5" type="text" id="nombbateria" size="40" maxlength="36">
            <input name="pregT2A6" type="text" id="nombbateria" size="40" maxlength="36">
</strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>OPCIONES RESPUESTAS </strong></div></td>
        <td><div align="center"><strong> 1.
                  <input name="resT2A1" type="text" id="nombbateria" size="20" maxlength="13">
&nbsp;&nbsp;&nbsp;&nbsp;2.
    <input name="resT2A2" type="text" id="nombbateria" size="20" maxlength="13">
&nbsp;&nbsp;&nbsp;&nbsp;3.
    <input name="resT2A3" type="text" id="nombbateria" size="20" maxlength="13">
&nbsp;&nbsp;&nbsp;RESP. CORRECTA
    <select name="RESPT2" id="RESPT2">
      <option selected> </option>
      <option>1</option>
      <option>2</option>
      <option>3</option>
    </select>
        </strong></div></td>
      </tr>
      <tr>
        <td colspan="2">&nbsp;</td>
      </tr>
      <tr>
        <td colspan="2"><div align="center"><strong> TERCER TEMA:
                  <input name="pregT3" type="text" id="nombbateria" size="40" maxlength="30">
        </strong></div></td>
      </tr>
      <tr>
        <td rowspan="2"><div align="center"> <strong> PREGUNTA 
          <input name="num1T3" type="hidden" id="num1T3" value="TEMA 3. PREGUNTA 1"> 
          </strong></div>          <div align="center"></div></td>
        <td><div align="center"><strong>
            <input name="pregT3A1" type="text" id="nombbateria" size="40" maxlength="36">
            <input name="pregT3A2" type="text" id="nombbateria" size="40" maxlength="36">
            <input name="pregT3A3" type="text" id="nombbateria" size="40" maxlength="36">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregT3A4" type="text" id="nombbateria" size="40" maxlength="36">
            <input name="pregT3A5" type="text" id="nombbateria" size="40" maxlength="36">
            <input name="pregT3A6" type="text" id="nombbateria" size="40" maxlength="36">
</strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>OPCIONES RESPUESTAS </strong></div></td>
        <td><div align="center"><strong> 1.
                  <input name="resT3A1" type="text" id="nombbateria" size="20" maxlength="13">
&nbsp;&nbsp;&nbsp;&nbsp;2.
    <input name="resT3A2" type="text" id="nombbateria" size="20" maxlength="13">
&nbsp;&nbsp;&nbsp;&nbsp;3.
    <input name="resT3A3" type="text" id="nombbateria" size="20" maxlength="13">
&nbsp;&nbsp;&nbsp;RESP. CORRECTA
    <select name="RESPT3" id="RESPT3">
      <option selected> </option>
      <option>1</option>
      <option>2</option>
      <option>3</option>
    </select>
        </strong></div></td>
      </tr>
      <tr>
        <td colspan="2">&nbsp;</td>
      </tr>
      <tr>
        <td colspan="2"><div align="center"><strong> CUARTO TEMA:
                  <input name="pregT4" type="text" id="nombbateria" size="40" maxlength="30">
        </strong></div></td>
      </tr>
      <tr>
        <td rowspan="2"><div align="center"> <strong> PREGUNTA
          <input name="num1T4" type="hidden" id="num1T4" value="TEMA 4. PREGUNTA 1">
        </strong></div>          <div align="center"></div></td>
        <td><div align="center"><strong>
            <input name="pregT4A1" type="text" id="nombbateria" size="40" maxlength="36">
            <input name="pregT4A2" type="text" id="nombbateria" size="40" maxlength="36">
            <input name="pregT4A3" type="text" id="nombbateria" size="40" maxlength="36">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregT4A4" type="text" id="nombbateria" size="40" maxlength="36">
            <input name="pregT4A5" type="text" id="nombbateria" size="40" maxlength="36">
            <input name="pregT4A6" type="text" id="nombbateria" size="40" maxlength="36">
</strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>OPCIONES RESPUESTAS </strong></div></td>
        <td><div align="center"><strong> 1.
                  <input name="resT4A1" type="text" id="nombbateria" size="20" maxlength="13">
&nbsp;&nbsp;&nbsp;&nbsp;2.
    <input name="resT4A2" type="text" id="nombbateria" size="20" maxlength="13">
&nbsp;&nbsp;&nbsp;&nbsp;3.
    <input name="resT4A3" type="text" id="nombbateria" size="20" maxlength="13">
&nbsp;&nbsp;&nbsp;RESP. CORRECTA
    <select name="RESPT4" id="RESPT4">
      <option selected> </option>
      <option>1</option>
      <option>2</option>
      <option>3</option>
    </select>
        </strong></div></td>
      </tr>
      <tr>
        <td colspan="2"><div align="center"></div>
            <div align="center"></div></td>
      </tr>
      <tr>
        <td colspan="2"><div align="center">
</div>          <div align="center">
            <input name="Enviar" type="submit" id="Enviar" value="Enviar">
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input name="borrar" type="reset" id="borrar" value="Borrar">
          </div></td>
      </tr>
    </table>
    <p><strong>Nota: Repasar bien los datos antes de enviar el formulario, ya que se si vuelve a la p&aacute;gina los datos introducidos se perder&aacute;n </strong></p>
    <p><strong>Para que este formulario genere el fichero de texto, debe estar siendo utilizado bajo un servidor que utilice php. </strong><br>
    </p>
  </div>
</form>
</body>
</html>

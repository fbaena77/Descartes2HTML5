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
<title>FyQ EL PARTIDO. DISE&Ntilde;O DE BATER&Iacute;A DE PREGUNTAS</title>
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


<form action="datoselpartido.php" method="post" name="formulario2">
  <div align="center">
    <h1><strong>  <img src="atomo.png" width="30" height="25">&nbsp;&nbsp;&nbsp;FyQ EL PARTIDO. DISE&Ntilde;O DE BATER&Iacute;A DE PREGUNTAS&nbsp;&nbsp; <img src="atomo.png" width="30" height="25"></strong></h1>
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
        <td><div align="center"><strong>Opci&oacute;n A:
              <input name="opcA1" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregB1" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n B:
              <input name="opcB1" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregC1" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n C:
              <input name="opcC1" type="text" id="nombbateria" size="70" maxlength="65">
</strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>Opci&oacute;n correcta:
              <select name="opcorrect1" size="1" id="opcorrect1">
                <option selected>A</option>
                <option>B</option>
                <option>C</option>
                <option>D</option>
              </select>
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n D:
                  <input name="opcD1" type="text" id="nombbateria" size="70" maxlength="65">
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
        <td><div align="center"><strong>Opci&oacute;n A:
              <input name="opcA2" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregB2" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n B:
              <input name="opcB2" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregC2" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n C:
              <input name="opcC2" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>Opci&oacute;n correcta:
              <select name="opcorrect2" size="1" id="opcorrect2">
                <option selected>A</option>
                <option>B</option>
                <option>C</option>
                <option>D</option>
              </select>
</strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n D:
                <input name="opcD2" type="text" id="nombbateria" size="70" maxlength="65">
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
        <td><div align="center"><strong>Opci&oacute;n A:
                  <input name="opcA3" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregB3" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n B:
                  <input name="opcB3" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregC3" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n C:
                  <input name="opcC3" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>Opci&oacute;n correcta:
                  <select name="opcorrect3" size="1" id="opcorrect3">
                    <option selected>A</option>
                    <option>B</option>
                    <option>C</option>
                    <option>D</option>
                  </select>
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n D:
                  <input name="opcD3" type="text" id="nombbateria" size="70" maxlength="65">
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
        <td><div align="center"><strong>Opci&oacute;n A:
                  <input name="opcA4" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregB4" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n B:
                  <input name="opcB4" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregC4" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n C:
                  <input name="opcC4" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>Opci&oacute;n correcta:
                  <select name="opcorrect4" size="1" id="opcorrect4">
                    <option selected>A</option>
                    <option>B</option>
                    <option>C</option>
                    <option>D</option>
                  </select>
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n D:
                  <input name="opcD4" type="text" id="nombbateria" size="70" maxlength="65">
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
        <td><div align="center"><strong>Opci&oacute;n A:
                  <input name="opcA5" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregB5" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n B:
                  <input name="opcB5" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregC5" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n C:
                  <input name="opcC5" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>Opci&oacute;n correcta:
                  <select name="opcorrect5" size="1" id="opcorrect5">
                    <option selected>A</option>
                    <option>B</option>
                    <option>C</option>
                    <option>D</option>
                  </select>
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n D:
                  <input name="opcD5" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td colspan="2"><div align="center" class="Estilo1"> PREGUNTA 6 
            <input name="num6" type="hidden" id="num6" value="PREGUNTA 6">
      (Escribir en min&uacute;sculas) </div></td>
      </tr>
      <tr>
        <td><div align="center"><strong> Enunciado de la pregunta: </strong></div></td>
        <td><div align="center"><strong>Respuestas: </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregA6" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n A:
                  <input name="opcA6" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregB6" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n B:
                  <input name="opcB6" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregC6" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n C:
                  <input name="opcC6" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>Opci&oacute;n correcta:
                  <select name="opcorrect6" size="1" id="opcorrect6">
                    <option selected>A</option>
                    <option>B</option>
                    <option>C</option>
                    <option>D</option>
                  </select>
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n D:
                  <input name="opcD6" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td colspan="2"><div align="center" class="Estilo1"> PREGUNTA 7 
            <input name="num7" type="hidden" id="num7" value="PREGUNTA 7">
      (Escribir en min&uacute;sculas) </div></td>
      </tr>
      <tr>
        <td><div align="center"><strong> Enunciado de la pregunta: </strong></div></td>
        <td><div align="center"><strong>Respuestas: </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregA7" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n A:
                  <input name="opcA7" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregB7" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n B:
                  <input name="opcB7" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregC7" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n C:
                  <input name="opcC7" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>Opci&oacute;n correcta:
                  <select name="opcorrect7" size="1" id="opcorrect7">
                    <option selected>A</option>
                    <option>B</option>
                    <option>C</option>
                    <option>D</option>
                  </select>
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n D:
                  <input name="opcD7" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td colspan="2"><div align="center" class="Estilo1"> PREGUNTA 8 
            <input name="num8" type="hidden" id="num8" value="PREGUNTA 8">
      (Escribir en min&uacute;sculas) </div></td>
      </tr>
      <tr>
        <td><div align="center"><strong> Enunciado de la pregunta: </strong></div></td>
        <td><div align="center"><strong>Respuestas: </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregA8" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n A:
                  <input name="opcA8" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregB8" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n B:
                  <input name="opcB8" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregC8" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n C:
                  <input name="opcC8" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>Opci&oacute;n correcta:
                  <select name="opcorrect8" size="1" id="opcorrect8">
                    <option selected>A</option>
                    <option>B</option>
                    <option>C</option>
                    <option>D</option>
                  </select>
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n D:
                  <input name="opcD8" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td colspan="2"><div align="center" class="Estilo1"> PREGUNTA 9 
            <input name="num9" type="hidden" id="num9" value="PREGUNTA 9">
      (Escribir en min&uacute;sculas) </div></td>
      </tr>
      <tr>
        <td><div align="center"><strong> Enunciado de la pregunta: </strong></div></td>
        <td><div align="center"><strong>Respuestas: </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregA9" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n A:
                  <input name="opcA9" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregB9" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n B:
                  <input name="opcB9" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregC9" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n C:
                  <input name="opcC9" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>Opci&oacute;n correcta:
                  <select name="opcorrect9" size="1" id="opcorrect9">
                    <option selected>A</option>
                    <option>B</option>
                    <option>C</option>
                    <option>D</option>
                  </select>
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n D:
                  <input name="opcD9" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td colspan="2"><div align="center" class="Estilo1"> PREGUNTA 10 
            <input name="num10" type="hidden" id="num10" value="PREGUNTA 10">
      (Escribir en min&uacute;sculas) </div></td>
      </tr>
      <tr>
        <td><div align="center"><strong> Enunciado de la pregunta: </strong></div></td>
        <td><div align="center"><strong>Respuestas: </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregA10" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n A:
                  <input name="opcA10" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregB10" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n B:
                  <input name="opcB10" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregC10" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n C:
                  <input name="opcC10" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>Opci&oacute;n correcta:
                  <select name="opcorrect10" size="1" id="opcorrect10">
                    <option selected>A</option>
                    <option>B</option>
                    <option>C</option>
                    <option>D</option>
                  </select>
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n D:
                  <input name="opcD10" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td colspan="2"><div align="center" class="Estilo1"> PREGUNTA 11 
            <input name="num11" type="hidden" id="num11" value="PREGUNTA 11">
      (Escribir en min&uacute;sculas) </div></td>
      </tr>
      <tr>
        <td><div align="center"><strong> Enunciado de la pregunta: </strong></div></td>
        <td><div align="center"><strong>Respuestas: </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregA11" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n A:
                  <input name="opcA11" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregB11" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n B:
                  <input name="opcB11" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregC11" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n C:
                  <input name="opcC11" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>Opci&oacute;n correcta:
                  <select name="opcorrect11" size="1" id="opcorrect11">
                    <option selected>A</option>
                    <option>B</option>
                    <option>C</option>
                    <option>D</option>
                  </select>
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n D:
                  <input name="opcD11" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td colspan="2"><div align="center" class="Estilo1"> PREGUNTA 12
                <input name="num12" type="hidden" id="num12" value="PREGUNTA 12">
      (Escribir en min&uacute;sculas) </div></td>
      </tr>
      <tr>
        <td><div align="center"><strong> Enunciado de la pregunta: </strong></div></td>
        <td><div align="center"><strong>Respuestas: </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregA12" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n A:
                  <input name="opcA12" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregB12" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n B:
                  <input name="opcB12" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregC12" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n C:
                  <input name="opcC12" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>Opci&oacute;n correcta:
                  <select name="opcorrect12" size="1" id="opcorrect12">
                    <option selected>A</option>
                    <option>B</option>
                    <option>C</option>
                    <option>D</option>
                  </select>
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n D:
                  <input name="opcD12" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td colspan="2"><div align="center" class="Estilo1"> PREGUNTA 13 
            <input name="num13" type="hidden" id="num13" value="PREGUNTA 13">
      (Escribir en min&uacute;sculas) </div></td>
      </tr>
      <tr>
        <td><div align="center"><strong> Enunciado de la pregunta: </strong></div></td>
        <td><div align="center"><strong>Respuestas: </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregA13" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n A:
                  <input name="opcA13" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregB13" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n B:
                  <input name="opcB13" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregC13" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n C:
                  <input name="opcC13" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>Opci&oacute;n correcta:
                  <select name="opcorrect13" size="1" id="opcorrect13">
                    <option selected>A</option>
                    <option>B</option>
                    <option>C</option>
                    <option>D</option>
                  </select>
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n D:
                  <input name="opcD13" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td colspan="2"><div align="center" class="Estilo1"> PREGUNTA 14 
            <input name="num14" type="hidden" id="num14" value="PREGUNTA 14">
      (Escribir en min&uacute;sculas) </div></td>
      </tr>
      <tr>
        <td><div align="center"><strong> Enunciado de la pregunta: </strong></div></td>
        <td><div align="center"><strong>Respuestas: </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregA14" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n A:
                  <input name="opcA14" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregB14" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n B:
                  <input name="opcB14" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregC14" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n C:
                  <input name="opcC14" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>Opci&oacute;n correcta:
                  <select name="opcorrect14" size="1" id="opcorrect14">
                    <option selected>A</option>
                    <option>B</option>
                    <option>C</option>
                    <option>D</option>
                  </select>
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n D:
                  <input name="opcD14" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td colspan="2"><div align="center" class="Estilo1"> PREGUNTA 15 
            <input name="num15" type="hidden" id="num15" value="PREGUNTA 15">
      (Escribir en min&uacute;sculas) </div></td>
      </tr>
      <tr>
        <td><div align="center"><strong> Enunciado de la pregunta: </strong></div></td>
        <td><div align="center"><strong>Respuestas: </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregA15" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n A:
                  <input name="opcA15" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregB15" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n B:
                  <input name="opcB15" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregC15" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n C:
                  <input name="opcC15" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>Opci&oacute;n correcta:
                  <select name="opcorrect15" size="1" id="opcorrect15">
                    <option selected>A</option>
                    <option>B</option>
                    <option>C</option>
                    <option>D</option>
                  </select>
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n D:
                  <input name="opcD15" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td colspan="2"><div align="center" class="Estilo1"> PREGUNTA 16 
            <input name="num16" type="hidden" id="num16" value="PREGUNTA 16">
      (Escribir en min&uacute;sculas) </div></td>
      </tr>
      <tr>
        <td><div align="center"><strong> Enunciado de la pregunta: </strong></div></td>
        <td><div align="center"><strong>Respuestas: </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregA16" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n A:
                  <input name="opcA16" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregB16" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n B:
                  <input name="opcB16" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregC16" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n C:
                  <input name="opcC16" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>Opci&oacute;n correcta:
                  <select name="opcorrect16" size="1" id="opcorrect16">
                    <option selected>A</option>
                    <option>B</option>
                    <option>C</option>
                    <option>D</option>
                  </select>
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n D:
                  <input name="opcD16" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td colspan="2"><div align="center" class="Estilo1"> PREGUNTA 17 
            <input name="num17" type="hidden" id="num17" value="PREGUNTA 17">
      (Escribir en min&uacute;sculas) </div></td>
      </tr>
      <tr>
        <td><div align="center"><strong> Enunciado de la pregunta: </strong></div></td>
        <td><div align="center"><strong>Respuestas: </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregA17" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n A:
                  <input name="opcA17" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregB17" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n B:
                  <input name="opcB17" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregC17" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n C:
                  <input name="opcC17" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>Opci&oacute;n correcta:
                  <select name="opcorrect17" size="1" id="opcorrect17">
                    <option selected>A</option>
                    <option>B</option>
                    <option>C</option>
                    <option>D</option>
                  </select>
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n D:
                  <input name="opcD17" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td colspan="2"><div align="center" class="Estilo1"> PREGUNTA 18 
            <input name="num18" type="hidden" id="num18" value="PREGUNTA 18">
      (Escribir en min&uacute;sculas) </div></td>
      </tr>
      <tr>
        <td><div align="center"><strong> Enunciado de la pregunta: </strong></div></td>
        <td><div align="center"><strong>Respuestas: </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregA18" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n A:
                  <input name="opcA18" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregB18" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n B:
                  <input name="opcB18" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregC18" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n C:
                  <input name="opcC18" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>Opci&oacute;n correcta:
                  <select name="opcorrect18" size="1" id="opcorrect18">
                    <option selected>A</option>
                    <option>B</option>
                    <option>C</option>
                    <option>D</option>
                  </select>
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n D:
                  <input name="opcD18" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td colspan="2"><div align="center" class="Estilo1"> PREGUNTA 19 
            <input name="num19" type="hidden" id="num19" value="PREGUNTA 19">
      (Escribir en min&uacute;sculas) </div></td>
      </tr>
      <tr>
        <td><div align="center"><strong> Enunciado de la pregunta: </strong></div></td>
        <td><div align="center"><strong>Respuestas: </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregA19" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n A:
                  <input name="opcA19" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregB19" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n B:
                  <input name="opcB19" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregC19" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n C:
                  <input name="opcC19" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>Opci&oacute;n correcta:
                  <select name="opcorrect19" size="1" id="opcorrect19">
                    <option selected>A</option>
                    <option>B</option>
                    <option>C</option>
                    <option>D</option>
                  </select>
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n D:
                  <input name="opcD19" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td colspan="2"><div align="center" class="Estilo1"> PREGUNTA 20
                <input name="num20" type="hidden" id="num20" value="PREGUNTA 20">
      (Escribir en min&uacute;sculas) </div></td>
      </tr>
      <tr>
        <td><div align="center"><strong> Enunciado de la pregunta: </strong></div></td>
        <td><div align="center"><strong>Respuestas: </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregA20" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n A:
                  <input name="opcA20" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregB20" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n B:
                  <input name="opcB20" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>
            <input name="pregC20" type="text" id="nombbateria" size="110" maxlength="100">
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n C:
                  <input name="opcC20" type="text" id="nombbateria" size="70" maxlength="65">
        </strong></div></td>
      </tr>
      <tr>
        <td><div align="center"><strong>Opci&oacute;n correcta:
                  <select name="opcorrect20" size="1" id="opcorrect20">
                    <option selected>A</option>
                    <option>B</option>
                    <option>C</option>
                    <option>D</option>
                  </select>
        </strong></div></td>
        <td><div align="center"><strong>Opci&oacute;n D:
                  <input name="opcD20" type="text" id="nombbateria" size="70" maxlength="65">
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

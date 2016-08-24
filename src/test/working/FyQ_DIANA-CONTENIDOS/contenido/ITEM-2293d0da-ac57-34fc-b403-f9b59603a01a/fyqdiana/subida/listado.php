<style type="text/css">
<!--
body {
	background-color: #CCFFFF;
}
-->
</style><?
$directorio = dir("./ficheros");
# en el caso de los objetos la manera de invocar
# uno de sus métodos y/o funciones requiere una sintaxis
# especifica con la que vera a lo largo de este ejemplo:
# es $objeto->metodo que equivale a la tradicional llamada
# a una variable en la forma $variable.
      echo "<H3> FICHEROS PUBLICADOS</H3>";
      while($fichero=$directorio->read()) {
             echo $fichero."<br>\n";
      }
      $directorio->rewind();
?>

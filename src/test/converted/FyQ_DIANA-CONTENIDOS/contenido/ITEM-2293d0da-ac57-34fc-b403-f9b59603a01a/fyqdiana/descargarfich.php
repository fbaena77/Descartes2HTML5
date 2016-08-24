<?
session_name();
session_start();
$FICHERO=$_SESSION['F1'];
	header("Content-Description: File Transfer"); 
    header("Content-Type: application/force-download"); 
       header("Pragma: public");
       header("Expires: 0");
      header("Cache-Control: must-revalidate, post-check=0, pre-check=0");
        header("Cache-Control: private",false);
      header("Content-Disposition: attachment; filename=$FICHERO");
      header("Content-Transfer-Encoding: binary");
readfile($FICHERO);

#quitando las instrucciones siguientes, el fichero se guarda también en el servidor además de descargarse
#if(file_exists($FICHERO))
#{
#unlink($FICHERO);
#}
?>
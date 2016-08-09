package com.emergya.descartes.validator;

import java.util.List;

import com.emergya.descartes.content.DescartesContentProxy;

/**
 * The Interface IJsoupHtml5Service.
 */
public interface IJsoupHtml5Service {

    /**
     * Analizar.
     * Dado un directorio como parámetro analiza recursivamente todos los ficheros html, css y js
     * para convertirlos a HTML5. El fichero HTML5 se guarda en el mismo directorio que el original
     * y del original se crea un backup.
     * 
     * Devolviendo un listado de contenidos analizados, con los errores subsanados y 
     * el resultado de su validación por la W3C.
     *
     * @param directorio the directorio
     * @return the list
     */
    List<DescartesContentProxy> analizar(String directorio);

    /**
     * Validar.
     * Dado un directorio como parámetro analiza recursivamente todos los ficheros html
     * y devolver un listado con el resultado su validación por la W3C.
     *
     * @param directorio the directorio
     * @return the list
     */
    List<DescartesContentProxy> validar(String directorio);
}

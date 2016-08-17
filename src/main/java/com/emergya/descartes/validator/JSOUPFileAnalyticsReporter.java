package com.emergya.descartes.validator;

import java.util.List;

import com.emergya.descartes.content.DescartesContentProxy;

/**
 * The Class JSOUPFileAnalyticsReporter.
 */
public class JSOUPFileAnalyticsReporter {

    /** The jsoup html5 service. */
    private IJsoupHtml5Service jsoupHtml5Service;

    /**
     * Establecido en la configuración el directorio 'contenido.origen', accede a él buscando recursivamente todos los ficheros html
     * para analizar las etiquetas, atributos, propiedades que no pasan la validación w3c y corregirlas pasandole posteriormente
     * la validación de w3c.
     * 
     * Los ficheros corregidos se guardan cn el mismo nombre que el original, y del original se realiza un backup.
     * 
     * La librería descartes-min.js es actualizada con la que se encuentra en el directorio 'src/main/resources'
     * 
     * El resultado de esta operación se visualiza en pantalla y en un csv generado en el mismo directorio 'contenido.origen'
     *
     * @param model the model
     * @return the string
     */
    public List<DescartesContentProxy> analizarOriginal(String path) {

        return jsoupHtml5Service.analizar(path);

    }

    /**
     * Establecido en la configuración el directorio 'contenido.html5', accede a él para ejecutar sobre todos sus ficheros html
     * la validación w3c mostrando su resulta en pantalla. No efectúa ningún cambio sobre el contenido de los ficheros..
     *
     * @param model the model
     * @return the string
     */
    public List<DescartesContentProxy> validateHtml5W3c(String path) {

        return jsoupHtml5Service.validar(path);

    }
}

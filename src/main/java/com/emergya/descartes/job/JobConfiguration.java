package com.emergya.descartes.job;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.emergya.descartes.utils.Constants;

/**
 * 
 * Clase que gestiona la configuracion de un trabajo de conversión
 * 
 * @author fbaena
 *
 */
public class JobConfiguration {

    private static Logger log = Logger.getLogger(JobConfiguration.class);

    private Map<String, String> commonProperties;

    private int sizeArrayBlockingqueue;

    /**
     * @return int
     */
    public int getSizeArrayBlockingqueue() {
        return sizeArrayBlockingqueue;
    }

    private void setSizeArrayBlockingqueue(int sizeArrayBlockingqueue) {
        this.sizeArrayBlockingqueue = sizeArrayBlockingqueue;
    }

    /**
     * @param commonPropertiesFile
     * @throws IllegalArgumentException Cuando la configuración es inválida
     */
    public JobConfiguration(String commonPropertiesFile)
            throws IllegalArgumentException {
        this.setCommonProperties(this.getProperties(commonPropertiesFile));
        this.setSizeArrayBlockingqueue(Integer.parseInt(this
                .getCommonProperties().get(Constants.SIZE_ARRAY_BLOCKINGQUEUE)));
    }

    /**
     * @param PropertiesFile
     * @return  Map<String, String>
     */
    private Map<String, String> getProperties(String PropertiesFile) {
        Properties props = new Properties();
        try {

            FileInputStream fileIS = new FileInputStream(PropertiesFile);
            props.load(fileIS);

        } catch (FileNotFoundException e) {
            log.error("Fichero no encontrado. " + e.getMessage());
        } catch (IOException e) {
            log.error("Fichero no accesible. " + e.getMessage());
        }

        @SuppressWarnings({ "unchecked", "rawtypes" })
        Map<String, String> properties = new HashMap<String, String>(
                (Map) props);
        return properties;
    }

    /**
     * @return boolean
     */
    public boolean isToAnalyze() {
        String toAnalyze = getCommonProperties().get(Constants.MUST_ANALYZE);
        return Boolean.parseBoolean(toAnalyze);
    }

    /**
     * @return boolean
     */
    public boolean isToW3CValidate() {
        String toW3CValidate = getCommonProperties().get(
                Constants.MUST_VALIDATE);
        return Boolean.parseBoolean(toW3CValidate);
    }

    /**
     * @return boolean
     */
    public boolean isToConvert2HTML5() {
        String toConvert2HTML5 = getCommonProperties().get(
                Constants.MUST_CONVERT);
        return Boolean.parseBoolean(toConvert2HTML5);
    }

    /**
     * @return boolean
     */
    public boolean isToCompressConverted() {
        String toZipConverted = getCommonProperties().get(
                Constants.COMPRESS_CONVERTED_CONTENT);
        return Boolean.parseBoolean(toZipConverted);
    }

    /**
     * @return String
     */
    public String getOriginalContentPath() {
        String originalContentPath = getCommonProperties().get(
                Constants.PATH_ORIGINAL_CONTENT);
        return originalContentPath;
    }

    /**
     * @return String
     */
    public String getConvertedContentPath() {
        String convertedContentPath = getCommonProperties().get(
                Constants.PATH_CONVERTED_CONTENT);
        return convertedContentPath;
    }

    /**
     * @return String
     */
    public String getAnalysisResultPath() {
        String analysisResultPath = getCommonProperties().get(
                Constants.PATH_ANALYSIS_RESULT);
        return analysisResultPath;
    }

    /**
     * @return String
     */
    public String getValidationResultPath() {
        String validationResultPath = getCommonProperties().get(
                Constants.PATH_W3C_VALIDATION);
        return validationResultPath;
    }

    /**
     * @return the commonProperties
     */
    public Map<String, String> getCommonProperties() {
        return commonProperties;
    }

    /**
     * @param commonProperties the commonProperties to set
     */
    public void setCommonProperties(Map<String, String> commonProperties) {
        this.commonProperties = commonProperties;
    }
}

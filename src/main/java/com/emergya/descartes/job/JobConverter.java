package com.emergya.descartes.job;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.emergya.descartes.content.DescartesContentProxy;
import com.emergya.descartes.content.DescartesZipContentProxy;

/**
 * 
 * Clase que gestiona el proceso de transformación a HTML5
 * 
 * @author fbaena
 *
 */
public class JobConverter {

    private JobConfiguration jobConfig;

    /* Flags */
    private boolean modelQueueReadyFlag = false;
    private boolean analyzeQueueReadyFlag = false;
    private boolean convertQueueReadyFlag = false;
    private boolean validateQueueReadyFlag = false;

    /* Colas de proceso */
    private BlockingQueue<DescartesZipContentProxy> contentsToModel;
    private BlockingQueue<DescartesZipContentProxy> contentsToAnalyze;
    private BlockingQueue<DescartesZipContentProxy> contentsToConvert;
    private BlockingQueue<DescartesContentProxy> contentsToValidate;
    /**
     * Poison pill para consumir los threads que usan Blocking Queues con DescartesZipContentProxy
     */
    public static final DescartesZipContentProxy STOP_QUEUE = new DescartesZipContentProxy();
    /**
     * Poison pill para consumir los threads que usan Blocking Queues con DescartesContentProxy
     */
    public static final DescartesContentProxy STOP_QUEUE_V = new DescartesContentProxy();

    /* Informacion estadistica */
    /**
     * Contenidos que se han modelado con éxito.
     */
    private Queue<DescartesZipContentProxy> contentsModeled;
    /**
     * Contenidos que se han analizado con éxito.
     */
    private Queue<DescartesZipContentProxy> contentsAnalyzed;

    /**
     * Contenidos que se han convertido con éxito.    
     */
    private Queue<DescartesZipContentProxy> contentsConverted;

    /**
     * Contenidos que se han validado con éxito
     */
    private Queue<DescartesContentProxy> contentsValidate;

    /**
     * Contenidos que no se han modelado correctamente.
     */
    private Queue<DescartesZipContentProxy> contentsModeledError;

    /**
     * Contenidos que no se han analizado correctamente.
     */
    private Queue<DescartesZipContentProxy> contentsAnalyzedError;

    /**
     * Contenidos que no se han convertido correctamente.
     */
    private Queue<DescartesZipContentProxy> contentsConvertedError;

    /**
     * Contenidos que no se han validado correctamente.
     */
    private Queue<DescartesContentProxy> contentsValidateError;

    /**
     * @param config
     */
    public JobConverter(JobConfiguration config) {

        this.setJobConfig(config);

        this.setContentsToModel(generateArrayBlockingQueueZip());
        this.setContentsToAnalyze(generateArrayBlockingQueueZip());
        this.setContentsToConvert(generateArrayBlockingQueueZip());
        this.setContentsToValidate(generateArrayBlockingQueue());

        this.setContentsModeled(generateArrayBlockingQueueZip());
        this.setContentsAnalyzed(generateArrayBlockingQueueZip());
        this.setContentsConverted(generateArrayBlockingQueueZip());
        this.setContentsValidate(generateArrayBlockingQueue());
        this.setContentsModeledError(generateArrayBlockingQueueZip());
        this.setContentsAnalyzedError(generateArrayBlockingQueueZip());
        this.setContentsConvertedError(generateArrayBlockingQueueZip());
        this.setContentsValidateError(generateArrayBlockingQueue());
    }

    /**
     * @return the jobConfig
     */
    public JobConfiguration getJobConfig() {
        return jobConfig;
    }

    /**
     * @param jobConfig the jobConfig to set
     */
    public void setJobConfig(JobConfiguration jobConfig) {
        this.jobConfig = jobConfig;
    }

    private BlockingQueue<DescartesContentProxy> generateArrayBlockingQueue() {
        return new ArrayBlockingQueue<DescartesContentProxy>(
                this.jobConfig.getSizeArrayBlockingqueue(), true);
    }

    private ArrayBlockingQueue<DescartesZipContentProxy> generateArrayBlockingQueueZip() {
        return new ArrayBlockingQueue<DescartesZipContentProxy>(
                this.jobConfig.getSizeArrayBlockingqueue(), true);
    }

    /* Gets BlockingQueue */
    /**
     * @return the analyzeQueueReadyFlag
     */
    public boolean isAnalyzeQueueReadyFlag() {
        return analyzeQueueReadyFlag;
    }

    /**
     * @param analyzeQueueReadyFlag the analyzeQueueReadyFlag to set
     */
    public void setAnalyzeQueueReadyFlag(boolean analyzeQueueReadyFlag) {
        this.analyzeQueueReadyFlag = analyzeQueueReadyFlag;
    }

    /**
     * @return the convertQueueReadyFlag
     */
    public boolean isConvertQueueReadyFlag() {
        return convertQueueReadyFlag;
    }

    /**
     * @param convertQueueReadyFlag the convertQueueReadyFlag to set
     */
    public void setConvertQueueReadyFlag(boolean convertQueueReadyFlag) {
        this.convertQueueReadyFlag = convertQueueReadyFlag;
    }

    /**
     * @return the validateQueueReadyFlag
     */
    public boolean isValidateQueueReadyFlag() {
        return validateQueueReadyFlag;
    }

    /**
     * @param validateQueueReadyFlag the validateQueueReadyFlag to set
     */
    public void setValidateQueueReadyFlag(boolean validateQueueReadyFlag) {
        this.validateQueueReadyFlag = validateQueueReadyFlag;
    }

    /**
     * @return the contentsToAnalyze
     */
    public BlockingQueue<DescartesZipContentProxy> getContentsToAnalyze() {
        return contentsToAnalyze;
    }

    /**
     * @param contentsToAnalyze the contentsToAnalyze to set
     */
    public void setContentsToAnalyze(
            BlockingQueue<DescartesZipContentProxy> contentsToAnalyze) {
        this.contentsToAnalyze = contentsToAnalyze;
    }

    /**
     * @return the contentsToConvert
     */
    public BlockingQueue<DescartesZipContentProxy> getContentsToConvert() {
        return contentsToConvert;
    }

    /**
     * @param contentsToConvert the contentsToConvert to set
     */
    public void setContentsToConvert(
            BlockingQueue<DescartesZipContentProxy> contentsToConvert) {
        this.contentsToConvert = contentsToConvert;
    }

    /**
     * @return the contentsToValidate
     */
    public BlockingQueue<DescartesContentProxy> getContentsToValidate() {
        return contentsToValidate;
    }

    /**
     * @param contentsToValidate the contentsToValidate to set
     */
    public void setContentsToValidate(
            BlockingQueue<DescartesContentProxy> contentsToValidate) {
        this.contentsToValidate = contentsToValidate;
    }

    /**
     * @return the contentsModeled
     */
    public Queue<DescartesZipContentProxy> getContentsModeled() {
        return contentsModeled;
    }

    /**
     * @param contentsModeled the contentsModeled to set
     */
    public void setContentsModeled(
            Queue<DescartesZipContentProxy> contentsModeled) {
        this.contentsModeled = contentsModeled;
    }

    /**
     * @return the contentsAnalyzed
     */
    public Queue<DescartesZipContentProxy> getContentsAnalyzed() {
        return contentsAnalyzed;
    }

    /**
     * @param contentsAnalyzed the contentsAnalyzed to set
     */
    public void setContentsAnalyzed(
            Queue<DescartesZipContentProxy> contentsAnalyzed) {
        this.contentsAnalyzed = contentsAnalyzed;
    }

    /**
     * @return the contentsConverted
     */
    public Queue<DescartesZipContentProxy> getContentsConverted() {
        return contentsConverted;
    }

    /**
     * @param contentsConverted the contentsConverted to set
     */
    public void setContentsConverted(
            Queue<DescartesZipContentProxy> contentsConverted) {
        this.contentsConverted = contentsConverted;
    }

    /**
     * @return the contentsValidate
     */
    public Queue<DescartesContentProxy> getContentsValidate() {
        return contentsValidate;
    }

    /**
     * @param contentsValidate the contentsValidate to set
     */
    public void setContentsValidate(
            Queue<DescartesContentProxy> contentsValidate) {
        this.contentsValidate = contentsValidate;
    }

    /**
     * @return the contentsModeledError
     */
    public Queue<DescartesZipContentProxy> getContentsModeledError() {
        return contentsModeledError;
    }

    /**
     * @param contentsModeledError the contentsModeledError to set
     */
    public void setContentsModeledError(
            Queue<DescartesZipContentProxy> contentsModeledError) {
        this.contentsModeledError = contentsModeledError;
    }

    /**
     * @return the contentsAnalyzedError
     */
    public Queue<DescartesZipContentProxy> getContentsAnalyzedError() {
        return contentsAnalyzedError;
    }

    /**
     * @param contentsAnalyzedError the contentsAnalyzedError to set
     */
    public void setContentsAnalyzedError(
            Queue<DescartesZipContentProxy> contentsAnalyzedError) {
        this.contentsAnalyzedError = contentsAnalyzedError;
    }

    /**
     * @return the contentsConvertedError
     */
    public Queue<DescartesZipContentProxy> getContentsConvertedError() {
        return contentsConvertedError;
    }

    /**
     * @param contentsConvertedError the contentsConvertedError to set
     */
    public void setContentsConvertedError(
            Queue<DescartesZipContentProxy> contentsConvertedError) {
        this.contentsConvertedError = contentsConvertedError;
    }

    /**
     * @return the contentsValidateError
     */
    public Queue<DescartesContentProxy> getContentsValidateError() {
        return contentsValidateError;
    }

    /**
     * @param contentsValidateError the contentsValidateError to set
     */
    public void setContentsValidateError(
            Queue<DescartesContentProxy> contentsValidateError) {
        this.contentsValidateError = contentsValidateError;
    }

    /**
     * @return the modelQueueReadyFlag
     */
    public boolean isModelQueueReadyFlag() {
        return modelQueueReadyFlag;
    }

    /**
     * @param modelQueueReadyFlag the modelQueueReadyFlag to set
     */
    public void setModelQueueReadyFlag(boolean modelQueueReadyFlag) {
        this.modelQueueReadyFlag = modelQueueReadyFlag;
    }

    /**
     * @return the contentsToModel
     */
    public BlockingQueue<DescartesZipContentProxy> getContentsToModel() {
        return contentsToModel;
    }

    /**
     * @param contentsToModel the contentsToModel to set
     */
    public void setContentsToModel(
            BlockingQueue<DescartesZipContentProxy> contentsToModel) {
        this.contentsToModel = contentsToModel;
    }

}

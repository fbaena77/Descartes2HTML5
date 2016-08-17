package com.emergya.descartes.job;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.emergya.descartes.content.DescartesContentProxy;
import com.emergya.descartes.content.ZipContent;

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
    private BlockingQueue<DescartesContentProxy> contentsToModel;
    private BlockingQueue<ZipContent> contentsToAnalyze;
    private BlockingQueue<DescartesContentProxy> contentsToConvert;
    private BlockingQueue<DescartesContentProxy> contentsToValidate;

    /**
     * Poison pill para consumir los threads que usan Blocking Queues con DescartesContentProxy
     */
    public static final DescartesContentProxy STOP_QUEUE = new DescartesContentProxy();
    /**
     * Poison pill para consumir los threads que usan Blocking Queues con ZipContent
     */
    public static final ZipContent STOP_QUEUE_ZIP = new ZipContent("");

    /* Informacion estadistica */
    /**
     * Contenidos que se han modelado con éxito.
     */
    private Queue<DescartesContentProxy> contentsModeled;
    /**
     * Contenidos que se han analizado con éxito.
     */
    private Queue<ZipContent> contentsAnalyzed;

    /**
     * Contenidos que se han convertido con éxito.    
     */
    private Queue<DescartesContentProxy> contentsConverted;

    /**
     * Contenidos que se han validado con éxito
     */
    private Queue<DescartesContentProxy> contentsValidate;

    /**
     * Contenidos que no se han modelado correctamente.
     */
    private Queue<DescartesContentProxy> contentsModeledError;

    /**
     * Contenidos que no se han analizado correctamente.
     */
    private Queue<DescartesContentProxy> contentsAnalyzedError;

    /**
     * Contenidos que no se han convertido correctamente.
     */
    private Queue<DescartesContentProxy> contentsConvertedError;

    /**
     * Contenidos que no se han validado correctamente.
     */
    private Queue<DescartesContentProxy> contentsValidateError;

    /**
     * @param config
     */
    public JobConverter(JobConfiguration config) {

        this.setJobConfig(config);

        this.setContentsToModel(generateArrayBlockingQueue());
        this.setContentsToAnalyze(generateArrayBlockingQueueZip());
        this.setContentsToConvert(generateArrayBlockingQueue());
        this.setContentsToValidate(generateArrayBlockingQueue());

        this.setContentsModeled(generateArrayBlockingQueue());
        this.setContentsAnalyzed(generateArrayBlockingQueueZip());
        this.setContentsConverted(generateArrayBlockingQueue());
        this.setContentsValidate(generateArrayBlockingQueue());
        this.setContentsModeledError(generateArrayBlockingQueue());
        this.setContentsAnalyzedError(generateArrayBlockingQueue());
        this.setContentsConvertedError(generateArrayBlockingQueue());
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

    private ArrayBlockingQueue<DescartesContentProxy> generateArrayBlockingQueue() {
        return new ArrayBlockingQueue<DescartesContentProxy>(
                this.jobConfig.getSizeArrayBlockingqueue(), true);
    }

    private ArrayBlockingQueue<ZipContent> generateArrayBlockingQueueZip() {
        return new ArrayBlockingQueue<ZipContent>(
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
    public BlockingQueue<ZipContent> getContentsToAnalyze() {
        return contentsToAnalyze;
    }

    /**
     * @param contentsToAnalyze the contentsToAnalyze to set
     */
    public void setContentsToAnalyze(BlockingQueue<ZipContent> contentsToAnalyze) {
        this.contentsToAnalyze = contentsToAnalyze;
    }

    /**
     * @return the contentsToConvert
     */
    public BlockingQueue<DescartesContentProxy> getContentsToConvert() {
        return contentsToConvert;
    }

    /**
     * @param contentsToConvert the contentsToConvert to set
     */
    public void setContentsToConvert(
            BlockingQueue<DescartesContentProxy> contentsToConvert) {
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
    public Queue<DescartesContentProxy> getContentsModeled() {
        return contentsModeled;
    }

    /**
     * @param contentsModeled the contentsModeled to set
     */
    public void setContentsModeled(Queue<DescartesContentProxy> contentsModeled) {
        this.contentsModeled = contentsModeled;
    }

    /**
     * @return the contentsAnalyzed
     */
    public Queue<ZipContent> getContentsAnalyzed() {
        return contentsAnalyzed;
    }

    /**
     * @param contentsAnalyzed the contentsAnalyzed to set
     */
    public void setContentsAnalyzed(Queue<ZipContent> contentsAnalyzed) {
        this.contentsAnalyzed = contentsAnalyzed;
    }

    /**
     * @return the contentsConverted
     */
    public Queue<DescartesContentProxy> getContentsConverted() {
        return contentsConverted;
    }

    /**
     * @param contentsConverted the contentsConverted to set
     */
    public void setContentsConverted(
            Queue<DescartesContentProxy> contentsConverted) {
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
    public Queue<DescartesContentProxy> getContentsModeledError() {
        return contentsModeledError;
    }

    /**
     * @param contentsModeledError the contentsModeledError to set
     */
    public void setContentsModeledError(
            Queue<DescartesContentProxy> contentsModeledError) {
        this.contentsModeledError = contentsModeledError;
    }

    /**
     * @return the contentsAnalyzedError
     */
    public Queue<DescartesContentProxy> getContentsAnalyzedError() {
        return contentsAnalyzedError;
    }

    /**
     * @param contentsAnalyzedError the contentsAnalyzedError to set
     */
    public void setContentsAnalyzedError(
            Queue<DescartesContentProxy> contentsAnalyzedError) {
        this.contentsAnalyzedError = contentsAnalyzedError;
    }

    /**
     * @return the contentsConvertedError
     */
    public Queue<DescartesContentProxy> getContentsConvertedError() {
        return contentsConvertedError;
    }

    /**
     * @param contentsConvertedError the contentsConvertedError to set
     */
    public void setContentsConvertedError(
            Queue<DescartesContentProxy> contentsConvertedError) {
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
    public BlockingQueue<DescartesContentProxy> getContentsToModel() {
        return contentsToModel;
    }

    /**
     * @param contentsToModel the contentsToModel to set
     */
    public void setContentsToModel(
            BlockingQueue<DescartesContentProxy> contentsToModel) {
        this.contentsToModel = contentsToModel;
    }

}

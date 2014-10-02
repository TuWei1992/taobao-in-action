
package com.dream.scheduling.model;

import java.math.BigDecimal;



/**
 * AbstractI2sTmcJobDetails entity provides the base persistence definition of the I2sTmcJobDetails entity. @author MyEclipse Persistence Tools
 */

public class JobDetails {

    // Fields

    private JobDetailsId id;
    private String description;
    private String jobClassName;
    private BigDecimal isDurable;
    private BigDecimal isVolatile;
    private BigDecimal isStateful;
    private BigDecimal requestsRecovery;
    private String jobData;
    private String state;



    // Constructors



    /** default constructor */
    public JobDetails() {

    }



    
    public String getState() {
    
        return state;
    }



    
    public void setState(String state) {
    
        this.state = state;
    }



    /** minimal constructor */
    public JobDetails(JobDetailsId id, String jobClassName, BigDecimal isDurable, BigDecimal isVolatile,
            BigDecimal isStateful, BigDecimal requestsRecovery) {

        this.id = id;
        this.jobClassName = jobClassName;
        this.isDurable = isDurable;
        this.isVolatile = isVolatile;
        this.isStateful = isStateful;
        this.requestsRecovery = requestsRecovery;
    }



    /** full constructor */
    public JobDetails(JobDetailsId id, String description, String jobClassName, BigDecimal isDurable,
            BigDecimal isVolatile, BigDecimal isStateful, BigDecimal requestsRecovery, String jobData) {

        this.id = id;
        this.description = description;
        this.jobClassName = jobClassName;
        this.isDurable = isDurable;
        this.isVolatile = isVolatile;
        this.isStateful = isStateful;
        this.requestsRecovery = requestsRecovery;
        this.jobData = jobData;
    }



    // Property accessors

    public JobDetailsId getId() {

        return this.id;
    }



    public void setId(JobDetailsId id) {

        this.id = id;
    }



    public String getDescription() {

        return this.description;
    }



    public void setDescription(String description) {

        this.description = description;
    }



    public String getJobClassName() {

        return this.jobClassName;
    }



    public void setJobClassName(String jobClassName) {

        this.jobClassName = jobClassName;
    }



    public BigDecimal getIsDurable() {

        return this.isDurable;
    }



    public void setIsDurable(BigDecimal isDurable) {

        this.isDurable = isDurable;
    }



    public BigDecimal getIsVolatile() {

        return this.isVolatile;
    }



    public void setIsVolatile(BigDecimal isVolatile) {

        this.isVolatile = isVolatile;
    }



    public BigDecimal getIsStateful() {

        return this.isStateful;
    }



    public void setIsStateful(BigDecimal isStateful) {

        this.isStateful = isStateful;
    }



    public BigDecimal getRequestsRecovery() {

        return this.requestsRecovery;
    }



    public void setRequestsRecovery(BigDecimal requestsRecovery) {

        this.requestsRecovery = requestsRecovery;
    }



    public String getJobData() {

        return this.jobData;
    }



    public void setJobData(String jobData) {

        this.jobData = jobData;
    }

}
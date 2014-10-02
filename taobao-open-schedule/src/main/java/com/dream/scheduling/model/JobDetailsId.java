
package com.dream.scheduling.model;

/**
 * AbstractI2sTmcJobDetailsId entity provides the base persistence definition of the I2sTmcJobDetailsId entity. @author MyEclipse Persistence Tools
 */

public class JobDetailsId implements java.io.Serializable {

    // Fields

    private String jobName;
    private String jobGroup;



    // Constructors

    /** default constructor */
    public JobDetailsId() {

    }



    /** full constructor */
    public JobDetailsId(String jobName, String jobGroup) {

        this.jobName = jobName;
        this.jobGroup = jobGroup;
    }



    // Property accessors

    public String getJobName() {

        return this.jobName;
    }



    public void setJobName(String jobName) {

        this.jobName = jobName;
    }



    public String getJobGroup() {

        return this.jobGroup;
    }



    public void setJobGroup(String jobGroup) {

        this.jobGroup = jobGroup;
    }

}
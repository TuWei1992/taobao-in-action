
package com.dream.scheduling.handler;

import java.io.Serializable;



/**
 * @author Wenjianchuang
 *
 * <b>Class Description:</b><br>
 * TODO Please add your class description here
 */
public abstract class TaskHandler implements Serializable {

    /**
    * <b>执行需要指定定时执行的事务:</b><br>
    * TODO Pleas Add your method function description here
    * <br><b>Method Logic Description:</b>
    * TODO Pleas Add your method Logic description here, if needed
    */

    public abstract void execute();

}

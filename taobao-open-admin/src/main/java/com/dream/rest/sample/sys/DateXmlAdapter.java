
package com.dream.rest.sample.sys;

import com.dream.rest.request.DateUtils;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Date;

/**
 * @author : Frank
 * @date: 14-3-18
 */
public class DateXmlAdapter extends XmlAdapter<String,Date> {


    public Date unmarshal(String v) throws Exception {
        return DateUtils.parseDate(v);
    }


    public String marshal(Date date) throws Exception {
        return DateUtils.format(date,DateUtils.DATETIME_FORMAT);
    }
}

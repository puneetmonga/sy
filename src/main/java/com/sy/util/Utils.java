package com.sy.util;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Created by pmonga on 11/2/16.
 */
public class Utils {

    public static ToStringBuilder newToStringBuilder(Object object) {
        return new ToStringBuilder(object, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}

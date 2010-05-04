package ar.noxit.ehockey.web.util;

import org.apache.wicket.extensions.yui.calendar.DatePicker;

public class YearMonthDatePicker extends DatePicker {

    @Override
    protected boolean enableMonthYearSelection() {
        return true;
    }
}
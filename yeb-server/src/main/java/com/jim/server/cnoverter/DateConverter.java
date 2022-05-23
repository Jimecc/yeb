package com.jim.server.cnoverter;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Jim
 * @Description 日期转换
 * @createTime 2022年05月23日
 */
public class DateConverter implements Converter<String, LocalDate> {
    @Override
    public LocalDate convert(String s) {
        try {
            return LocalDate.parse(s, DateTimeFormatter.ofPattern("yyy-MM-dd"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

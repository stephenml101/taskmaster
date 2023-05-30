package com.stephenml101.taskmaster.database;


import androidx.room.TypeConverter;

import java.util.Date;

public class TaskMasterDatabaseConverters {
    @TypeConverter
    public static Date fromTimeStamp(Long dateValue){
        return dateValue == null ? null : new Date(dateValue);
    }

    @TypeConverter
    public static Long dateToTimeStamp(Date date){
        return date == null ? null : date.getTime();
    }

}

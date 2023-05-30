package com.stephenml101.taskmaster.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.stephenml101.taskmaster.dao.TaskDao;
import com.stephenml101.taskmaster.models.Tasks;



@TypeConverters({TaskMasterDatabaseConverters.class})
@Database(entities = {Tasks.class}, version = 1)
public abstract class TaskMasterDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();
}

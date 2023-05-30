package com.stephenml101.taskmaster.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.stephenml101.taskmaster.models.Tasks;

import java.util.List;

@Dao
public interface TaskDao {
    // Create
    @Insert
    void insertATask(Tasks task);
    // Read
    @Query("SELECT * FROM Tasks WHERE id = :id")
    Tasks findTaskById(Long id);

    @Query("SELECT * FROM Tasks")
    List<Tasks> findAllTasks();

    @Query("SELECT * FROM Tasks WHERE state = :state")
    List<Tasks> findAllTasksByState(Tasks.TaskStateEnum state);
    // Update
    @Update
    void updateTask(Tasks task);
    // Delete
    @Delete
    void deleteTask(Tasks task);
}

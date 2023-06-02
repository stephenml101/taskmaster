package com.stephenml101.taskmaster.models;


import java.util.Date;


public class Task {



    private String name;
    private String description;
    private java.util.Date startBy;
    private TaskStateEnum state;

//    private taskState taskState;



    public Task(String name, String description, Date startBy, TaskStateEnum state) {
        this.name = name;
        this.description = description;
        this.startBy = startBy;
        this.state = state;
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartBy() {
        return startBy;
    }

    public void setStartBy(Date startBy) {
        this.startBy = startBy;
    }

    public TaskStateEnum getState() {
        return state;
    }

    public void setState(TaskStateEnum state) {
        this.state = state;
    }

    public enum TaskStateEnum{
        NEW("new"),
        ASSIGNED("assigned"),
        IN_PROGRESS("in progress"),
        COMPLETE("complete");

        private final String taskState;

        TaskStateEnum(String taskState) {
            this.taskState = taskState;
        }

        @Override
        public String toString() {
            return "TaskStateEnum{" +
                    "taskState='" + taskState + '\'' +
                    '}';
        }

        public static TaskStateEnum fromString(String possibleTaskState){
            for (TaskStateEnum state: TaskStateEnum.values()) {
                if(state.taskState.equals(possibleTaskState)){
                    return state;
                }
            }
            return null;
        }
    }
}
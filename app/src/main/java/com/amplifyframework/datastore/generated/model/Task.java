package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.core.model.annotations.BelongsTo;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.AuthStrategy;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelOperation;
import com.amplifyframework.core.model.annotations.AuthRule;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the Task type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Tasks", type = Model.Type.USER, version = 1, authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
@Index(name = "byTeam", fields = {"teamId","name"})
public final class Task implements Model {
  public static final QueryField ID = field("Task", "id");
  public static final QueryField NAME = field("Task", "name");
  public static final QueryField DESCRIPTION = field("Task", "description");
  public static final QueryField DATE_CREATED = field("Task", "dateCreated");
  public static final QueryField TASK_STATE = field("Task", "taskState");
  public static final QueryField TASK_OWNER = field("Task", "teamId");
  public static final QueryField S3_KEY = field("Task", "s3Key");
  public static final QueryField LATITUDE = field("Task", "latitude");
  public static final QueryField LONGITUDE = field("Task", "longitude");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String name;
  private final @ModelField(targetType="String") String description;
  private final @ModelField(targetType="AWSDateTime") Temporal.DateTime dateCreated;
  private final @ModelField(targetType="TaskStateEnum") TaskStateEnum taskState;
  private final @ModelField(targetType="Team") @BelongsTo(targetName = "teamId", targetNames = {"teamId"}, type = Team.class) Team taskOwner;
  private final @ModelField(targetType="String") String s3Key;
  private final @ModelField(targetType="String") String latitude;
  private final @ModelField(targetType="String") String longitude;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String resolveIdentifier() {
    return id;
  }
  
  public String getId() {
      return id;
  }
  
  public String getName() {
      return name;
  }
  
  public String getDescription() {
      return description;
  }
  
  public Temporal.DateTime getDateCreated() {
      return dateCreated;
  }
  
  public TaskStateEnum getTaskState() {
      return taskState;
  }
  
  public Team getTaskOwner() {
      return taskOwner;
  }
  
  public String getS3Key() {
      return s3Key;
  }
  
  public String getLatitude() {
      return latitude;
  }
  
  public String getLongitude() {
      return longitude;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Task(String id, String name, String description, Temporal.DateTime dateCreated, TaskStateEnum taskState, Team taskOwner, String s3Key, String latitude, String longitude) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.dateCreated = dateCreated;
    this.taskState = taskState;
    this.taskOwner = taskOwner;
    this.s3Key = s3Key;
    this.latitude = latitude;
    this.longitude = longitude;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Task task = (Task) obj;
      return ObjectsCompat.equals(getId(), task.getId()) &&
              ObjectsCompat.equals(getName(), task.getName()) &&
              ObjectsCompat.equals(getDescription(), task.getDescription()) &&
              ObjectsCompat.equals(getDateCreated(), task.getDateCreated()) &&
              ObjectsCompat.equals(getTaskState(), task.getTaskState()) &&
              ObjectsCompat.equals(getTaskOwner(), task.getTaskOwner()) &&
              ObjectsCompat.equals(getS3Key(), task.getS3Key()) &&
              ObjectsCompat.equals(getLatitude(), task.getLatitude()) &&
              ObjectsCompat.equals(getLongitude(), task.getLongitude()) &&
              ObjectsCompat.equals(getCreatedAt(), task.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), task.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getName())
      .append(getDescription())
      .append(getDateCreated())
      .append(getTaskState())
      .append(getTaskOwner())
      .append(getS3Key())
      .append(getLatitude())
      .append(getLongitude())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Task {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("name=" + String.valueOf(getName()) + ", ")
      .append("description=" + String.valueOf(getDescription()) + ", ")
      .append("dateCreated=" + String.valueOf(getDateCreated()) + ", ")
      .append("taskState=" + String.valueOf(getTaskState()) + ", ")
      .append("taskOwner=" + String.valueOf(getTaskOwner()) + ", ")
      .append("s3Key=" + String.valueOf(getS3Key()) + ", ")
      .append("latitude=" + String.valueOf(getLatitude()) + ", ")
      .append("longitude=" + String.valueOf(getLongitude()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static NameStep builder() {
      return new Builder();
  }
  
  /**
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   */
  public static Task justId(String id) {
    return new Task(
      id,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      name,
      description,
      dateCreated,
      taskState,
      taskOwner,
      s3Key,
      latitude,
      longitude);
  }
  public interface NameStep {
    BuildStep name(String name);
  }
  

  public interface BuildStep {
    Task build();
    BuildStep id(String id);
    BuildStep description(String description);
    BuildStep dateCreated(Temporal.DateTime dateCreated);
    BuildStep taskState(TaskStateEnum taskState);
    BuildStep taskOwner(Team taskOwner);
    BuildStep s3Key(String s3Key);
    BuildStep latitude(String latitude);
    BuildStep longitude(String longitude);
  }
  

  public static class Builder implements NameStep, BuildStep {
    private String id;
    private String name;
    private String description;
    private Temporal.DateTime dateCreated;
    private TaskStateEnum taskState;
    private Team taskOwner;
    private String s3Key;
    private String latitude;
    private String longitude;
    @Override
     public Task build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Task(
          id,
          name,
          description,
          dateCreated,
          taskState,
          taskOwner,
          s3Key,
          latitude,
          longitude);
    }
    
    @Override
     public BuildStep name(String name) {
        Objects.requireNonNull(name);
        this.name = name;
        return this;
    }
    
    @Override
     public BuildStep description(String description) {
        this.description = description;
        return this;
    }
    
    @Override
     public BuildStep dateCreated(Temporal.DateTime dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }
    
    @Override
     public BuildStep taskState(TaskStateEnum taskState) {
        this.taskState = taskState;
        return this;
    }
    
    @Override
     public BuildStep taskOwner(Team taskOwner) {
        this.taskOwner = taskOwner;
        return this;
    }
    
    @Override
     public BuildStep s3Key(String s3Key) {
        this.s3Key = s3Key;
        return this;
    }
    
    @Override
     public BuildStep latitude(String latitude) {
        this.latitude = latitude;
        return this;
    }
    
    @Override
     public BuildStep longitude(String longitude) {
        this.longitude = longitude;
        return this;
    }
    
    /**
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     */
    public BuildStep id(String id) {
        this.id = id;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String name, String description, Temporal.DateTime dateCreated, TaskStateEnum taskState, Team taskOwner, String s3Key, String latitude, String longitude) {
      super.id(id);
      super.name(name)
        .description(description)
        .dateCreated(dateCreated)
        .taskState(taskState)
        .taskOwner(taskOwner)
        .s3Key(s3Key)
        .latitude(latitude)
        .longitude(longitude);
    }
    
    @Override
     public CopyOfBuilder name(String name) {
      return (CopyOfBuilder) super.name(name);
    }
    
    @Override
     public CopyOfBuilder description(String description) {
      return (CopyOfBuilder) super.description(description);
    }
    
    @Override
     public CopyOfBuilder dateCreated(Temporal.DateTime dateCreated) {
      return (CopyOfBuilder) super.dateCreated(dateCreated);
    }
    
    @Override
     public CopyOfBuilder taskState(TaskStateEnum taskState) {
      return (CopyOfBuilder) super.taskState(taskState);
    }
    
    @Override
     public CopyOfBuilder taskOwner(Team taskOwner) {
      return (CopyOfBuilder) super.taskOwner(taskOwner);
    }
    
    @Override
     public CopyOfBuilder s3Key(String s3Key) {
      return (CopyOfBuilder) super.s3Key(s3Key);
    }
    
    @Override
     public CopyOfBuilder latitude(String latitude) {
      return (CopyOfBuilder) super.latitude(latitude);
    }
    
    @Override
     public CopyOfBuilder longitude(String longitude) {
      return (CopyOfBuilder) super.longitude(longitude);
    }
  }
  
}

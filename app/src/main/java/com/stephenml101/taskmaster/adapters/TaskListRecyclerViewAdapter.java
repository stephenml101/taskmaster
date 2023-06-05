package com.stephenml101.taskmaster.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.Task;
import com.stephenml101.taskmaster.MainActivity;
import com.stephenml101.taskmaster.R;
import com.stephenml101.taskmaster.activities.TaskDetailActivity;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;

// Only purpose of this class is to manage RecyclerView


public class TaskListRecyclerViewAdapter extends RecyclerView.Adapter<TaskListRecyclerViewAdapter.TaskListViewHolder> {
    public static final String TAG = "ProductListRecyclerViewAdapter";
    private final List<Task> tasks;
    Context callingActivity;

    public TaskListRecyclerViewAdapter(List<Task> tasks, Context callingActivity){
        this.tasks = tasks;
        this.callingActivity = callingActivity;
    }

    public static class TaskListViewHolder extends RecyclerView.ViewHolder {
        public TaskListViewHolder(View fragmentItemView){
            super(fragmentItemView);
        }
    }



    @NonNull
    @Override
    public TaskListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View taskFragment = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_task_list, parent, false);

        return new TaskListViewHolder(taskFragment);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskListViewHolder holder, int position) {
        TextView taskFragmentTextView = holder.itemView.findViewById(R.id.taskFragmentTextView);
        String taskName = tasks.get(position).getName();
        String taskDetail = tasks.get(position).getDescription();

        DateFormat dateCreatedIso8601InputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
        dateCreatedIso8601InputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        @SuppressLint("SimpleDateFormat") DateFormat dateCreatedOutputFormat = new SimpleDateFormat("MM-dd-yyyy");
        dateCreatedOutputFormat.setTimeZone(TimeZone.getDefault());
        String dateCreatedCreateString = "";

        try {
            Date dateCreatedJavaDate = dateCreatedIso8601InputFormat.parse(tasks.get(position).getDateCreated().format());
            dateCreatedCreateString = dateCreatedOutputFormat.format(Objects.requireNonNull(dateCreatedJavaDate));
        } catch (ParseException e) {
            Log.e(TAG, "Failed to format date " + e);
            e.printStackTrace();
        }

        String taskFragmentText = position + ". " + taskName + "     " + dateCreatedCreateString;
        taskFragmentTextView.setText(taskFragmentText);

        View taskViewHolder = holder.itemView;
        taskViewHolder.setOnClickListener(view -> {
            Intent gotToTaskDetailIntent = new Intent(callingActivity, TaskDetailActivity.class);
            gotToTaskDetailIntent.putExtra(MainActivity.TASK_NAME_EXTRA_TAG, taskName);
            gotToTaskDetailIntent.putExtra(MainActivity.TASK_DETAIL_EXTRA_TAG, taskDetail);
            callingActivity.startActivity(gotToTaskDetailIntent);
        });
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }
}
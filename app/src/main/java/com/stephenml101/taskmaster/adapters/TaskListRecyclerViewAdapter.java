package com.stephenml101.taskmaster.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stephenml101.taskmaster.MainActivity;
import com.stephenml101.taskmaster.R;
import com.stephenml101.taskmaster.activities.TaskDetailActivity;
import com.stephenml101.taskmaster.models.Tasks;

import java.util.List;

// Only purpose of this class is to manage RecyclerView


public class TaskListRecyclerViewAdapter extends RecyclerView.Adapter<TaskListRecyclerViewAdapter.TaskListViewHolder> {
    private List<Tasks> tasks;
    Context callingActivity;

    public TaskListRecyclerViewAdapter(List<Tasks> tasks, Context callingActivity){
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
        TextView taskFragmentTextView = (TextView) holder.itemView.findViewById(R.id.taskFragmentTextView);
        String taskName = tasks.get(position).getName();
        String taskDetail = tasks.get(position).getDescription();
        String taskFragmentText = position + ". " + taskName;
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
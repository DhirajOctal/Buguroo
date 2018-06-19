package com.octalsoftaware.buguroo.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.octalsoftaware.buguroo.Delegate.SetOnClickListener;
import com.octalsoftaware.buguroo.R;

public class ContextAdapter extends RecyclerView.Adapter<ContextAdapter.MyHolder> {
    private Context context;
    private SetOnClickListener setOnClickListener;
    private String[] contextArray = {"Number of entries in agenda", "Disk Space", "Screen Lock Status",
            "Battery consumption per APP", "Data consumption per APP", "Number of APPs installed", "OS information (version)", "Device Type", "Language"};

    public ContextAdapter(Context context, SetOnClickListener setOnClickListener) {
        this.context = context;
        this.setOnClickListener = setOnClickListener;
    }

    @NonNull
    @Override
    public ContextAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.inflate_layout, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContextAdapter.MyHolder holder, final int position) {
        holder.textView.setText(contextArray[position]);
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOnClickListener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contextArray.length;
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView textView;

        MyHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
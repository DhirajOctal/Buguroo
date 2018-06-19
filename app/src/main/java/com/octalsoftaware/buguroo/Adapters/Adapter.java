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

public class Adapter extends RecyclerView.Adapter<Adapter.MyHolder> {
    private Context context;
    private SetOnClickListener setOnClickListener;
    private String[] array = {"Device ID", "Installation ID", "User Details", "Context Information", "Network Information", "Behavioral Indicators", "Device Rooted Check", "Emulation Detection"};

    public Adapter(Context context, SetOnClickListener setOnClickListener) {
        this.context = context;
        this.setOnClickListener = setOnClickListener;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.inflate_layout, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, final int position) {
        holder.textView.setText(array[position]);
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOnClickListener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return array.length;
    }

    class MyHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        MyHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
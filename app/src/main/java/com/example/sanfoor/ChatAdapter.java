package com.example.sanfoor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_USER = 1;
    private static final int TYPE_BOT = 2;

    private final List<Message> messages;

    public ChatAdapter(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public int getItemViewType(int position) {
        return messages.get(position).isUser ? TYPE_USER : TYPE_BOT;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == TYPE_USER) {
            View v = inflater.inflate(R.layout.item_message_user, parent, false);
            return new UserVH(v);
        } else {
            View v = inflater.inflate(R.layout.item_message_bot, parent, false);
            return new BotVH(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message m = messages.get(position);
        if (holder instanceof UserVH) {
            ((UserVH) holder).tv.setText(m.text);
        } else if (holder instanceof BotVH) {
            ((BotVH) holder).tv.setText(m.text);
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    static class UserVH extends RecyclerView.ViewHolder {
        TextView tv;
        UserVH(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tvUserMessage);
        }
    }

    static class BotVH extends RecyclerView.ViewHolder {
        TextView tv;
        BotVH(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tvBotMessage);
        }
    }
}

package com.junga.hongchat;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder>{

    private List<ChatData> mDataset;
    private String myNickName;
    private Context context;

    public ChatAdapter(Context context, List<ChatData> mDataset, String myNickName) {
        this.context = context;
        this.mDataset = mDataset;
        this.myNickName = myNickName;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //create Linear Layout

        LinearLayout layout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.row_chat,parent,false);


        return new MyViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ChatData data = mDataset.get(position);

        holder.textView_nickname.setText(data.getNickname());
        holder.textView_msg.setText(data.getMeg());

        //align chat message, if it is mine align to right, not mine, align to left

        if(data.getNickname().equals(this.myNickName)){
            holder.textView_msg.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
            holder.textView_nickname.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        }else{
            holder.textView_msg.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            holder.textView_nickname.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        }

    }

    @Override
    public int getItemCount() {
        return mDataset == null ? 0 : mDataset.size();
    }

    public ChatData getChat(int position){
        return mDataset != null ? mDataset.get(position) : null;
    }

    public void addChat(ChatData chat){
        mDataset.add(chat);
        notifyItemInserted(mDataset.size()-1); //새롭게 갱신
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textView_nickname;
        TextView textView_msg;
        View root_layout;

        public MyViewHolder(View itemView) {
            super(itemView);

            textView_nickname = (TextView) itemView.findViewById(R.id.textView_nickname);
            textView_msg = (TextView) itemView.findViewById(R.id.textView_msg);
            root_layout = itemView;
        }
    }
}

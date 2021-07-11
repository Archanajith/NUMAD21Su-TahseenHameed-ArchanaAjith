package edu.neu.madcourse.numad21su_tahseenhameed_archanaajith;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private Context mContext;
    private List<ChatActivity.Sticker> stickers;
    int [] stickerList = {R.drawable.choice_icon, R.drawable.heart_icon, R.drawable.smile_icon, R.drawable.angry_icon};
    //ImageView sticker;
    //TextView senderName;

    public Adapter(Context mContext, List<ChatActivity.Sticker> mData) {
        this.mContext = mContext;
        this.stickers = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        v = inflater.inflate(R.layout.each_sticker_grid_item, parent, false);

        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
holder.sticker.setImageResource(stickerList[stickers.get(position).getEmojiId()]);
             holder.senderName.setText(stickers.get(position).getSender());


    }

    @Override
    public int getItemCount() {
        return stickers.size();
    }


    public  class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView sticker;
        TextView senderName;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            sticker = itemView.findViewById(R.id.each_grid_sticker);
            senderName=itemView.findViewById(R.id.sender);



        }
    }
}

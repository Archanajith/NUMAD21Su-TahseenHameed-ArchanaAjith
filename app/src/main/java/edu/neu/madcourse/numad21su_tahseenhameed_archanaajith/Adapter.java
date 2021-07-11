package edu.neu.madcourse.numad21su_tahseenhameed_archanaajith;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private Context mContext;
    private List<Sticker> stickers;

    public Adapter(Context mContext, List<Sticker> mData) {
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

        ImageView imageView = convertView.findViewById(R.id.grid_stickers);
        imageView.setImageResource(stickerList[stickers.get(position).getEmojiID()]);
        TextView tv = convertView.findViewById(R.id.senderName);
        tv.setText(stickers.get(position).getUserFrom());
        holder.senderName.setText();
        holder.id.setText(mData.get(position).getId());
        holder.name.setText(mData.get(position).getName());
        holder.description.setText(mData.get(position).getDescription());



    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView sticker;
        TextView senderName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.id_txt);
            name = itemView.findViewById(R.id.name_txt);
            img = itemView.findViewById(R.id.imageView);
            description=itemView.findViewById(R.id.text_view_description);



        }
    }
}

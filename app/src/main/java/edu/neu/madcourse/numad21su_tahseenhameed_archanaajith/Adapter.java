package edu.neu.madcourse.numad21su_tahseenhameed_archanaajith;

import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


public class Adapter extends BaseAdapter {
    private int [] stickerList = {R.drawable.choice_icon, R.drawable.heart_icon, R.drawable.smile_icon, R.drawable.angry_icon};
    private Context context;
    private LayoutInflater layoutInflater;
    private List<ChatActivity.Sticker> stickers;
    private ImageView displaySticker;
    private TextView senderName;



    public Adapter(Context context, List<ChatActivity.Sticker> stickers){
        this.context = context;
        this.stickers = stickers;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (layoutInflater == null){
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.each_sticker_grid_item, null);
        }
        displaySticker= convertView.findViewById(R.id.grid_stickers);
        displaySticker.setImageResource(stickerList[stickers.get(position).getEmojiId()]);
        senderName = convertView.findViewById(R.id.senderName);
        senderName.setText(stickers.get(position).getSender());

        return convertView;
    }


    @Override
    public int getCount() {
        return stickers.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}

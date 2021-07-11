package edu.neu.madcourse.numad21su_tahseenhameed_archanaajith;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class ChatHistoryActivity extends AppCompatActivity {
    private GridView each_Sticker_gridview;
    private String currentUser;
    private List<ChatActivity.Sticker> stickerList;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_history);

        currentUser = (String) getIntent().getSerializableExtra("currentUser");
        stickerList = new ArrayList<>();
        each_Sticker_gridview = findViewById(R.id.gridView_each_sticker);

        Adapter insertDataAdapter = new Adapter(this, stickerList);
        each_Sticker_gridview.setAdapter(insertDataAdapter);

        DatabaseReference stkRef = FirebaseDatabase.getInstance().getReference().child("stickers");;
        stkRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot snapshot) {
                for (DataSnapshot stickerSnapshot: snapshot.getChildren()) {

                    String sender = stickerSnapshot.child("sender").getValue(String.class);
                    String receiver = stickerSnapshot.child("receiver").getValue(String.class);
                    int emojiId = stickerSnapshot.child("emojiId").getValue(Integer.class);
                    ChatActivity.Sticker stkFromDB = new ChatActivity.Sticker(sender, receiver, emojiId);
                    stickerList.add(stkFromDB);
                }

            }
            @Override
            public void onCancelled( DatabaseError error) {
            }
        });


    }
}
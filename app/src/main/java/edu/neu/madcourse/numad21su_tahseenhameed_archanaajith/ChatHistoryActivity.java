package edu.neu.madcourse.numad21su_tahseenhameed_archanaajith;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.ImageView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ChatHistoryActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private String currentUser;
    private TextView noOfStickers;
    private int listOfReceivedStcikers[] ;
    List<ChatActivity.Sticker> listOfStickers;
    RecyclerView recyclerView;
    ImageView header;
    Adapter adapter = new Adapter(this, listOfStickers);
    private FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_history);
        db = FirebaseDatabase.getInstance();
        currentUser = (String) getIntent().getSerializableExtra("loggedinUser");
        listOfStickers=new ArrayList<>();
        //noOfStickers=findViewById();
// list of stickers

        DatabaseReference stkRef = db.getReference().child("stickers");
        stkRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot snapshot) {
                for (DataSnapshot stickerSnapshot: snapshot.getChildren()) {

                    String sender = stickerSnapshot.child("sender").getValue(String.class);
                    //Log.i("sender is", sender);
                    String receiver = stickerSnapshot.child("receiver").getValue(String.class);
                    //Log.i("receiver is", receiver);
                    int emojiId = stickerSnapshot.child("emojiId").getValue(Integer.class);
                    //Log.d(emojiId, emojiId);
                    ChatActivity.Sticker stkDb = new ChatActivity.Sticker(sender, receiver, emojiId);
                    listOfStickers.add(stkDb);
                }

            }
            @Override
            public void onCancelled( DatabaseError error) {
            }
        });



        PutDataIntoRecyclerView(listOfStickers);
        //

    }



    private void PutDataIntoRecyclerView(List<ChatActivity.Sticker> listOfStickers){


        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(adapter);
    }
}
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_history);

        currentUser = (String) getIntent().getSerializableExtra("loggedinUser");
        listOfStickers=new ArrayList<>();
        //noOfStickers=findViewById();

        //Insert DB Code here
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Stickers");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listOfStickers.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    ChatActivity.Sticker sticker = snapshot.getValue(ChatActivity.Sticker.class);
                    if (sticker.getSender().equals(currentUser)) {
                        //numStickerSent++;
                    }
                    if (sticker.getReceiver().equals(currentUser)) {
                        listOfStickers.add(sticker);
                    }
                }
                adapter.notifyDataSetChanged();
                //displayNums.setText("You have sent " + Integer.toString(numStickerSent) + " stickers");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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
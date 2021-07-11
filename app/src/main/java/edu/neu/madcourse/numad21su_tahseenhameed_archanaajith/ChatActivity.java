package edu.neu.madcourse.numad21su_tahseenhameed_archanaajith;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener {


    private ImageView displayEmoji;
    private ImageView heartEmoji;
    private ImageView smileEmoji;
    private ImageView angryEmoji;
    private Button historyButton;
    private Button sendButton;
    private EditText receiverInput;
    private String user;
    int emojiId;
    private int [] emojiList = {R.drawable.choice_icon, R.drawable.heart_icon, R.drawable.smile_icon, R.drawable.angry_icon};
    private FirebaseDatabase db;
    private List<String> receivers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        user = (String) getIntent().getSerializableExtra("userLoggedIn");
        db = FirebaseDatabase.getInstance();
        emojiId = 0;
        receiverInput = findViewById(R.id.receiverUname);
        sendButton = findViewById(R.id.sendButton);
        displayEmoji = findViewById(R.id.displayEmoji);
        displayEmoji.setImageResource(emojiList[0]);
        historyButton=findViewById(R.id.historyButton);

        // set Emoji on Click
        heartEmoji = findViewById(R.id.heartEmoji);
        heartEmoji.setOnClickListener(this);
        smileEmoji = findViewById(R.id.smileEmoji);
        smileEmoji.setOnClickListener(this);
        angryEmoji = findViewById(R.id.angryEmoji);
        angryEmoji.setOnClickListener(this);

        // send Emoji sticker
        sendButton.setOnClickListener(this);

        //Chat History
        historyButton.setOnClickListener(this);




    }

    @Override
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.heartEmoji:
                emojiId = 1;
                displayEmoji.setImageResource(emojiList[1]);
                break;
            case R.id.smileEmoji:
                emojiId = 2;
                displayEmoji.setImageResource(emojiList[2]);
                break;
            case R.id.angryEmoji:
                emojiId = 3;
                displayEmoji.setImageResource(emojiList[3]);
                break;
            case R.id.historyButton:
                Intent intent = new Intent(this, ChatHistoryActivity.class);
                 intent.putExtra("loggedinUser", user);
                startActivity(intent);
                break;
            case R.id.sendButton:
                String receiver = receiverInput.getText().toString().toLowerCase();
                if(!receiver.isEmpty() && emojiId > 0) {

                    // create a sticker
                    DatabaseReference dbRef = db.getReference();
                    DatabaseReference newStkRef = dbRef.child("stickers").push();
                    newStkRef.child("sender").setValue(user);
                    newStkRef.child("receiver").setValue(receiver);
                    newStkRef.child("emojiId").setValue(emojiId);

                    // reset emoji and receiver
                    displayEmoji.setImageResource(emojiList[0]);
                    receiverInput.setText("");
                    Snackbar.make(view, "Sticker Sent", Snackbar.LENGTH_LONG)
                            .show();

                }
                else if(emojiId == 0) {
                    Snackbar.make(view, "Please select a sticker to send", Snackbar.LENGTH_LONG)
                            .show();
                }
                else{
                    Snackbar.make(view, "Receiver username cannot be empty", Snackbar.LENGTH_LONG)
                            .show();
                }

        }
    }


    public static class Sticker {

        public String getSender() {
            return sender;
        }

        public void setSender(String sender) {
            this.sender = sender;
        }

        public String getReceiver() {
            return receiver;
        }

        public void setReceiver(String receiver) {
            this.receiver = receiver;
        }

        public int getEmojiId() {
            return emojiId;
        }

        public void setEmojiId(int emojiId) {
            this.emojiId = emojiId;
        }

        private String sender;
        private String receiver;
        private int emojiId;

        Sticker(String sender, String receiver, int emojiId) {
            this.sender = sender;
            this.receiver = receiver;
            this.emojiId = emojiId;

        }



    }
}
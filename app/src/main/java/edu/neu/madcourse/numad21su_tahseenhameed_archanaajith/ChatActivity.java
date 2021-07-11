package edu.neu.madcourse.numad21su_tahseenhameed_archanaajith;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener {

    private FloatingActionButton addStickerButton;
    private ImageView displayEmoji;
    private Button historyButton;
    private Button sendButton;
    private EditText receiverInput;
    private String user;
    int stickerId;
    private int [] emojiList = {R.drawable.choice_icon, R.drawable.heart_icon, R.drawable.smile_icon, R.drawable.angry_icon};
    private FirebaseDatabase db;
    private List<String> receivers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        historyButton=findViewById(R.id.historyButton);
        historyButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.historyButton:
                Intent intent = new Intent(ChatActivity.this, ChatHistoryActivity.class);
                //Add your current user
               // intent.putExtra("currentUser", currentUser);
                startActivity(intent);
                break;

        }
    }
}
package edu.neu.madcourse.numad21su_tahseenhameed_archanaajith;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

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
    }
}
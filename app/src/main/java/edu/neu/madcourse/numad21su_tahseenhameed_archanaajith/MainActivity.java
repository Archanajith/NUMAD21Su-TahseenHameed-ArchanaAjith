package edu.neu.madcourse.numad21su_tahseenhameed_archanaajith;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {


    private FirebaseDatabase db = FirebaseDatabase.getInstance();
     private DatabaseReference mRootRef = db.getReference();
     private DatabaseReference mUserRef = mRootRef.child("user");

     private EditText userName;
     private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userName = findViewById(R.id.userName);
        login = findViewById(R.id.loginButton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = userName.getText().toString().trim().toLowerCase();

                mUserRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        String text = snapshot.getValue(String.class);
                        userName.setText(text);

                    }

                    @Override
                    public void onCancelled(DatabaseError error) {

                    }
                });

            }
        });


    }


}
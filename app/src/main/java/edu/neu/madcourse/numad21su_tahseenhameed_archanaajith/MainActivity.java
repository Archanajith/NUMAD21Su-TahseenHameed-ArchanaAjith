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

import java.util.Iterator;

public class MainActivity extends AppCompatActivity {


     private FirebaseDatabase db = FirebaseDatabase.getInstance();
     private DatabaseReference mRootRef = db.getReference();
     private DatabaseReference mUserRef = mRootRef.child("users");

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
                String userInput = userName.getText().toString().trim().toLowerCase();
                userInDb(userInput);
                if(userValidation(userInput)){
                    // open chat
                }

            }
        });


    }

    private void userInDb(String userInput) {

        mUserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Iterator<DataSnapshot> dataSnapshots = snapshot.getChildren().iterator();
                while (dataSnapshots.hasNext()) {
                    DataSnapshot dataSnapshotChild = dataSnapshots.next();
                    String Uname = dataSnapshotChild.getValue().toString();
                    if (Uname.equals(userInput)) {
                        userName.setError("Username already exists");
                        return;
                    }
                }
                createNewUser(db, userInput);

            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }

    private void createNewUser(FirebaseDatabase db, String userInput) {

        DatabaseReference dbRef = db.getReference();
        DatabaseReference newUserRef = dbRef.child("users").push();
        newUserRef.setValue(userInput);
    }

    private boolean userValidation(String userInput) {
        if (userInput.isEmpty()){
            userName.setError("username cannot be empty");
            return false;
        } else{
            userName.setError(null);
            return true;
        }
    }


}
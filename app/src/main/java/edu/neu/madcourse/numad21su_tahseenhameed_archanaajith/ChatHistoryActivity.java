package edu.neu.madcourse.numad21su_tahseenhameed_archanaajith;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.ImageView;

import edu.neu.madcourse.numad21su_tahseenhameed_archanaajith.Adapter;

import android.os.Bundle;

public class ChatHistoryActivity extends AppCompatActivity {
    private ProgressBar progressBar;

    //List<TMDBModelClass> movieList;
    RecyclerView recyclerView;
    ImageView header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_history);
    }



    private void PutDataIntoRecyclerView(List<TMDBModelClass> movieList){

        Adapter adapter = new Adapter(this, movieList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(adapter);
    }
}
package com.example.binht.testvocabulary;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerView;

/**
 * Created by binht on 5/11/2017.
 */

public class Compare extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    ListView listView;
    Button word1, word2, testBtn;
    YouTubePlayerFragment youTubePlayerFragment;
    Vocabulary vocabulary;
    TextView header;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compare);

      //  listView = (ListView)findViewById(R.id.compare_list);
        word1 = (Button)findViewById(R.id.compare_word1_textview);
        word2 = (Button)findViewById(R.id.compare_word2_textview);
        testBtn = (Button)findViewById(R.id.test_compare_Btn);
        header = (TextView)findViewById(R.id.compare_header_textview);

        youTubePlayerFragment = (YouTubePlayerFragment)getFragmentManager().findFragmentById(R.id.youtube_play_view);
        youTubePlayerFragment.initialize(PlayerConfig.API_KEY, this);

        Intent i = getIntent();
       vocabulary = (Vocabulary)i.getSerializableExtra("Vocabulary");

        if(vocabulary == null){
            Toast.makeText(getApplicationContext(),"There is no app!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(), vocabulary.getWord()[1], Toast.LENGTH_SHORT).show();
            word1.setText("Learn: " + vocabulary.getWord()[0]);
            word2.setText("Learn: " + vocabulary.getWord()[1]);
            header.setText(vocabulary.getWord()[0] + " and " + vocabulary.getWord()[1]);
        }

        word1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Compare.this, Listen.class);
                intent.putExtra("Word1",vocabulary);
                startActivity(intent);

            }
        });
        word2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Compare.this, Listen.class);
                intent.putExtra("Word2",vocabulary);
                startActivity(intent);
            }
        });
        testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Compare.this, TestCompare.class);
                intent.putExtra("Test",vocabulary);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        Log.e("Youtube", vocabulary.getYoutube());
        youTubePlayer.cueVideo(vocabulary.getYoutube());
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if(youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(Compare.this, 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            getYoutubePlayerProvider().initialize(PlayerConfig.API_KEY, this);
        }
    }
    protected YouTubePlayer.Provider getYoutubePlayerProvider() {
        return (YouTubePlayerView)findViewById(R.id.youtube_play_view);
    }
}

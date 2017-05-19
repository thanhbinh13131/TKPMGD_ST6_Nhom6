package com.example.binht.testvocabulary;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerView;

public class Youtube_Player extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    Button button;
    YouTubePlayerFragment youTubePlayerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.watch_youtube);

        button = (Button)findViewById(R.id.play_btn);
        youTubePlayerFragment = (YouTubePlayerFragment)getFragmentManager().findFragmentById(R.id.youtube_play_view);
       // youTubePlayerFragment = (YouTubePlayerFragment)findViewById(R.id.youtube_play_view);
        youTubePlayerFragment.initialize(PlayerConfig.API_KEY, this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.cueVideo("FN7ALfpGxiI");
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if(youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(Youtube_Player.this, 1);
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

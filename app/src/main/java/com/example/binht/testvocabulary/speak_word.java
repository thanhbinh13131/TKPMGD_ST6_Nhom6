package com.example.binht.testvocabulary;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class speak_word extends AppCompatActivity {
    ImageButton speech;
    TextView result, englishWord;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speak_word);

        Intent intent = getIntent();
        String vocabulary = intent.getStringExtra("Word");

        speech = (ImageButton)findViewById(R.id.speech_btn);
        result = (TextView)findViewById(R.id.result_textview);
        englishWord = (TextView)findViewById(R.id.english_word_textview);
//
//        String words[] = {"Happy", "Sad", "Grinning", "Funny"};
//        ArrayAdapter arrayAdapter;
//        arrayAdapter = new ArrayAdapter(this,R.layout.list_item_synonyms,R.id.synonyms_list_textview,words);
//
        if(vocabulary != null){
            englishWord.setText(vocabulary);
        }
        else {
            Toast.makeText(getApplicationContext(),"Get no word!", Toast.LENGTH_SHORT).show();
        }
        speech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                    intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak now...!");
                    intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1);
                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH);
                    startActivityForResult(intent, 2);
                }
                catch(ActivityNotFoundException e)
                {
                    Intent your_browser_intent = new Intent(Intent.ACTION_VIEW,

                            Uri.parse("https://market.android.com/details?id=APP_PACKAGE_NAME"));
                    startActivity(your_browser_intent);

                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 2: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    this.result.setText(result.get(0));

                    if((result.get(0).toLowerCase()).equals(englishWord.getText().toString().toLowerCase())){
                        this.result.setTextColor(Color.parseColor("green"));
                    }
                    else {
                        this.result.setTextColor(Color.parseColor("red"));
                    }
                }
                break;
            }
            default:
                break;
        }
    }
}

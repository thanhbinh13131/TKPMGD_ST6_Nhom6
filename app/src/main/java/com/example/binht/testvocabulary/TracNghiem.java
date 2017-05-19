package com.example.binht.testvocabulary;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by binht on 4/25/2017.
 */

public class TracNghiem extends AppCompatActivity {
    ListView listView;
    ImageButton speech;
    TextView result;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_synonyms);

        Intent intent = new Intent();
        String vocabulary = intent.getStringExtra("Word");

        listView = (ListView)findViewById(R.id.synonyms_list);
        speech = (ImageButton) findViewById(R.id.speech_btn);
        result = (TextView)findViewById(R.id.result_textview);

        String words[] = {"Happy", "Sad", "Grinning", "Funny"};
        ArrayAdapter arrayAdapter;
        arrayAdapter = new ArrayAdapter(this,R.layout.list_item_synonyms,R.id.synonyms_list_textview,words);

        listView.setAdapter(arrayAdapter);

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
                    Toast.makeText(getApplicationContext(),"There is no app!", Toast.LENGTH_SHORT).show();

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
                }
                break;
            }
        }
//        if(requestCode == 2) {
//            ArrayList<String> results;
//            results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
//            String result = results.get(0).replace("'","");
//            this.result.setText(result);
//        }
    }
}

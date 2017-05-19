package com.example.binht.testvocabulary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    private Context mContext;
    EditText word, type, pronounce;
    Button start, about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);

        start = (Button) findViewById(R.id.startBtn);
        about = (Button) findViewById(R.id.aboutBtn);


        addData();
        //  showData();
        setTracnghiemBtn();
        // copyData();
    }

    public void setTracnghiemBtn() {
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TracNghiem.class);
                startActivity(intent);
            }
        });
    }

    public void addData() {
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Vocabularies.class);
                startActivity(intent);
            }
        });
//        addBtn.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        boolean insertResult = db.insertData(word.getText().toString(),
//                               Integer.parseInt(type.getText().toString()),pronounce.getText().toString());
//                        if(insertResult == true)
//                            Toast.makeText(MainActivity.this,"Insert Successfull",Toast.LENGTH_LONG).show();
//                        else
//                            Toast.makeText(MainActivity.this, "Insert dont successfull", Toast.LENGTH_LONG).show();
//                    }
//                }
//        );
    }
//    public void copyData(){
//        stranslteBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                BufferedReader reader = null;
//
//                try {
////                    AssetManager am = mContext.getAssets();
////                    InputStream is = am.open("QuotesMonkeyBusiness.txt");
//                    reader = new BufferedReader(
//                            new InputStreamReader(getAssets().open("words")));
//
//                    // do reading, usually loop until end of file reading
//                    String mWord = null;
//                    String[] mWordArray = null;
//                    int i= 0;
//                    while ((mWord = reader.readLine()) != null) {
//
//                        mWordArray = mWord.split(",");
//                        boolean result = db.insertData(mWordArray[1],Integer.parseInt(mWordArray[2]),mWordArray[3]);
//
//                        if(result == true) {
//                            Log.e("Insert success record", "" + i++);
//                            Toast.makeText(MainActivity.this, "Insert Successfull", Toast.LENGTH_LONG).show();
//                        }
//                        else {
//                            Log.e("Insert not success", "" + i++);
//                            Toast.makeText(MainActivity.this, "Insert dont successfull", Toast.LENGTH_LONG).show();
//                        }
//
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } finally {
//                    if (reader != null) {
//                        try {
//                            reader.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//
//            }
//        });
//          }
//
//    public void showData(){
//        showBtn.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Cursor res = db.getAllVoca();
//                        if(res.getCount() == 0){
//                            Toast.makeText(MainActivity.this, "No data to show!", Toast.LENGTH_LONG).show();
//                        }
//                        StringBuffer stringBuffer = new StringBuffer();
//                        while (res.moveToNext()) {
//                            stringBuffer.append("Word: "+ res.getString(1)+"\n");
//                            stringBuffer.append("Type: "+ res.getString(2)+"\n");
//                            stringBuffer.append("Pronouce: "+ res.getString(3)+"\n");
//                            stringBuffer.append("Id: "+ res.getString(0)+"\n");
//                        }
//                        showData("Data", stringBuffer.toString());
//                    }
//                }
//        );
//    }
//
//    public void showData(String title, String message){
//        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
//        alertDialog.setCancelable(true);
//        alertDialog.setTitle(title);
//        alertDialog.setMessage(message);
//        alertDialog.show();
//    }
}

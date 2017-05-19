package com.example.binht.testvocabulary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Vocabularies extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabularies);
        listView = (ListView) findViewById(R.id.vocabulary_list);

//        Intent intent = getIntent();
//        String arrayVoca = intent.getStringExtra("Compare");

//        Vocabulary mother = new Vocabulary("Mother", "Mothers", new String[]{"Mẹ"},
//                new String[]{"My mother is beautiful!"}, "Noun");
//        Vocabulary father = new Vocabulary("Father", "Fathers", new String[]{"Cha"},
//                new String[]{"My father is strong!"}, "Noun");
//        Vocabulary brother = new Vocabulary("Brother", "Brothers", new String[]{"Anh trai/em trai","Anh em"},
//                new String[]{"My brother is sleeping!"}, "Noun");
//        Vocabulary sister = new Vocabulary("Sister", "Sisters", new String[]{"Chị gái/em gái", "Chị em"},
//                new String[]{"My sister is standing near the door!"}, "Noun");
//        Vocabulary son = new Vocabulary("Son", "Sons", new String[]{"Con trai"},
//                new String[]{"That is my son.", "They are my sons."}, "Noun");
//        Vocabulary daughter = new Vocabulary("Daughter", "Daughters", new String[]{"Con gái", "Nhứng đứa con gái"},
//                new String[]{"That is my daughter.", "My daughters are having lunch."}, "Noun");
//        Vocabulary parent = new Vocabulary("Parent", "Parent", new String[]{"Cha mẹ","Cha mẹ"},
//                new String[]{"My parent is traveling.", "They are my parents."}, "Noun");
//        Vocabulary uncle = new Vocabulary("Uncle", "Uncles", new String[]{"Chú/cậu/bác trai", "Chú bác"},
//                new String[]{"That is my son.", "They are my sons."}, "Noun");
//        Vocabulary aunt = new Vocabulary("Aunt", "Aunts", new String[]{"Cô/dì/bác gái", "Cô dì"},
//                new String[]{"That is my son.", "They are my sons."}, "Noun");
//        Vocabulary grandmother = new Vocabulary("Grandmother", "Grandmothers", new String[]{"Bà ngoại", "Bà ngoại"},
//                new String[]{"My grandmother is 80.", "They are out grandmothers."}, "Noun");

        Vocabulary work = new Vocabulary(
                new String[]{"Work", "Walk"},
                new String[]{"Works", "Walks"},
                new String[]{"Công việc/Việc làm", "Đi bộ/Đi dạo"},
                new String[]{"I am working.", "He works a lot to day.","I am woking"},
                new String[]{"I am walking.", "He walks with his dog.", "She is walking"},
                new String[]{"/wɝːk/", "/wɑːk/"},
                new String[]{"Verb", "Verb"},
                "AX0QM6xRCT4", false);

        Vocabulary can = new Vocabulary(
                new String[]{"Can", "Can't"},
                new String[]{"Could", "Could't"},
                new String[]{"Co the/Co kha nang", "Khong the"},
                new String[]{"I can do it.", "Can you help me?.", "I can do it"},
                new String[]{"I can't do it.", "She can't help you.", "You can't do"},
                new String[]{"/kæn/", "/kænt/"},
                new String[]{"Verb", "Verb"},
                "agC7_1ZxJGI", false);


        Vocabulary heart =
                new Vocabulary(
                        new String[]{"heart", "hurt"},
                        new String[]{"hearts", "hurts"},
                        new String[]{"Trái tim", "Đau"},
                        new String[]{"His heart is not healthy.", "He's got a bad heart.", "The sweet heart"},
                        new String[]{"He hurts my head.", "Tell me where it hurts.", "It hurts me"},
                        new String[]{"/hɑːt/", "/hɜːt/"},
                        new String[]{"Noun", "Verb"},"kKpwxMFIE4U", false);

        Vocabulary travel =
                new Vocabulary(
                        new String[]{"travel", "trouble"},
                        new String[]{"travels", "troubles"},
                        new String[]{"du lich", "rac roi"},
                        new String[]{"I am going to travel.", "He travelled over 1,000 miles to be at the wedding.", "Traveling"},
                        new String[]{"I'll be in big trouble.", "I am going to in the trouble", "The small trouble"},
                        new String[]{"/ˈtræv.əl/", "/ˈtrʌb.əl/"},
                        new String[]{"Verb", "Noun"}, "IiaOn7SrU1A", false);

        Vocabulary year =
                new Vocabulary(
                        new String[]{"year", "ear"},
                        new String[]{"years", "ears"},
                        new String[]{"Năm", "Tai"},
                        new String[]{"Happy new year!", "We look forward to greater success in the coming year.", "Next year"},
                        new String[]{"Maybe She's deaf ears.", "She dabbed a little perfume behind her ears.", "Left ear"},
                        new String[]{"/jɪər/", "/ɪər/"},
                        new String[]{"Nound", "Noun"}, "DDcFqenCmfs", false);

        Vocabulary want =
                new Vocabulary(
                        new String[]{"Want", "Won't"},
                        new String[]{"Wants", "wouldn't"},
                        new String[]{"Muốn", "Sẽ Không"},
                        new String[]{"She want to let me go.", "They want to go to cinema.","She wants it."},
                        new String[]{"she won't let me go.", "she won't visit me again.", "He won't play."},
                        new String[]{"/wɒnt/", "/wəʊnt/"},
                        new String[]{"Verb", "Verb"}, "HrXkrkUZqr0", false);


        Vocabulary but =
                new Vocabulary(
                        new String[]{"But", "Bat"},
                        new String[]{"But ", "Bats"},
                        new String[]{"Nhưng mà", "Con dơi, cây gậy"},
                        new String[]{"But, you must go.","The play's good, but not that good.","But why"},
                        new String[]{ "The bats is gone.","I accidentally bashed him with my bat.","I like the bat"},
                        new String[]{"/bʌt/", "/bæt/"},
                        new String[]{"Conjunction ", "Noun"}, "x-2D5YNpkz8", true);

        Vocabulary fast =
                new Vocabulary(
                        new String[]{"Fast", "First"},
                        new String[]{"Fast", "First"},
                        new String[]{"Nhanh chóng", "Đầu tiên"},
                        new String[]{"Today is the faster time.","It's pretty fast and furious.","A fast swimmer"},
                        new String[]{"Today is the first time.","It is the first time I saw him.","The first time"},
                        new String[]{"/fɑːst/", "/ˈfɜːst/"},
                        new String[]{"Adjective", "Determiner"}, "AXeco09YTlg", true);

        Vocabulary bought =
                new Vocabulary(
                        new String[]{"Bought", "Boat"},
                        new String[]{"Bought", "Boats"},
                        new String[]{"Mua", "Con thuyền"},
                        new String[]{"I have bought you a toy.","He bought apples carrots and cereal.","She bought it"},
                        new String[]{"I have bought you a boat?","The boat was swept out to sea by the tide.","A fishing boat"},
                        new String[]{"/bɔːt/","/bəʊt/"},
                        new String[]{"Verb", "Noun"},"9rusSFuG8B8", true);

        Vocabulary base =
                new Vocabulary(
                        new String[]{"Base", "Vase"},
                        new String[]{"Base", "Vase"},
                        new String[]{"Cơ sở, căn cứ", "Bình hoa"},
                        new String[]{"He has the heavy base.","It bases on the good book.","Base on it"},
                        new String[]{"He brokes the big vase.","The vase landed on the floor with a crash.","She bought the vase"},
                        new String[]{"/beɪs/","/vɑːz/"},
                        new String[]{"Noun", "Noun"},"AnezumzC-BY", true);

        Vocabulary all =
                new Vocabulary(
                        new String[]{"All", "Old"},
                        new String[]{"All", "Old"},
                        new String[]{"Tất cả", "Già, tuổi tác"},
                        new String[]{"All man have to go .","All the eggs got broken.","That's all"},
                        new String[]{"The old man has to go.","I have the old house.","The old car"},
                        new String[]{"/ɔːl/","/əʊld/"},
                        new String[]{"Determiner", "Adjective"},"aGXkBD5HeaU", true);

        ArrayList<Vocabulary> vocabularyList = new ArrayList<Vocabulary>();

        vocabularyList.add(all);
        vocabularyList.add(base);
        vocabularyList.add(but);
        vocabularyList.add(bought);
        vocabularyList.add(can);
        vocabularyList.add(heart);
        vocabularyList.add(fast);
        vocabularyList.add(travel);
        vocabularyList.add(want);
        vocabularyList.add(work);
        vocabularyList.add(year);

        // final ListView listView = (ListView) findViewById(R.id.imageView_flag);


        //   int position = Integer.parseInt(arrayVoca);
        //arrayAdapter = new ArrayAdapter(this, R.layout.list_item_vocabulary, R.id.vocabulary_list_textview, family);
        listView.setAdapter(new AdapterVocabulary(this, vocabularyList));
        //} else {
        Toast.makeText(this, "There is no compare!", Toast.LENGTH_SHORT).show();
        //  }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Vocabulary vocabulary = (Vocabulary) listView.getItemAtPosition(position);
                Intent intent = new Intent(Vocabularies.this, Compare.class);
                intent.putExtra("Vocabulary", vocabulary);
                startActivity(intent);
            }
        });

    }

}

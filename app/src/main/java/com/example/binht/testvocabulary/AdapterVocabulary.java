package com.example.binht.testvocabulary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by binht on 5/13/2017.
 */

public class AdapterVocabulary  extends BaseAdapter {
    private List<Vocabulary> listData;
    private LayoutInflater layoutInflater;
    private Context context;


    public AdapterVocabulary(Context aContext,  List<Vocabulary> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_vocabulary, null);
            holder= new ViewHolder();
            holder.forecast_icon = (ImageView) convertView.findViewById(R.id.vocabulary_image);
            holder.word_textview1 = (TextView) convertView.findViewById(R.id.vocabulary_list_textview1);
            holder.word_textview2 = (TextView) convertView.findViewById(R.id.vocabulary_list_textview2);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Vocabulary vocabulary = this.listData.get(position);


        holder.word_textview1.setText(vocabulary.getWord()[0]);
        holder.word_textview2.setText(vocabulary.getWord()[1]);
        holder.forecast_icon.setImageResource(R.drawable.ic_logo);


        return convertView;
    }

    // Tìm ID của Image ứng với tên của ảnh (Trong thư mục mipmap).
    public int getMipmapResIdByName(String resName)  {
        String pkgName = context.getPackageName();

        // Trả về 0 nếu không tìm thấy.
        int resID = context.getResources().getIdentifier(resName , "mipmap", pkgName);
        Log.i("CustomListView", "Res Name: " + resName + "==> Res ID = " + resID);
        return resID;
    }

    static class ViewHolder {
        ImageView forecast_icon;
        TextView word_textview1;
        TextView word_textview2;
    }
    class LoadHinh extends AsyncTask<String,Integer,Bitmap> {
        protected Bitmap doInBackground(String... params){
            Bitmap b=null;
            try {
                URL u=new URL(params[0]);
                b= BitmapFactory.decodeStream(u.openConnection().getInputStream());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return b;
        }
        protected void onProgressUpdate(Integer...a){

        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            //holder.forecast_icon.setImageBitmap(bitmap);

        }

    }

}
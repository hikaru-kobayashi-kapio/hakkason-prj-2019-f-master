package com.example.hak;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

//投稿画面
public class InputActivity extends AppCompatActivity{

    public static final String EXTRA_MESSAGE= "YourPackageName.MESSAGE";
    private TextView textView;
    static final int RESULT_SUBACTIVITY = 1000;
    // DBヘルパー
    private databaseHelper mDbHelper = null;

    // SQlite オブジェクト
    private SQLiteDatabase mSqLiteDb = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_input);            //デザイン部のファイル指定

        findViewById(R.id.Return).setOnClickListener(new View.OnClickListener() {  //idがMapのボタンをクリックした際の処理
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), TopMapsActivity.class);   //遷移先指定
                startActivity(intent);
            }
        });

        findViewById(R.id.Map).setOnClickListener(new View.OnClickListener() {  //idがMapのボタンをクリックした際の処理
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), TopMapsActivity.class);   //遷移先指定
                startActivity(intent);
            }
        });

        //端末のギャラリー参照
        findViewById(R.id.Gallery).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("*/*");
                startActivity(intent);
            }
        });

        //端末のカメラ起動
        findViewById(R.id.Camera).setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intent);
            }
        });

         findViewById(R.id.Submit).setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), MenuActivity.class);

                //DB登録など
                writeRecord();

                startActivity(intent);

            }
        });

        //データベースヘルパーのインスタンスを作成する
        mDbHelper = new databaseHelper(this);

        //データベースオブジェクトを取得する
        mSqLiteDb = mDbHelper.getWritableDatabase();

    }


    /**
     *レコード書き込み
     */
    private void writeRecord(){

        mSqLiteDb.beginTransaction();
        Button b = findViewById(R.id.Submit);
        b.setEnabled(false);

        try {

            //EditText et = findViewById(R.id.etItemDataDate);

//           String strDate = et.getText().toString();
//           SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
//           Date date = dateFormat.parse(strDate);

            Calendar c = Calendar.getInstance();
            //c.setTime(date);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            ContentValues cv = new ContentValues();
            Random rnd = new Random();
            for (int Cnt = 0; Cnt < 144; Cnt++) {
                cv.clear();
                cv.put("KCAL", (int) (rnd.nextInt(80) + 121));
                cv.put("DT10MIN", c.getTime().getTime());
                if (mSqLiteDb.insert(databaseHelper.TABLE_NAME, null, cv) == -1) {
                    //return;
                }
                c.add(Calendar.MINUTE, 10);

            }
            mSqLiteDb.setTransactionSuccessful();

        }
        catch (Exception ex){

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT);

        }
        finally {
            mSqLiteDb.endTransaction();
            b.setEnabled(true);
        }
    }
}
package com.example.hak;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class SqlTestActivity extends AppCompatActivity {
    private databaseHelper dbHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqltest);

        this.initDBCreateTable();
    }

    /**
     * <pre>
     *   DBテーブル初期作成処理
     * </pre>
     * 　DBのテーブルが作成されていない場合、作成します。
     * */
    private void initDBCreateTable(){
        // DB初期設定
        if(dbHelper == null){
            //データベースヘルパーのインスタンスを作成する
            dbHelper = new databaseHelper(this);
        }
        // データベースオブジェクトを取得する
        SQLiteDatabase sqLiteDb = dbHelper.getWritableDatabase();
        try {
            // テーブルクリエイトを実行（テーブルが存在しなければクリエイトが実行される）
            dbHelper.onCreate(sqLiteDb);
        } finally {
            // DBクローズ
            sqLiteDb.close();
        }
    }
}
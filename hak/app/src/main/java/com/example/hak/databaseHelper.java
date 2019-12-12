package com.example.hak;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class databaseHelper extends SQLiteOpenHelper {

    // データーベースのバージョン
    private static final int DATABASE_VERSION = 1;

    // データーベース名
    private static final String DATABASE_NAME = "TestDB.db";
    // テーブル名・カラム名
    public static final String TABLE_NAME = "testdb";

    public static final String TABLE_NAME1 = "TOKO";
    public static final String TABLE1_COLUMN_1 = "TOKO_ID";
    public static final String TABLE1_COLUMN_2 = "TITLE";
    public static final String TABLE1_COLUMN_3 = "ICHI_JOHO";
    public static final String TABLE1_COLUMN_4 = "IDO";
    public static final String TABLE1_COLUMN_5 = "KEIDO";
    public static final String TABLE1_COLUMN_6 = "TOKO_DT";
    public static final String TABLE_NAME2 = "TOKO_COMMENT";
    public static final String TABLE2_COLUMN_1 = "TOKO_ID";
    public static final String TABLE2_COLUMN_2 = "COMENT_SN";
    public static final String TABLE2_COLUMN_3 = "COMENT";
    public static final String TABLE_NAME3 = "TOKO_TAG_MEISAI";
    public static final String TABLE3_COLUMN_1 = "TOKO_ID";
    public static final String TABLE3_COLUMN_2 = "TAG_SN";
    public static final String TABLE3_COLUMN_3 = "TAG";
    public static final String TABLE_NAME4 = "TOKO_GAZO_MEISAI";
    public static final String TABLE4_COLUMN_1 = "TOKO_ID";
    public static final String TABLE4_COLUMN_2 = "GAZO_SN";
    public static final String TABLE4_COLUMN_3 = "FILE_NAME";
    public static final String TABLE4_COLUMN_4 = "GAZO_PATH";
    public static final String TABLE_NAME5 = "TAG_KANRI";
    public static final String TABLE5_COLUMN_1 = "TAG";
    public static final String TABLE5_COLUMN_2 = "TUSE_COUNT";
    private static final String _ID = "_id";
    private static final String COLUMN_NAME_TITLE = "company";
    private static final String COLUMN_NAME_SUBTITLE = "stockprice";
    private static final String KEY_NAME = "image_name";
    private static final String KEY_IMAGE = "image_data";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                    _ID + " INTEGER PRIMARY KEY," +
                    COLUMN_NAME_TITLE + " TEXT," +
                    KEY_NAME + " TEXT," +
                    KEY_IMAGE + " BLOB, " +
                    COLUMN_NAME_SUBTITLE + " INTEGER)";

    //各テーブル作成メソッド
    /** 投稿テーブル作成 */
    private static final String SQL_CREATE_ENTRIES_TOKO =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME1 + " (" +
                    TABLE1_COLUMN_1 + " TEXT PRIMARY KEY," +
                    TABLE1_COLUMN_2 + " TEXT," +
                    TABLE1_COLUMN_3 + " TEXT," +
                    TABLE1_COLUMN_4 + " REAL, " +
                    TABLE1_COLUMN_5 + " REAL," +
                    TABLE1_COLUMN_6 + "TIMESTAMP DEFAULT (datetime(CURRENT_TIMESTAMP,'localtime'))" +
                    ")";

    /** 投稿コメント明細テーブル作成 */
    private static final String SQL_CREATE_ENTRIES_TOKO_COMMENT =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME2 + " (" +
                    TABLE2_COLUMN_1 + " TEXT," +
                    TABLE2_COLUMN_2 + " INTEGER," +
                    TABLE2_COLUMN_3 + " TEXT," +
                    "PRIMARY KEY(" +
                    TABLE2_COLUMN_1 + "," +
                    TABLE2_COLUMN_2 + ")" +
                    ")";

    /** 投稿タグ明細テーブル作成 */
    private static final String SQL_CREATE_ENTRIES_TOKO_TAG_MEISAI =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME3 + " (" +
                    TABLE3_COLUMN_1 + " TEXT ," +
                    TABLE3_COLUMN_2 + " INTEGER, " +
                    TABLE3_COLUMN_3 + " TEXT, " +
                    "PRIMARY KEY(" +
                    TABLE3_COLUMN_1 + "," +
                    TABLE3_COLUMN_2 + ")" +
                    ")";

    /** 投稿画像明細テーブル作成 */
    private static final String SQL_CREATE_ENTRIES_TOKO_GAZO_MEISAI =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME4 + " (" +
                    TABLE4_COLUMN_1 + " TEXT," +
                    TABLE4_COLUMN_2 + " INTEGER," +
                    TABLE4_COLUMN_3 + " TEXT," +
                    TABLE4_COLUMN_4 + " TEXT," +
                    "PRIMARY KEY(" +
                    TABLE4_COLUMN_1 + "," +
                    TABLE4_COLUMN_2 + ")" +
                    ")";

    /** タグ管理テーブル作成 */
    private static final String SQL_CREATE_ENTRIES_TAG_KANRI =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME5 + " (" +
                    TABLE5_COLUMN_1 + " TEXT PRIMARY KEY," +
                    TABLE5_COLUMN_2 + " INTEGER" +
                    ")";


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    // 各テーブル削除メソッド
    /** 投稿テーブル作成 */
    private static final String SQL_DELETE_ENTRIES_TOKO =
            "DROP TABLE IF EXISTS " + TABLE_NAME1;
    /** 投稿コメント明細テーブル作成 */
    private static final String SQL_DELETE_ENTRIES_TOKO_COMMENT =
            "DROP TABLE IF EXISTS " + TABLE_NAME2;
    /** 投稿タグ明細テーブル作成 */
    private static final String SQL_DELETE_ENTRIES_TOKO_TAG_MEISAI =
            "DROP TABLE IF EXISTS " + TABLE_NAME3;
    /** 投稿画像明細テーブル作成 */
    private static final String SQL_DELETE_ENTRIES_TOKO_GAZO_MEISAI =
            "DROP TABLE IF EXISTS " + TABLE_NAME4;
    /** タグ管理テーブル作成 */
    private static final String SQL_DELETE_ENTRIES_TAG_KANRI =
            "DROP TABLE IF EXISTS " + TABLE_NAME5;


    public databaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // テーブル作成
        // SQLiteファイルがなければSQLiteファイルが作成される
        db.execSQL(
                SQL_CREATE_ENTRIES
        );
        Log.d("debug", "onCreate(SQLiteDatabase db)");

        db.execSQL(
                SQL_CREATE_ENTRIES_TOKO
        );
        Log.d("debug", "onCreate(SQLiteDatabase db)");

        db.execSQL(
                SQL_CREATE_ENTRIES_TOKO_COMMENT
        );
        Log.d("debug", "onCreate(SQLiteDatabase db)");

        db.execSQL(
                SQL_CREATE_ENTRIES_TOKO_TAG_MEISAI
        );
        Log.d("debug", "onCreate(SQLiteDatabase db)");

        db.execSQL(
                SQL_CREATE_ENTRIES_TOKO_GAZO_MEISAI
        );
        Log.d("debug", "onCreate(SQLiteDatabase db)");

        db.execSQL(
                SQL_CREATE_ENTRIES_TAG_KANRI
        );
        Log.d("debug", "onCreate(SQLiteDatabase db)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // アップデートの判別
        db.execSQL(
                SQL_DELETE_ENTRIES
        );
        onCreate(db);

        db.execSQL(
                SQL_DELETE_ENTRIES_TOKO
        );
        onCreate(db);

        db.execSQL(
                SQL_DELETE_ENTRIES_TOKO_COMMENT
        );
        onCreate(db);

        db.execSQL(
                SQL_DELETE_ENTRIES_TOKO_TAG_MEISAI
        );
        onCreate(db);

        db.execSQL(
                SQL_DELETE_ENTRIES_TOKO_GAZO_MEISAI
        );
        onCreate(db);

        db.execSQL(
                SQL_DELETE_ENTRIES_TAG_KANRI
        );
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}
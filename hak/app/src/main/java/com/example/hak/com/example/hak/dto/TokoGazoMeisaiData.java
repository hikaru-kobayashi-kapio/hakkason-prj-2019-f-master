package com.example.hak.com.example.hak.dto;

/**
 * <pre>
 * 投稿画像明細データDTO
 * </pre>
 * 投稿データに紐づく、投稿画像の内容を保持するDTO
 * */
public class TokoGazoMeisaiData {
    // 投稿ID
    private String tokoId;
    // タグ明細連番
    private int gazoSn = 0;
    // ファイル名
    private String fileName;
    // 画像パス
    private String gazoPath;
}

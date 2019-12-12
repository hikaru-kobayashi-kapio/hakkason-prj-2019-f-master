package com.example.hak.com.example.hak.dto;

import java.time.LocalDateTime;

/**
 * <pre>
 * 投稿データDTO
 * </pre>
 * DBから取得した投稿データを格納するクラスです。
 * */
public class TokoData {
    // 投稿ID
    private String tokoId;
    // タイトル
    private String title;
    // 位置情報
    private String ichiJoho;
    // 緯度
    private String ido;
    // 経度
    private String keido;
    // 投稿日時
    private LocalDateTime tokoDt;
}

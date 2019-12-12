package com.example.hak.com.example.hak.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * 投稿データDTO
 * </pre>
 * DBから取得した投稿データのすべてを格納するクラスです。
 * */
public class TokoDataAll {
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
    // 投稿コメント明細データ
    private List<TokoCommentMeisaiData> tokoCommentMeisaiDataList = new ArrayList<>();
    // 投稿タグ明細データ
    private List<TokoTagMeisaiData> tokoTagMeisaiDataList = new ArrayList<>();
    // 投稿画像明細データ
    private List<TokoGazoMeisaiData> tokoGazoMeisaiDataList = new ArrayList<>();
}

package com.example.hak.com.example.hak.dto;

/**
 * <pre>
 * 投稿コメント明細データDTO
 * </pre>
 * 投稿データに紐づく、投稿コメント内容を保持するDTO
 * */
public class TokoCommentMeisaiData {
    // 投稿ID
    private String tokoId;
    // コメント明細連番
    private int comentSn = 0;
    // コメント内容
    private String coment;
}

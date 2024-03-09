package com.example.firstproject.dto;

public class ArticleForm {
    private String tite;    //제목 받는 필드
    private String content; //내용 받는 필드

    public ArticleForm(String tite, String content) {
        this.tite = tite;
        this.content = content;
    }

    @Override
    public String toString() {
        return "ArticleForm{" +
                "tite='" + tite + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

}

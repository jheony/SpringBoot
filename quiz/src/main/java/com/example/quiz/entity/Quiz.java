package com.example.quiz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("quiz") // 매핑할 테이블 이름 지정
public class Quiz {
    @Id
    private Integer id; // PostgreSQL의 BIGSERIAL과 매핑

    private String question;
    private Boolean answer;
    private String author;
}

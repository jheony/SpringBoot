package com.example.quiz.service;

import com.example.quiz.entity.Quiz;
import com.example.quiz.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class QuizServiceImpl implements QuizService{
    @Autowired
    QuizRepository repository;
    @Override
    public Iterable<Quiz> selectAll() {
        return repository.findAll();
    }
    @Override
    public Optional<Quiz> selectOneById(Integer id) {
        return repository.findById(id);
    }
    
    @Override
    public Optional<Quiz> selectOneRandomQuiz(){
        //랜덤 id값 가져오기
        Integer randId = repository.getRandomId();
        //퀴즈가 없을 경우
        if (randId == null){
            return Optional.empty();
        }
        return repository.findById(randId);
    }
    @Override
    public Boolean checkQuiz(Integer id, Boolean myAnswer){
        //정/오답 판별 변수
        Boolean check = false;
        //퀴즈 가져오기
        Optional<Quiz> optQuiz = repository.findById(id);
        //가져온 퀴즈 확인
        if (optQuiz.isPresent()){
            Quiz quiz = optQuiz.get();
            //퀴즈 정답 확인
            if (quiz.getAnswer().equals(myAnswer)){
                check = true;
            }
        }
        return check;
    }
    @Override
    public void insertQuiz(Quiz quiz) {
        repository.save(quiz);
    }
    @Override
    public void updateQuiz(Quiz quiz) {
        repository.save(quiz);
    }
    @Override
    public void deleteQuizById(Integer id) {
        repository.deleteById(id);
    }
    
}

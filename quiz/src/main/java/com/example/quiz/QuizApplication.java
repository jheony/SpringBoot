package com.example.quiz;

import com.example.quiz.entity.Quiz;
import com.example.quiz.repository.QuizRepository;
import com.example.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class QuizApplication implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(QuizApplication.class, args);
	}
	@Autowired
	QuizService service;

	@Override
	public void run(String... args) throws Exception {
//		setup();
//		showList();
//		showOne();
//		updateQuiz();
//		deleteQuiz();
		doQuiz();
	}

	private void setup() {
		Quiz quiz1 = new Quiz(null, "붕어빵과 잉어빵은 똑같다", false, "등록");
		Quiz quiz2 = new Quiz(null, "원두마다 맛이 다르다", true, "등록");
		Quiz quiz3 = new Quiz(null, "2024 기후는 이상하다", true, "등록");
		Quiz quiz4 = new Quiz(null, "20은 22다", false, "등록");
		Quiz quiz5 = new Quiz(null, "겨울에는 대체로 건조하다", true, "등록");
		List<Quiz> quizList = new ArrayList<>();
		Collections.addAll(quizList, quiz1, quiz2, quiz3, quiz4, quiz5);
		for(Quiz quiz:quizList){
			service.insertQuiz(quiz);
		}
		System.out.println("Saved Quiz: " + quiz1);
	}
	private void showList(){
		System.out.println("---모든 데이터 취득 개시---");
		Iterable<Quiz> quizzes = service.selectAll();
		for (Quiz quiz : quizzes) {
			System.out.println(quiz);
		}
		System.out.println("---취득 종료---");
	}
	private void showOne(){
		System.out.println("---1건 취득 개시---");
		Optional<Quiz> quizOpt = service.selectOneById(5);
		if (quizOpt.isPresent()) {
			System.out.println(quizOpt.get());
		}else {
			System.out.println("해당 데이터는 존재하지 않습니다.");
		}
		System.out.println("---1건 취득 완료---");
	}
	private void updateQuiz(){
		System.out.println("---변경 처리 개시---");
		Quiz quiz = new Quiz(5, "스프링은 프레임워크?", true, "변경 담당");
		service.updateQuiz(quiz);
		System.out.println("변경된 데이터는"+quiz);
		System.out.println("---변경 처리 완료---");
	}
	private void deleteQuiz(){
		System.out.println("---삭제 처리 개시---");
		service.deleteQuizById(1);
		System.out.println("---삭제 처리 완료---");
	}
	private void doQuiz(){
		System.out.println("---퀴즈 1건 취득 개시---");
		Optional<Quiz> quizOpt = service.selectOneRandomQuiz();
		if(quizOpt.isPresent()){
			System.out.println(quizOpt.get());
		}else{
			System.out.println("해당 데이터는 존재하지 않습니다.");
		}
		System.out.println("---퀴즈 1건 취득 완료---");
		Boolean myAnswer = false;
		Integer id = quizOpt.get().getId();
		if(service.checkQuiz(id, myAnswer)){
			System.out.println("정답입니다~");
		}else{
			System.out.println("오답입니다ㅠ");
		}
	}
}

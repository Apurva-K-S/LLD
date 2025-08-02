package com.example.StackOverflow_advanced.Service;

import com.example.StackOverflow_advanced.Entity.Answer;
import com.example.StackOverflow_advanced.Entity.Question;
import com.example.StackOverflow_advanced.Entity.User;

import java.sql.Time;
import java.time.LocalTime;
import java.util.*;

public class QuestionService {
    Map<String, Question> questions;
    UserService userService;
    public QuestionService(UserService userService1) {
        questions = new HashMap<>();
        userService = userService1;
    }

    public Question addQuestion(String title, String text, String postedBy)
    {
        User user = userService.getByEmail(postedBy);
        if(user==null)
        {
            System.out.println("User cant post a question");
            return null;
        }
        UUID uuid = UUID.randomUUID();
        LocalTime currentTime = LocalTime.now();
        Question question = new Question(uuid.toString(),
                title,
                text,
                new HashSet<>(),
                new HashSet<>(),
                0,
                0,
                user,
                currentTime
        );
        questions.put(uuid.toString(), question);
        return question;
    }

    public Question getQuestionById(String id){
        if(questions.containsKey(id))
            return questions.get(id);
        else {
            System.out.println("question doesnt exist with this id");
            return null;
        }
    }

    public void seeAllQuestions() {
        List<Question> result = new ArrayList<>(questions.values());
        for(Question q: result){
            System.out.println(q);
        }
    }
}

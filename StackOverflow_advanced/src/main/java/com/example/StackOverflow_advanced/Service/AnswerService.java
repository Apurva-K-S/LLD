package com.example.StackOverflow_advanced.Service;

import com.example.StackOverflow_advanced.Entity.Answer;
import com.example.StackOverflow_advanced.Entity.Question;
import com.example.StackOverflow_advanced.Entity.User;

import java.time.LocalTime;
import java.util.*;

public class AnswerService {
    Map<String, Answer> answers;
    UserService userService;
    public AnswerService(UserService userService1) {
        answers = new HashMap<>();
        userService = userService1;
    }

    public Answer addAnswer(String title, String text, String postedBy, String questionId)
    {
        User user = userService.getByEmail(postedBy);
        if(user==null)
        {
            System.out.println("User cant post a question");
            return null;
        }
        UUID uuid = UUID.randomUUID();
        LocalTime currentTime = LocalTime.now();

        Answer answer = new Answer(uuid.toString(),
                questionId,
                title,
                text,
                new HashSet<>(),
                new HashSet<>(),
                0,
                0,
                user,
                currentTime
        );
        answers.put(uuid.toString(), answer);
        return answer;
    }

    public Answer getAnswerById(String id){
        if(answers.containsKey(id))
            return answers.get(id);
        else {
            System.out.println("question doesnt exist with this id");
            return null;
        }
    }

    public List<Answer> getAnswerByQuestionId(String questionId){
        List<Answer> result = new ArrayList<>();
        for(Answer ans: answers.values())
        {
            if(ans.getQuestionId().equals(questionId))
                result.add(ans);
        }
        return result;
    }

    public void seeAllAnswers() {
        List<Answer> result = new ArrayList<>(answers.values());
        for(Answer a: result)
        {
            System.out.println(a);
        }
    }
}

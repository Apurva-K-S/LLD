package com.example.StackOverflow_advanced.Service;

import com.example.StackOverflow_advanced.Entity.Answer;
import com.example.StackOverflow_advanced.Entity.Question;
import jdk.jfr.Frequency;

import java.util.*;

public class SearchService {
    Map<String, Set<String>> searchBase;
    QuestionService questionService;
    AnswerService answerService;

    public SearchService(QuestionService questionService1, AnswerService answerService1) {
        searchBase = new HashMap<>();
        questionService = questionService1;
        answerService = answerService1;
    }

    public void addToSearchBase(String text, String id)
    {
        String []tokens = text.split(" ");
        for (String token : tokens) {
            if (searchBase.containsKey(token)) {
                searchBase.get(token).add(id);
            } else {
                Set<String> temp = new HashSet<>();
                temp.add(id);
                searchBase.put(token, temp);
            }
        }
    }

    public void searchResults(String text)
    {
        if(!searchBase.containsKey(text))
        {
            System.out.println("No results for this text");
        }
        Set<String> ids = searchBase.get(text);
        List<Object> results=new ArrayList<>();
        for(String id: ids)
        {
            Question question = questionService.getQuestionById(id);
            Answer answer = answerService.getAnswerById(id);
            if(question!=null)
                results.add(question);
            if(answer!=null)
                results.add(answer);
        }

        for(Object o : results){
            System.out.println(o);
        }
    }
}

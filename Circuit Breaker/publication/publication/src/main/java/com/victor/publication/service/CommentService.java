package com.victor.publication.service;


import com.victor.publication.client.CommentClient;
import com.victor.publication.domain.Comment;
import com.victor.publication.domain.Publication;
import com.victor.publication.exceptions.FallbackException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CommentService {

    @Autowired
    private CommentClient commentClient;

    @Autowired
    private RedisService redisService;

    @CircuitBreaker(name= "comments", fallbackMethod = "getCommentsFallback")
    public List<Comment> getComments(String id){

        var comments = commentClient.getComments(id);

        redisService.save(comments, id);

        return comments;
    }


    private List<Comment> getCommentsFallback(String id, Throwable cause){

        log.warn("[WARN] fallback with id {}", id);

        return redisService.findById(id);
    }
}

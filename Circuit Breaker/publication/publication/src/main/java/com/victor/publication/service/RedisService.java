package com.victor.publication.service;


import com.victor.publication.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RedisService {

    private static final String KEY = "Comment";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public List<Comment> save(List<Comment> comments, String id){

        redisTemplate.opsForHash().put(KEY, id, comments);

        return comments;
    }

    public List<Comment> findById(String id){
        return (List<Comment>)redisTemplate.opsForHash().get(KEY, id);
    }
}

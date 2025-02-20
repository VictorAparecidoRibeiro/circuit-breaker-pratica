package com.victor.publication.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Publication {

    private String id;

    private String title;
    private String imageUrl;
    private String text;
    private List<Comment> comments;
}

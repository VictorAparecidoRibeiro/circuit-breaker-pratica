package com.victor.publication.service;

import com.victor.publication.domain.Publication;
import com.victor.publication.mapper.PublicationMapper;
import com.victor.publication.repository.PublicationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.commons.config.DefaultsBindHandlerAdvisor;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PublicationService {

    @Autowired
    private PublicationRepository publicationRepository;

    @Autowired
    private PublicationMapper publicationMapper;

    @Autowired
    private DefaultsBindHandlerAdvisor.MappingsProvider mappingsProvider;

    @Autowired
    private CommentService commentService;

    public void insert(Publication publication){

        var publicationEntity = publicationMapper.toPublicationEntity(publication);

        publicationRepository.save(publicationEntity);
    }

    public List<Publication> findAll(){
        var publications = publicationRepository.findAll();
        return publications.stream().map(publicationMapper::toPublication).toList();
    }

    public Publication findById(String id){
        var publication = publicationRepository.findById(id).map(publicationMapper::toPublication).orElseThrow(RuntimeException::new);

        var comments = commentService.getComments(id);

        publication.setComments(comments);

        return publication;
    }


}

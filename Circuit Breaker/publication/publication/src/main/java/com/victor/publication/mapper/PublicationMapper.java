package com.victor.publication.mapper;

import com.victor.publication.controller.request.PublicationRequest;
import com.victor.publication.domain.Publication;
import com.victor.publication.repository.entity.PublicationEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PublicationMapper {

    PublicationEntity toPublicationEntity(Publication publication);

    Publication toPublication(PublicationEntity publicationEntity);

    Publication toPublication (PublicationRequest request);

    PublicationRequest toPublicationRequest(Publication publication);
}

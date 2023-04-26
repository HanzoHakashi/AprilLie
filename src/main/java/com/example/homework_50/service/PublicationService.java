package com.example.homework_50.service;

import com.example.homework_50.dao.PublicationDao;
import com.example.homework_50.dto.PublicationDto;
import com.example.homework_50.entity.Publication;
import com.example.homework_50.exeption.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicationService {
    private final PublicationDao publicationDao;

    public PublicationService(PublicationDao publicationDao) {
        this.publicationDao = publicationDao;
    }

    public void addPublication(String image,String description,String email){
        Publication publication = new Publication();
        publication.setImage(image);
        publication.setDescription(description);
        publication.setOwnerEmail(email);
        publication.setPostDate(LocalDate.now());
        publicationDao.save(publication);

    }

    public void deletePublication(Long publicationID,String email){
        publicationDao.deletePublication(publicationID,email);
    }

    public PublicationDto findPostByUser(String email){
        var publication = publicationDao.findPostByUser(email)
                .orElseThrow(() -> new ResourceNotFoundException("Can't find post of this user email: "+ email));
        return PublicationDto.from(publication);
    }
    public List<PublicationDto> findAll(){
        var publicationList = publicationDao.findAll();
        return publicationList.stream().map(PublicationDto::from).collect(Collectors.toList());
    }

    public List<PublicationDto> findPostBySubscriptions(String owner){
        var publication = publicationDao.findPostBySubscriptions(owner);
        return publication.stream().map(PublicationDto::from).collect(Collectors.toList());
    }
}

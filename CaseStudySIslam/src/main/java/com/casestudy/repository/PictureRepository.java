package com.casestudy.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.casestudy.model.Picture;

@Repository
public interface PictureRepository extends CrudRepository<Picture, Long> {
	Picture findPictureByName(String name);
}

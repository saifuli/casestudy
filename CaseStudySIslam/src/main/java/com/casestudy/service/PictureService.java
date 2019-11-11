package com.casestudy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casestudy.dao.PictureDAO;
import com.casestudy.model.Picture;
import com.casestudy.repository.PictureRepository;

@Service("pictureService")
public class PictureService implements PictureDAO {

	@Autowired
	PictureRepository pictureRepository;

	@Override
	public boolean addPicture(Picture picture) {
		pictureRepository.save(picture);
		return findPictureByName(picture.getName()) != null;
	}

	@Override
	public Picture findPictureByName(String name) {
		return pictureRepository.findPictureByName(name);
	}

	@Override
	public boolean removePicture(Picture picture) {
		// TODO Auto-generated method stub
		pictureRepository.delete(picture);
		return findPictureByName(picture.getName()) == null;
	}

}

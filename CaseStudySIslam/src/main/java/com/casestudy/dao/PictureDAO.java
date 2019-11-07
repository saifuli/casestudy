package com.casestudy.dao;

import com.casestudy.model.Picture;

public interface PictureDAO {
    boolean addPicture(Picture picture);

    Picture findPictureByName(String name);

    boolean removePicture(Picture picture);
}

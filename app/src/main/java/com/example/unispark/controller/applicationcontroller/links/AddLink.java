package com.example.unispark.controller.applicationcontroller.links;

import com.example.unispark.bean.BeanLink;
import com.example.unispark.bean.login.BeanLoggedStudent;
import com.example.unispark.database.dao.StudentLinksDAO;
import com.example.unispark.exceptions.DatabaseOperationError;
import com.example.unispark.exceptions.GenericException;
import com.example.unispark.exceptions.LinkAlreadyExists;
import com.example.unispark.model.LinkModel;

public class AddLink {
    //Add Link
    public void addLink(BeanLoggedStudent student, BeanLink link) throws LinkAlreadyExists, GenericException
    {
        LinkModel newLink = new LinkModel(link.getLinkName(), link.getLinkAddress());

        try {
            StudentLinksDAO.addStudentLink(newLink, student.getId());
        } catch (DatabaseOperationError databaseOperationError) {
            databaseOperationError.printStackTrace();
            throw new GenericException("Cannot add link, try again");
        }
    }
}
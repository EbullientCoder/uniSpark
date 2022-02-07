package com.example.common.applicationcontroller.links;

import com.example.common.bean.BeanLink;
import com.example.common.bean.student.BeanLoggedStudent;
import com.example.common.database.dao.StudentLinksDAO;
import com.example.common.exceptions.GenericException;
import com.example.common.exceptions.LinkAlreadyExists;
import com.example.common.model.LinkModel;

import java.sql.SQLException;

public class AddLink {
    //Add Link
    public void addLink(BeanLoggedStudent student, BeanLink link) throws LinkAlreadyExists, GenericException
    {
        LinkModel newLink = new LinkModel(link.getLinkName(), link.getLinkAddress());

        try {
            StudentLinksDAO.addStudentLink(newLink, student.getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new GenericException("Try again");
        }
    }
}
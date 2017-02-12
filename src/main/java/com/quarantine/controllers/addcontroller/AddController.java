package com.quarantine.controllers.addcontroller;

import com.google.appengine.api.users.User;
import com.quarantine.beans.ItemBean;
import com.quarantine.beans.ToDoListBean;
import com.quarantine.services.ServiceManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Karl on 2/8/2017.
 */
@Controller
public class AddController {


    @RequestMapping(value = "additem.htm", method = RequestMethod.GET)
    public void processAddRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        ToDoListBean list = new ToDoListBean();
        list = (ToDoListBean) request.getSession().getAttribute("ACTIVE_LIST");
        String category = request.getParameter("category");
        String description = request.getParameter("description");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String completed = request.getParameter("completed");

        if (category.trim().length() == 0 || description.trim().length() == 0 || startDate.trim().length() == 0
                || endDate.trim().length() == 0 || completed.trim().length() == 0) {
            out.println("FAILURE");
        } else {
            ItemBean newItem = new ItemBean(category, description, startDate, endDate, completed);
            list.addItem(newItem);

            out.println("SUCCESS");
        }
    }
}

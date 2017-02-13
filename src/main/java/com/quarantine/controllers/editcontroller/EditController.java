package com.quarantine.controllers.editcontroller;

import com.quarantine.beans.ItemBean;
import com.quarantine.beans.ToDoListBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by li on 2/12/2017.
 */
@Controller
public class EditController {
    @RequestMapping(value = "edititem.htm", method = RequestMethod.GET)
    public void processEditRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        // Get the list
        ToDoListBean list;
        list = (ToDoListBean) request.getSession().getAttribute("ACTIVE_LIST");
        // Get the array of items
        ArrayList<ItemBean> items;
        items = list.getItems();
        //Get the index
        String indexS;
        indexS = request.getParameter("index");
        int index = Integer.parseInt(indexS);
        // Get the item to edit
        ItemBean item = items.get(index);
        // Get the new information
        String category = request.getParameter("category");
        String description = request.getParameter("description");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String completed = request.getParameter("completed");

        if (category.trim().length() == 0 || description.trim().length() == 0 || startDate.trim().length() == 0
                || endDate.trim().length() == 0 || completed.trim().length() == 0) {
            out.println("FAILURE");
        } else {
            // Set the new information
            item.setCategory(category);
            item.setDescription(description);
            item.setStartDate(startDate);
            item.setEndDate(endDate);
            item.setCompleted(completed);
            // Place the newly edited item back.
            items.set(index,item);

            out.println("SUCCESS");
        }

    }
}
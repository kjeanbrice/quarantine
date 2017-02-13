package com.quarantine.controllers.sortcontroller;


import org.springframework.stereotype.Controller;
import com.quarantine.beans.ItemBean;
import com.quarantine.beans.ToDoListBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by li on 2/10/2017.
 */
@Controller
public class StartDateController {
    @RequestMapping(value = "sortbystart.htm", method = RequestMethod.GET)
    public void sortByStart(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        // Get the list
        ToDoListBean list;
        list = (ToDoListBean) request.getSession().getAttribute("ACTIVE_LIST");
        // Get the array of items
        ArrayList<ItemBean> items;
        items = list.getItems();
        // Check if the list is empty
        if(!items.isEmpty()) {
            //  We will swap between sorts.
            boolean sorted = true;
            for (int i = 1; i < items.size(); i++) {
                if (items.get(i-1).getStartDate().compareTo(items.get(i).getStartDate()) > 0) sorted = false;
            }
            // Sort the Items
            if(!sorted) {
                // Sort ABC
                Collections.sort(items, new Comparator<ItemBean>() {
                    @Override
                    public int compare(ItemBean item1, ItemBean item2) {

                        return item1.getStartDate().compareTo(item2.getStartDate());
                    }
                });
            }else{
                // Sort CBA
                Collections.sort(items, new Comparator<ItemBean>() {
                    @Override
                    public int compare(ItemBean item2, ItemBean item1) {

                        return item1.getStartDate().compareTo(item2.getStartDate());
                    }
                });
            }
            list.setItems(items);
        }
    }

}
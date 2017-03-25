package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results
    @RequestMapping(value = "results", method = RequestMethod.GET)
//method Post
    public String search(@RequestParam String searchKey, @RequestParam String searchTerm, Model model) {
        ArrayList<HashMap<String, String>> results;
        if (searchKey.equals("all")) {
            results = JobData.findByValue(searchTerm);
        } else {
            results = JobData.findByColumnAndValue(searchKey, searchTerm);
            model.addAttribute("selectColumn", searchKey);
        }
        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("results", results);
//                = JobData.
//                findByColumnAndValue("${column.key}", "${column.value}");

//        search the list create instance of job data access find methods
//        for (HashMap<String, String> row : results) {
//            String aValue = row.get(id);
//            if (aValue != null && aValue.toLowerCase().contains(text.toLowerCase())) {
//                results.add(row);
//            }
//         model.addAttribute("search type", "All Jobs");
//         model.addAttribute("${column.value}", results );
    return "search";
    }
}
//    public String listColumnValues(Model model, @RequestParam String column) {

//        if (column.equals("all")) {
//            ArrayList<HashMap<String, String>> jobs = JobData.findAll();
//            model.addAttribute("title", "All Jobs");
//            model.addAttribute("jobs", jobs);
//            return "list-jobs";
//        } else {
//            ArrayList<String> items = JobData.findAll(column);
//            model.addAttribute("title", "All " + columnChoices.get(column) + " Values");
//            model.addAttribute("column", column);
//            model.addAttribute("items", items);
//            return "list-column";
//        }
//
//    }
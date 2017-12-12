package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

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
    public String search(@RequestParam String searchKey, @RequestParam String searchTerm, Model model) {
        List<HashMap<String, String>> results;
        if (searchKey.equals("all")) {
            results = JobData.findByValue(searchTerm);
        } else {
            results = JobData.findByColumnAndValue(searchKey, searchTerm);
            model.addAttribute("selectColumn", searchKey);
        }
        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("results", results);
        model.addAttribute("searchQuery", searchTerm);
    return "search";
    }
}

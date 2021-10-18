package io.project.coronavirustracker.controller;

import io.project.coronavirustracker.model.LocationStats;
import io.project.coronavirustracker.service.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/")
    public String home(Model model) {

//        1 st method for 'stat'
        int totalReportedCases = coronaVirusDataService.getAllStats().stream().mapToInt(LocationStats::getLatestTotalCases).sum();
//        2 nd method for 'LocationStats'
//        int totalReportedCases = coronaVirusDataService.getAllStats().stream().mapToInt(LocationStats::getLatestTotalCases).sum();
        model.addAttribute("locationStats", coronaVirusDataService.getAllStats());
        int totalNewCases = coronaVirusDataService.getAllStats().stream().mapToInt(LocationStats::getDiffFromPreDay).sum();
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalNewCases", totalNewCases);

//        3 rd method use 'List<LocationStats> allStats = coronaVirusDataService.getAllStats()' for easy usage

        return "home";
    }
}

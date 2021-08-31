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
        int totalReportedCases = coronaVirusDataService.getAllstats().stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
//        2 nd method for 'LocationStats'
//        int totalReportedCases = coronaVirusDataService.getAllstats().stream().mapToInt(LocationStats::getLatestTotalCases).sum();
        model.addAttribute("locationStats", coronaVirusDataService.getAllstats());
        int totalNewCases = coronaVirusDataService.getAllstats().stream().mapToInt(LocationStats::getDiffFromPreDay).sum();
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalNewCases", totalNewCases);

//        3 rd method use 'List<LocationStats> allStats = coronaVirusDataService.getAllstats()' for easy usage

        return "home";
    }
}

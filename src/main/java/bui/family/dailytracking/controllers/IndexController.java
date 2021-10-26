package bui.family.dailytracking.controllers;

import bui.family.dailytracking.services.NgayKhamBenhService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IndexController {
    private final NgayKhamBenhService ngayKhamBenhService;

    public IndexController(NgayKhamBenhService ngayKhamBenhService) {
        this.ngayKhamBenhService = ngayKhamBenhService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model){
        log.debug("Getting Index page");
        model.addAttribute("ngayKhamBenhs", ngayKhamBenhService.getNgayKhamBenhs());
        return "index";
    }
}

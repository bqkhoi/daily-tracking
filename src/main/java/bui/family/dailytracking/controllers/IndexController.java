package bui.family.dailytracking.controllers;

import bui.family.dailytracking.commands.PageNgayKhamBenhCommand;
import bui.family.dailytracking.domain.NgayKhamBenh;
import bui.family.dailytracking.services.NgayKhamBenhService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Controller
public class IndexController {
    private final NgayKhamBenhService ngayKhamBenhService;

    public IndexController(NgayKhamBenhService ngayKhamBenhService) {
        this.ngayKhamBenhService = ngayKhamBenhService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model,
                               @RequestParam("page") Optional<Integer> page){
        log.debug("Getting Index page");
        PageNgayKhamBenhCommand pageNgayKhamBenhCommand = ngayKhamBenhService.getThongTinNgayKhamBenhTaiTrang(page);
        model.addAttribute("pageNgayKhamBenhCommand", pageNgayKhamBenhCommand);
        return "index";
    }

    @PostMapping("index/page")
    public String updateThongTinLuotKhamBenhTrongngay(@ModelAttribute PageNgayKhamBenhCommand pageNgayKhamBenhCommand, RedirectAttributes redirectAttributes){
        redirectAttributes.addAttribute("page", pageNgayKhamBenhCommand.getSelectedPage());
        return "redirect:/index";
    }
}

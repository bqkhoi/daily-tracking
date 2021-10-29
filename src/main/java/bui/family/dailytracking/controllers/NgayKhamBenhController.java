package bui.family.dailytracking.controllers;

import bui.family.dailytracking.commands.NgayKhamBenhCommand;
import bui.family.dailytracking.services.NgayKhamBenhService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class NgayKhamBenhController {
    private final NgayKhamBenhService ngayKhamBenhService;

    public NgayKhamBenhController(NgayKhamBenhService ngayKhamBenhService) {
        this.ngayKhamBenhService = ngayKhamBenhService;
    }

    @GetMapping
    @RequestMapping("/ngaykhambenh/{id}/show")
    public String showById(@PathVariable String id, Model model){

        model.addAttribute("ngayKhamBenhCommand", ngayKhamBenhService.findCommandById(Long.valueOf(id)));
        return "ngaykhambenh/ngaykhambenhshow";
    }

    @GetMapping
    @RequestMapping("ngaykhambenh/new")
    public String newNgayKhamBenh(Model model){
        NgayKhamBenhCommand savedCommand = ngayKhamBenhService.createNgayKhamBenh();
        return "redirect:/ngaykhambenh/" + savedCommand.getId() + "/show";
    }

    @PostMapping("ngaykhambenh")
    public String saveOrUpdate(@ModelAttribute NgayKhamBenhCommand command){
        NgayKhamBenhCommand savedCommand = ngayKhamBenhService.saveNgayKhamBenhCommand(command);

        return "redirect:/ngaykhambenh/" + savedCommand.getId() + "/show";
    }
}

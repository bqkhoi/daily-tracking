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

        model.addAttribute("ngayKhamBenh", ngayKhamBenhService.findById(Long.valueOf(id)));
        return "ngaykhambenh/ngaykhambenhshow";
    }

    @GetMapping
    @RequestMapping("ngaykhambenh/new")
    public String newNgayKhamBenh(Model model){
        NgayKhamBenhCommand savedCommand = ngayKhamBenhService.createNgayKhamBenh();
        return "redirect:/ngaykhambenh/" + savedCommand.getId() + "/show";
    }

    @GetMapping
    @RequestMapping("ngaykhambenh/{id}/update")
    public String updateNgayKhamBenh(@PathVariable String id, Model model){
        model.addAttribute("recipe", ngayKhamBenhService.findCommandById(Long.valueOf(id)));
        return "ngaykhambenhview";
    }

    @PostMapping("ngaykhambenh")
    public String saveOrUpdate(@ModelAttribute NgayKhamBenhCommand command){
        NgayKhamBenhCommand savedCommand = ngayKhamBenhService.saveNgayKhamBenhCommand(command);

        return "redirect:/ngaykhambenh/" + savedCommand.getId() + "/show";
    }

    @GetMapping
    @RequestMapping("ngaykhambenh/{id}/delete")
    public String deleteById(@PathVariable String id){

        log.debug("Deleting id: " + id);

        ngayKhamBenhService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }
}

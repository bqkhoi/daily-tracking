package bui.family.dailytracking.controllers;

import bui.family.dailytracking.commands.NgayKhamBenhCommand;
import bui.family.dailytracking.commands.ThongTinLuotKhamBenhTrongNgayCommand;
import bui.family.dailytracking.services.ThongTinLuotKhamBenhTrongNgayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class ThongTinLuotKhamBenhTrongNgayController {

    private final ThongTinLuotKhamBenhTrongNgayService thongTinLuotKhamBenhTrongNgayService;

    public ThongTinLuotKhamBenhTrongNgayController(ThongTinLuotKhamBenhTrongNgayService thongTinLuotKhamBenhTrongNgayService) {
        this.thongTinLuotKhamBenhTrongNgayService = thongTinLuotKhamBenhTrongNgayService;
    }


    @GetMapping
    @RequestMapping("/ngaykhambenh/{ngayKhamBenhId}/thongtinluotkhambenhtrongngay/{id}/show")
    public String showById(@PathVariable String ngayKhamBenhId,
                           @PathVariable String id,
                           Model model){
        model.addAttribute("ngayKhamBenh", thongTinLuotKhamBenhTrongNgayService.findByNgayKhamBenhIdAndThongTinLuotKhamBenhTrongNgayId(Long.valueOf(ngayKhamBenhId), Long.valueOf(id)));
        return "ngaykhambenh/ngaykhambenhshow";
    }

    @GetMapping
    @RequestMapping("ngaykhambenh/thongtinluotkhambenhtrongngay/new")
    public String newNgayKhamBenh(Model model){
        model.addAttribute("thongtinluotkhambenhtrongngay", new ThongTinLuotKhamBenhTrongNgayCommand());
        return "ngaykhambenh/thongtinluotkhambenhtrongngay/thongtinluotkhambenhtrongngayform";
    }
}

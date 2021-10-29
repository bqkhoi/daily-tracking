package bui.family.dailytracking.controllers;

import bui.family.dailytracking.commands.ThongTinLuotKhamBenhTrongNgayCommand;
import bui.family.dailytracking.domain.NgayKhamBenh;
import bui.family.dailytracking.services.NgayKhamBenhService;
import bui.family.dailytracking.services.ThongTinLuotKhamBenhTrongNgayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class ThongTinLuotKhamBenhTrongNgayController {

    private final ThongTinLuotKhamBenhTrongNgayService thongTinLuotKhamBenhTrongNgayService;
    private final NgayKhamBenhService ngayKhamBenhService;

    public ThongTinLuotKhamBenhTrongNgayController(ThongTinLuotKhamBenhTrongNgayService thongTinLuotKhamBenhTrongNgayService, NgayKhamBenhService ngayKhamBenhService) {
        this.thongTinLuotKhamBenhTrongNgayService = thongTinLuotKhamBenhTrongNgayService;
        this.ngayKhamBenhService = ngayKhamBenhService;
    }


    @GetMapping
    @RequestMapping("/ngaykhambenh/{ngayKhamBenhId}/thongtinluotkhambenhtrongngay/{id}/update")
    public String showById(@PathVariable String ngayKhamBenhId,
                           @PathVariable String id,
                           Model model){
        model.addAttribute("thongtinluotkhambenhtrongngaycommand", thongTinLuotKhamBenhTrongNgayService.findByNgayKhamBenhIdAndThongTinLuotKhamBenhTrongNgayId(Long.valueOf(ngayKhamBenhId), Long.valueOf(id)));
        return "ngaykhambenh/thongtinngaykhambenh/thongtinluotkhambenhtrongngayform";
    }

    @GetMapping
    @RequestMapping("ngaykhambenh/{ngayKhamBenhId}/thongtinluotkhambenhtrongngay/new")
    public String createThongTinLuotKhamBenhTrongNgay(@PathVariable String ngayKhamBenhId, Model model){
        ThongTinLuotKhamBenhTrongNgayCommand thongTinLuotKhamBenhTrongNgayCommand =
                thongTinLuotKhamBenhTrongNgayService.createThongTinLuotKhamBenhTrongNgay(Long.valueOf(ngayKhamBenhId));
        model.addAttribute("thongtinluotkhambenhtrongngaycommand", thongTinLuotKhamBenhTrongNgayCommand);
        return "ngaykhambenh/thongtinngaykhambenh/thongtinluotkhambenhtrongngayform";
    }

    @PostMapping("ngaykhambenh/thongtinngaykhambenh/luotkham")
    public String updateThongTinLuotKhamBenhTrongngay(@ModelAttribute ThongTinLuotKhamBenhTrongNgayCommand command){
        NgayKhamBenh savedNgayKhamBenh = thongTinLuotKhamBenhTrongNgayService.saveThongTinLuotKhamBenhTrongNgayCommand(command);
        if(savedNgayKhamBenh != null){
            savedNgayKhamBenh.tinhTongSieuAmTrongNgay();
            savedNgayKhamBenh.tinhTongXetNghiemTrongNgay();
            ngayKhamBenhService.saveNgayKhamBenh(savedNgayKhamBenh);
        }
        return "redirect:/ngaykhambenh/" + savedNgayKhamBenh.getId() + "/show";
    }
}

package bui.family.dailytracking.services;

import bui.family.dailytracking.commands.ThongTinLuotKhamBenhTrongNgayCommand;
import bui.family.dailytracking.domain.ThongTinLuotKhamBenhTrongNgay;

import java.util.Set;

public interface ThongTinLuotKhamBenhTrongNgayService {
    ThongTinLuotKhamBenhTrongNgayCommand findByNgayKhamBenhIdAndThongTinLuotKhamBenhTrongNgayId(Long ngayKhamBenhId, Long thongTinLuotKhamBenhTrongNgayId);

    ThongTinLuotKhamBenhTrongNgayCommand saveThongTinLuotKhamBenhTrongNgayCommand(ThongTinLuotKhamBenhTrongNgayCommand command);

    void deleteById(Long idToDelete);
}

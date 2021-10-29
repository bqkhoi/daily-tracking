package bui.family.dailytracking.services;

import bui.family.dailytracking.commands.ThongTinLuotKhamBenhTrongNgayCommand;
import bui.family.dailytracking.domain.NgayKhamBenh;

public interface ThongTinLuotKhamBenhTrongNgayService {
    ThongTinLuotKhamBenhTrongNgayCommand findByNgayKhamBenhIdAndThongTinLuotKhamBenhTrongNgayId(Long ngayKhamBenhId, Long thongTinLuotKhamBenhTrongNgayId);

    NgayKhamBenh saveThongTinLuotKhamBenhTrongNgayCommand(ThongTinLuotKhamBenhTrongNgayCommand command);

    ThongTinLuotKhamBenhTrongNgayCommand createThongTinLuotKhamBenhTrongNgay(long ngayKhamBenhId);

    void deleteById(Long idToDelete);
}

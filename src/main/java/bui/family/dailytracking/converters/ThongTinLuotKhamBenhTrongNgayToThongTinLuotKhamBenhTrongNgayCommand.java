package bui.family.dailytracking.converters;

import bui.family.dailytracking.commands.ThongTinLuotKhamBenhTrongNgayCommand;
import bui.family.dailytracking.domain.ThongTinLuotKhamBenhTrongNgay;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class ThongTinLuotKhamBenhTrongNgayToThongTinLuotKhamBenhTrongNgayCommand implements Converter<ThongTinLuotKhamBenhTrongNgay, ThongTinLuotKhamBenhTrongNgayCommand> {
    @Synchronized
    @Nullable
    @Override
    public ThongTinLuotKhamBenhTrongNgayCommand convert(ThongTinLuotKhamBenhTrongNgay thongTinLuotKhamBenhTrongNgay) {
        if (thongTinLuotKhamBenhTrongNgay == null){
            return null;
        }
        ThongTinLuotKhamBenhTrongNgayCommand thongTinLuotKhamBenhTrongNgayCommand = new ThongTinLuotKhamBenhTrongNgayCommand();

        thongTinLuotKhamBenhTrongNgayCommand.setId(thongTinLuotKhamBenhTrongNgay.getId());
        if(thongTinLuotKhamBenhTrongNgay.getNgayKhamBenh() != null) {
            thongTinLuotKhamBenhTrongNgayCommand.setNgayKhamBenhId(thongTinLuotKhamBenhTrongNgay.getNgayKhamBenh().getId());
        }
        thongTinLuotKhamBenhTrongNgayCommand.setStt(thongTinLuotKhamBenhTrongNgay.getStt());
        thongTinLuotKhamBenhTrongNgayCommand.setHoTen(thongTinLuotKhamBenhTrongNgay.getHoTen());
        thongTinLuotKhamBenhTrongNgayCommand.setTuoi(thongTinLuotKhamBenhTrongNgay.getTuoi());
        thongTinLuotKhamBenhTrongNgayCommand.setDiaChi(thongTinLuotKhamBenhTrongNgay.getDiaChi());
        thongTinLuotKhamBenhTrongNgayCommand.setSoDienThoai(thongTinLuotKhamBenhTrongNgay.getSoDienThoai());
        thongTinLuotKhamBenhTrongNgayCommand.setSieuAm(thongTinLuotKhamBenhTrongNgay.getSieuAm());
        thongTinLuotKhamBenhTrongNgayCommand.setTienSieuAm(thongTinLuotKhamBenhTrongNgay.getTienSieuAm());
        thongTinLuotKhamBenhTrongNgayCommand.setXetNghiem(thongTinLuotKhamBenhTrongNgay.getXetNghiem());
        thongTinLuotKhamBenhTrongNgayCommand.setTienXetNghiem(thongTinLuotKhamBenhTrongNgay.getTienXetNghiem());
        thongTinLuotKhamBenhTrongNgayCommand.setGhiChu(thongTinLuotKhamBenhTrongNgay.getGhiChu());


        return thongTinLuotKhamBenhTrongNgayCommand;
    }
}

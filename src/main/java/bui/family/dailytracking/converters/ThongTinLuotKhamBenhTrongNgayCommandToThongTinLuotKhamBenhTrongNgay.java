package bui.family.dailytracking.converters;

import bui.family.dailytracking.commands.ThongTinLuotKhamBenhTrongNgayCommand;
import bui.family.dailytracking.domain.NgayKhamBenh;
import bui.family.dailytracking.domain.ThongTinLuotKhamBenhTrongNgay;
import bui.family.dailytracking.services.NgayKhamBenhService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class ThongTinLuotKhamBenhTrongNgayCommandToThongTinLuotKhamBenhTrongNgay implements Converter<ThongTinLuotKhamBenhTrongNgayCommand, ThongTinLuotKhamBenhTrongNgay> {
    private final NgayKhamBenhService ngayKhamBenhService;

    public ThongTinLuotKhamBenhTrongNgayCommandToThongTinLuotKhamBenhTrongNgay(NgayKhamBenhService ngayKhamBenhService) {
        this.ngayKhamBenhService = ngayKhamBenhService;
    }


    @Nullable
    @Override
    public ThongTinLuotKhamBenhTrongNgay convert(ThongTinLuotKhamBenhTrongNgayCommand source) {
        if(source == null){
            return null;
        }
        final ThongTinLuotKhamBenhTrongNgay thongTinLuotKhamBenhTrongNgay = new ThongTinLuotKhamBenhTrongNgay();
        thongTinLuotKhamBenhTrongNgay.setId(source.getId());
        if(source.getNgayKhamBenhId() != null) {
            NgayKhamBenh ngayKhamBenh = ngayKhamBenhService.findById(source.getNgayKhamBenhId());
            ngayKhamBenh.addThongTinLuotKhamBenhTrongNgay(thongTinLuotKhamBenhTrongNgay);
        }
        thongTinLuotKhamBenhTrongNgay.setStt(source.getStt());
        thongTinLuotKhamBenhTrongNgay.setHoTen(source.getHoTen());
        thongTinLuotKhamBenhTrongNgay.setTuoi(source.getTuoi());
        thongTinLuotKhamBenhTrongNgay.setDiaChi(source.getDiaChi());
        thongTinLuotKhamBenhTrongNgay.setSoDienThoai(source.getSoDienThoai());
        thongTinLuotKhamBenhTrongNgay.setLamSang(source.getLamSang());
        thongTinLuotKhamBenhTrongNgay.setTienSieuAm(source.getTienSieuAm());
        thongTinLuotKhamBenhTrongNgay.setXetNghiem(source.getXetNghiem());
        thongTinLuotKhamBenhTrongNgay.setTienXetNghiem(source.getTienXetNghiem());
        thongTinLuotKhamBenhTrongNgay.setGhiChu(source.getGhiChu());

        return thongTinLuotKhamBenhTrongNgay;
    }
}

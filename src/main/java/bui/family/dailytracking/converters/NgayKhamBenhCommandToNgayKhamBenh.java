package bui.family.dailytracking.converters;

import bui.family.dailytracking.commands.NgayKhamBenhCommand;
import bui.family.dailytracking.domain.NgayKhamBenh;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NgayKhamBenhCommandToNgayKhamBenh implements Converter<NgayKhamBenhCommand, NgayKhamBenh> {
    //private final ThongTinLuotKhamBenhTrongNgayCommandToThongTinLuotKhamBenhTrongNgay thongTinLuotKhamBenhTrongNgayConverter;

    /*public NgayKhamBenhCommandToNgayKhamBenh(ThongTinLuotKhamBenhTrongNgayCommandToThongTinLuotKhamBenhTrongNgay thongTinLuotKhamBenhTrongNgayConverter) {
        //this.thongTinLuotKhamBenhTrongNgayConverter = thongTinLuotKhamBenhTrongNgayConverter;
    }*/

    @Override
    public NgayKhamBenh convert(NgayKhamBenhCommand source) {
        if(source == null){
            return null;
        }

        final NgayKhamBenh ngayKhamBenh = new NgayKhamBenh();
        ngayKhamBenh.setId(source.getId());
        ngayKhamBenh.setNgayKhamBenh(source.getNgayKhamBenh());
        ngayKhamBenh.setTongSoLuotSieuAm(source.getTongSoLuotSieuAm());
        ngayKhamBenh.setTongTienSieuAm(source.getTongTienSieuAm());
        ngayKhamBenh.setTongSoLuotXetNghiem(source.getTongSoLuotXetNghiem());
        ngayKhamBenh.setTongTienXetNghiem(source.getTongTienXetNghiem());

        /*if(source.getThongTinLuotKhamBenhTrongNgayCommands() != null && source.getThongTinLuotKhamBenhTrongNgayCommands().size() > 0){
            source.getThongTinLuotKhamBenhTrongNgayCommands()
                    .forEach(thongTinLuotKhamBenhTrongNgayCommand -> ngayKhamBenh.getThongTinLuotKhamBenhTrongNgays().add(thongTinLuotKhamBenhTrongNgayConverter.convert(thongTinLuotKhamBenhTrongNgayCommand)));
        }*/

        return ngayKhamBenh;
    }
}

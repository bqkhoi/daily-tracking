package bui.family.dailytracking.converters;

import bui.family.dailytracking.commands.NgayKhamBenhCommand;
import bui.family.dailytracking.domain.NgayKhamBenh;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NgayKhamBenhCommandToNgayKhamBenh implements Converter<NgayKhamBenhCommand, NgayKhamBenh> {
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

        return ngayKhamBenh;
    }
}

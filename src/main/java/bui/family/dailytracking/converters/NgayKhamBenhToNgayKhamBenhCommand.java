package bui.family.dailytracking.converters;

import bui.family.dailytracking.commands.NgayKhamBenhCommand;
import bui.family.dailytracking.domain.NgayKhamBenh;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NgayKhamBenhToNgayKhamBenhCommand implements Converter<NgayKhamBenh, NgayKhamBenhCommand> {

    private final ThongTinLuotKhamBenhTrongNgayToThongTinLuotKhamBenhTrongNgayCommand thongTinLuotKhamBenhTrongNgayConverter;

    public NgayKhamBenhToNgayKhamBenhCommand(ThongTinLuotKhamBenhTrongNgayToThongTinLuotKhamBenhTrongNgayCommand thongTinLuotKhamBenhTrongNgayConverter) {
        this.thongTinLuotKhamBenhTrongNgayConverter = thongTinLuotKhamBenhTrongNgayConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public NgayKhamBenhCommand convert(NgayKhamBenh ngayKhamBenh) {
        if (ngayKhamBenh == null){
            return null;
        }

        final NgayKhamBenhCommand ngayKhamBenhCommand = new NgayKhamBenhCommand();

        ngayKhamBenhCommand.setId(ngayKhamBenh.getId());
        ngayKhamBenhCommand.setNgayKhamBenh(ngayKhamBenh.getNgayKhamBenh());
        ngayKhamBenhCommand.setTongSoLuotSieuAm(ngayKhamBenh.getTongSoLuotSieuAm());
        ngayKhamBenhCommand.setTongTienSieuAm(ngayKhamBenh.getTongTienSieuAm());
        ngayKhamBenhCommand.setTongSoLuotXetNghiem(ngayKhamBenh.getTongSoLuotXetNghiem());
        ngayKhamBenhCommand.setTongTienXetNghiem(ngayKhamBenh.getTongTienXetNghiem());

        if(ngayKhamBenh.getThongTinLuotKhamBenhTrongNgays() != null && ngayKhamBenh.getThongTinLuotKhamBenhTrongNgays().size() > 0){
            ngayKhamBenh.getThongTinLuotKhamBenhTrongNgays()
                    .forEach(thongTinLuotKhamBenhTrongNgay -> ngayKhamBenhCommand.getThongTinLuotKhamBenhTrongNgayCommands().add(thongTinLuotKhamBenhTrongNgayConverter.convert(thongTinLuotKhamBenhTrongNgay)));
        }

        return ngayKhamBenhCommand;
    }
}

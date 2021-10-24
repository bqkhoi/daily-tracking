package bui.family.dailytracking.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ThongTinLuotKhamBenhTrongNgayCommand {
    private Long id;
    private Long ngayKhamBenhId;

    private Integer stt;
    private String hoTen;
    private Integer tuoi;
    private String diaChi;
    private Integer soDienThoai;
    private String sieuAm;
    private Integer tienSieuAm;
    private String xetNghiem;
    private Integer tienXetNghiem;
    private String ghiChu;
}

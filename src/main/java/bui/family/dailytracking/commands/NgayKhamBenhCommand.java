package bui.family.dailytracking.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class NgayKhamBenhCommand {
    private Long id;

    private Date ngayKhamBenh;
    private Integer tongSoLuotSieuAm;
    private Integer tongTienSieuAm;
    private Integer tongSoLuotXetNghiem;
    private Integer tongTienXetNghiem;
    private Set<ThongTinLuotKhamBenhTrongNgayCommand> thongTinLuotKhamBenhTrongNgayCommands = new HashSet<>();
}

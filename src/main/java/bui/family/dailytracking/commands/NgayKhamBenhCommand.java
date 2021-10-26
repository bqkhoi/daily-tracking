package bui.family.dailytracking.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class NgayKhamBenhCommand {
    private Long id;

    private Timestamp ngayKhamBenh;
    private Integer tongSoLuotSieuAm;
    private Integer tongTienSieuAm;
    private Integer tongSoLuotXetNghiem;
    private Integer tongTienXetNghiem;
    private Set<ThongTinLuotKhamBenhTrongNgayCommand> thongTinLuotKhamBenhTrongNgayCommands = new HashSet<>();
}

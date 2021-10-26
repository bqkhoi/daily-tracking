package bui.family.dailytracking.domain;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class NgayKhamBenh {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Timestamp ngayKhamBenh;
    private Integer tongSoLuotSieuAm;
    private Integer tongTienSieuAm;
    private Integer tongSoLuotXetNghiem;
    private Integer tongTienXetNghiem;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ngayKhamBenh")
    private Set<ThongTinLuotKhamBenhTrongNgay> thongTinLuotKhamBenhTrongNgays = new HashSet<>();

    public NgayKhamBenh addThongTinLuotKhamBenhTrongNgay(ThongTinLuotKhamBenhTrongNgay thongTinLuotKhamBenhTrongNgay){
        thongTinLuotKhamBenhTrongNgay.setNgayKhamBenh(this);
        this.thongTinLuotKhamBenhTrongNgays.add(thongTinLuotKhamBenhTrongNgay);
        return this;
    }
}

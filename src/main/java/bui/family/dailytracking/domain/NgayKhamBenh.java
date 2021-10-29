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

    public void tinhTongSieuAmTrongNgay(){
        int tongSoLuotSieuAmTrongNgay = 0;
        int tongTienSieuAmTrongNgay = 0;
        for(ThongTinLuotKhamBenhTrongNgay thongTinLuotKhamBenhTrongNgay : thongTinLuotKhamBenhTrongNgays){
            if(thongTinLuotKhamBenhTrongNgay.getTienSieuAm() != null){
                tongSoLuotSieuAmTrongNgay = tongSoLuotSieuAmTrongNgay + 1;
                tongTienSieuAmTrongNgay = tongTienSieuAmTrongNgay + thongTinLuotKhamBenhTrongNgay.getTienSieuAm();
            }
        }
        tongSoLuotSieuAm = tongSoLuotSieuAmTrongNgay;
        tongTienSieuAm = tongTienSieuAmTrongNgay;
    }

    public void tinhTongXetNghiemTrongNgay(){
        int tongSoLuotXetNghiemTrongNgay = 0;
        int tongTienXetNghiemTrongNgay = 0;
        for(ThongTinLuotKhamBenhTrongNgay thongTinLuotKhamBenhTrongNgay : thongTinLuotKhamBenhTrongNgays){
            if(thongTinLuotKhamBenhTrongNgay.getTienXetNghiem() != null){
                tongSoLuotXetNghiemTrongNgay = tongSoLuotXetNghiemTrongNgay + 1;
                tongTienXetNghiemTrongNgay = tongTienXetNghiemTrongNgay + thongTinLuotKhamBenhTrongNgay.getTienXetNghiem();
            }
        }
        tongSoLuotXetNghiem = tongSoLuotXetNghiemTrongNgay;
        tongTienXetNghiem = tongTienXetNghiemTrongNgay;
    }

}

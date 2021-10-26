package bui.family.dailytracking.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = {"ngayKhamBenh"})
@Entity
public class ThongTinLuotKhamBenhTrongNgay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @ManyToOne
    private NgayKhamBenh ngayKhamBenh;
}

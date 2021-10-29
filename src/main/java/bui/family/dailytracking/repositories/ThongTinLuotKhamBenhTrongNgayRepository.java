package bui.family.dailytracking.repositories;

import bui.family.dailytracking.domain.ThongTinLuotKhamBenhTrongNgay;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ThongTinLuotKhamBenhTrongNgayRepository extends CrudRepository<ThongTinLuotKhamBenhTrongNgay, Long> {
    List<ThongTinLuotKhamBenhTrongNgay> findByNgayKhamBenhId(long ngayKhamBenhId);
}

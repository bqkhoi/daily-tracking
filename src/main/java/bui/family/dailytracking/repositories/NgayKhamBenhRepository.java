package bui.family.dailytracking.repositories;

import bui.family.dailytracking.domain.NgayKhamBenh;
import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;
import java.util.Set;

public interface NgayKhamBenhRepository extends CrudRepository<NgayKhamBenh, Long> {
    Set<NgayKhamBenh> findByNgayKhamBenhBetween(Timestamp startDate, Timestamp endDay);
}

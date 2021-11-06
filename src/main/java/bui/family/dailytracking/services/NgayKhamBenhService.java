package bui.family.dailytracking.services;

import bui.family.dailytracking.commands.NgayKhamBenhCommand;
import bui.family.dailytracking.domain.NgayKhamBenh;
import org.springframework.data.domain.Page;

import java.util.Set;

public interface NgayKhamBenhService {
    Set<NgayKhamBenh> getNgayKhamBenhs();

    Page<NgayKhamBenh> getNgayKhamBenhsInPage(int pageNumber);

    NgayKhamBenh findById(Long l);

    NgayKhamBenhCommand findCommandById(Long l);

    NgayKhamBenhCommand saveNgayKhamBenhCommand(NgayKhamBenhCommand command);

    NgayKhamBenh saveNgayKhamBenh(NgayKhamBenh ngayKhamBenh);

    NgayKhamBenhCommand createNgayKhamBenh();

    void deleteById(Long idToDelete);
}

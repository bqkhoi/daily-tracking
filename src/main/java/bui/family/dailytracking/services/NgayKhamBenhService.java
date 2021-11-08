package bui.family.dailytracking.services;

import bui.family.dailytracking.commands.NgayKhamBenhCommand;
import bui.family.dailytracking.commands.PageNgayKhamBenhCommand;
import bui.family.dailytracking.domain.NgayKhamBenh;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface NgayKhamBenhService {
    Page<NgayKhamBenh> getNgayKhamBenhsTaiTrang(int pageNumber);

    PageNgayKhamBenhCommand getThongTinNgayKhamBenhTaiTrang(Optional<Integer> pageNumber);

    NgayKhamBenh findById(Long l);

    NgayKhamBenhCommand findCommandById(Long l);

    NgayKhamBenhCommand saveNgayKhamBenhCommand(NgayKhamBenhCommand command);

    NgayKhamBenh saveNgayKhamBenh(NgayKhamBenh ngayKhamBenh);

    NgayKhamBenhCommand createNgayKhamBenh();
}

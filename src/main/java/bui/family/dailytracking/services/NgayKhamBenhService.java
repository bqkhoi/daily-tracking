package bui.family.dailytracking.services;

import bui.family.dailytracking.commands.NgayKhamBenhCommand;
import bui.family.dailytracking.domain.NgayKhamBenh;

import java.util.List;
import java.util.Set;

public interface NgayKhamBenhService {
    List<NgayKhamBenh> getNgayKhamBenhs();

    NgayKhamBenh findById(Long l);

    NgayKhamBenhCommand findCommandById(Long l);

    NgayKhamBenhCommand saveNgayKhamBenhCommand(NgayKhamBenhCommand command);

    NgayKhamBenhCommand createNgayKhamBenh();

    void deleteById(Long idToDelete);
}

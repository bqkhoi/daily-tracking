package bui.family.dailytracking.services;

import bui.family.dailytracking.commands.NgayKhamBenhCommand;
import bui.family.dailytracking.converters.NgayKhamBenhCommandToNgayKhamBenh;
import bui.family.dailytracking.converters.NgayKhamBenhToNgayKhamBenhCommand;
import bui.family.dailytracking.domain.NgayKhamBenh;
import bui.family.dailytracking.repositories.NgayKhamBenhRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class NgayKhamBenhServiceImpl implements NgayKhamBenhService {
    private final NgayKhamBenhRepository ngayKhamBenhRepository;
    private final NgayKhamBenhCommandToNgayKhamBenh ngayKhamBenhCommandToNgayKhamBenh;
    private final NgayKhamBenhToNgayKhamBenhCommand ngayKhamBenhToNgayKhamBenhCommand;

    public NgayKhamBenhServiceImpl(NgayKhamBenhRepository ngayKhamBenhRepository, NgayKhamBenhCommandToNgayKhamBenh ngayKhamBenhCommandToNgayKhamBenh, NgayKhamBenhToNgayKhamBenhCommand ngayKhamBenhToNgayKhamBenhCommand) {
        this.ngayKhamBenhRepository = ngayKhamBenhRepository;
        this.ngayKhamBenhCommandToNgayKhamBenh = ngayKhamBenhCommandToNgayKhamBenh;
        this.ngayKhamBenhToNgayKhamBenhCommand = ngayKhamBenhToNgayKhamBenhCommand;
    }

    @Override
    public Set<NgayKhamBenh> getNgayKhamBenhs() {
        Set<NgayKhamBenh> ngayKhamBenhSet = new HashSet<>();
        ngayKhamBenhRepository.findAll().iterator().forEachRemaining(ngayKhamBenhSet::add);
        return ngayKhamBenhSet;
    }

    @Override
    public NgayKhamBenh findById(Long l) {
        Optional<NgayKhamBenh> ngayKhamBenhOptional = ngayKhamBenhRepository.findById(l);
        if(!ngayKhamBenhOptional.isPresent()){
            throw new RuntimeException("Khong Kiem Thay Ngay Kham Benh!");
        }
        return ngayKhamBenhOptional.get();
    }

    @Override
    public NgayKhamBenhCommand findCommandById(Long l) {
        return ngayKhamBenhToNgayKhamBenhCommand.convert(findById(l));
    }

    @Override
    public NgayKhamBenhCommand saveNgayKhamBenhCommand(NgayKhamBenhCommand command) {
        NgayKhamBenh detachedNgayKhamBenh = ngayKhamBenhCommandToNgayKhamBenh.convert(command);

        NgayKhamBenh savedNgayKhamBenh = ngayKhamBenhRepository.save(detachedNgayKhamBenh);
        log.debug("Luu Ngay Kham Benh Id:" + savedNgayKhamBenh.getId());
        return ngayKhamBenhToNgayKhamBenhCommand.convert(savedNgayKhamBenh);
    }

    @Override
    public void deleteById(Long idToDelete) {
        ngayKhamBenhRepository.deleteById(idToDelete);
    }
}

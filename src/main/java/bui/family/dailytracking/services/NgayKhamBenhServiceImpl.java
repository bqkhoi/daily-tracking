package bui.family.dailytracking.services;

import bui.family.dailytracking.commands.NgayKhamBenhCommand;
import bui.family.dailytracking.converters.NgayKhamBenhCommandToNgayKhamBenh;
import bui.family.dailytracking.converters.NgayKhamBenhToNgayKhamBenhCommand;
import bui.family.dailytracking.domain.NgayKhamBenh;
import bui.family.dailytracking.repositories.NgayKhamBenhRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

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
    public List<NgayKhamBenh> getNgayKhamBenhs() {
        Set<NgayKhamBenh> ngayKhamBenhSet = new HashSet<>();
        ngayKhamBenhRepository.findAll().iterator().forEachRemaining(ngayKhamBenhSet::add);
        List<NgayKhamBenh> ngayKhamBenhList = ngayKhamBenhSet.stream().sorted(Comparator.comparing(NgayKhamBenh::getId)).collect(Collectors.toList());
        return ngayKhamBenhList;
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
    public NgayKhamBenhCommand createNgayKhamBenh() {
        Timestamp currentDate = new Timestamp(new Date().getTime());
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(currentDate.getTime());
        cal.add(Calendar.DATE, -1);
        Timestamp beforeOneDate = new Timestamp(cal.getTime().getTime());

        Set<NgayKhamBenh> ngayKhamBenhs = ngayKhamBenhRepository.findByNgayKhamBenhBetween(beforeOneDate, currentDate);
        if(ngayKhamBenhs.size() > 0) {
            for (NgayKhamBenh ngayKhamBenh : ngayKhamBenhs) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
                String currentDay = simpleDateFormat.format(currentDate);
                String ngayKhamBenhDay = simpleDateFormat.format(ngayKhamBenh.getNgayKhamBenh());
                if (currentDay.equalsIgnoreCase(ngayKhamBenhDay)) {
                    return ngayKhamBenhToNgayKhamBenhCommand.convert(ngayKhamBenh);
                }
            }
        }

        NgayKhamBenhCommand ngayKhamBenhCommand = initNgayKhamBenh();
        return saveNgayKhamBenhCommand(ngayKhamBenhCommand);
    }

    private NgayKhamBenhCommand initNgayKhamBenh(){
        NgayKhamBenhCommand command = new NgayKhamBenhCommand();
        command.setNgayKhamBenh(Timestamp.from(ZonedDateTime.now().toInstant()));
        command.setTongSoLuotSieuAm(0);
        command.setTongTienSieuAm(0);
        command.setTongSoLuotXetNghiem(0);
        command.setTongTienXetNghiem(0);
        return command;
    }

    @Override
    public void deleteById(Long idToDelete) {
        ngayKhamBenhRepository.deleteById(idToDelete);
    }
}

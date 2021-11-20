package bui.family.dailytracking.services;

import bui.family.dailytracking.commands.NgayKhamBenhCommand;
import bui.family.dailytracking.commands.PageNgayKhamBenhCommand;
import bui.family.dailytracking.commands.ThongTinLuotKhamBenhTrongNgayCommand;
import bui.family.dailytracking.converters.NgayKhamBenhCommandToNgayKhamBenh;
import bui.family.dailytracking.converters.NgayKhamBenhToNgayKhamBenhCommand;
import bui.family.dailytracking.domain.NgayKhamBenh;
import bui.family.dailytracking.repositories.NgayKhamBenhRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    public Page<NgayKhamBenh> getNgayKhamBenhsTaiTrang(int pageNumber) {
        Pageable sortedById = PageRequest.of(pageNumber, 30, Sort.by("ngayKhamBenh").descending());
        Page<NgayKhamBenh> ngayKhamBenhs = ngayKhamBenhRepository.findAll(sortedById);
        return ngayKhamBenhs;
    }

    @Override
    public PageNgayKhamBenhCommand getThongTinNgayKhamBenhTaiTrang(Optional<Integer> pageNumber) {
        int currentPage = pageNumber.orElse(1);
        Page<NgayKhamBenh> ngayKhamBenhPages = this.getNgayKhamBenhsTaiTrang(currentPage - 1);
        int totalPages = ngayKhamBenhPages.getTotalPages();
        PageNgayKhamBenhCommand pageNgayKhamBenhCommand = new PageNgayKhamBenhCommand();
        pageNgayKhamBenhCommand.setNgayKhamBenhPages(ngayKhamBenhPages);
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            pageNgayKhamBenhCommand.setPageNumbers(pageNumbers);
            pageNgayKhamBenhCommand.setSelectedPage(currentPage);
        }
        return pageNgayKhamBenhCommand;
    }

    @Override
    public NgayKhamBenh findById(Long l) {
        Optional<NgayKhamBenh> ngayKhamBenhOptional = ngayKhamBenhRepository.findById(l);
        if(!ngayKhamBenhOptional.isPresent()){
            return null;
        }
        return ngayKhamBenhOptional.get();
    }

    @Override
    public NgayKhamBenhCommand findCommandById(Long l) {
        //TODO sort thong tin luot kham benh trong ngay
        NgayKhamBenhCommand ngayKhamBenhCommand = ngayKhamBenhToNgayKhamBenhCommand.convert(findById(l));
        Set<ThongTinLuotKhamBenhTrongNgayCommand> thongTinLuotKhamBenhTrongNgayCommands = ngayKhamBenhCommand.getThongTinLuotKhamBenhTrongNgayCommands();
        Set<ThongTinLuotKhamBenhTrongNgayCommand> sortedThongTinLuotKhamBenhTrongNgayCommands = thongTinLuotKhamBenhTrongNgayCommands.stream().sorted(Comparator.comparing(ThongTinLuotKhamBenhTrongNgayCommand::getStt)).collect(Collectors.toCollection(LinkedHashSet::new));
        ngayKhamBenhCommand.setThongTinLuotKhamBenhTrongNgayCommands(sortedThongTinLuotKhamBenhTrongNgayCommands);
        return ngayKhamBenhCommand;
    }

    @Override
    public NgayKhamBenhCommand saveNgayKhamBenhCommand(NgayKhamBenhCommand command) {
        NgayKhamBenh detachedNgayKhamBenh = ngayKhamBenhCommandToNgayKhamBenh.convert(command);

        NgayKhamBenh savedNgayKhamBenh = ngayKhamBenhRepository.save(detachedNgayKhamBenh);
        log.debug("Luu Ngay Kham Benh Id:" + savedNgayKhamBenh.getId());
        return ngayKhamBenhToNgayKhamBenhCommand.convert(savedNgayKhamBenh);
    }

    @Override
    public NgayKhamBenh saveNgayKhamBenh(NgayKhamBenh ngayKhamBenh) {
        return ngayKhamBenhRepository.save(ngayKhamBenh);
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
}

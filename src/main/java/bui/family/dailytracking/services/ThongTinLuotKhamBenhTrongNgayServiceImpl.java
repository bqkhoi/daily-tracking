package bui.family.dailytracking.services;

import bui.family.dailytracking.commands.ThongTinLuotKhamBenhTrongNgayCommand;
import bui.family.dailytracking.converters.ThongTinLuotKhamBenhTrongNgayCommandToThongTinLuotKhamBenhTrongNgay;
import bui.family.dailytracking.converters.ThongTinLuotKhamBenhTrongNgayToThongTinLuotKhamBenhTrongNgayCommand;
import bui.family.dailytracking.domain.NgayKhamBenh;
import bui.family.dailytracking.domain.ThongTinLuotKhamBenhTrongNgay;
import bui.family.dailytracking.repositories.NgayKhamBenhRepository;
import bui.family.dailytracking.repositories.ThongTinLuotKhamBenhTrongNgayRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ThongTinLuotKhamBenhTrongNgayServiceImpl implements ThongTinLuotKhamBenhTrongNgayService {
    private final ThongTinLuotKhamBenhTrongNgayToThongTinLuotKhamBenhTrongNgayCommand thongTinLuotKhamBenhTrongNgayToThongTinLuotKhamBenhTrongNgayCommand;
    private final ThongTinLuotKhamBenhTrongNgayCommandToThongTinLuotKhamBenhTrongNgay thongTinLuotKhamBenhTrongNgayCommandToThongTinLuotKhamBenhTrongNgay;
    private final NgayKhamBenhRepository ngayKhamBenhRepository;
    private final ThongTinLuotKhamBenhTrongNgayRepository thongTinLuotKhamBenhTrongNgayRepository;

    public ThongTinLuotKhamBenhTrongNgayServiceImpl(ThongTinLuotKhamBenhTrongNgayToThongTinLuotKhamBenhTrongNgayCommand thongTinLuotKhamBenhTrongNgayToThongTinLuotKhamBenhTrongNgayCommand,
                                                    ThongTinLuotKhamBenhTrongNgayCommandToThongTinLuotKhamBenhTrongNgay thongTinLuotKhamBenhTrongNgayCommandToThongTinLuotKhamBenhTrongNgay,
                                                    NgayKhamBenhRepository ngayKhamBenhRepository, ThongTinLuotKhamBenhTrongNgayRepository thongTinLuotKhamBenhTrongNgayRepository) {
        this.thongTinLuotKhamBenhTrongNgayToThongTinLuotKhamBenhTrongNgayCommand = thongTinLuotKhamBenhTrongNgayToThongTinLuotKhamBenhTrongNgayCommand;
        this.thongTinLuotKhamBenhTrongNgayCommandToThongTinLuotKhamBenhTrongNgay = thongTinLuotKhamBenhTrongNgayCommandToThongTinLuotKhamBenhTrongNgay;
        this.ngayKhamBenhRepository = ngayKhamBenhRepository;
        this.thongTinLuotKhamBenhTrongNgayRepository = thongTinLuotKhamBenhTrongNgayRepository;
    }

    @Override
    public ThongTinLuotKhamBenhTrongNgayCommand findByNgayKhamBenhIdAndThongTinLuotKhamBenhTrongNgayId(Long ngayKhamBenhId, Long thongTinLuotKhamBenhTrongNgayId) {
        Optional<NgayKhamBenh> ngayKhamBenhOptional = ngayKhamBenhRepository.findById(ngayKhamBenhId);
        if(!ngayKhamBenhOptional.isPresent()){
            log.error("Khong Tim Thay Ngay Kham Benh Id:" + ngayKhamBenhId);
            return null;
        }

        NgayKhamBenh ngayKhamBenh = ngayKhamBenhOptional.get();

        Optional<ThongTinLuotKhamBenhTrongNgayCommand> thongTinLuotKhamBenhTrongNgayCommandOptional =
                ngayKhamBenh.getThongTinLuotKhamBenhTrongNgays().stream()
                .filter(thongTinLuotKhamBenhTrongNgay -> thongTinLuotKhamBenhTrongNgay.getId().equals(thongTinLuotKhamBenhTrongNgayId))
                .map(thongTinLuotKhamBenhTrongNgay -> thongTinLuotKhamBenhTrongNgayToThongTinLuotKhamBenhTrongNgayCommand.convert(thongTinLuotKhamBenhTrongNgay)).findFirst();
        if(!thongTinLuotKhamBenhTrongNgayCommandOptional.isPresent()){
            log.error("Khong Tim Thay Thong Tin Luot Kham Benh Trong Ngay Voi Id:" + thongTinLuotKhamBenhTrongNgayId);
            return null;
        }

        return thongTinLuotKhamBenhTrongNgayCommandOptional.get();
    }

    @Override
    public NgayKhamBenh saveThongTinLuotKhamBenhTrongNgayCommand(ThongTinLuotKhamBenhTrongNgayCommand command) {
        Optional<NgayKhamBenh> ngayKhamBenhOptional = ngayKhamBenhRepository.findById(command.getNgayKhamBenhId());
        if(!ngayKhamBenhOptional.isPresent()){
            log.error("Khong Tim Thay Ngay Kham Benh Voi Id:"+command.getNgayKhamBenhId());
            return null;
        }
        NgayKhamBenh ngayKhamBenh = ngayKhamBenhOptional.get();
        Optional<ThongTinLuotKhamBenhTrongNgay> thongTinLuotKhamBenhTrongNgayOptional = null;
        if(command.getId() != null) {
            thongTinLuotKhamBenhTrongNgayOptional =
                    ngayKhamBenh.getThongTinLuotKhamBenhTrongNgays()
                            .stream()
                            .filter(thongTinLuotKhamBenhTrongNgay -> thongTinLuotKhamBenhTrongNgay.getId().equals(command.getId()))
                            .findFirst();
        }

        if(thongTinLuotKhamBenhTrongNgayOptional != null && thongTinLuotKhamBenhTrongNgayOptional.isPresent()){
            ThongTinLuotKhamBenhTrongNgay thongTinLuotKhamBenhTrongNgayFound = thongTinLuotKhamBenhTrongNgayOptional.get();
            thongTinLuotKhamBenhTrongNgayFound.setStt(command.getStt());
            thongTinLuotKhamBenhTrongNgayFound.setHoTen(command.getHoTen());
            thongTinLuotKhamBenhTrongNgayFound.setTuoi(command.getTuoi());
            thongTinLuotKhamBenhTrongNgayFound.setDiaChi(command.getDiaChi());
            thongTinLuotKhamBenhTrongNgayFound.setSoDienThoai(command.getSoDienThoai());
            thongTinLuotKhamBenhTrongNgayFound.setLamSang(command.getLamSang());
            thongTinLuotKhamBenhTrongNgayFound.setTienSieuAm(command.getTienSieuAm());
            thongTinLuotKhamBenhTrongNgayFound.setXetNghiem(command.getXetNghiem());
            thongTinLuotKhamBenhTrongNgayFound.setTienXetNghiem(command.getTienXetNghiem());
            thongTinLuotKhamBenhTrongNgayFound.setGhiChu(command.getGhiChu());
        }else{
            ngayKhamBenh.addThongTinLuotKhamBenhTrongNgay(thongTinLuotKhamBenhTrongNgayCommandToThongTinLuotKhamBenhTrongNgay.convert(command));
        }
        NgayKhamBenh savedNgayKhamBenh = ngayKhamBenhRepository.save(ngayKhamBenh);

        return savedNgayKhamBenh;
    }

    @Override
    public ThongTinLuotKhamBenhTrongNgayCommand createThongTinLuotKhamBenhTrongNgay(long ngayKhamBenhId) {
        Optional<NgayKhamBenh> ngayKhamBenhOptional = ngayKhamBenhRepository.findById(ngayKhamBenhId);
        if(!ngayKhamBenhOptional.isPresent()){
            log.error("Khong Tim Thay Ngay Kham Benh Voi Id:"+ngayKhamBenhId);
            return null;
        }
        NgayKhamBenh ngayKhamBenh = ngayKhamBenhOptional.get();

        List<ThongTinLuotKhamBenhTrongNgay> thongTinLuotKhamBenhTrongNgays = thongTinLuotKhamBenhTrongNgayRepository.findByNgayKhamBenhId(ngayKhamBenhId);
        int highestStt = 0;
        if(thongTinLuotKhamBenhTrongNgays.size() > 0) {
            List<ThongTinLuotKhamBenhTrongNgay> sortedThongTinLuotKhamBenhTrongNgays =
                    thongTinLuotKhamBenhTrongNgays.stream().sorted(Comparator.comparing(ThongTinLuotKhamBenhTrongNgay::getStt)).collect(Collectors.toList());
            highestStt = sortedThongTinLuotKhamBenhTrongNgays.get(sortedThongTinLuotKhamBenhTrongNgays.size() - 1).getStt();
        }

        ThongTinLuotKhamBenhTrongNgay thongTinLuotKhamBenhTrongNgay = new ThongTinLuotKhamBenhTrongNgay();
        thongTinLuotKhamBenhTrongNgay.setStt(highestStt + 1);
        thongTinLuotKhamBenhTrongNgay.setNgayKhamBenh(ngayKhamBenh);

        return thongTinLuotKhamBenhTrongNgayToThongTinLuotKhamBenhTrongNgayCommand.convert(thongTinLuotKhamBenhTrongNgay);
    }
}

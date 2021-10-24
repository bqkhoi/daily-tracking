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

import java.util.Optional;

@Slf4j
@Service
public class ThongTinLuotKhamBenhTrongNgayServiceImpl implements ThongTinLuotKhamBenhTrongNgayService {
    private final ThongTinLuotKhamBenhTrongNgayToThongTinLuotKhamBenhTrongNgayCommand thongTinLuotKhamBenhTrongNgayToThongTinLuotKhamBenhTrongNgayCommand;
    private final ThongTinLuotKhamBenhTrongNgayCommandToThongTinLuotKhamBenhTrongNgay thongTinLuotKhamBenhTrongNgayCommandToThongTinLuotKhamBenhTrongNgay;
    private final NgayKhamBenhRepository ngayKhamBenhRepository;

    public ThongTinLuotKhamBenhTrongNgayServiceImpl(ThongTinLuotKhamBenhTrongNgayToThongTinLuotKhamBenhTrongNgayCommand thongTinLuotKhamBenhTrongNgayToThongTinLuotKhamBenhTrongNgayCommand,
                                                    ThongTinLuotKhamBenhTrongNgayCommandToThongTinLuotKhamBenhTrongNgay thongTinLuotKhamBenhTrongNgayCommandToThongTinLuotKhamBenhTrongNgay,
                                                    NgayKhamBenhRepository ngayKhamBenhRepository) {
        this.thongTinLuotKhamBenhTrongNgayToThongTinLuotKhamBenhTrongNgayCommand = thongTinLuotKhamBenhTrongNgayToThongTinLuotKhamBenhTrongNgayCommand;
        this.thongTinLuotKhamBenhTrongNgayCommandToThongTinLuotKhamBenhTrongNgay = thongTinLuotKhamBenhTrongNgayCommandToThongTinLuotKhamBenhTrongNgay;
        this.ngayKhamBenhRepository = ngayKhamBenhRepository;
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
    public ThongTinLuotKhamBenhTrongNgayCommand saveThongTinLuotKhamBenhTrongNgayCommand(ThongTinLuotKhamBenhTrongNgayCommand command) {
        Optional<NgayKhamBenh> ngayKhamBenhOptional = ngayKhamBenhRepository.findById(command.getNgayKhamBenhId());
        if(!ngayKhamBenhOptional.isPresent()){
            log.error("Khong Tim Thay Ngay Kham Benh Voi Id:"+command.getNgayKhamBenhId());
            return null;
        }
        NgayKhamBenh ngayKhamBenh = ngayKhamBenhOptional.get();

        Optional<ThongTinLuotKhamBenhTrongNgay> thongTinLuotKhamBenhTrongNgayOptional =
                ngayKhamBenh.getThongTinLuotKhamBenhTrongNgays()
                .stream()
                .filter(thongTinLuotKhamBenhTrongNgay -> thongTinLuotKhamBenhTrongNgay.getId().equals(command.getId()))
                .findFirst();

        if(thongTinLuotKhamBenhTrongNgayOptional.isPresent()){
            ThongTinLuotKhamBenhTrongNgay thongTinLuotKhamBenhTrongNgayFound = thongTinLuotKhamBenhTrongNgayOptional.get();
            thongTinLuotKhamBenhTrongNgayFound.setStt(command.getStt());
            thongTinLuotKhamBenhTrongNgayFound.setHoTen(command.getHoTen());
            thongTinLuotKhamBenhTrongNgayFound.setTuoi(command.getTuoi());
            thongTinLuotKhamBenhTrongNgayFound.setDiaChi(command.getDiaChi());
            thongTinLuotKhamBenhTrongNgayFound.setSoDienThoai(command.getSoDienThoai());
            thongTinLuotKhamBenhTrongNgayFound.setSieuAm(command.getSieuAm());
            thongTinLuotKhamBenhTrongNgayFound.setTienSieuAm(command.getTienSieuAm());
            thongTinLuotKhamBenhTrongNgayFound.setXetNghiem(command.getXetNghiem());
            thongTinLuotKhamBenhTrongNgayFound.setTienXetNghiem(command.getTienXetNghiem());
            thongTinLuotKhamBenhTrongNgayFound.setGhiChu(command.getGhiChu());
        }else{
            ngayKhamBenh.addThongTinLuotKhamBenhTrongNgay(thongTinLuotKhamBenhTrongNgayCommandToThongTinLuotKhamBenhTrongNgay.convert(command));
        }
        NgayKhamBenh savedNgayKhamBenh = ngayKhamBenhRepository.save(ngayKhamBenh);

        return thongTinLuotKhamBenhTrongNgayToThongTinLuotKhamBenhTrongNgayCommand
                .convert(savedNgayKhamBenh.getThongTinLuotKhamBenhTrongNgays().stream()
                .filter(thongTinLuotKhamBenhTrongNgay -> thongTinLuotKhamBenhTrongNgay.getId().equals(command.getId()))
                .findFirst().get());
    }

    @Override
    public void deleteById(Long idToDelete) {

    }
}

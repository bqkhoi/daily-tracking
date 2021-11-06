package bui.family.dailytracking.commands;

import bui.family.dailytracking.domain.NgayKhamBenh;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@NoArgsConstructor
@Data
public class PageNgayKhamBenhCommand {
    int selectedPage;
    List<Integer> pageNumbers;
    Page<NgayKhamBenh> ngayKhamBenhPages;
}

package bui.family.dailytracking;

import bui.family.dailytracking.domain.NgayKhamBenh;
import bui.family.dailytracking.services.NgayKhamBenhService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

@SpringBootTest
class DailyTrackingApplicationTests {

	@Autowired
	NgayKhamBenhService ngayKhamBenhService;

	/*@Test
	void contextLoads() {
		Page<NgayKhamBenh> ngayKhamBenhs = ngayKhamBenhService.getNgayKhamBenhsTaiTrang(0);
		int totalPage = ngayKhamBenhs.getTotalPages();
		for (NgayKhamBenh ngayKhamBenh : ngayKhamBenhs){
			System.out.println(ngayKhamBenh.getNgayKhamBenh());
		}
		System.out.println(totalPage);
	}*/

	@Test
	void testCircleCi(){
		int i = 1;
		Assert.assertEquals(i,1);
	}

}

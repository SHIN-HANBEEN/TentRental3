package green.teamproject.tentrental.goods;

import java.util.List;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;



import green.teamproject.tentrental.goods.dto.GoodsDTO;
import green.teamproject.tentrental.goods.service.GoodsService;

@SpringBootTest

public class GoodsServiceTest {
	
	@Autowired
	GoodsService service;
	
	/*
	 * @Test public void 데이터등록() { GoodsDTO dto = new GoodsDTO(3, "ccc", 30000,
	 * "cccc", null, null); int result = service.register(dto);
	 * System.out.println(result); }
	 */
	
	@Test
	public void 데이터개별조회() {
		GoodsDTO dto = service.read(2);
		System.out.println(dto);
		}
	
	@Test
	public void 데이터수정() {
		GoodsDTO dto = service.read(3);
		dto.setGoodsDescription("내용수정");
		service.modify(dto);
	}
	
	@Test
	public void 데이터삭제() {
		service.remove(2);
	}
	
	
	}





package green.teamproject.tentrental.goods;

import java.io.File;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import green.teamproject.tentrental.goods.entity.GoodsEntity;
import green.teamproject.tentrental.goods.repository.GoodsRepository;

@SpringBootTest
public class GoodsRepositoryTest {
	
	@Autowired
	GoodsRepository repository;
	
	@Test
	public void 데이터조회() {
		GoodsEntity entity = GoodsEntity.builder()
				.goodsNo(2)
				.goodsName("텐트2")
				.goodsPrice(10000)
				.goodsDescription("굿")
				.goodsView(10)
				.build();
		repository.save(entity);
	}
	
	@Test
	public void 데이터등록() {
		GoodsEntity entity = GoodsEntity.builder()
				.goodsNo(1)
				.goodsName("aaa")
				.goodsPrice(11000)
				.goodsDescription("aaaa")
				.build();
		repository.save(entity);
		GoodsEntity entity2 = GoodsEntity.builder()
				.goodsNo(2)
				.goodsName("bbb")
				.goodsPrice(12000)
				.goodsDescription("bbbb")
				.build();
		repository.save(entity2);
	}
	
	@Test
	public void 데이터전체조회() {
		List<GoodsEntity> result = repository.findAll();
		for(GoodsEntity entity : result) {
			System.out.println(entity);
		}
	}
	
	@Test
	public void 데이터단건조회() {
		Optional<GoodsEntity> result = repository.findById(1);
		if(result.isPresent()) {
			GoodsEntity entity = result.get();
			System.out.println(entity);
		}
	}
	
	@Test
	public void 데이터수정() {
		Optional<GoodsEntity> result = repository.findById(1);
		GoodsEntity entity = result.get();
		entity.setGoodsDescription("내용수정");
		repository.save(entity);
	}
	
	@Test
	public void 데이터삭제() {
		repository.deleteAll();
	}
	
	@Test
	public void 데이터개별삭제() {
		repository.deleteById(1);
	}
	
	@Test
	public void 파일등록() {
		File file = new File("file.txt");
		if(file.exists()) {
	}
		
	
	
	

}
}

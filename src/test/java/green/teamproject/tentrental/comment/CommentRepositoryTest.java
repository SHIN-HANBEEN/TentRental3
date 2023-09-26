package green.teamproject.tentrental.comment;

import java.util.List;
import java.util.Optional;

import javax.xml.stream.events.Comment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import green.teamproject.tentrental.comment.entity.CommentEntity;
import green.teamproject.tentrental.comment.repository.CommentRepository;
import green.teamproject.tentrental.goods.entity.GoodsEntity;

@SpringBootTest

public class CommentRepositoryTest {
	
	@Autowired
	CommentRepository repository;
		
	
//	 @Test public void 데이터등록() { CommentEntity entity = CommentEntity.builder()
//	  .cmtContents("정말 좋아요") .cmtWriter("둘리")
//	  .goodsEntity(GoodsEntity.builder().goodsNo(1).build())
//	  .build();
//	  repository.save(entity); }
//
//
//	@Test
//	public void 데이터전체조회() {
//		List<CommentEntity> list = repository.findAll();
//		for(CommentEntity entity : list) {
//			System.out.println(entity);
//		}
//	}
//
//	@Test
//	public void 데이터수정() {
//		Optional<CommentEntity> result = repository.findById(4);
//		CommentEntity entity = result.get();
//		entity.setCmtContents("내용수정함");
//		repository.save(entity);
//	}

}

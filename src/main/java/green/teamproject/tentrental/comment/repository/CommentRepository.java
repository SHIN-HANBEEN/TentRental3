package green.teamproject.tentrental.comment.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import green.teamproject.tentrental.comment.entity.CommentEntity;
import green.teamproject.tentrental.goods.entity.GoodsEntity;




public interface CommentRepository extends JpaRepository<CommentEntity, Integer>{
	
	List<CommentEntity> getCommentEntityByGoodsEntity(GoodsEntity entity);
	
	@Modifying
	@Query("delete from CommentEntity c where c.goodsEntity.goodsNo =:goodsNo")
	void deleteByGoodsNo(@Param("goodsNo") int goodsNo);
	

}

package green.teamproject.tentrental.goods.repository;

import com.querydsl.core.BooleanBuilder;
import green.teamproject.tentrental.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import green.teamproject.tentrental.goods.entity.GoodsEntity;

public interface GoodsRepository extends JpaRepository<GoodsEntity, Integer>, QuerydslPredicateExecutor<GoodsEntity> {

	  //조회수 증가
	  @Modifying
	  @Query("UPDATE GoodsEntity set goodsView = goodsView + 1 where goodsNo = :goodsNo") 
	  int updateView(@Param(value = "goodsNo") int goodsNo);
	  
	//List<GoodsEntity> findByTitleContaining(String keyword);
	
}

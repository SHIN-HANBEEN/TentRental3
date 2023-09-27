package green.teamproject.tentrental.goods.service;

import java.util.List;
import java.util.Optional;

import green.teamproject.tentrental.common.dto.PageRequestDTO;
import green.teamproject.tentrental.common.dto.PageResultDTO;
import green.teamproject.tentrental.user.dto.UserDTO;
import green.teamproject.tentrental.user.entity.User;
import org.springframework.data.domain.Page;

import green.teamproject.tentrental.goods.dto.GoodsDTO;
import green.teamproject.tentrental.goods.entity.GoodsEntity;

import jakarta.servlet.http.HttpSession;


public interface GoodsService {
	
	//상품등록
	int register(GoodsDTO dto);
	
	//상품리스트조회
	PageResultDTO<GoodsDTO, GoodsEntity> getList(PageRequestDTO requestDTO);
	
	//상품상세정보조회
	GoodsDTO read(int goodsNo);
	
	//상품정보수정
	void modify(GoodsDTO dto);
	
	//상품정보제거
	void remove(int GoodsNo);
	
	//상품조회수
	//int updateView(int goodsNo);
	
	//상품조회수중복방지
	Optional<GoodsEntity> updateView(int goodsNo, HttpSession session);

	List<GoodsDTO> getAllList();
	
	//상품엔티티를 티디오로 변경
	default GoodsEntity dtoToEntity(GoodsDTO dto) {
		GoodsEntity entity = GoodsEntity.builder()
				.goodsNo(dto.getGoodsNo())
				.goodsName(dto.getGoodsName())
				.goodsPrice(dto.getGoodsPrice())
				.goodsDescription(dto.getGoodsDescription())
				.build();
		return entity;
	}
		
	//상품디티오를 엔티티로 변경
	default GoodsDTO entityToDto(GoodsEntity entity) {
		GoodsDTO dto = GoodsDTO.builder()
				.goodsNo(entity.getGoodsNo())
				.goodsName(entity.getGoodsName())
				.goodsPrice(entity.getGoodsPrice())
				.goodsDescription(entity.getGoodsDescription())
				.goodsRegDate(entity.getRegDate())
				.goodsModDate(entity.getModDate())
				.imgPath(entity.getImgPath())
				.goodsView(entity.getGoodsView())
				.build();
		
		return dto;
	}

	



	
	
	
	 
	
	
}

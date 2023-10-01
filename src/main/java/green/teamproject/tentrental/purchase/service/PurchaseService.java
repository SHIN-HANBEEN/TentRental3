package green.teamproject.tentrental.purchase.service;

import java.util.List;

import org.springframework.data.domain.Page;

import green.teamproject.tentrental.goods.entity.GoodsEntity;
import green.teamproject.tentrental.purchase.dto.PurchaseDto;
import green.teamproject.tentrental.purchase.entity.PurchaseEntity;

public interface PurchaseService {
	
	int add(PurchaseDto dto);
	
	List<PurchaseDto> getList();
	
	Page<PurchaseDto> getPage(int page);
	
	PurchaseDto read(int goodsNo);
	
	void remove(int pcsNo);
	
	default PurchaseDto entityToDto(PurchaseEntity entity) {
		
		PurchaseDto dto = PurchaseDto.builder()
				.pcsNo(entity.getPcsNo())
				.goodsNo(entity.getGoodsEntity().getGoodsNo())
				.goodsName(entity.getGoodsEntity().getGoodsName())
				.userId(entity.getUserId())
				.pcsStartDate(entity.getPcsStartDate())
				.pcsEndDate(entity.getPcsEndDate())
				.explanation(entity.getExplanation())
				.build();
		return dto;	
	}
	
	default PurchaseEntity dtoToEntity(PurchaseDto dto) {
		
		GoodsEntity goodsEntity = GoodsEntity.builder().goodsName(dto.getGoodsName()).build();
		GoodsEntity goodsEntity2 = GoodsEntity.builder().goodsNo(dto.getGoodsNo()).build();
		
		PurchaseEntity entity = PurchaseEntity.builder()
				.pcsNo(dto.getPcsNo())
				.goodsEntity(goodsEntity)
				.goodsEntity(goodsEntity2)
				.userId(dto.getUserId())
				.pcsStartDate(dto.getPcsStartDate())
				.pcsEndDate(dto.getPcsEndDate())
				.explanation(dto.getExplanation())
				.build();
		return entity;
	}
	
	
	
	
}

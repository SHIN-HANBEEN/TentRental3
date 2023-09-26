package green.teamproject.tentrental.comment.service;

import java.util.List;

import org.springframework.data.domain.Page;

import green.teamproject.tentrental.comment.dto.CommentDTO;
import green.teamproject.tentrental.comment.entity.CommentEntity;
import green.teamproject.tentrental.goods.entity.GoodsEntity;

public interface CommentService {

	int register(CommentDTO dto); //댓글 등록
	
	List<CommentDTO> getList(int cmtNo); //특정 게시물의 댓글 등록
	
	CommentDTO read(int cmtNo); //댓글 읽기
	
	void modify(CommentDTO dto); //댓글 수정
	
	void remove(int cmtNo); //댓글 삭제

	default CommentDTO entityToDto(CommentEntity entity) {

		CommentDTO dto = CommentDTO.builder()
				.cmtNo(entity.getCmtNo())
				.cmtContents(entity.getCmtContents())
				.cmtWriter(entity.getCmtWriter())
				.cmtRegDate(entity.getCmtRegDate())
				.cmtModDate(entity.getCmtModDate())
				.build();
		return dto;
	}
	
	default CommentEntity dtoToEntity(CommentDTO dto) {
		
		GoodsEntity goodsentity = GoodsEntity.builder().goodsNo(dto.getGoodsNo()).build();

		
		CommentEntity entity = CommentEntity.builder()
				.cmtNo(dto.getCmtNo())
				.cmtContents(dto.getCmtContents())
				.cmtWriter(dto.getCmtWriter())
				.goodsEntity(goodsentity)
				.build();
				return entity;
	}
	
}

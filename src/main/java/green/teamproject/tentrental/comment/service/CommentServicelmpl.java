package green.teamproject.tentrental.comment.service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import green.teamproject.tentrental.comment.dto.CommentDTO;
import green.teamproject.tentrental.comment.entity.CommentEntity;
import green.teamproject.tentrental.comment.repository.CommentRepository;
import green.teamproject.tentrental.goods.entity.GoodsEntity;
@Service

public class CommentServicelmpl implements CommentService{
	
	@Autowired
	private CommentRepository repository;

	@Override
	public int register(CommentDTO dto) {
		CommentEntity entity = dtoToEntity(dto);
		repository.save(entity);
		return entity.getCmtNo();
	}
	
	@Override
	public List<CommentDTO> getList(int goodsNo) {
		List<CommentEntity> result = repository.getCommentEntityByGoodsEntity(GoodsEntity.builder().goodsNo(goodsNo).build());
		return result.stream().map(CommentEntity -> entityToDto(CommentEntity)).collect(Collectors.toList());
	}

	@Override
	public void modify(CommentDTO dto) {
		Optional<CommentEntity> result = repository.findById(dto.getCmtNo());
		if(result.isPresent()) {
			CommentEntity entity = result.get();
			entity.setCmtContents(dto.getCmtContents());
			repository.save(entity);
		}
	}

	@Override
	public void remove(int cmtNo) {
		repository.deleteById(cmtNo);
		
	}

	@Override
	public CommentDTO read(int cmtNo) {
		Optional<CommentEntity> result = repository.findById(cmtNo);
		if(result.isPresent()) {
			CommentEntity entity = result.get();
			return entityToDto(entity);
		} else {
			return null;
		}
		
	}
}

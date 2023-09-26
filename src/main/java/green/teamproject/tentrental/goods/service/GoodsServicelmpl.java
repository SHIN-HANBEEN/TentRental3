package green.teamproject.tentrental.goods.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import green.teamproject.tentrental.comment.repository.CommentRepository;
import green.teamproject.tentrental.goods.dto.GoodsDTO;
import green.teamproject.tentrental.goods.entity.GoodsEntity;
import green.teamproject.tentrental.goods.repository.GoodsRepository;
import green.teamproject.tentrental.util.FileUploadUtil;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Service
public class GoodsServicelmpl implements GoodsService{
	
	@Autowired
	private GoodsRepository repository;
	
	@Autowired
	private FileUploadUtil fileUploadUtil;
	
	@Autowired
	private CommentRepository commentRepository;

	//상품등록
	@Override
	public int register(GoodsDTO dto) {		
		GoodsEntity entity = dtoToEntity(dto);
		String imgPath = fileUploadUtil.fileUpload(dto.getUploadfile());
		entity.setImgPath(imgPath);
		repository.save(entity);
		return entity.getGoodsNo();
	}

	//상품조회
	@Override
	public Page<GoodsDTO> getList(int page) {
		int pageNum = (page == 0) ? 0 : page-1;
		Pageable pageable = PageRequest.of(pageNum, 10, Sort.by("goodsNo").descending());
		Page<GoodsEntity> entityPage = repository.findAll(pageable);
		Page<GoodsDTO> dtoPage = entityPage.map(entity->entityToDto(entity));
		return dtoPage;
	}

	//상품상세정보
	@Override
	public GoodsDTO read(int goodsNo) {
		Optional<GoodsEntity> result = repository.findById(goodsNo);
		if(result.isPresent()) {
			GoodsEntity entity = result.get();
			return entityToDto(entity);
		} else {
			return null;
		}
	}

	//상품수정
	@Override
	public void modify(GoodsDTO dto) {
		Optional<GoodsEntity> result = repository.findById(dto.getGoodsNo());
		if(result.isPresent()) {
			GoodsEntity entity = result.get();
			entity.setGoodsName(dto.getGoodsName());
			entity.setGoodsPrice(dto.getGoodsPrice());
			entity.setGoodsDescription(dto.getGoodsDescription());
			repository.save(entity);
		}
	}

	//상품제거 및 댓글정보 삭제
	@Transactional
	@Override
	public void remove(int goodsNo) {
		//해당 상품의 댓글을 모두 삭제
		commentRepository.deleteByGoodsNo(goodsNo);
		repository.deleteById(goodsNo);

	}

	//조회수, 중복방지
	
	@Override
	@Transactional 
	
	public Optional<GoodsEntity> updateView(int goodsNo, HttpSession session) {
		 
		 Optional<GoodsEntity> GoodsOptional = repository.findById(goodsNo);
		 
		 if(GoodsOptional.isPresent()) {
			 GoodsEntity entity = GoodsOptional.get();
			 String sessionKey = "viewed_post_" + goodsNo;
			 if(session.getAttribute(sessionKey) == null) {
				 entity.setGoodsView(entity.getGoodsView() + 1);
				 repository.save(entity);
				 
				 session.setAttribute(sessionKey, true);
			 }
			 
			 return Optional.of(entity);
		 } else {
			 return Optional.empty();
		 }
	 }

	//상품검색
	/*
	 * @Override
	 *
	 * @Transactional public List<GoodsEntity> search(String keyword) {
	 * List<GoodsEntity> goodsList = repository.findByTitleContaining(keyword);
	 * return goodsList;
	 *
	 * }
	 */
}
	 
	
	

	
	 
	  
	 
	

	
	
	  
	 


	

package green.teamproject.tentrental.goods.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;


import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import green.teamproject.tentrental.common.dto.PageRequestDTO;
import green.teamproject.tentrental.common.dto.PageResultDTO;
import green.teamproject.tentrental.goods.entity.QGoodsEntity;
import green.teamproject.tentrental.user.dto.UserDTO;
import green.teamproject.tentrental.user.entity.QUser;
import green.teamproject.tentrental.user.entity.User;
import green.teamproject.tentrental.user.entityenum.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import green.teamproject.tentrental.comment.repository.CommentRepository;
import green.teamproject.tentrental.goods.dto.GoodsDTO;
import green.teamproject.tentrental.goods.entity.GoodsEntity;
import green.teamproject.tentrental.goods.repository.GoodsRepository;
import green.teamproject.tentrental.purchase.repository.PurchaseRepository;
import green.teamproject.tentrental.util.FileUploadUtil;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Service
@Log4j2
@RequiredArgsConstructor
public class GoodsServicelmpl implements GoodsService{
	
	@Autowired
	private GoodsRepository repository;
	
	@Autowired
	private FileUploadUtil fileUploadUtil;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private PurchaseRepository purchaseRepository;

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
	public PageResultDTO<GoodsDTO, GoodsEntity> getList(PageRequestDTO requestDTO) {
		Pageable pageable = requestDTO.getPageable(Sort.by("regDate").descending());
		BooleanBuilder booleanBuilder = getSearch(requestDTO); // 검색 조건 처리
		Page<GoodsEntity> result = repository.findAll(booleanBuilder, pageable); // Querydsl 사용
		Function<GoodsEntity, GoodsDTO> fn = (entity -> entityToDto(entity));
		// entity to dto 의 결과로 dtoList 를 반환하게 된다.
		return new PageResultDTO<>(result, fn);
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

	@Override
	public List<GoodsDTO> getAllList() {
		List<GoodsEntity> entityList = repository.findAll();
		List<GoodsDTO> dtoList = new ArrayList<>();
		for(GoodsEntity entity : entityList) {
			GoodsDTO dto = entityToDto(entity);
			dtoList.add(dto);
		}
		return dtoList;
	}

	private BooleanBuilder getSearch(PageRequestDTO requestDTO) { //Querydsl 처리

		BooleanBuilder booleanBuilder = new BooleanBuilder();
		QGoodsEntity qGoodsEntity = QGoodsEntity.goodsEntity;

		BooleanExpression expression = qGoodsEntity.goodsName.isNotEmpty(); // userEmail isNotEmpty( )조건 생성
		booleanBuilder.and(expression); // 조건 탑재

		return booleanBuilder;
	}
}
	 
	
	

	
	 
	  
	 
	

	
	
	  
	 


	

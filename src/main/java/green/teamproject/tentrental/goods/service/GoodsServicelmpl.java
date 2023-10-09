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
import jakarta.persistence.criteria.CriteriaBuilder;
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
		String imgPath1 = fileUploadUtil.fileUpload1(dto.getUploadfile1());
		String imgPath2 = fileUploadUtil.fileUpload2(dto.getUploadfile2());
		entity.setImgPath(imgPath);
		entity.setImgPath1(imgPath1);
		entity.setImgPath2(imgPath2);
		repository.save(entity);
		return entity.getGoodsNo();
	}

	//상품조회
	@Override
	public PageResultDTO<GoodsDTO, GoodsEntity> getList(PageRequestDTO requestDTO) {
		Pageable pageable = requestDTO.getPageable(Sort.by("regDate").descending());
		BooleanBuilder booleanBuilder = getSearch(requestDTO); // 검색 조건 처리
		Page<GoodsEntity> result;
		if (requestDTO.getKeyword() == null) { //home page 에서는 조건 검색을 진행하지 않으므로, 분기 처리를 해주어야 한다.
			result = repository.findAll(pageable); //조건 검색 없이 검색 진행
		} else {
			result = repository.findAll(booleanBuilder, pageable); // Querydsl 사용
		}

		Function<GoodsEntity, GoodsDTO> fn = (entity -> entityToDto(entity));
		// entity to dto 의 결과로 dtoList 를 반환하게 된다.
		return new PageResultDTO<>(result, fn);
	}

	private BooleanBuilder getSearch(PageRequestDTO requestDTO) { //Querydsl 처리
		String type = requestDTO.getType();
		String keyword = requestDTO.getKeyword();
		String minPrice = requestDTO.getMinPrice();
		String maxPrice = requestDTO.getMaxPrice();
		int intMinPrice = 0; //0원 minPrice로 초기화
		int intMaxPrice = 10000000; //천만원 maxPrice 로 초기화

		if (minPrice!=null) { //가격 검색 input 을 클릭을 사용자가 하면, 기본 값으로 "" 공이 잡힌다. 그것을 처리해주어야 한다.
			if (!minPrice.isEmpty()) { //String 의 isEmpty() 와 isBlank() 는 String 이 Null 일 때 NullPointerException 을 발생시키므로, Null 여부를 먼저 파악해야 오류를 방지할 수 있다.
				intMinPrice = Integer.parseInt(minPrice);
			}
		}
		if (maxPrice!=null) {
			if (!maxPrice.isEmpty()) {
				intMaxPrice = Integer.parseInt(maxPrice);
			}
		}

		BooleanBuilder booleanBuilder = new BooleanBuilder(); // 최종적으로 반환될 검색 조건
		QGoodsEntity qGoodsEntity = QGoodsEntity.goodsEntity;

		BooleanExpression expression = qGoodsEntity.goodsName.isNotEmpty(); // userEmail isNotEmpty( )조건 생성
		booleanBuilder.and(expression); // 조건 탑재

		//검색 조건을 선정한 경우 검색 조건 작성
		BooleanBuilder conditionBuilder = new BooleanBuilder(); //조건을 쌓을 BooleanBuilder 객체인 conditionBuilder 생성
		if (type == null) {
			if (maxPrice == null) {
				if (minPrice == null) {
					return booleanBuilder;
				} else {
					conditionBuilder.and(qGoodsEntity.goodsPrice.gt(intMinPrice));
					// 검색 조건을 기존 조건에 통합한 후 검색 조건 반환
					booleanBuilder.and(conditionBuilder);
					return booleanBuilder;
				}
			} else {
				conditionBuilder.and(qGoodsEntity.goodsPrice.lt(intMaxPrice));
				if (minPrice == null) {
					// 검색 조건을 기존 조건에 통합한 후 검색 조건 반환
					booleanBuilder.and(conditionBuilder);
					return booleanBuilder;
				} else {
					conditionBuilder.and(qGoodsEntity.goodsPrice.gt(intMinPrice));
					// 검색 조건을 기존 조건에 통합한 후 검색 조건 반환
					booleanBuilder.and(conditionBuilder);
					return booleanBuilder;
				}
			}
		} else {
			if(type.contains("g")) { // type = g 일때는 상품명 검색
				conditionBuilder.or(qGoodsEntity.goodsName.contains(keyword));
			}
			if(type.contains("gd")) { // type = gd 일때는 상품명 or 상품설명 검색
				conditionBuilder.or(qGoodsEntity.goodsName.contains(keyword));
				conditionBuilder.or(qGoodsEntity.goodsDescription.contains(keyword));
			}
			if (maxPrice == null) {
				if (minPrice == null) {
					// 검색 조건을 기존 조건에 통합한 후 검색 조건 반환
					booleanBuilder.and(conditionBuilder);
					return booleanBuilder;
				} else {
					conditionBuilder.and(qGoodsEntity.goodsPrice.gt(intMinPrice));
					// 검색 조건을 기존 조건에 통합한 후 검색 조건 반환
					booleanBuilder.and(conditionBuilder);
					return booleanBuilder;
				}
			} else {
				conditionBuilder.and(qGoodsEntity.goodsPrice.lt(intMaxPrice));
				// 검색 조건을 기존 조건에 통합한 후 검색 조건 반환
				booleanBuilder.and(conditionBuilder);
				return booleanBuilder;
			}
		}
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


}
	 
	
	

	
	 
	  
	 
	

	
	
	  
	 


	

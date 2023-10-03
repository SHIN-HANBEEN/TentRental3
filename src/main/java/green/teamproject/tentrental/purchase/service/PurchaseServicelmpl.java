package green.teamproject.tentrental.purchase.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import green.teamproject.tentrental.purchase.dto.PurchaseDto;
import green.teamproject.tentrental.purchase.entity.PurchaseEntity;
import green.teamproject.tentrental.purchase.repository.PurchaseRepository;

@Service

public class PurchaseServicelmpl implements PurchaseService {
	
	@Autowired
	private PurchaseRepository repository;

	@Override
	public int add(PurchaseDto dto) {
		PurchaseEntity purchaseEntity = dtoToEntity(dto);
		repository.save(purchaseEntity);
		return purchaseEntity.getPcsNo();
	}

	@Override
	public void remove(int pcsNo) {
		repository.deleteById(pcsNo);
	}

	@Override
	public List<PurchaseDto> getList() {
		List<PurchaseEntity> entityList = repository.findAll();
		List<PurchaseDto> dtoList = new ArrayList<>();
		for(PurchaseEntity entity : entityList) {
			PurchaseDto dto = entityToDto(entity);
			dtoList.add(dto);
		}
		return dtoList;
	}

	@Override
	public Page<PurchaseDto> getPage(int page) {
		int pageNum = (page == 0) ? 0 : page -1;
		Pageable pageable = (Pageable) PageRequest.of(pageNum, 10, Sort.by("pcsNo").descending());
		Page<PurchaseEntity> entityPage = repository.findAll(pageable);
		Page<PurchaseDto> dtoPage = entityPage.map(entity -> entityToDto(entity));
		return dtoPage;
	}

	@Override
	public PurchaseDto read(int goodsNo) {
		Optional<PurchaseEntity> result = repository.findById(goodsNo);
		if(result.isPresent()) {
			PurchaseEntity entity = result.get();
			return entityToDto(entity);
		}
		return null;
	}


	
	
	

	

}

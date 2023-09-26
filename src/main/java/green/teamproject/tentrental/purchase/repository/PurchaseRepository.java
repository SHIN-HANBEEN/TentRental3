package green.teamproject.tentrental.purchase.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import green.teamproject.tentrental.purchase.entity.PurchaseEntity;

public interface PurchaseRepository extends JpaRepository<PurchaseEntity, Integer>{

	
	
}

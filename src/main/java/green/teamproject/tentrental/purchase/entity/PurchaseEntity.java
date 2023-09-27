package green.teamproject.tentrental.purchase.entity;
import java.time.LocalDate;

import green.teamproject.tentrental.common.entity.BaseEntity;
import green.teamproject.tentrental.goods.entity.GoodsEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tbl_purchase")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder

public class PurchaseEntity extends BaseEntity {
	
	//예약번호
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pcsNo;
	
	//상품번호를 받아오기 위한 외래키
	@ManyToOne
	private GoodsEntity goodsEntity;
	
	//유저아이디
	@Column
	private String userId;
	
	//예약시작일
	@Column(nullable = false)
	private LocalDate pcsStartDate;
	
	//예약종료일
	@Column(nullable = false)
	private LocalDate pcsEndDate;
	
	@Column(length = 1000)
	private String explanation; //비고
	
}

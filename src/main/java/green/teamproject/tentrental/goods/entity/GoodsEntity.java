package green.teamproject.tentrental.goods.entity;

import green.teamproject.tentrental.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tbl_board")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder


public class GoodsEntity extends BaseEntity {
	
	//상품번호
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int goodsNo;
	
	//상품명
	@Column(length = 200, nullable = false)
	private String goodsName;
	
	//상품가격
	@Column(length = 10)
	private int goodsPrice;
	
	//상품설명
	@Column(length = 200, nullable = false)
	private String goodsDescription;
	
	//조회수
	@Column(columnDefinition = "integer default 0", nullable = false) 
	private int goodsView;
	 
	//이미지첨부
	@Column(length = 200, nullable = true)
	private String imgPath;

	//이미지첨부
	@Column(length = 200, nullable = true)
	private String imgPath1;

	//이미지첨부
	@Column(length = 200, nullable = true)
	private String imgPath2;
	
	//검색
	//private String title;
	
	
}

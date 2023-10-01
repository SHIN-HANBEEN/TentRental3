package green.teamproject.tentrental.purchase.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class PurchaseDto {
	
	private int pcsNo; //예약번호
	
	private int goodsNo; //상품번호
	
	private String goodsName; //상품이름
	
	private String userId; //유저아이디
	
	private LocalDate pcsStartDate; //예약시작일
	
	private LocalDate pcsEndDate; //예약종료일
	
	private String explanation; //비고
}

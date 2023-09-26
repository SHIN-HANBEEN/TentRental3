package green.teamproject.tentrental.cart.dto;

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

public class CartDto {
	
	private int cartNo;
	
	private String member;
	
	private int goodsNo;
	
	private int quantity;

}

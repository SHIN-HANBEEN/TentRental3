package green.teamproject.tentrental.goods.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class GoodsDTO {
	
	private int goodsNo;
	private String goodsName;
	private int goodsPrice;
	private String goodsDescription;
	private LocalDateTime goodsRegDate;
	private LocalDateTime goodsModDate;
	private int goodsView;
	private String imgPath; //조회
	private MultipartFile uploadfile; //등록
}

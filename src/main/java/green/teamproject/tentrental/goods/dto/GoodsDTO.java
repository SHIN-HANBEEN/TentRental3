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
	private LocalDateTime regDate;
	private LocalDateTime modDate;
	private int goodsView;
	private String imgPath; //조회
	private String imgPath1; //조회
	private String imgPath2; //조회
	private MultipartFile uploadfile; //등록
	private MultipartFile uploadfile1; //등록
	private MultipartFile uploadfile2; //등록
}

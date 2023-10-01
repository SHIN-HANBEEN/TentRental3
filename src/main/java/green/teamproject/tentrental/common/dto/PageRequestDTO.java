package green.teamproject.tentrental.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Builder
@AllArgsConstructor
@Data
public class PageRequestDTO {
    private int page;
    private int size;
    private String type; // 일반 검색 조건
    private String keyword; // 일반 검색 키워드
    private String r_type; // 권한 검색 조건
    private String r_keyword; // 권한 검색 키워드
    private String minPrice; // 상품 검색 조건
    private String maxPrice; // 상품 검색 조건

    private int goodsNo; // 상품번호
    private String goodsName; // 상품명
    private int goodsPrice; // 상품가격
    private String imgPath; // 이미지 주소


    public PageRequestDTO() {
        this.page = 1;
        this.size = 10;
    }
    public Pageable getPageable(Sort sort) {
        return PageRequest.of(page -1, size, sort);
    }
}

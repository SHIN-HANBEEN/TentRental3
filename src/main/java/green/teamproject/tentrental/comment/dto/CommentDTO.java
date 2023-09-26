package green.teamproject.tentrental.comment.dto;

import java.time.LocalDate;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

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

public class CommentDTO {
	
	private int cmtNo;
	
	private String cmtContents;
	
	private String cmtWriter;
	
	private int goodsNo;
	
	private LocalDateTime cmtRegDate;
	
	private LocalDateTime cmtModDate;

}

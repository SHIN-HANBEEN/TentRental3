package green.teamproject.tentrental.comment.entity;

import org.springframework.stereotype.Service;

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
@Table(name = "tbl_comment")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CommentEntity extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cmtNo;
	
	@Column(length = 1000, nullable = false)
	private String cmtContents;
	
	@Column(length = 20, nullable = false)
	private String cmtWriter;
	
	@ManyToOne
	private GoodsEntity goodsEntity;
	
}

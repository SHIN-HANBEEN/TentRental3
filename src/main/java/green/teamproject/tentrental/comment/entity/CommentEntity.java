package green.teamproject.tentrental.comment.entity;

import green.teamproject.tentrental.user.entity.User;
import jakarta.persistence.*;
import org.springframework.stereotype.Service;

import green.teamproject.tentrental.goods.entity.GoodsEntity;
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
@ToString(exclude = {"user", "goodsEntity"})
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

	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private GoodsEntity goodsEntity;
	
}

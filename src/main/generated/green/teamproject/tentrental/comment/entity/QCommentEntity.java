package green.teamproject.tentrental.comment.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCommentEntity is a Querydsl query type for CommentEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCommentEntity extends EntityPathBase<CommentEntity> {

    private static final long serialVersionUID = 1845885020L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCommentEntity commentEntity = new QCommentEntity("commentEntity");

    public final green.teamproject.tentrental.common.entity.QBaseEntity _super = new green.teamproject.tentrental.common.entity.QBaseEntity(this);

    public final StringPath cmtContents = createString("cmtContents");

    public final NumberPath<Integer> cmtNo = createNumber("cmtNo", Integer.class);

    public final StringPath cmtWriter = createString("cmtWriter");

    public final green.teamproject.tentrental.goods.entity.QGoodsEntity goodsEntity;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final green.teamproject.tentrental.user.entity.QUser user;

    public QCommentEntity(String variable) {
        this(CommentEntity.class, forVariable(variable), INITS);
    }

    public QCommentEntity(Path<? extends CommentEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCommentEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCommentEntity(PathMetadata metadata, PathInits inits) {
        this(CommentEntity.class, metadata, inits);
    }

    public QCommentEntity(Class<? extends CommentEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.goodsEntity = inits.isInitialized("goodsEntity") ? new green.teamproject.tentrental.goods.entity.QGoodsEntity(forProperty("goodsEntity")) : null;
        this.user = inits.isInitialized("user") ? new green.teamproject.tentrental.user.entity.QUser(forProperty("user")) : null;
    }

}


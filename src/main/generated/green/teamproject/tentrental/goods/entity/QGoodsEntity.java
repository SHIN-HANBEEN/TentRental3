package green.teamproject.tentrental.goods.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QGoodsEntity is a Querydsl query type for GoodsEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGoodsEntity extends EntityPathBase<GoodsEntity> {

    private static final long serialVersionUID = -561846852L;

    public static final QGoodsEntity goodsEntity = new QGoodsEntity("goodsEntity");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath goodsDescription = createString("goodsDescription");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> goodsModDate = _super.goodsModDate;

    public final StringPath goodsName = createString("goodsName");

    public final NumberPath<Integer> goodsNo = createNumber("goodsNo", Integer.class);

    public final NumberPath<Integer> goodsPrice = createNumber("goodsPrice", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> goodsRegDate = _super.goodsRegDate;

    public final NumberPath<Integer> goodsView = createNumber("goodsView", Integer.class);

    public final StringPath imgPath = createString("imgPath");

    public QGoodsEntity(String variable) {
        super(GoodsEntity.class, forVariable(variable));
    }

    public QGoodsEntity(Path<? extends GoodsEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGoodsEntity(PathMetadata metadata) {
        super(GoodsEntity.class, metadata);
    }

}


package green.teamproject.tentrental.purchase.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPurchaseEntity is a Querydsl query type for PurchaseEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPurchaseEntity extends EntityPathBase<PurchaseEntity> {

    private static final long serialVersionUID = 1540831000L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPurchaseEntity purchaseEntity = new QPurchaseEntity("purchaseEntity");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath explanation = createString("explanation");

    public final green.teamproject.tentrental.goods.entity.QGoodsEntity goodsEntity;

    public final DatePath<java.time.LocalDate> pcsEndDate = createDate("pcsEndDate", java.time.LocalDate.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> pcsModDate = _super.pcsModDate;

    public final NumberPath<Integer> pcsNo = createNumber("pcsNo", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> pcsRegDate = _super.pcsRegDate;

    public final DatePath<java.time.LocalDate> pcsStartDate = createDate("pcsStartDate", java.time.LocalDate.class);

    public final StringPath userId = createString("userId");

    public QPurchaseEntity(String variable) {
        this(PurchaseEntity.class, forVariable(variable), INITS);
    }

    public QPurchaseEntity(Path<? extends PurchaseEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPurchaseEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPurchaseEntity(PathMetadata metadata, PathInits inits) {
        this(PurchaseEntity.class, metadata, inits);
    }

    public QPurchaseEntity(Class<? extends PurchaseEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.goodsEntity = inits.isInitialized("goodsEntity") ? new green.teamproject.tentrental.goods.entity.QGoodsEntity(forProperty("goodsEntity")) : null;
    }

}


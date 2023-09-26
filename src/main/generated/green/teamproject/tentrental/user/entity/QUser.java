package green.teamproject.tentrental.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -2022965655L;

    public static final QUser user = new QUser("user");

    public final green.teamproject.tentrental.common.entity.QBaseEntity _super = new green.teamproject.tentrental.common.entity.QBaseEntity(this);

    public final BooleanPath fromSocial = createBoolean("fromSocial");

    public final EnumPath<green.teamproject.tentrental.user.entityenum.Gender> gender = createEnum("gender", green.teamproject.tentrental.user.entityenum.Gender.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final SetPath<green.teamproject.tentrental.user.entityenum.Role, EnumPath<green.teamproject.tentrental.user.entityenum.Role>> role = this.<green.teamproject.tentrental.user.entityenum.Role, EnumPath<green.teamproject.tentrental.user.entityenum.Role>>createSet("role", green.teamproject.tentrental.user.entityenum.Role.class, EnumPath.class, PathInits.DIRECT2);

    public final StringPath userAddressDetail = createString("userAddressDetail");

    public final StringPath userAddressMain = createString("userAddressMain");

    public final StringPath userAddressNumber = createString("userAddressNumber");

    public final StringPath userAddressReference = createString("userAddressReference");

    public final StringPath userDirectory = createString("userDirectory");

    public final StringPath userEmail = createString("userEmail");

    public final StringPath userName = createString("userName");

    public final StringPath userPhoneNumber = createString("userPhoneNumber");

    public final StringPath userPw = createString("userPw");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}


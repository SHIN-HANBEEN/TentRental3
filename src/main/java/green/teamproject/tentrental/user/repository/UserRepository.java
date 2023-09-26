package green.teamproject.tentrental.user.repository;

import green.teamproject.tentrental.user.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String>, QuerydslPredicateExecutor<User> {
    @EntityGraph(attributePaths = {"role"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select m from User m where m.fromSocial = :fromSocial and m.userEmail = :userEmail")
    Optional<User> findByEmail(@Param("userEmail") String userEmail, @Param("fromSocial") boolean fromSocial);
}

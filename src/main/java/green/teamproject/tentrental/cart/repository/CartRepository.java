package green.teamproject.tentrental.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import green.teamproject.tentrental.cart.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer>{

}

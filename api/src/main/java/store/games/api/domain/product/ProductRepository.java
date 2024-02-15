package store.games.api.domain.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface ProductRepository  extends JpaRepository<Product, String> {

    Page<Product> findAllByStatusTrue(Pageable paginacao);
}

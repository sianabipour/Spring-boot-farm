package khash.Farm.dao;

import khash.Farm.Models.Sheep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmRepository extends JpaRepository<Sheep, Long> {}

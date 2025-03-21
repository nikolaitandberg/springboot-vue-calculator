package no.ntnu.idatt2105.V25.oving5.repo;

import no.ntnu.idatt2105.V25.oving5.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import no.ntnu.idatt2105.V25.oving5.model.Calculation;

import java.util.List;

@Repository
public interface CalculationRepository extends JpaRepository<Calculation, Long> {
    List<Calculation> findAllByUser(User user);
}
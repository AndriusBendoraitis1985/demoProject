package lt.bank.account.demo.project.repository;

import lt.bank.account.demo.project.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long>, CustomOperationRepository {
}

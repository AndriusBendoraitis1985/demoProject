package lt.bank.account.demo.project.repository;

import lt.bank.account.demo.project.model.Operation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.List;

public class CustomOperationRepositoryImpl implements CustomOperationRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Operation> findAllByAccountDateFromDateTo(String account, LocalDateTime dateFrom, LocalDateTime dateTo) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Operation> q = criteriaBuilder.createQuery(Operation.class);
        Root<Operation> operation = q.from(Operation.class);

        Predicate predicate = criteriaBuilder.equal(operation.get("accountNumber"), account);

        if (dateFrom != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(operation.get("date"), dateFrom));
        }
        if (dateTo != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(operation.get("date"), dateTo));
        }
        q.select(operation).where(predicate);

        TypedQuery<Operation> query = entityManager.createQuery(q);

        return query.getResultList();
    }
}

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
import java.util.ArrayList;
import java.util.List;

public class CustomOperationRepositoryImpl implements CustomOperationRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Operation> findAllByAccountDateFromDateTo(String account, LocalDateTime dateFrom, LocalDateTime dateTo) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Operation> q = criteriaBuilder.createQuery(Operation.class);
        Root<Operation> operation = q.from(Operation.class);
        List<Predicate> predicates = new ArrayList<>();

        if (account != null) {
            predicates.add(criteriaBuilder.equal(operation.get("accountNumber"), account));
        }
        if (dateFrom != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(operation.get("date"), dateFrom));
        }
        if (dateTo != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(operation.get("date"), dateTo));
        }
        q.select(operation).where(predicates.toArray(new Predicate[0]));
        q.orderBy(criteriaBuilder.desc(operation.get("date")));

        TypedQuery<Operation> query = entityManager.createQuery(q);

        return query.getResultList();
    }
}

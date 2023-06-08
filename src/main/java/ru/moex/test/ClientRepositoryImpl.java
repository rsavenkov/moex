package ru.moex.test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Client repository implementation
 */
public class ClientRepositoryImpl implements CustomClientRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Client> findAllByFields(String lastName, String firstName, String thirdName, String phone, String email) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Client> cq = cb.createQuery(Client.class);
        Root<Client> root = cq.from(Client.class);
        List<Predicate> predicates = new ArrayList<>();
        if (lastName != null) {
            predicates.add(cb.equal(root.get("lastName"), lastName));
        }
        if (firstName != null) {
            predicates.add(cb.equal(root.get("firstName"), firstName));
        }
        if (thirdName != null) {
            predicates.add(cb.equal(root.get("thirdName"), thirdName));
        }
        if (phone != null) {
            predicates.add(cb.equal(root.get("phone"), phone));
        }
        if (email != null) {
            predicates.add(cb.equal(root.get("email"), email));
        }
        cq.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
        return entityManager.createQuery(cq).getResultList();
    }
}

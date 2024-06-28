package com.wrkspot.assignment.service.spec;

import com.wrkspot.assignment.model.Address;
import com.wrkspot.assignment.model.Customer;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

public class CustomerSpecification {

    public static Specification<Customer> searchCustomers(String name, String city, String state) {
        return (Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (name != null && !name.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("firstName"), name));
            }

            Join<Customer, Address> addressJoin = root.join("addresses", JoinType.INNER);

            if (city != null && !city.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(addressJoin.get("city"), city));
            }

            if (state != null && !state.isEmpty()) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(addressJoin.get("state"), state));
            }

            query.distinct(true); // Ensures distinct results

            return predicate;
        };
    }
}


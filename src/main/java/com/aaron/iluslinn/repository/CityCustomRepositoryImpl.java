package com.aaron.iluslinn.repository;

import com.aaron.iluslinn.model.City;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository
@RequiredArgsConstructor
public class CityCustomRepositoryImpl implements CityCustomRepository {

    private final EntityManager entityManager;

    @Override
    public Page<City> findAll(Specification<City> specification, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<City> criteriaQuery = criteriaBuilder.createQuery(City.class);
        Root<City> root = criteriaQuery.from(City.class);
        Predicate predicate = specification.toPredicate(root, criteriaQuery, criteriaBuilder);

        if (predicate != null) {
            criteriaQuery.where(predicate);
        }

        List<City> employees = entityManager.createQuery(criteriaQuery)
            .setFirstResult((int) pageable.getOffset())
            .setMaxResults(pageable.getPageSize())
            .getResultList();


        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<City> roots = query.from(City.class);
        query = query.select(builder.count(roots));
        predicate = specification.toPredicate(roots, query, builder);
        if (predicate != null) {
            query.where(predicate);
        }
        Long total = entityManager.createQuery(query).getSingleResult();

        return new PageImpl<>(employees, pageable, total);
    }
}

package com.shubho.cupon.repo;

import com.shubho.cupon.entity.Cupon;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuponRepo extends JpaRepository<Cupon,Integer> {

}

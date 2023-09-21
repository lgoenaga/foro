package com.alura.foro.repository;

import com.alura.foro.model.Discusion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscusionRepository extends JpaRepository<Discusion, Long> {
}

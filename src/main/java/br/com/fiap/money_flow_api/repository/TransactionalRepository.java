package br.com.fiap.money_flow_api.repository;

import br.com.fiap.money_flow_api.model.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionalRepository extends JpaRepository<Transactional, Long> {
}

package chukhlantsev.oleg;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FraudCheckHistoryRepositor extends JpaRepository<FraudCheckHistory, Integer> {
}

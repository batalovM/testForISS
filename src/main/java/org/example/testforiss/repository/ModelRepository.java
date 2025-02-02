package org.example.testforiss.repository;
import org.example.testforiss.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * @author batal
 * @Date 01.02.2025
 */
@Repository
public interface ModelRepository extends JpaRepository<Model, UUID> {
    List<Model> findByDeviceId(UUID deviceId);
    boolean existsBySerialNumber(String serialNumber);
}
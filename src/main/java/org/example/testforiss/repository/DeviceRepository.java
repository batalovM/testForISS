package org.example.testforiss.repository;


import org.example.testforiss.entity.Device;
import org.example.testforiss.entity.DeviceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * @author batal
 * @Date 01.02.2025
 */
@Repository
public interface DeviceRepository extends JpaRepository<Device, UUID> {
    List<Device> findByType(DeviceType type);
    List<Device> findByNameContainingIgnoreCase(String name);
}
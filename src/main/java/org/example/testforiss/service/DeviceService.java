package org.example.testforiss.service;

import org.example.testforiss.entity.Device;
import org.example.testforiss.entity.DeviceType;
import org.example.testforiss.entity.Model;
import org.example.testforiss.repository.DeviceRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author batal
 * @Date 01.02.2025
 */
@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    public Optional<Device> getDeviceById(UUID id) {
        return deviceRepository.findById(id);
    }

    public List<Device> searchDevicesByName(String name) {
        return deviceRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Device> filterDevices(DeviceType type, String color, BigDecimal minPrice, BigDecimal maxPrice) {
        return deviceRepository.findAll().stream()
                .filter(device -> type == null || device.getType() == type)
                .map(device -> {
                    List<Model> filteredModels = device.getModels().stream()
                            .filter(model -> color == null || model.getColor().equalsIgnoreCase(color))
                            .filter(model -> minPrice == null || model.getPrice().compareTo(minPrice) >= 0)
                            .filter(model -> maxPrice == null || model.getPrice().compareTo(maxPrice) <= 0)
                            .collect(Collectors.toList());

                    Device filteredDevice = new Device(
                            device.getId(),
                            device.getName(),
                            device.getManufacturerCountry(),
                            device.getManufacturerCompany(),
                            device.isOnlineOrderAvailable(),
                            device.isInstallmentAvailable(),
                            device.getType(),
                            filteredModels
                    );

                    return filteredModels.isEmpty() ? null : filteredDevice;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }



    public List<Device> getSortedDevices(String sortBy) {
        if (!"name".equalsIgnoreCase(sortBy) && !"price".equalsIgnoreCase(sortBy)) {
            return Collections.emptyList();
        }

        List<Device> devices = deviceRepository.findAll();

        if ("name".equalsIgnoreCase(sortBy)) {
            devices.sort(Comparator.comparing(Device::getName));
        } else if ("price".equalsIgnoreCase(sortBy)) {
            devices.sort(Comparator.comparing(device ->
                    device.getModels().stream()
                            .map(Model::getPrice)
                            .min(BigDecimal::compareTo)
                            .orElse(BigDecimal.ZERO) // Если нет моделей, считаем цену 0
            ));
        }

        return devices;
    }


    public Device saveDevice(Device device) {
        return deviceRepository.save(device);
    }

    public boolean deleteDevice(UUID id) {
        Optional<Device> device = deviceRepository.findById(id);
        if (device.isPresent()) {
            deviceRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
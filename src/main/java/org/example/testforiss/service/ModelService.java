package org.example.testforiss.service;

import org.example.testforiss.entity.Device;
import org.example.testforiss.entity.Model;
import org.example.testforiss.repository.DeviceRepository;
import org.example.testforiss.repository.ModelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author batal
 * @Date 01.02.2025
 */
@Service
public class ModelService {

    private final ModelRepository modelRepository;
    private final DeviceRepository deviceRepository;

    public ModelService(ModelRepository modelRepository, DeviceRepository deviceRepository) {
        this.modelRepository = modelRepository;
        this.deviceRepository = deviceRepository;
    }

    public List<Model> getAllModels() {
        return modelRepository.findAll();
    }

    public Optional<Model> getModelById(UUID id) {
        return modelRepository.findById(id);
    }

    public List<Model> getModelsByDevice(UUID deviceId) {
        return modelRepository.findByDeviceId(deviceId);
    }

    public Model saveModel(Model model, UUID deviceId) {
        Optional<Device> deviceOpt = deviceRepository.findById(deviceId);
        if (deviceOpt.isEmpty()) {
            throw new IllegalArgumentException("Устройство с указанным ID не найдено.");
        }

        if (modelRepository.existsBySerialNumber(model.getSerialNumber())) {
            throw new IllegalArgumentException("Модель с таким серийным номером уже существует.");
        }

        model.setDevice(deviceOpt.get());
        return modelRepository.save(model);
    }


    public boolean deleteModel(UUID id) {
        Optional<Model> model = modelRepository.findById(id);
        if (model.isPresent()) {
            modelRepository.deleteById(id);
            return true;
        }
        return false;
    }

}

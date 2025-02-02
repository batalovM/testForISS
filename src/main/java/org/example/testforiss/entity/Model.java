package org.example.testforiss.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author batal
 * @Date 01.02.2025
 */
@Entity
@Table(name = "models")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String serialNumber;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private String size;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private boolean availability;

    // 🔹 Дополнительные поля в зависимости от типа устройства
    private String category;        // TV, PC
    private String technology;      // TV
    private Double dustContainerVolume; // Vacuum
    private Integer modeCount;      // Vacuum
    private Integer doorCount;      // Fridge
    private String compressorType;  // Fridge
    private Integer memory;         // Smartphone
    private Integer cameraCount;    // Smartphone
    private String processorType;   // PC

    @ManyToOne
    @JoinColumn(name = "device_id", nullable = false)
    @JsonBackReference
    private Device device;
}



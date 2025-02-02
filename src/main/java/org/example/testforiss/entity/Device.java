package org.example.testforiss.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author batal
 * @Date 01.02.2025
 */
@Entity
@Table(name = "devices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String manufacturerCountry;

    @Column(nullable = false)
    private String manufacturerCompany;

    @Column(nullable = false)
    private boolean onlineOrderAvailable;

    @Column(nullable = false)
    private boolean installmentAvailable;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeviceType type;

    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Model> models = new ArrayList<>();
}









































































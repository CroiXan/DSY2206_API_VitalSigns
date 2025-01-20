package com.duoc.VitalSigns.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "vital_signs")
@Setter
@Getter
public class VitalSign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "El ID del paciente es obligatorio")
    @Column(name = "id_paciente", nullable = false)
    private int idPaciente;

    @NotNull(message = "La frecuencia cardíaca es obligatoria")
    @Min(value = 0, message = "La frecuencia cardíaca debe ser al menos 0 lpm")
    @Max(value = 300, message = "La frecuencia cardíaca no puede ser mayor a 300 lpm")
    @Column(name = "frecuencia_cardiaca", nullable = false)
    private int frecuenciaCardiaca;

    @NotNull(message = "La frecuencia respiratoria es obligatoria")
    @Min(value = 0, message = "La frecuencia respiratoria debe ser al menos 0 rpm")
    @Max(value = 100, message = "La frecuencia respiratoria no puede ser mayor a 100 rpm")
    @Column(name = "frecuencia_respiratoria", nullable = false)
    private int frecuenciaRespiratoria;

    @NotNull(message = "La presión arterial sistólica es obligatoria")
    @Min(value = 0, message = "La presión sistólica debe ser al menos 0 mmHg")
    @Max(value = 300, message = "La presión sistólica no puede ser mayor a 300 mmHg")
    @Column(name = "presion_arterial_sistolica", nullable = false)
    private int presionArterialSistolica;

    @NotNull(message = "La presión arterial diastólica es obligatoria")
    @Min(value = 0, message = "La presión diastólica debe ser al menos 0 mmHg")
    @Max(value = 200, message = "La presión diastólica no puede ser mayor a 200 mmHg")
    @Column(name = "presion_arterial_diastolica", nullable = false)
    private int presionArterialDiastolica;

    @NotNull(message = "La temperatura corporal es obligatoria")
    @DecimalMin(value = "0.0", message = "La temperatura corporal debe ser al menos 0.0 °C")
    @DecimalMax(value = "50.0", message = "La temperatura corporal no puede ser mayor a 50.0 °C")
    @Column(name = "temperatura_corporal", nullable = false)
    private Double temperaturaCorporal;

    @NotNull(message = "La saturación de oxígeno es obligatoria")
    @DecimalMin(value = "0.0", message = "La saturación de oxígeno debe ser al menos 0%")
    @DecimalMax(value = "100.0", message = "La saturación de oxígeno no puede ser mayor a 100%")
    @Column(name = "saturacion_oxigeno", nullable = false)
    private Double saturacionOxigeno;

    @NotNull(message = "La fecha y hora del registro es obligatoria")
    @Column(name = "instante", nullable = false)
    private LocalDateTime instante;

}

package com.duoc.VitalSigns.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.duoc.VitalSigns.models.VitalSign;

public interface VitalSignRepository extends JpaRepository<VitalSign, Integer> {

    @Query(value = "SELECT * FROM vital_signs WHERE id_paciente = :idPaciente ORDER BY instante DESC FETCH FIRST 1 ROWS ONLY", nativeQuery = true)
    Optional<VitalSign> findLatestByIdPaciente(@Param("idPaciente") int idPaciente);

    @Query("SELECT v FROM VitalSign v WHERE v.idPaciente = :idPaciente ORDER BY v.instante DESC")
    List<VitalSign> findAllByIdPacienteOrdered(@Param("idPaciente") int idPaciente);

    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END FROM patientdsy2205 WHERE id = :idPaciente", nativeQuery = true)
    int existsPacienteById(@Param("idPaciente") int idPaciente);

}

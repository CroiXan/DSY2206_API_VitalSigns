package com.duoc.VitalSigns.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.duoc.VitalSigns.models.VitalSign;

public interface VitalSignRepository extends JpaRepository<VitalSign,Integer>{

    @Query("SELECT v FROM VitalSign v WHERE v.idPaciente = :idPaciente ORDER BY v.instante DESC")
    VitalSign findLatestByIdPaciente(@Param("idPaciente") int idPaciente);

    @Query("SELECT v FROM VitalSign v WHERE v.idPaciente = :idPaciente ORDER BY v.instante DESC")
    List<VitalSign> findAllByIdPacienteOrdered(@Param("idPaciente") int idPaciente);

    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END FROM patientdsy2205 WHERE id = :idPaciente", nativeQuery = true)
    int existsPacienteById(@Param("idPaciente") int idPaciente);
    
}

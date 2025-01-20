package com.duoc.VitalSigns.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duoc.VitalSigns.exception.ResourceNotFoundException;
import com.duoc.VitalSigns.models.VitalSign;
import com.duoc.VitalSigns.service.VitalSignService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/vitalsign")
@Validated
public class VitalSignController {

    @Autowired
    private VitalSignService vitalSignService;

    @GetMapping
    public ResponseEntity<List<VitalSign>> getAllVitalSigns() {
        List<VitalSign> vitalSigns = vitalSignService.getAllVitalSigns();
        return ResponseEntity.ok(vitalSigns);
    }
    
    @PostMapping
    public ResponseEntity<Object> createVitalSign(@Valid @RequestBody VitalSign vitalSign) {
        if (vitalSignService.checkPatientId(vitalSign.getIdPaciente())) {
            VitalSign newVitalSign = vitalSignService.saveVitalSign(vitalSign);
            return new ResponseEntity<>(newVitalSign, HttpStatus.CREATED);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente no encontrado");
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<VitalSign> getVitalSignById(@PathVariable int id) {
        VitalSign vitalSign = vitalSignService.findVitalSignById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Signo vital con ID "+ id +" no se encuentra"));
        return ResponseEntity.ok(vitalSign);
    }

    @GetMapping("/last/patient/{id}")
    public ResponseEntity<VitalSign> getLastVitalSignByPatient(@PathVariable int id) {
        VitalSign vitalSign = vitalSignService.findLatestByIdPaciente(id);
        return ResponseEntity.ok(vitalSign);
    }

    @GetMapping("/patient/{id}")
    public ResponseEntity<List<VitalSign>> getVitalSignByPatient(@PathVariable int id) {
        List<VitalSign> vitalSignList = vitalSignService.findAllByIdPacienteOrdered(id);
        return ResponseEntity.ok(vitalSignList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VitalSign> updateVitalSign(@PathVariable int id, @Valid @RequestBody VitalSign updatedVitalSign ) {
        VitalSign vitalSign = vitalSignService.findVitalSignById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Signo vital con ID "+ id +" no se encuentra"));
        VitalSign vitalSignResult = vitalSignService.saveVitalSign(vitalSign);
        return ResponseEntity.ok(vitalSignResult);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVitalSign(@PathVariable int id){
        vitalSignService.findVitalSignById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Signo vital con ID "+ id +" no se encuentra"));
        vitalSignService.deleteVitalSignById(id);
        return ResponseEntity.noContent().build();
    }

}

package com.duoc.VitalSigns.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duoc.VitalSigns.models.VitalSign;
import com.duoc.VitalSigns.repository.VitalSignRepository;

@Service
public class VitalSignService {

    @Autowired
    private VitalSignRepository vitalSignRepository;

    public List<VitalSign> getAllVitalSigns(){
        return vitalSignRepository.findAll();
    }

    public Optional<VitalSign> findVitalSignById(int id){
        return vitalSignRepository.findById(id);
    }

    public VitalSign saveVitalSign (VitalSign vitalSign){
        return vitalSignRepository.save(vitalSign);
    }

    public void deleteVitalSignById(int id){
        vitalSignRepository.deleteById(id);
    }

    public VitalSign findLatestByIdPaciente(int idPaciente){
        Optional<VitalSign> vitalSign = vitalSignRepository.findLatestByIdPaciente(idPaciente);
        if (vitalSign.isPresent()) {
            return vitalSign.get();
        } else {
            return new VitalSign();
        }
    }

    public List<VitalSign> findAllByIdPacienteOrdered(int idPaciente){
        return vitalSignRepository.findAllByIdPacienteOrdered(idPaciente);
    }

    public boolean checkPatientId(int idPaciente){
        return vitalSignRepository.existsPacienteById(idPaciente) == 1;
    }

}

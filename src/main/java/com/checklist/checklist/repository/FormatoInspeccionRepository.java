

package com.checklist.checklist.repository;

import com.checklist.checklist.models.FormatoInspeccion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormatoInspeccionRepository extends JpaRepository<FormatoInspeccion, Integer>{
    
    List<FormatoInspeccion> findByEstado(String estado);

}

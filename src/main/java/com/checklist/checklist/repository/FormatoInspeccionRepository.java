

package com.checklist.checklist.repository;

import com.checklist.checklist.models.FormatoInspeccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormatoInspeccionRepository extends JpaRepository<FormatoInspeccion, Integer>{

}

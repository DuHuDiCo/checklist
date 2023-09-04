

package com.checklist.checklist.repository;

import com.checklist.checklist.models.ReporteExcel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReporteExcelRepository extends JpaRepository<ReporteExcel, Long>{

}

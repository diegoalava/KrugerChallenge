package com.example.kruger.repository;

import com.example.kruger.model.Userinfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Diego.Alava
 */
public interface UserinfoRepository extends JpaRepository<Userinfo, Long>{
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edgar.biblioteca.repositorios;

import com.edgar.biblioteca.entidad.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author EANDRADA
 */


@Repository //MARCO QUE ES UN REPOSITORIO
public interface AutorRepositorio extends JpaRepository<Autor, Integer>{
    
}

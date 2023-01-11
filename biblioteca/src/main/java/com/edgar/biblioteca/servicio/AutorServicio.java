/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edgar.biblioteca.servicio;

import com.edgar.biblioteca.entidad.Autor;
import com.edgar.biblioteca.excepciones.MiException;
import com.edgar.biblioteca.repositorios.AutorRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author EANDRADA
 */

@Service
public class AutorServicio {
    
    @Autowired
    private AutorRepositorio autorrepositorio;
    
    
    @Transactional //SE ANOTAN LOS QUE REALIZAN MODIFICACIONES EN LA BASE DE DATOS
    public void crearAutor(String nombre){
        Autor autor = new Autor();
        
        autor.setNombre(nombre);
        
        autorrepositorio.save(autor);
    }
    
    public List<Autor> listarAutores(){
        List<Autor> autores = new ArrayList();
        
        autores = autorrepositorio.findAll();
        return autores;
    }
    
    public void modificarAutor(Integer id, String nombre){
        
        
        Optional<Autor> respuestaautor = autorrepositorio.findById(id);
        
        if (respuestaautor.isPresent()){
            
            Autor autor = respuestaautor.get();
            
            autor.setNombre(nombre);
            autorrepositorio.save(autor);
        }
        
    }
    
    private void validar(Integer id, String nombre) throws MiException{
        
        if(id==null){
            throw new MiException("El ID de autor no puede ser nulo");
        }
        if(nombre==null){
            throw new MiException("El nombre del autor no puede ser nulo");
        }
        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edgar.biblioteca.servicio;

import com.edgar.biblioteca.entidad.Editorial;
import com.edgar.biblioteca.excepciones.MiException;
import com.edgar.biblioteca.repositorios.EditorialRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 *
 * @author EANDRADA
 */
@Service
public class EditorialServicio {
    
    private EditorialRepositorio editorialrepositorio;
    
    @Transactional
    public void crearEditorial (String nombre){
        
        Editorial editorial = new Editorial();
        
        editorial.setNombre(nombre);
        
        editorialrepositorio.save(editorial);

    }
    
    public List<Editorial> listarEditoriales(){
        List<Editorial> editoriales = new ArrayList();
        
        editoriales= editorialrepositorio.findAll();
        return editoriales;
    }
    
    public void modificarEditorial(Integer id, String nombre){
        
        Optional<Editorial> respuestaeditorial = editorialrepositorio.findById(id);
         if (respuestaeditorial.isPresent()){
             Editorial editorial = respuestaeditorial.get();
             
             editorial.setNombre(nombre);
             
             editorialrepositorio.save(editorial);
         }
        
        
    }
    private void validar(Integer id, String nombre) throws MiException{
        
        if(id==null){
            throw new MiException("El ID de la editorial no puede ser nulo");
        }
        if(nombre==null){
            throw new MiException("El nombre de la editorial no puede ser nulo");
        }
        
    }
}

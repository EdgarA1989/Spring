/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edgar.biblioteca.servicio;

import com.edgar.biblioteca.entidad.Autor;
import com.edgar.biblioteca.entidad.Editorial;
import com.edgar.biblioteca.entidad.Libro;
import com.edgar.biblioteca.excepciones.MiException;
import com.edgar.biblioteca.repositorios.AutorRepositorio;
import com.edgar.biblioteca.repositorios.EditorialRepositorio;
import com.edgar.biblioteca.repositorios.LibroRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author EANDRADA
 */
@Service //DEBO INDICAR  A SPRING QUE ES UN SERVICIO
public class LibroServicio {

    @Autowired //LE INDICAMOS QUE ESTA VARIABLE VA A SER INICIADA POR EL (INYECCION DE DEPENDENCIAS)
    private LibroRepositorio librorepositorio;

    @Autowired
    private AutorRepositorio autorrepositorio;

    @Autowired
    private EditorialRepositorio editorialrepositorio;

    @Transactional
    public void crearLibro(Long ISBN, String titulo, Integer ejemplares, Integer idAutor, Integer idEditorial) throws MiException {
        
        validar(ISBN,titulo,ejemplares,idAutor,idEditorial);
        
        Autor autor = autorrepositorio.findById(idAutor).get(); //EN ESTA LINEA BUSCA EL AUTOR POR id Y LO ASIGNA AL OBJETO INSTANCIADO AUTOR

        Editorial editorial = editorialrepositorio.findById(idEditorial).get(); //COn el punto get lo asigno al objeto instanciado

        //CREAMOS EL LIBRO PRINCIPALES ATRIBUTOS
        Libro libro = new Libro();

        libro.setIsbn(ISBN);
        libro.setTitulo(titulo);
        libro.setEjemplares(ejemplares);

        //SETEAMOS EL DATE
        libro.setAlta(new Date());

        libro.setAutor(autor);
        libro.setEditorial(editorial);

        librorepositorio.save(libro); //LLAMO A LOS METODOS SPRING EN ESTE CASO SAVE PARA CREAR EL LIBRO
    }

    public List<Libro> listarLibros() {

        List<Libro> libros = new ArrayList(); //TIENE QUE SER ARRAYLIST

        libros = librorepositorio.findAll(); //METODO QUE TRAE TODO
        return libros;

    }

    @Transactional
    public void modificarLibro(Long ISBN, String titulo, Integer ejemplares, Integer idAutor, Integer idEditorial) throws MiException {

        validar(ISBN,titulo,ejemplares,idAutor,idEditorial);
        
        Autor autor = new Autor();
        Editorial editorial = new Editorial();

        Optional<Autor> respuestaautor = autorrepositorio.findById(idAutor);

        if (respuestaautor.isPresent()) {
            autor = respuestaautor.get();

        }

        Optional<Editorial> respuestaeditorial = editorialrepositorio.findById(idEditorial);

        if (respuestaeditorial.isPresent()) {
            editorial = respuestaeditorial.get();

        }
        Optional<Libro> respuesta = librorepositorio.findById(ISBN);

        if (respuesta.isPresent()) { //MEDIANTE OPCIONAL OBTENGO RESPUESTA , SI HAY ALGO PRESENTE REALIZO LA MODIFICACION SINO NO HAGO NADA
            Libro libro = respuesta.get();

            libro.setTitulo(titulo);
            libro.setEditorial(editorial);
            libro.setAutor(autor);
            libro.setEjemplares(ejemplares);

            librorepositorio.save(libro);
        }

    }

    private void validar(Long ISBN, String titulo, Integer ejemplares, Integer idAutor, Integer idEditorial) throws MiException {

        if (ISBN == null) {
            throw new MiException("EL ISBN NO PUEDE SER NULO");
        }
        if (titulo == null) {
            throw new MiException("El TITULO NO PUEDE SER NULO");
        }
        if (ejemplares == null) {
            throw new MiException("LOS ENJEMPLARES NO PUEDEN SER NULOS");
        }
        if (idAutor == null) {
            throw new MiException("El AUTOR NO PUEDE SER NULO");
        }
        if (idEditorial== null) {
            throw new MiException("LA EDITORIAL NO PUEDE SER NULA");
        }
    }
}

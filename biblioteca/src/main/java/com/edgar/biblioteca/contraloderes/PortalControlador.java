/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edgar.biblioteca.contraloderes;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author EANDRADA
 */
@Controller
@RequestMapping("/") //INDICAMOS QUE ESCUCHA A PARTIR DE LA BARRA
public class PortalControlador {
    
    @GetMapping("/") //CUANDO INGRESAMOS A LA URL SE EJECUTA EL BLOQUE DE CODIGO A CONTINUACION
    public String index(){
        return "index.html"; //VISTA
        
    }
    
}

package com.uca.capas.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.uca.capas.dao.EstudianteDAO;
import com.uca.capas.domain.Estudiante;

@Controller
public class MainController {
	@Autowired
	private EstudianteDAO estudianteDAO;
	
	@RequestMapping("/inicio")
	public ModelAndView initMain() {
		Estudiante estudiante = new Estudiante();
		ModelAndView mav = new ModelAndView();
		mav.addObject("estudiante",estudiante);
		mav.setViewName("index");
		return mav;
	}
	
	//Mostrar Lista de Estudiantes
	@RequestMapping("/ingresar")
	public ModelAndView ingresar(@Valid @ModelAttribute Estudiante estudiante, BindingResult result ) {
		ModelAndView mav = new ModelAndView();
		if(result.hasErrors()) {
			mav.setViewName("index");
		}
		else {
			estudianteDAO.ingresar(estudiante);
			mav.setViewName("index");
			mav.addObject("resultado", 1);
		}
		
		return mav;
	}
	
	@RequestMapping(value = "/mostrarEstudiante")
	public ModelAndView findAll() {
		ModelAndView mav = new ModelAndView();
		List<Estudiante> estudiantes = null;
		try {
			estudiantes = estudianteDAO.findAll();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		mav.addObject("estudiantes",estudiantes);
		mav.setViewName("listado");
		return mav;
	}
}
	
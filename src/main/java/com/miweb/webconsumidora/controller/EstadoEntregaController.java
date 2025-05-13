package com.miweb.webconsumidora.controller;

import com.miweb.webconsumidora.model.EstadoEntrega;
import com.miweb.webconsumidora.service.EstadoEntregaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/estados-entrega") // Dedicated path for specific actions, main display is on /distritos
public class EstadoEntregaController {

    private final EstadoEntregaService estadoEntregaService;

    public EstadoEntregaController(EstadoEntregaService estadoEntregaService) {
        this.estadoEntregaService = estadoEntregaService;
    }

    // The main listing and form for adding new will be handled by DistritoController on /distritos page
    // This controller handles specific actions like saving, showing edit form, updating, and deleting.

    @PostMapping("/agregar")
    public String addEstadoEntrega(@ModelAttribute("estadoEntregaNuevo") EstadoEntrega estadoEntrega) {
        estadoEntregaService.createEstadoEntrega(estadoEntrega);
        return "redirect:/distritos#gestion-estados-entrega"; // Redirect to the main page, anchor to the new section
    }

    @GetMapping("/editar/{id}")
    public String showEditEstadoEntregaForm(@PathVariable Long id, Model model) {
        EstadoEntrega estadoEntrega = estadoEntregaService.getEstadoEntregaById(id);
        if (estadoEntrega == null) {
            // Optionally, add a specific error parameter for estado-entrega not found
            return "redirect:/distritos?errorEstadoEntrega=notfound#gestion-estados-entrega";
        }
        model.addAttribute("estadoEntregaEditar", estadoEntrega);
        return "estado-entrega-form-edit"; // Path to the new edit form template
    }

    @PostMapping("/actualizar/{id}")
    public String updateEstadoEntrega(@PathVariable Long id, @ModelAttribute("estadoEntregaEditar") EstadoEntrega estadoEntrega) {
        estadoEntregaService.updateEstadoEntrega(id, estadoEntrega);
        return "redirect:/distritos#gestion-estados-entrega";
    }

    @GetMapping("/eliminar/{id}")
    public String deleteEstadoEntrega(@PathVariable Long id) {
        estadoEntregaService.deleteEstadoEntrega(id);
        return "redirect:/distritos#gestion-estados-entrega";
    }
}
package com.miweb.webconsumidora.controller;

import com.miweb.webconsumidora.model.Rol;
import com.miweb.webconsumidora.service.RolService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/roles") // Base path for role-specific actions not handled by the main page
public class RolController {

    private final RolService rolService;

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    // Handles the submission of the 'add new rol' form from the main page
    @PostMapping("/agregar")
    public String addRol(@ModelAttribute("rolNuevo") Rol rol) {
        rolService.createRol(rol);
        return "redirect:/distritos"; // Redirect back to the main page where both lists are shown
    }

    @GetMapping("/editar/{id}")
    public String showEditRolForm(@PathVariable Integer id, Model model) {
        Rol rol = rolService.getRolById(id);
        if (rol == null) {
            return "redirect:/distritos?errorRol=notfound"; // Redirect with an error if rol not found
        }
        model.addAttribute("rolEditar", rol);
        return "rol-form-edit"; // Thymeleaf template for editing a rol
    }

    @PostMapping("/actualizar/{id}")
    public String updateRol(@PathVariable Integer id, @ModelAttribute("rolEditar") Rol rol) {
        rolService.updateRol(id, rol);
        return "redirect:/distritos"; // Redirect back to the main page
    }

    @GetMapping("/eliminar/{id}")
    public String deleteRol(@PathVariable Integer id) {
        rolService.deleteRol(id);
        return "redirect:/distritos"; // Redirect back to the main page
    }
}
package com.miweb.webconsumidora.controller;

import com.miweb.webconsumidora.model.Distrito;
import com.miweb.webconsumidora.model.Rol;
import com.miweb.webconsumidora.model.EstadoEntrega; // Import EstadoEntrega
import com.miweb.webconsumidora.service.DistritoService;
import com.miweb.webconsumidora.service.RolService;
import com.miweb.webconsumidora.service.EstadoEntregaService; // Import EstadoEntregaService
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/distritos")
public class DistritoController {

    private final DistritoService distritoService;
    private final RolService rolService;
    private final EstadoEntregaService estadoEntregaService; // Inject EstadoEntregaService

    public DistritoController(DistritoService distritoService, RolService rolService, EstadoEntregaService estadoEntregaService) {
        this.distritoService = distritoService;
        this.rolService = rolService;
        this.estadoEntregaService = estadoEntregaService; // Initialize EstadoEntregaService
    }

    @GetMapping
    public String listAllEntities(Model model) {
        // Load distritos
        List<Distrito> distritos = distritoService.getAllDistritos();
        model.addAttribute("distritos", distritos);
        model.addAttribute("distritoNuevo", new Distrito());

        // Load roles
        List<Rol> roles = rolService.getAllRoles();
        model.addAttribute("roles", roles);
        model.addAttribute("rolNuevo", new Rol());

        // Load estados de entrega
        List<EstadoEntrega> estadosEntrega = estadoEntregaService.getAllEstadosEntrega();
        model.addAttribute("estadosEntrega", estadosEntrega);
        model.addAttribute("estadoEntregaNuevo", new EstadoEntrega()); // For the add estado-entrega form

        return "distritos"; // This template will now show all three entities
    }

    // --- Distrito specific actions ---
    @PostMapping("/distrito/agregar")
    public String addDistrito(@ModelAttribute("distritoNuevo") Distrito distrito) {
        distritoService.createDistrito(distrito);
        return "redirect:/distritos#gestion-distritos";
    }

    @GetMapping("/distrito/editar/{id}")
    public String showEditDistritoForm(@PathVariable Long id, Model model) {
        Distrito distrito = distritoService.getDistritoById(id);
        if (distrito == null) {
            return "redirect:/distritos?errorDistrito=notfound#gestion-distritos";
        }
        model.addAttribute("distritoEditar", distrito);
        return "distrito-form-edit";
    }

    @PostMapping("/distrito/actualizar/{id}")
    public String updateDistrito(@PathVariable Long id, @ModelAttribute("distritoEditar") Distrito distrito) {
        distritoService.updateDistrito(id, distrito);
        return "redirect:/distritos#gestion-distritos";
    }

    @GetMapping("/distrito/eliminar/{id}")
    public String deleteDistrito(@PathVariable Long id) {
        distritoService.deleteDistrito(id);
        return "redirect:/distritos#gestion-distritos";
    }

    // Rol actions are handled by RolController.
    // EstadoEntrega actions are handled by EstadoEntregaController.
    // They will redirect to /distritos with appropriate anchors.
}
package com.patitasfelices.patitasfelices.config;

import com.patitasfelices.patitasfelices.model.entity.Persona;
import com.patitasfelices.patitasfelices.model.entity.Rol;
import com.patitasfelices.patitasfelices.model.entity.Usuario;
import com.patitasfelices.patitasfelices.repository.PersonaRepository;
import com.patitasfelices.patitasfelices.repository.RolRepository;
import com.patitasfelices.patitasfelices.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("---- VERIFICANDO DATOS INICIALES ----");

        // 1. Crear Roles si no existen
        crearRolSiNoExiste("ADMIN");
        crearRolSiNoExiste("VETERINARIO");
        crearRolSiNoExiste("RECEPCIONISTA");

        // 2. Crear Usuario Admin por defecto si no existe
        if (!usuarioRepository.existsByNombreUsuario("admin")) {
            System.out.println("Creando usuario administrador por defecto...");

            // Paso A: Crear la Persona
            Persona personaAdmin = new Persona();
            personaAdmin.setNombres("Super");
            personaAdmin.setApellidos("Administrador");
            personaAdmin.setCorreoElectronico("admin@patitasfelices.com");
            personaAdmin.setDireccion("Sede Central");
            personaAdmin.setTelefono("000-000-000");
            personaRepository.save(personaAdmin);

            // Paso B: Asignar Rol
            Rol rolAdmin = rolRepository.findByNombreRol("ADMIN").get();

            // Paso C: Crear Usuario
            Usuario usuarioAdmin = new Usuario();
            usuarioAdmin.setNombreUsuario("admin");
            usuarioAdmin.setContrasena("admin123");
            usuarioAdmin.setPersona(personaAdmin);
            usuarioAdmin.setRol(rolAdmin);

            usuarioRepository.save(usuarioAdmin);
            System.out.println("---- USUARIO 'admin' CREADO CON ÉXITO (Pass: admin123) ----");
        } else {
            System.out.println("---- EL USUARIO 'admin' YA EXISTE - SE OMITE CREACIÓN ----");
        }
    }

    private void crearRolSiNoExiste(String nombreRol) {
        if (rolRepository.findByNombreRol(nombreRol).isEmpty()) {
            Rol rol = new Rol();
            rol.setNombreRol(nombreRol);
            rolRepository.save(rol);
        }
    }
}

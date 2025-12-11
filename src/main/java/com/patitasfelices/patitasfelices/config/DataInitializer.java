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
import java.util.Optional;

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

        crearRolSiNoExiste("ADMIN");
        crearRolSiNoExiste("VETERINARIO");
        crearRolSiNoExiste("RECEPCIONISTA");

        if (!usuarioRepository.existsByNombreUsuario("admin")) {
            System.out.println("Creando usuario administrador por defecto...");

            String emailAdmin = "admin@patitasfelices.com";
            Optional<Persona> personaExistente = personaRepository.findByCorreoElectronico(emailAdmin);

            Persona personaAdmin;
            if (personaExistente.isPresent()) {
                personaAdmin = personaExistente.get();
                System.out.println("-> Persona encontrada, reutilizando registro existente.");
            } else {

                personaAdmin = new Persona();
                personaAdmin.setNombres("Super");
                personaAdmin.setApellidos("Administrador");
                personaAdmin.setCorreoElectronico(emailAdmin);
                personaAdmin.setDireccion("Sede Central");
                personaAdmin.setTelefono("000-000-000");

            }

            Rol rolAdmin = rolRepository.findByNombreRol("ADMIN").get();

            Usuario usuarioAdmin = new Usuario();
            usuarioAdmin.setNombreUsuario("admin");
            usuarioAdmin.setContrasena("admin123");
            usuarioAdmin.setPersona(personaAdmin);
            usuarioAdmin.setRol(rolAdmin);

            usuarioRepository.save(usuarioAdmin);
            System.out.println("---- USUARIO 'admin' CREADO/RESTAURADO CON ÉXITO ----");
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
package com.banana.bananawhatsapp.servicios;

import com.banana.bananawhatsapp.config.SpringConfig;
import com.banana.bananawhatsapp.exceptions.MensajeException;
import com.banana.bananawhatsapp.modelos.Mensaje;
import com.banana.bananawhatsapp.modelos.Usuario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class})
@ActiveProfiles("prod")
class ServicioMensajeriaTest {

    @Autowired
    IServicioMensajeria servicio;
    @Autowired
    IServicioUsuarios servUser;

    @Test
    void dadoRemitenteYDestinatarioYTextoValido_cuandoEnviarMensaje_entoncesMensajeValido() {
        Usuario usRemi = new Usuario(null, "Pepelu", "pepelu@banana.com", LocalDate.now(), true);
        servUser.crearUsuario(usRemi);

        Usuario usDesti = new Usuario(null, "Antonia", "antonia@banana.com", LocalDate.now(), true);
        servUser.crearUsuario(usDesti);

        Mensaje menEnviado = servicio.enviarMensaje(usRemi, usDesti,"Enviando mensaje num1");

        assertThat(menEnviado.getCuerpo(), notNullValue());
        assertThat(menEnviado.getId(), greaterThan(0));
    }

    @Test
    void dadoRemitenteYDestinatarioYTextoNOValido_cuandoEnviarMensaje_entoncesExcepcion() {

        Usuario usDesti = new Usuario(null, "Antonia", "antonia@banana.com", LocalDate.now(), true);
        servUser.crearUsuario(usDesti);

        assertThrows(MensajeException.class, () -> {
            servicio.enviarMensaje(null, usDesti,"Enviando no válido");
             });
    }

    @Test
    void dadoRemitenteYDestinatarioValido_cuandoMostrarChatConUsuario_entoncesListaMensajes() {
    }

    @Test
    void dadoRemitenteYDestinatarioNOValido_cuandoMostrarChatConUsuario_entoncesExcepcion() {
    }

    @Test
    void dadoRemitenteYDestinatarioValido_cuandoBorrarChatConUsuario_entoncesOK() {
    }

    @Test
    void dadoRemitenteYDestinatarioNOValido_cuandoBorrarChatConUsuario_entoncesExcepcion() {
    }
}
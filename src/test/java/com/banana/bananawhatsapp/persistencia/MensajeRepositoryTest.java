package com.banana.bananawhatsapp.persistencia;

import com.banana.bananawhatsapp.config.SpringConfig;
import com.banana.bananawhatsapp.exceptions.MensajeException;
import com.banana.bananawhatsapp.modelos.Mensaje;
import com.banana.bananawhatsapp.modelos.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;
import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class})
@ActiveProfiles("prod")
//@ActiveProfiles("dev")
class MensajeRepositoryTest {

    @Autowired
    IMensajeRepository repo;
    @Autowired
    IUsuarioRepository repoUser;

    @Test
    void dadoUnMensajeValido_cuandoCrear_entoncesMensajeValido() throws SQLException {

        Usuario usRemi = new Usuario(null, "Pepelu", "pepelu@banana.com", LocalDate.now(), true);
        repoUser.crear(usRemi);

        Usuario usDesti = new Usuario(null, "Antonia", "antonia@banana.com", LocalDate.now(), true);
        repoUser.crear(usDesti);

        Mensaje nuevo = new Mensaje(1,usRemi,usDesti,"Primer mensaje", LocalDate.now());
        repo.crear(nuevo);

        assertThat(nuevo, notNullValue());
        assertThat(nuevo.getId(), greaterThan(0));
    }

    @Test
    void dadoUnMensajeNOValido_cuandoCrear_entoncesExcepcion() throws SQLException {
        Usuario usRemi = new Usuario(null, "Pepelu", "pepelu@banana.com", LocalDate.now(), true);
        repoUser.crear(usRemi);


        Mensaje nuevo = new Mensaje(1,usRemi,null,"Sin destinatario", LocalDate.now());

        assertThrows(Exception.class, () -> {
            repo.crear(nuevo);
        });
    }

    @Test
    void dadoUnUsuarioValido_cuandoObtener_entoncesListaMensajes() {
    }

    @Test
    void dadoUnUsuarioNOValido_cuandoObtener_entoncesExcepcion() {
    }

    @Test
    void dadoUnUsuarioValido_cuandoBorrarTodos_entoncesOK() {
    }

    @Test
    void dadoUnUsuarioNOValido_cuandoBorrarTodos_entoncesExcepcion() {
    }

}
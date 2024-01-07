package com.banana.bananawhatsapp.servicios;

import com.banana.bananawhatsapp.exceptions.MensajeException;
import com.banana.bananawhatsapp.exceptions.UsuarioException;
import com.banana.bananawhatsapp.modelos.Mensaje;
import com.banana.bananawhatsapp.modelos.Usuario;
import com.banana.bananawhatsapp.persistencia.IMensajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MensajeServicioImpl implements IServicioMensajeria{
    @Autowired
    IMensajeRepository mensajeRepo;
    @Override
    public Mensaje enviarMensaje(Usuario remitente, Usuario destinatario, String texto) throws UsuarioException, MensajeException {
         try {
            remitente.valido();
            destinatario.valido();
            Mensaje nuevo = new Mensaje(1,remitente, destinatario,"Primer mensaje servicio", LocalDate.now());
            mensajeRepo.crear(nuevo);
            return nuevo;
        } catch (Exception e) {
            e.printStackTrace();
            throw new MensajeException("Error en la creación mensaje: " + e.getMessage());
        }


    }

    @Override
    public List<Mensaje> mostrarChatConUsuario(Usuario remitente, Usuario destinatario) throws UsuarioException, MensajeException {
        return null;
    }

    @Override
    public boolean borrarChatConUsuario(Usuario remitente, Usuario destinatario) throws UsuarioException, MensajeException {
        return false;
    }
}

package com.banana.bananawhatsapp.persistencia;

import com.banana.bananawhatsapp.modelos.Mensaje;
import com.banana.bananawhatsapp.modelos.Usuario;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MensajeInMemoryRepo implements IMensajeRepository{

    Set<Mensaje> mensajeSet = new HashSet<>();
    private Integer num = 0;
    @Override
    public Mensaje crear(Mensaje mensaje) throws SQLException {
        mensaje.valido();
        mensaje.setId(num + 1);
        mensajeSet.add(mensaje);

        return mensaje;

    }

    @Override
    public List<Mensaje> obtener(Usuario usuario) throws SQLException {
        return null;
    }

    @Override
    public boolean borrarTodos(Usuario usuario) throws SQLException {
        return false;
    }
}

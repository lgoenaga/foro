package com.alura.foro.service.interfaces;

import com.alura.foro.dto.request.actualizar.DtoActualizarTopico;
import com.alura.foro.dto.request.crear.DtoCrearTopico;
import com.alura.foro.dto.response.DtoListarTopico;

import java.util.List;

public interface TopicoInterface {

    List<DtoListarTopico> listarTopicos();
    DtoListarTopico listarTopico(Long id);

    void registrarTopico(DtoCrearTopico topico);

    void actualizarTopico(Long id, DtoActualizarTopico topico);
}

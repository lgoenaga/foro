package com.alura.foro.service.interfaces;

import com.alura.foro.dto.response.DtoListarTopico;

import java.util.List;

public interface TopicoInterface {

    List<DtoListarTopico> listarTopicos();
    DtoListarTopico listarTopico(Long id);
}

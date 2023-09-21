package com.alura.foro.service.interfaces;

import com.alura.foro.dto.response.DtoListarDiscucion;

import java.util.List;

public interface DiscusionInterface {

    List<DtoListarDiscucion> listarDiscuciones();

    DtoListarDiscucion buscarDiscucion(Long id);

}

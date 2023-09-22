package com.alura.foro.service.interfaces;

import com.alura.foro.dto.request.crear.DtoCrearDiscusion;
import com.alura.foro.dto.response.DtoListarDiscusion;

import java.util.List;

public interface DiscusionInterface {

    List<DtoListarDiscusion> listarDiscusiones();

    DtoListarDiscusion buscarDiscusion(Long id);

    void registrarDiscusion(DtoCrearDiscusion discusion);

    void eliminarDiscusion(Long id);
}

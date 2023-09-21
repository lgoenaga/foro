package com.alura.foro.service.implement;

import com.alura.foro.dto.response.DtoListarDiscucion;
import com.alura.foro.model.Discusion;
import com.alura.foro.repository.DiscusionRepository;
import com.alura.foro.service.interfaces.DiscusionInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiscusionService implements DiscusionInterface {

    final DiscusionRepository discusionRepository;
    @Override
    public List<DtoListarDiscucion> listarDiscuciones() {
        List<Discusion> discusiones = discusionRepository.findAll();
        return discusiones.stream().map(DtoListarDiscucion::new).toList();
    }

    @Override
    public DtoListarDiscucion buscarDiscucion(Long id) {
            Discusion discusion = discusionRepository.findById(id).orElseThrow();
            return new DtoListarDiscucion(discusion);
    }
}

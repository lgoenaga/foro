package com.alura.foro.service.implement;

import com.alura.foro.dto.response.DtoListarDiscusion;
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
    public List<DtoListarDiscusion> listarDiscusiones() {
        List<Discusion> discusiones = discusionRepository.findAll();
        return discusiones.stream().map(DtoListarDiscusion::new).toList();
    }

    @Override
    public DtoListarDiscusion buscarDiscusion(Long id) {
            Discusion discusion = discusionRepository.findById(id).orElseThrow();
            return new DtoListarDiscusion(discusion);
    }
}

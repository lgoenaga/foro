package com.alura.foro.service.implement;

import com.alura.foro.dto.request.crear.DtoCrearDiscusion;
import com.alura.foro.dto.response.DtoListarDiscusion;
import com.alura.foro.model.Discusion;
import com.alura.foro.repository.DiscusionRepository;
import com.alura.foro.service.interfaces.DiscusionInterface;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    @Override
    @Transactional
    public void registrarDiscusion(DtoCrearDiscusion discusion) {
        Discusion discusionModel = new Discusion();
        discusionModel.setMensaje(discusion.mensaje());
        discusionModel.setIdTopico(discusion.idTopico());
        discusionModel.setIdUsuario(discusion.idUsuario());
        discusionModel.setFechaCreacion(LocalDateTime.now());

        discusionRepository.save(discusionModel);
    }

    @Override
    public void eliminarDiscusion(Long id) {
        discusionRepository.findById(id).orElseThrow();
        discusionRepository.deleteById(id);
    }

}

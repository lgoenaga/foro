package com.alura.foro.service.implement;

import com.alura.foro.dto.request.crear.DtoCrearDiscusion;
import com.alura.foro.dto.response.DtoListarDiscusion;
import com.alura.foro.model.Discusion;
import com.alura.foro.repository.DiscusionRepository;
import com.alura.foro.service.interfaces.DiscusionInterface;
import com.alura.foro.util.ConstantService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class DiscusionService implements DiscusionInterface {

    final DiscusionRepository discusionRepository;

    Logger logger = Logger.getLogger(DiscusionService.class.getName());
    String message;

    @Override
    public List<DtoListarDiscusion> listarDiscusiones() {
        List<Discusion> discusiones = discusionRepository.findAll();
        message = "Listando discusiones";
        logger.info(message);
        return discusiones.stream().map(DtoListarDiscusion::new).toList();
    }

    @Override
    public DtoListarDiscusion buscarDiscusion(Long id) {
            Discusion discusion = discusionRepository.findById(id).orElseThrow();
            message = "Buscando " + ConstantService.MODEL_DISCUSSION + " " + discusion.getId();
            logger.info(message);
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

        message = ConstantService.MODEL_DISCUSSION + " creada";
        logger.info(message);
    }

    @Override
    public void eliminarDiscusion(Long id) {
        Discusion discusion = discusionRepository.findById(id).orElseThrow();
        message = ConstantService.MODEL_DISCUSSION +" Eliminada " + discusion.getId();
        logger.info(message);
        discusionRepository.deleteById(id);
    }

}

package com.alura.foro.service.implement;

import com.alura.foro.dto.request.actualizar.DtoActualizarTopico;
import com.alura.foro.dto.request.crear.DtoCrearTopico;
import com.alura.foro.dto.response.DtoListarTopico;
import com.alura.foro.model.Estatus;
import com.alura.foro.model.Topico;
import com.alura.foro.repository.TopicoRepository;
import com.alura.foro.service.interfaces.TopicoInterface;
import com.alura.foro.util.ConstantService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class TopicoService implements TopicoInterface {

    final TopicoRepository topicoRepository;

    Logger logger = Logger.getLogger(TopicoService.class.getName());
    String message;

    @Override
    public List<DtoListarTopico> listarTopicos() {
        List<Topico> topicos = topicoRepository.findAll();
        message = "Listando " + ConstantService.TOPICOS;
        logger.info(message);
        return topicos.stream().map(DtoListarTopico::new).toList();
    }

    @Override
    public DtoListarTopico listarTopico(Long id){
        Topico topico = topicoRepository.findById(id).orElseThrow();
        message = "Buscando " + ConstantService.MODEL_TOPIC + " " + topico.getTitulo();
        logger.info(message);
        return new DtoListarTopico(topico);
    }

    @Override
    public List<DtoListarTopico> listarTopicosPorEstatus(String estatus) {
        List<Topico> topicos = topicoRepository.findByEstatus(Estatus.valueOf(estatus));
        message = "Listando "+ ConstantService.TOPICOS + " " + estatus;
        logger.info(message);
        return topicos.stream().map(DtoListarTopico::new).toList();
    }

    @Override
    @Transactional
    public void registrarTopico(DtoCrearTopico topico) {
        Topico topicoModel = new Topico();
        topicoModel.setTitulo(topico.titulo());
        topicoModel.setDescripcion(topico.descripcion());
        topicoModel.setIdCurso(topico.idCurso());
        topicoModel.setIdUsuario(topico.idUsuario());
        topicoModel.setEstatus(Estatus.ABIERTO);
        topicoModel.setFechaCreacion(LocalDateTime.now());
        topicoModel.setFechaActualizacion(LocalDateTime.now());
        topicoRepository.save(topicoModel);
        message = ConstantService.MODEL_TOPIC + " " + topicoModel.getTitulo() +" " +ConstantService.INFO_CREATED;
        logger.info(message);
    }

    @Override
    @Transactional
    public void actualizarTopico(Long id, DtoActualizarTopico topico) {
        Topico topicoModel = topicoRepository.findById(id).orElseThrow();
        topicoModel.setTitulo(topico.titulo());
        topicoModel.setDescripcion(topico.descripcion());
        if (topico.estatus()==null) {
            topicoModel.setEstatus(topicoModel.getEstatus());
        }else{
            topicoModel.setEstatus(topico.estatus());
        }

        if (topico.idCurso()==null) {
            topicoModel.setIdCurso(topicoModel.getIdCurso());
        }else{
            topicoModel.setIdCurso(topico.idCurso());
        }
        if (topico.idUsuario()==null) {
            topicoModel.setIdUsuario(topicoModel.getIdUsuario());
        }else{
            topicoModel.setIdUsuario(topico.idUsuario());
        }
        topicoModel.setFechaActualizacion(LocalDateTime.now());

        message = ConstantService.MODEL_TOPIC + " " + topicoModel.getTitulo() + ConstantService.INFO_UPDATED;
        logger.info(message);
    }

    @Override
    @Transactional
    public void eliminarTopico(Long id) {
        Topico topico = topicoRepository.findById(id).orElseThrow();
        message = ConstantService.MODEL_TOPIC + " " + topico.getTitulo() + ConstantService.INFO_DELETED;
        logger.info(message);
        topicoRepository.deleteById(id);
    }

}

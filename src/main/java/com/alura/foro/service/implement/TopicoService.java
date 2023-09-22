package com.alura.foro.service.implement;

import com.alura.foro.dto.request.actualizar.DtoActualizarTopico;
import com.alura.foro.dto.request.crear.DtoCrearTopico;
import com.alura.foro.dto.response.DtoListarTopico;
import com.alura.foro.model.Estatus;
import com.alura.foro.model.Topico;
import com.alura.foro.repository.TopicoRepository;
import com.alura.foro.service.interfaces.TopicoInterface;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicoService implements TopicoInterface {

    final TopicoRepository topicoRepository;

    @Override
    public List<DtoListarTopico> listarTopicos() {

        List<Topico> topicos = topicoRepository.findAll();
        return topicos.stream().map(DtoListarTopico::new).toList();
    }

    @Override
    public DtoListarTopico listarTopico(Long id){
        Topico topico = topicoRepository.findById(id).orElseThrow();
        return new DtoListarTopico(topico);
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
    }

    @Override
    @Transactional
    public void eliminarTopico(Long id) {
        topicoRepository.findById(id).orElseThrow();
        topicoRepository.deleteById(id);
    }

    @Override
    public List<DtoListarTopico> listarTopicosPorEstatus(String estatus) {
        List<Topico> topicos = topicoRepository.findByEstatus(Estatus.valueOf(estatus));
        return topicos.stream().map(DtoListarTopico::new).toList();
    }
}

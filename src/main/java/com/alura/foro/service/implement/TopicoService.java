package com.alura.foro.service.implement;

import com.alura.foro.dto.response.DtoListarTopico;
import com.alura.foro.model.Topico;
import com.alura.foro.repository.TopicoRepository;
import com.alura.foro.service.interfaces.TopicoInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}

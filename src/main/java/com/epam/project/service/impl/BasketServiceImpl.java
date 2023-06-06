package com.epam.project.service.impl;

import com.epam.project.mapper.BasketMapper;
import com.epam.project.model.dto.BasketDto;
import com.epam.project.model.entitity.Basket;
import com.epam.project.repository.BasketRepository;
import com.epam.project.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {
    private final BasketRepository basketRepository;
    private final BasketMapper basketMapper;

    @Override
    public List<BasketDto> findAll() {
        return basketMapper.toDtos(basketRepository.findAll());
    }

    @Override
    public BasketDto findById(Long id) {
        Basket basket = basketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("The basket with id" + id + " not found"));
        return basketMapper.toDto(basket);
    }

    @Override
    public BasketDto save(BasketDto basketDto) {
        Basket basket = basketMapper.toEntity(basketDto);
        basket = basketRepository.save(basket);
        return basketMapper.toDto(basket);
    }

    @Override
    public BasketDto update(BasketDto basketDto) {
        if (!basketRepository.existsById(basketDto.getId())) {
            throw new RuntimeException("The basket not available");
        }

        Basket basket = basketMapper.toEntity(basketDto);
        basket = basketRepository.save(basket);
        return basketMapper.toDto(basket);
    }

    @Override
    public void deleteById(Long id) {
        basketRepository.deleteById(id);
    }
}

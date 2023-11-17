package com.jejujaju.place.model.service;

import com.jejujaju.place.model.dto.PlaceRequestDto;
import com.jejujaju.place.model.dto.PlaceResponseDto;
import com.jejujaju.place.model.mapper.PlaceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PlaceServiceImpl implements PlaceService{

    private final PlaceMapper placeMapper;

    @Override
    public void savePlace(PlaceRequestDto place) {
        placeMapper.insertPlace(place);
    }

    @Override
    public PlaceResponseDto findPlaceByPlaceId(Long placeId) {
        return placeMapper.selectPlaceByPlaceId(placeId);
    }
}

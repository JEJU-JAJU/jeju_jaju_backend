package com.jejujaju.place.model.service;

import com.jejujaju.place.model.dto.PlaceRequestDto;
import com.jejujaju.place.model.dto.PlaceResponseDto;

public interface PlaceService {
    void savePlace(PlaceRequestDto place);
    PlaceResponseDto findPlaceByPlaceId(Long placeId);
}

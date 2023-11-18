package com.jejujaju.tag.controller;

import com.jejujaju.tag.model.dto.Tag;
import com.jejujaju.tag.model.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tags")
public class TagController {

    private final TagService tagService;

    @GetMapping
    public ResponseEntity<List<Tag>> findTagByCategoryCode(@RequestParam("categoryCode") String categoryCode){
        List<Tag> tag = tagService.findTagByCategoryCode(categoryCode);
        return new ResponseEntity<>(tag, HttpStatus.OK);
    }
}

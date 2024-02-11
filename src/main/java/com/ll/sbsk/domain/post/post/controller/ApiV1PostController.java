package com.ll.sbsk.domain.post.post.controller;

import com.ll.sbsk.global.rsData.RsData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/posts", produces = APPLICATION_JSON_VALUE)
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Tag(name = "ApiV1PostController", description = "글 컨트롤러")
public class ApiV1PostController {
    public record GetPostResponseBody() {
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "글 단건조회")
    public RsData<GetPostResponseBody> getPost(
            @PathVariable long id
    ) {
        return null;
    }
}

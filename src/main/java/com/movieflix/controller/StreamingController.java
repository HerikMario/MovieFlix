package com.movieflix.controller;

import com.movieflix.controller.request.StreamingRequest;
import com.movieflix.controller.response.StreamingResponse;
import com.movieflix.entity.Streaming;
import com.movieflix.mapper.StreamingMapper;
import com.movieflix.service.StreamingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/streaming")
public class StreamingController {

    private final StreamingService streamingService;

    public StreamingController(StreamingService streamingService) {
        this.streamingService = streamingService;
    }

    @PostMapping()
    public ResponseEntity<StreamingResponse> saveStreaming(@RequestBody StreamingRequest streamingRequest) {
        Streaming streaming = streamingService.save(StreamingMapper.toStreaming(streamingRequest));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(StreamingMapper.toStreamingResponse(streaming));
    }

    @GetMapping()
    public ResponseEntity<List<StreamingResponse>> getAllStreaming() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        streamingService.findAll()
                                .stream()
                                .map(StreamingMapper::toStreamingResponse)
                                .toList()
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<StreamingResponse> getByStreamingId(@PathVariable Long id) {
        return streamingService.findById(id)
                .map(streaming -> ResponseEntity.status(HttpStatus.OK)
                                                .body(StreamingMapper.toStreamingResponse(streaming))
                )
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteByStreamingId(@PathVariable Long id) {
        streamingService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

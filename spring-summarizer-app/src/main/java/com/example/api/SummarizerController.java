package com.example.api;

import com.example.summarizer.SummarizerBridge;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/summarizer")
public class SummarizerController {

    @GetMapping("/summary")
    public String summarize(@RequestParam String url) throws ExecutionException, InterruptedException {
        // Call the summarize method from Scala
        CompletableFuture<String> summaryFuture = SummarizerBridge.summarize(url);

        // Blocking call to get the result from the CompletableFuture
        return summaryFuture.get();
    }

    @GetMapping("/history")
    public List<String> getHistory() {
        // Fetch and return the summary history from Scala
        return SummarizerBridge.getHistory();
    }
}

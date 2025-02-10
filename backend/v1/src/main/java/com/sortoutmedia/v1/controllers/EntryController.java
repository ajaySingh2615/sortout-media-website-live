package com.sortoutmedia.v1.controllers;

import com.sortoutmedia.v1.models.Entry;
import com.sortoutmedia.v1.services.EntryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/entries")
public class EntryController {

    private final EntryService entryService;

    public EntryController(EntryService entryService) {
        this.entryService = entryService;
    }

    // ✅ API to Submit a New Entry
    @PostMapping("/submit")
    public Entry submitEntry(@RequestParam String name,
                             @RequestParam String category,
                             @RequestParam int followers,
                             @RequestParam int age,
                             @RequestParam String language,
                             @RequestParam String gender,
                             @RequestParam String profession,
                             @RequestParam(required = false) MultipartFile image) throws Exception {

        Entry entry = new Entry(null, name, category, followers, age, language, gender, profession, null);
        return entryService.saveEntry(entry, image);
    }

    // ✅ API to Fetch Entries by Category (No Pagination)
    @GetMapping("/categories/{category}")
    public List<Entry> getEntriesByCategory(@PathVariable String category) {
        return entryService.getEntriesByCategory(category);
    }

    // ✅ API to Fetch All Entries (No Pagination)
    @GetMapping("/all/no-pagination")
    public List<Entry> getAllEntriesWithoutPagination() {
        return entryService.getAllEntries();
    }

    // ✅ API to Fetch All Entries with Pagination
    @GetMapping("/all")
    public Page<Entry> getAllEntries(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return entryService.getAllEntries(PageRequest.of(page, size));
    }

    // ✅ API to Fetch Filtered Entries (No Pagination)
    @GetMapping("/filter/no-pagination")
    public List<Entry> getFilteredEntriesWithoutPagination(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) String language,
            @RequestParam(required = false) String profession
    ) {
        return entryService.getFilteredEntries(category, age, gender, language, profession);
    }

    // ✅ API to Fetch Filtered Entries with Pagination
    @GetMapping("/filter")
    public Page<Entry> getFilteredEntries(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) String language,
            @RequestParam(required = false) String profession,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return entryService.getFilteredEntries(category, age, gender, language, profession, PageRequest.of(page, size));
    }
}

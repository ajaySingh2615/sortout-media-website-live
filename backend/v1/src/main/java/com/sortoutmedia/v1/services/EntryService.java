package com.sortoutmedia.v1.services;

import com.sortoutmedia.v1.models.Entry;
import com.sortoutmedia.v1.repositories.EntryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class EntryService {

    private final EntryRepository entryRepository;
    private final CloudinaryService cloudinaryService;

    public EntryService(EntryRepository entryRepository, CloudinaryService cloudinaryService) {
        this.entryRepository = entryRepository;
        this.cloudinaryService = cloudinaryService;
    }

    public Entry saveEntry(Entry entry, MultipartFile image) throws Exception {
        if (image != null && !image.isEmpty()) {
            String imageUrl = cloudinaryService.uploadImage(image.getBytes());
            entry.setImageUrl(imageUrl);
        }
        return entryRepository.save(entry);
    }

    public List<Entry> getEntriesByCategory(String category) {
        return entryRepository.findByCategory(category);
    }

    public List<Entry> getFilteredEntries(String category, Integer age, String gender, String language, String profession) {
        return entryRepository.findFilteredEntries(category, age, gender, language, profession);
    }

    // Fetch all data
    public List<Entry> getAllEntries() {
        return entryRepository.findAll();
    }

    // Fetch all entries with pagination
    public Page<Entry> getAllEntries(Pageable pageable) {
        return entryRepository.findAll(pageable);
    }

    // Fetch filtered entries with pagination
    public Page<Entry> getFilteredEntries(String category, Integer age, String gender, String language, String profession, Pageable pageable) {
        return entryRepository.findFilteredEntries(category, age, gender, language, profession, pageable);
    }
}

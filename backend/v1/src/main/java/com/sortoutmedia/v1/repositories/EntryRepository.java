package com.sortoutmedia.v1.repositories;

import com.sortoutmedia.v1.models.Entry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EntryRepository extends JpaRepository<Entry, Long> {
    List<Entry> findByCategory(String category);

    @Query("SELECT e FROM Entry e " +
            "WHERE (:category IS NULL OR e.category = :category) " +
            "AND (:age IS NULL OR e.age = :age) " +
            "AND (:gender IS NULL OR e.gender = :gender) " +
            "AND (:language IS NULL OR e.language = :language) " +
            "AND (:profession IS NULL OR e.profession = :profession)")
    List<Entry> findFilteredEntries(
            @Param("category") String category,
            @Param("age") Integer age,
            @Param("gender") String gender,
            @Param("language") String language,
            @Param("profession") String profession
    );

    // Fetch all entries
    List<Entry> findAll();

    // Fetch all entries with pagination
    Page<Entry> findAll(Pageable pageable);

    // Fetch filtered entries with pagination
    @Query("SELECT e FROM Entry e " +
            "WHERE (:category IS NULL OR e.category = :category) " +
            "AND (:age IS NULL OR e.age = :age) " +
            "AND (:gender IS NULL OR e.gender = :gender) " +
            "AND (:language IS NULL OR e.language = :language) " +
            "AND (:profession IS NULL OR e.profession = :profession)")
    Page<Entry> findFilteredEntries(
            @Param("category") String category,
            @Param("age") Integer age,
            @Param("gender") String gender,
            @Param("language") String language,
            @Param("profession") String profession,
            Pageable pageable
    );
}

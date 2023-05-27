package ru.kurkin.springpetproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kurkin.springpetproject.model.Artist;

public interface ArtistRepository extends JpaRepository<Artist,Long> {
}

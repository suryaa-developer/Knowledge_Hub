package com.PKH.Hub.repository;

import com.PKH.Hub.Entity.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface NotesRepo  extends JpaRepository<Notes,Integer> {
    List<Notes> findByUser_Id(int userId);

    Optional<Notes> findByUser_IdAndId(int userId, int id);
}

package com.PKH.Hub.repository;

import com.PKH.Hub.Entity.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface NotesRepo  extends JpaRepository<Notes,Long> {
    List<Notes> findByUser_Id(long userId);

    Optional<Notes> findByUser_IdAndId(long userId, long id);
}

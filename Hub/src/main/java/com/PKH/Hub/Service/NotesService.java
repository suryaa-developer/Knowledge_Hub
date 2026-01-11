package com.PKH.Hub.Service;

import com.PKH.Hub.Entity.Notes;
import com.PKH.Hub.repository.NotesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NotesService {
    @Autowired
    NotesRepo notesRepo;

    public Notes CreateNotes(Notes notes){

        notes.setCreatedAt(LocalDateTime.now());
        notes.setUpdatedAt(LocalDateTime.now());
      return notesRepo.save(notes);
    }

    public Notes UpdateNotes(int id , Notes notes){
        Notes note = notesRepo.findById(id).orElseThrow(() -> new RuntimeException("Notes Not Found"));

        if(notes.getTitle() != null) {
            note.setTitle(notes.getTitle());
        }
        if(notes.getDescription() != null) {
            note.setDescription(notes.getDescription());
        }

        note.setUpdatedAt(LocalDateTime.now());
        return  notesRepo.save(note);
    }

    public List<Notes> GetAllNotes(int user_id){
        return  notesRepo.findByUser_Id(user_id);
    }

    public Optional<Notes> GetSpecificNotesByID(int User_id, int id){
        return notesRepo.findByUser_IdAndId(User_id,id);
    }

    public void DeleteNotes(int id){
        notesRepo.deleteById(id);
    }
}

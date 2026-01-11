package com.PKH.Hub.Cotroller;

import com.PKH.Hub.Entity.Notes;
import com.PKH.Hub.Service.NotesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

    @RestController
    @RequestMapping("/notes")
    public class NotesController {
        @Autowired
        private NotesService notesService;

        @PostMapping("/Create")
        public ResponseEntity<?> CreateNotes(@Valid @RequestBody Notes notes){
            try {
                Notes note = notesService.CreateNotes(notes);
                return ResponseEntity.status(HttpStatus.CREATED).body(note);
            } catch (RuntimeException e) {
                return new ResponseEntity<>("Unable to Create Note at this moment",HttpStatus.BAD_REQUEST);
            }
        }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> UpdateNote(@Valid @PathVariable int id,@RequestBody Notes notes){
        try {
            Notes UpNote = notesService.UpdateNotes(id, notes);
            return ResponseEntity.status(HttpStatus.OK).body(UpNote);
        }catch (RuntimeException e) {
            return  new ResponseEntity<>("Notes Not Found",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<?> GetAllNotes(@PathVariable int user_id){
        try{
            List<Notes> notes = notesService.GetAllNotes(user_id);
            if (notes.isEmpty()) { return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Notes Not Available"); }
            return  ResponseEntity.status(HttpStatus.OK).body(notes);
        }catch (RuntimeException e){
            return  new ResponseEntity<>("Notes Not Available",HttpStatus.NO_CONTENT);        }
    }

    @GetMapping("/{user_id}/{id}")
    public ResponseEntity<?> GetSpecificNotes(@PathVariable int user_id,@PathVariable int id){
        try{
            Optional<Notes> notes = notesService.GetSpecificNotesByID(user_id,id);
            if(notes.isEmpty()){
                new ResponseEntity<>("Notes Not Found",HttpStatus.NO_CONTENT);
            }
            return  ResponseEntity.status(HttpStatus.OK).body(notes);
        }catch (RuntimeException e){
            return  new ResponseEntity<>("Notes Not Available",HttpStatus.NO_CONTENT);        }
    }

        @DeleteMapping("/delete/{id}")
        public ResponseEntity<?> deleteNote(@PathVariable int id) {
            try {
                notesService.DeleteNotes(id);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204 No Content
            } catch (RuntimeException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Note not found");
            }
        }


    }

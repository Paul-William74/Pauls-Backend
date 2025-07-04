package com.example.notes.Service;


import com.example.notes.DTOs.Note.BaseNoteDTO;
import com.example.notes.DTOs.Note.ChecklistDTO;
import com.example.notes.DTOs.Note.ChecklistItemDTO;
import com.example.notes.DTOs.Note.RichTextDTO;
import com.example.notes.Mapper.NoteMapper;
import com.example.notes.Model.Notes.*;
import com.example.notes.Model.User.User;
import com.example.notes.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    private BaseNoteRepo noteRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RichTextRepo richTextRepo;

    @Autowired
    private ChecklistNoteRepo checklistNoteRepo;

    @Autowired
    private ChecklistItemRepo checklistItemRepo;

    @Autowired
    private NoteMapper noteMapper;



    /**
     * Retrieves all notes for a specific user.
     *
     * @param userId user ID for which notes are to be retrieved
     * @return response entity containing the list of notes or an error message
     */
    public ResponseEntity<?> getUserNotes(Long userId) {
        //validate if the user exists
        Optional<User> userOptional = userRepo.findById(userId);
        if (userOptional.isEmpty()) //if user does not exist
            return ResponseEntity.badRequest().body("User not found");

        List<BaseNote> noteList = userOptional.get().getNoteList();
        List<BaseNoteDTO> dtoList = noteMapper.toBaseNoteDTOList(noteList);

        for (int i=0;i<noteList.size();i++){
            dtoList.get(i).setUpdatedAt(noteList.get(i).getUpdatedAt());

            if(noteList.get(i).getNoteType()==NOTE_TYPE.CHECK_LIST){
                CheckListNote checkListNote=(CheckListNote) noteList.get(i);
                List<ChecklistItem> items=checkListNote.getChecklistItemList();

                List<ChecklistItemDTO> itemDTOS=noteMapper.toChecklistItemDTOs(items);

                ChecklistDTO checklistDTO=((ChecklistDTO) dtoList.get(i));
                checklistDTO.setChecklistItemList(itemDTOS);
            }

        }

        //the return will change, according to the front end requirements(needs DTO)
        return ResponseEntity.ok(dtoList);

    }



    public ResponseEntity<?>updateRichNote(RichTextDTO dto, Long noteId){

        Optional <RichTextNote> DbRichTextNote=richTextRepo.findById(noteId);

        if (DbRichTextNote.isEmpty()){
            return ResponseEntity.badRequest().body("Invalid Note Id");
        }

        RichTextNote richTextNote= DbRichTextNote.get();
        richTextNote.setTitle(dto.getTitle());
        richTextNote.setDescription(dto.getDescription());
        richTextNote.setRichTextContent(dto.getRichTextContent());
        RichTextNote save=noteRepo.save(richTextNote);

        RichTextDTO update =noteMapper.toRichTextDTO(save);
        update.setUpdatedAt(save.getUpdatedAt());

        return ResponseEntity.ok(update);
    }



    public ResponseEntity<?>updateChecklistNote(ChecklistDTO dto, Long noteId){

        if (dto==null){
            return ResponseEntity.badRequest().body("Invalid checklist object");
        }

        Optional<CheckListNote> DbCheckListNote=checklistNoteRepo.findById(noteId);

        if (DbCheckListNote.isEmpty()){
            return ResponseEntity.badRequest().body("Invalid Note Id");
        }

        CheckListNote checkListNote=DbCheckListNote.get();
        checkListNote.setTitle(dto.getTitle());
        checkListNote.setDescription(dto.getDescription());

        CheckListNote save=noteRepo.save(checkListNote);
        ChecklistDTO update=noteMapper.toCheckListDTO(save);


        return ResponseEntity.ok(update);
    }



    public ResponseEntity<?> updateChecklistItem(ChecklistItemDTO dto, Long itemId){

        if (dto==null){
            return ResponseEntity.badRequest().body("Invalid checklist object");
        }

        Optional<ChecklistItem> DbChecklistItem=checklistItemRepo.findById(itemId);

        if (DbChecklistItem.isEmpty()){
            return ResponseEntity.badRequest().body("Invalid Note Id");
        }

        ChecklistItem checklistItem= DbChecklistItem.get();

        checklistItem.setPoint(dto.getPoint());
        checklistItem.setChecked(dto.isChecked());

        ChecklistItem save=checklistItemRepo.save(checklistItem);
        ChecklistItemDTO update=noteMapper.toChecklistItemDto(save);


        return ResponseEntity.ok(update);
    }



    public ResponseEntity<?> createRichTextNote(RichTextDTO richTextDTO, Long userId) {

        if (richTextDTO == null) {
            return ResponseEntity.badRequest().body("Invalid Note Type Object");
        }

        Optional<User> DbUser = userRepo.findById(userId);

        if (DbUser.isEmpty()) {
            return ResponseEntity.badRequest().body("User does not exist");
        }

        User user = DbUser.get();

        RichTextNote richTextNote = noteMapper.toRichTextNote(richTextDTO);
        richTextNote.setUser(user);
        richTextNote.setNoteType(NOTE_TYPE.RICH_TEXT);

        RichTextNote save = richTextRepo.save(richTextNote);
        RichTextDTO dto = noteMapper.toRichTextDTO(save);


        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }




    public ResponseEntity<?> createChecklistNote(ChecklistDTO checklistDTO, Long user_Id) {

        if (checklistDTO == null) {
            return ResponseEntity.badRequest().body("Invalid Note Type Object");
        }

        Optional<User> DbUser = userRepo.findById(user_Id);

        if (DbUser.isEmpty()) {
            return ResponseEntity.badRequest().body("User does not exist");
        }

        User user = DbUser.get();

        CheckListNote checkListNote = noteMapper.toChecklistNote(checklistDTO);
        checkListNote.setUser(user);
        checkListNote.setNoteType(NOTE_TYPE.CHECK_LIST);

        CheckListNote save = checklistNoteRepo.save(checkListNote);
        ChecklistDTO dto=noteMapper.toCheckListDTO(save);


        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }




    public  ResponseEntity<?> createChecklistItem(ChecklistItemDTO checklistItemDTO, Long checklistNoteId){

        if (checklistItemDTO==null){
            return ResponseEntity.badRequest().body("Invalid Checklist Item");
        }

        Optional<CheckListNote>DbCheckListNote=checklistNoteRepo.findById(checklistNoteId);

        if (DbCheckListNote.isEmpty()){
            return  ResponseEntity.badRequest().body("Invalid Checklist Note Id");
        }

        CheckListNote checkListNote=DbCheckListNote.get();

        ChecklistItem item=noteMapper.toChecklistItem(checklistItemDTO);
        item.setCheckListNote(checkListNote);

        ChecklistItem save=checklistItemRepo.save(item);
        ChecklistItemDTO dto=noteMapper.toChecklistItemDto(save);


        return ResponseEntity.ok(dto);
    }



    public ResponseEntity<?> deleteNote(Long userId,Long noteId) {

        Optional<User>DbUser=userRepo.findById(userId);
        Optional<BaseNote> DbNote=noteRepo.findById(noteId);

        if(DbUser.isEmpty()||DbNote.isEmpty()){
            return ResponseEntity.badRequest().body("Invalid Note Id or User Id");
        }
        User user=DbUser.get();
        List <BaseNote> noteList=user.getNoteList();
        BaseNote note=DbNote.get();

        noteList.remove(note);
        userRepo.save(user);


        return new ResponseEntity<>("Note Successfully Deleted",HttpStatus.OK);
    }
}

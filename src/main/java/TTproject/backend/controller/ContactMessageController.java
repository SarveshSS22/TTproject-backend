package TTproject.backend.controller;

import TTproject.backend.model.ContactMessage;
import TTproject.backend.repository.ContactMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "http://localhost:5173")
public class ContactMessageController {

    @Autowired
    private ContactMessageRepository messageRepository;

    @PostMapping
    public ResponseEntity<ContactMessage> submitMessage(@RequestBody ContactMessage message) {
        return ResponseEntity.ok(messageRepository.save(message));
    }

    @GetMapping
    public List<ContactMessage> getAllMessages() {
        return messageRepository.findAll();
    }
}
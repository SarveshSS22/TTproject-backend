package TTproject.backend.controller;

import TTproject.backend.model.Project;
import TTproject.backend.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "http://localhost:5173") // Default Vite/React port
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    // GET all projects
    @GetMapping
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    // GET single project by ID
    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
        return projectRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST create a new project
    @PostMapping
    public Project createProject(@RequestBody Project project) {
        return projectRepository.save(project);
    }

    // PUT update an existing project
    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody Project updatedProject) {
        return projectRepository.findById(id).map(existing -> {
            existing.setTitle(updatedProject.getTitle());
            existing.setDescription(updatedProject.getDescription());
            existing.setCategory(updatedProject.getCategory());
            existing.setTags(updatedProject.getTags());
            existing.setImageUrl(updatedProject.getImageUrl());
            existing.setGithubUrl(updatedProject.getGithubUrl());
            existing.setLiveUrl(updatedProject.getLiveUrl());
            existing.setFeatured(updatedProject.isFeatured());
            return ResponseEntity.ok(projectRepository.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    // DELETE project
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        if (projectRepository.existsById(id)) {
            projectRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

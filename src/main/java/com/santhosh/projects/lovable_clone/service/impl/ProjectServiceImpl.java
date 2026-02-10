package com.santhosh.projects.lovable_clone.service.impl;

import com.santhosh.projects.lovable_clone.dto.auth.UserProfileResponse;
import com.santhosh.projects.lovable_clone.dto.project.ProjectRequest;
import com.santhosh.projects.lovable_clone.dto.project.ProjectResponse;
import com.santhosh.projects.lovable_clone.dto.project.ProjectSummaryResponse;
import com.santhosh.projects.lovable_clone.entity.Project;
import com.santhosh.projects.lovable_clone.entity.User;
import com.santhosh.projects.lovable_clone.error.ResourceNotFoundException;
import com.santhosh.projects.lovable_clone.repository.ProjectRepository;
import com.santhosh.projects.lovable_clone.repository.UserRepository;
import com.santhosh.projects.lovable_clone.service.ProjectService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
@Transactional
public class ProjectServiceImpl implements ProjectService {

    ProjectRepository projectRepository;
    UserRepository userRepository;
    //ProjectMapper projectMapper;

    @Override
    public ProjectResponse createProject(ProjectRequest request, Long userId) {
        User owner = userRepository.findById(userId).orElseThrow();

        Project project = Project.builder()
                .name(request.name())
                .owner(owner)
                .email(owner.getEmail())
                .build();
        project = projectRepository.save(project);


        return new ProjectResponse(
                project.getId(),
                project.getName(),
                project.getCreatedAt(),
                project.getUpdatedAt(),
                new UserProfileResponse(
                        owner.getId(),
                        owner.getEmail(),
                        owner.getName(),
                        owner.getAvatarUrl()
                )
        );
    //    return projectMapper.toProjectResponse(project);

    }

    @Override
    public List<ProjectSummaryResponse> getUserProjects(Long userId) {
        return projectRepository.findAllAccessibleProjectsByUser(userId)
                .stream()
                .map(project -> new ProjectSummaryResponse(
                        project.getId(),
                        project.getName(),
                        project.getCreatedAt(),
                        project.getUpdatedAt()
                )).collect(Collectors.toList());
    }

    @Override
    public ProjectResponse getUserProjectById(Long id, Long userId) {
        User owner = userRepository.findById(userId).orElseThrow();
        Project project = projectRepository.findProjectById(id, userId).orElseThrow(
                ()-> new ResourceNotFoundException("Project", id.toString())
        );

                return new ProjectResponse(
                        project.getId(),
                        project.getName(),
                        project.getCreatedAt(),
                        project.getUpdatedAt(),
                        new UserProfileResponse(
                                owner.getId(),
                                owner.getEmail(),
                                owner.getName(),
                                owner.getAvatarUrl()
                        )
                );
    }



    @Override
    public ProjectResponse updateProject(Long id, ProjectRequest request, Long userId) {
        User owner = userRepository.findById(userId).orElseThrow();

        Project getProject = getAccessibleProjectById(id,userId);
        if(!getProject.getOwner().getId().equals(userId))
        {
            throw new RuntimeException("You are not allowed to update!");
        }
        getProject.setName(request.name());
        Project savedProject = projectRepository.save(getProject);

        return new ProjectResponse(
                savedProject.getId(),
                savedProject.getName(),
                savedProject.getCreatedAt(),
                savedProject.getUpdatedAt(),
                new UserProfileResponse(
                        owner.getId(),
                        owner.getEmail(),
                        owner.getName(),
                        owner.getAvatarUrl()
                )
        );
    }



    @Override
    public void softDelete(Long id, Long userId) {
    Project project = getAccessibleProjectById(id,userId);
    if(!project.getOwner().getId().equals(userId))
    {
        throw new RuntimeException("You are not allowed to delete!");
    }
    project.setDeletedAt(Instant.now());
    projectRepository.save(project);
    }

    public Project getAccessibleProjectById(Long projectId, Long userId)
    {
        return projectRepository.findProjectById(projectId,userId).orElseThrow(
                ()->new ResourceNotFoundException("Project", projectId.toString())
        );
    }
}

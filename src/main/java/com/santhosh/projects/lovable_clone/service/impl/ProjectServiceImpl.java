package com.santhosh.projects.lovable_clone.service.impl;

import com.santhosh.projects.lovable_clone.dto.project.ProjectRequest;
import com.santhosh.projects.lovable_clone.dto.project.ProjectResponse;
import com.santhosh.projects.lovable_clone.dto.project.ProjectSummaryResponse;
import com.santhosh.projects.lovable_clone.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProjectServiceImpl implements ProjectService {
    @Override
    public List<ProjectSummaryResponse> getUserProjects(Long userId) {
        return List.of();
    }

    @Override
    public ProjectResponse getUserProjectById(Long id, Long userId) {
        return null;
    }

    @Override
    public ProjectResponse createProject(ProjectRequest request, Long userId) {
        return null;
    }

    @Override
    public ProjectResponse updateProject(Long id, ProjectRequest request, Long userId) {
        return null;
    }

    @Override
    public ProjectResponse deleteProjectById(Long id, Long userId) {
        return null;
    }

    @Override
    public void softDelete(Long id, Long userId) {

    }
}

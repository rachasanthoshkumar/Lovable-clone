package com.santhosh.projects.lovable_clone.mapper;


import com.santhosh.projects.lovable_clone.dto.project.ProjectResponse;
import com.santhosh.projects.lovable_clone.entity.Project;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring")
public interface ProjectMapper {
    ProjectResponse toProjectResponse(Project project);

}

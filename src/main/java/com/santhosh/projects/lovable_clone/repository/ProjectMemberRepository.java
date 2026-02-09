package com.santhosh.projects.lovable_clone.repository;

import com.santhosh.projects.lovable_clone.entity.Project;
import com.santhosh.projects.lovable_clone.entity.ProjectMember;
import com.santhosh.projects.lovable_clone.entity.ProjectMemberId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProjectMemberRepository extends JpaRepository<ProjectMember, ProjectMemberId> {

    List<ProjectMember> findByIdProjectId(Long projectId);


}

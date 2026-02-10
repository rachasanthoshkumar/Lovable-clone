package com.santhosh.projects.lovable_clone.service.impl;

import com.santhosh.projects.lovable_clone.dto.member.InviteMemberRequest;
import com.santhosh.projects.lovable_clone.dto.member.MemberResponse;
import com.santhosh.projects.lovable_clone.dto.member.UpdateMemberRoleRequest;
import com.santhosh.projects.lovable_clone.entity.Project;
import com.santhosh.projects.lovable_clone.entity.ProjectMember;
import com.santhosh.projects.lovable_clone.entity.ProjectMemberId;
import com.santhosh.projects.lovable_clone.entity.User;
import com.santhosh.projects.lovable_clone.error.ResourceNotFoundException;
import com.santhosh.projects.lovable_clone.mapper.ProjectMemberMapper;
import com.santhosh.projects.lovable_clone.repository.ProjectMemberRepository;
import com.santhosh.projects.lovable_clone.repository.ProjectRepository;
import com.santhosh.projects.lovable_clone.repository.UserRepository;
import com.santhosh.projects.lovable_clone.service.ProjectMemberService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor

public class ProjectMemberServiceImpl implements ProjectMemberService {

    ProjectRepository projectRepository;
    ProjectMemberMapper projectMemberMapper;
    ProjectMemberRepository projectMemberRepository;
    UserRepository userRepository;

    @Override
    public List<MemberResponse> getProjectMembers(Long projectId, Long userId) {
        Project project = getAccessibleProjectById(projectId, userId);
        List<MemberResponse> memberResponseList = new ArrayList<>();
        memberResponseList.add(projectMemberMapper.toProjectMemberResponseFromOwner(project.getOwner()));
        memberResponseList.addAll(
                projectMemberRepository.findByIdProjectId(projectId)
                        .stream()
                        .map(projectMember -> projectMemberMapper.toProjectMemberResponseFromProjectMember(projectMember))
                        .toList());

        return memberResponseList;
    }

    @Override
    public MemberResponse inviteMember(Long projectId, InviteMemberRequest request, Long userId) {
        Project project = getAccessibleProjectById(projectId,userId);
        if(!project.getOwner().getId().equals(userId)){
            throw new RuntimeException("Not allowed");
        }

        User invitee = userRepository.findByEmail(request.email());
        if(invitee.getId().equals(userId))
        {
            throw new RuntimeException("Cannot invite yourself");
        }

        ProjectMemberId projectMemberId = new ProjectMemberId(projectId,userId);
        if(projectMemberRepository.existsById(projectMemberId))
        {
            throw new RuntimeException("Cannot invite once again!");
        }

        ProjectMember projectMember = ProjectMember.builder()
                .id(projectMemberId)
                .project(project)
                .user(invitee)
                .projectRole(request.role())
                .invitedAt(Instant.now())
                .build();

        projectMemberRepository.save(projectMember);

        return projectMemberMapper.toProjectMemberResponseFromProjectMember(projectMember);
    }

    @Override
    public MemberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequest request, Long userId) {
        Project project = getAccessibleProjectById(projectId,userId);
        if(!project.getOwner().getId().equals(userId)){
            throw new RuntimeException("Not allowed");
        }
        ProjectMemberId projectMemberId = new ProjectMemberId(projectId,memberId);
        ProjectMember projectMember = projectMemberRepository.findById(projectMemberId).orElseThrow();
        projectMember.setProjectRole(request.role());
        projectMemberRepository.save(projectMember);
        return projectMemberMapper.toProjectMemberResponseFromProjectMember(projectMember);
    }


    @Override
    public void removeProjectMember(Long projectId, Long memberId, Long userId) {
        Project project = getAccessibleProjectById(projectId,userId);
        if(!project.getOwner().getId().equals(userId)){
            throw new RuntimeException("Not allowed");
        }

        ProjectMemberId projectMemberId = new ProjectMemberId(projectId,memberId);
        if(!projectMemberRepository.existsById(projectMemberId))
        {
            throw new RuntimeException("Member not found!");
        }

        projectMemberRepository.deleteById(projectMemberId);
    }

    public Project getAccessibleProjectById(Long projectId, Long userId) {
        return projectRepository.findProjectById(projectId, userId).orElseThrow(()->new ResourceNotFoundException("Project",projectId.toString()));
    }
}

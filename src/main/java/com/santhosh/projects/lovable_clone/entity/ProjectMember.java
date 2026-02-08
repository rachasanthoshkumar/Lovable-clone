package com.santhosh.projects.lovable_clone.entity;

import com.santhosh.projects.lovable_clone.enums.ProjectRole;
import jakarta.persistence.EmbeddedId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectMember {

    @EmbeddedId
    ProjectMemberId id;

     Project project;
     User user;
     ProjectRole projectRole;
     Instant invitedAt;
     Instant acceptedAt;


}

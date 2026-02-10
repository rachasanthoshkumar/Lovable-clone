package com.santhosh.projects.lovable_clone.entity;


import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectMemberId {
    Long projectId;
    Long userId;
}

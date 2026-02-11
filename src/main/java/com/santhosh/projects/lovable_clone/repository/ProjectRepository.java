package com.santhosh.projects.lovable_clone.repository;

import com.santhosh.projects.lovable_clone.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {


    @Query("""
            SELECT p FROM Project p
            WHERE p.deletedAt is Null
            ORDER BY p.updatedAt DESC
            """)
    public List<Project> findAllAccessibleProjectsByUser(@Param("userId") Long userId);


    @Query("""
            SELECT p from Project p
            where p.id = :projectId
                and p.deletedAt is NULL
            """)
    public Optional<Project> findProjectById(@Param("projectId") Long projectId, @Param("userId") Long userId);
}

package com.revy.freewheelin.model.subject;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubjectRepo extends JpaRepository<Subject, Long> {

    Optional<Subject> findOneByName(String name);

}

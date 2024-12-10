package com.MyAiApply.MyAiApply.repository;

import com.MyAiApply.MyAiApply.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {}
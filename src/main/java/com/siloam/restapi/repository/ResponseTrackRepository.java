package com.siloam.restapi.repository;

import com.siloam.restapi.entity.ResponseTrack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseTrackRepository extends JpaRepository<ResponseTrack, Long>, JpaSpecificationExecutor<ResponseTrack> {
}
